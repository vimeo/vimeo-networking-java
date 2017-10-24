package com.vimeo.android.networking.example;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.callbacks.AuthCallback;
import com.vimeo.networking.callbacks.VimeoCallback;
import com.vimeo.networking.callers.GetRequestCaller;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import okhttp3.CacheControl;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    public static final String STAFF_PICKS_VIDEO_URI = "/channels/927/videos"; // 927 == staffpicks

    private VimeoClient mApiClient = VimeoClient.getInstance();
    private ProgressDialog mProgressDialog;

    private TextView mRequestOutputTv;

    // <editor-fold desc="Life Cycle">

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---- Initial UI Setup ----
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("All of your API are belong to us...");

        // ---- Code Grant Check ----
        handleCodeGrantIfNecessary();

        // ---- Client Credentials Auth ----
        if (mApiClient.getVimeoAccount().getAccessToken() == null) {
            // If there is no access token, fetch one on first app open
            authenticateWithClientCredentials();
        }

        // ---- View Binding ----
        mRequestOutputTv = (TextView) findViewById(R.id.request_output_tv);
        findViewById(R.id.fab).setOnClickListener(this);
        findViewById(R.id.code_grant_btn).setOnClickListener(this);
        findViewById(R.id.request_output_tv).setOnClickListener(this);
        findViewById(R.id.staff_picks_btn).setOnClickListener(this);
        findViewById(R.id.account_type_btn).setOnClickListener(this);
        findViewById(R.id.logout_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.staff_picks_btn:
                fetchStaffPicks();
                break;
            case R.id.account_type_btn:
                fetchAccountType();
                break;
            case R.id.logout_btn:
                logout();
                break;
            case R.id.code_grant_btn:
            case R.id.fab:
                toast("Authenticate on Web");
                goToWebForCodeGrantAuth();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // </editor-fold>

    // <editor-fold desc="Requests">

    private void fetchStaffPicks() {
        mProgressDialog.show();
        mApiClient.getContent(STAFF_PICKS_VIDEO_URI, CacheControl.FORCE_NETWORK, GetRequestCaller.VIDEO_LIST, null, null, null, new VimeoCallback<VideoList>() {
            @Override
            public void success(VideoList videoList) {
                if (videoList != null && videoList.getData() != null) {
                    String videoTitlesString = "";
                    boolean addNewLine = false;
                    for (Video video : videoList.getData()) {
                        if (addNewLine) {
                            videoTitlesString += "\n";
                        }
                        addNewLine = true;
                        videoTitlesString += video.mName;
                    }
                    mRequestOutputTv.setText(videoTitlesString);
                }
                toast("Staff Picks Success");
                mProgressDialog.hide();
            }

            @Override
            public void failure(VimeoError error) {
                toast("Staff Picks Failure");
                mRequestOutputTv.setText(error.getDeveloperMessage());
                mProgressDialog.hide();
            }
        });
    }

    private void fetchAccountType() {
        mProgressDialog.show();
        mApiClient.getCurrentUser(new VimeoCallback<User>() {
            @Override
            public void success(User user) {
                if (user != null) {
                    mRequestOutputTv.setText("Current account type: " + user.mAccount);
                    toast("Account Check Success");
                } else {
                    toast("Account Check Failure");
                }
                mProgressDialog.hide();
            }

            @Override
            public void failure(VimeoError error) {
                toast("Account Check Failure");
                mRequestOutputTv.setText(error.getDeveloperMessage());
                mProgressDialog.hide();
            }
        });
    }

    private void logout() {
        mProgressDialog.show();
        mApiClient.logOut(new VimeoCallback<Object>() {
            @Override
            public void success(Object o) {
                AccountPreferenceManager.removeClientAccount();
                toast("Logout Success");
                mProgressDialog.hide();
            }

            @Override
            public void failure(VimeoError error) {
                AccountPreferenceManager.removeClientAccount();
                toast("Logout Failure");
                mRequestOutputTv.setText(error.getDeveloperMessage());
                mProgressDialog.hide();
            }
        });
    }

    // You can't make any requests to the api without an access token. This will get you a basic
    // "Client Credentials" gran which will allow you to make requests
    private void authenticateWithClientCredentials() {
        mProgressDialog.show();
        mApiClient.authorizeWithClientCredentialsGrant(new AuthCallback() {
            @Override
            public void success() {
                toast("Client Credentials Authorization Success");
                mProgressDialog.hide();
            }

            @Override
            public void failure(VimeoError error) {
                toast("Client Credentials Authorization Failure");
                mRequestOutputTv.setText(error.getDeveloperMessage());
                mProgressDialog.hide();
            }
        });
    }

    private void authenticateWithCodeGrant(Uri uri) {
        mProgressDialog.show();
        if (uri.getQuery() == null || uri.getQuery().isEmpty()) {
            toast("Bad deep link - no query parameters");
            return;
        }
        mApiClient.authenticateWithCodeGrant(uri.toString(), new AuthCallback() {
            @Override
            public void success() {
                toast("Code Grant Success");
                mProgressDialog.hide();
            }

            @Override
            public void failure(VimeoError error) {
                toast("Code Grant Failure");
                mRequestOutputTv.setText(error.getDeveloperMessage());
                mProgressDialog.hide();
            }
        });
    }

    // </editor-fold>

    // <editor-fold desc="Code Grant">

    // We deep link to this activity as specified in the AndroidManifest.
    private void handleCodeGrantIfNecessary() {
        if (getIntent() != null) {
            String action = getIntent().getAction();
            Uri uri = getIntent().getData();
            if (Intent.ACTION_VIEW.equals(action) && uri != null) {
                // This is coming from a deep link
                authenticateWithCodeGrant(uri);
            }
        }
    }

    private void goToWebForCodeGrantAuth() {
        String uri = mApiClient.getCodeGrantAuthorizationURI();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(browserIntent);
    }

    // </editor-fold>

    // <editor-fold desc="Helpers">

    private void toast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    /*
    // Can only fetch quota if you have the upload privilege
    private void fetchAccountType() {
        mProgressDialog.show();
        mApiClient.fetchCurrentUser(new ModelCallback<User>(User.class) {
            @Override
            public void success(User user) {
                long fileSizeBytes = user.getFreeUploadSpace();
                if (user.getFreeUploadSpace() != Vimeo.NOT_FOUND) {
                    String formattedFileSize =
                            Formatter.formatShortFileSize(TestApp.getAppContext(), fileSizeBytes);
                    mRequestOutputTv.setText("Available Space: " + formattedFileSize);
                    toast("Quote Check Success");
                } else {
                    toast("Quote Check Failure");
                }
                mProgressDialog.hide();
            }

            @Override
            public void failure(VimeoError error) {
                mRequestOutputTv.setText(error.getDeveloperMessage());
                mProgressDialog.hide();
            }
        });
    }
    */

    // </editor-fold>
}
