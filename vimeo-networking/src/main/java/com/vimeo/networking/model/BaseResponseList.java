/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.Paging.PagingTypeAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Response for list requests
 * <p/>
 * Created by kylevenn on 5/28/15.
 */
public abstract class BaseResponseList<T> implements Serializable {

    private static final long serialVersionUID = -1641146617506148394L;
    public int total;
    public int page;
    public int perPage;
    public Paging paging;
    public ArrayList<T> data;

    public abstract Class getModelClass();

    public static abstract class BaseResponseTypeAdapter<S extends BaseResponseList> extends TypeAdapter<S> {

        @NotNull
        private final PagingTypeAdapter mPagingTypeAdapter;
        @NotNull
        private final TypeAdapter mTypeAdapter;

        public BaseResponseTypeAdapter(@NotNull PagingTypeAdapter pagingTypeAdapter,
                                       @NotNull TypeAdapter typeAdapter) {
            mPagingTypeAdapter = pagingTypeAdapter;
            mTypeAdapter = typeAdapter;
        }

        protected abstract S createResponseListObject();

        @Override
        public void write(JsonWriter out, S value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("BaseResponseList null in write()");
                out.endObject();
                return;
            }

            out.name("total").value(value.total);
            out.name("page").value(value.page);
            out.name("per_page").value(value.perPage);

            if (value.paging != null) {
                out.name("paging");
                mPagingTypeAdapter.write(out, value.paging);
            }
            if (value.data != null) {
                out.name("data").beginArray();
                for (final Object o : value.data) {
                    mTypeAdapter.write(out, o);
                }
                out.endArray();
            }

            out.endObject();
        }

        @Override
        public S read(JsonReader in) throws IOException {
            final S list = createResponseListObject();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "total":
                        list.total = in.nextInt();
                        break;
                    case "page":
                        list.page = in.nextInt();
                        break;
                    case "per_page":
                        list.perPage = in.nextInt();
                        break;
                    case "paging":
                        list.paging = mPagingTypeAdapter.read(in);
                        break;
                    case "data":
                        in.beginArray();
                        list.data = new ArrayList<>();
                        while (in.hasNext()) {
                            list.data.add(mTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return list;
        }
    }
}
