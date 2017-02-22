package com.vimeo.networking;

import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.error.VimeoError;
import com.vimeo.networking.model.search.SuggestionResponse;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.Call;

/**
 * Tests for the Search class
 * <p>
 * Created by zetterstromk on 2/22/17.
 */
public class SearchTest {

    @Test
    public void testSuggest_emptyQuery() throws Exception {
        ModelCallback<SuggestionResponse> callback = new ModelCallback<SuggestionResponse>(SuggestionResponse.class) {
            @Override
            public void success(SuggestionResponse suggestionResponse) {

            }

            @Override
            public void failure(VimeoError error) {

            }
        };
        Call<SuggestionResponse> call = Search.suggest("", 0, 0, callback);

        Assert.assertNull(call);
    }
}
