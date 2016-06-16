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

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.vimeo.networking.model.Paging.PagingTypeAdapter;
import com.vimeo.networking.model.Video.VideoTypeAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * Response list for Videos
 * Created by alfredhanssen on 4/12/15.
 */
public class VideoList extends BaseResponseList<Video> {

    private static final long serialVersionUID = 4897008008830603213L;

    @Override
    public Class getModelClass() {
        return Video.class;
    }

    public static class VideoListTypeAdapter extends BaseResponseTypeAdapter<VideoList> {

        public VideoListTypeAdapter(@NotNull PagingTypeAdapter pagingTypeAdapter,
                                    @NotNull VideoTypeAdapter typeAdapter) {
            super(pagingTypeAdapter, typeAdapter);
        }

        @Override
        protected VideoList createResponseListObject() {
            return new VideoList();
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (VideoList.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter pagingTypeAdapter = gson.getAdapter(Paging.class);
                TypeAdapter videoTypeAdapter = gson.getAdapter(Video.class);
                if (pagingTypeAdapter instanceof PagingTypeAdapter &&
                    videoTypeAdapter instanceof VideoTypeAdapter) {
                    return (TypeAdapter<T>) new VideoListTypeAdapter((PagingTypeAdapter) pagingTypeAdapter,
                                                                     (VideoTypeAdapter) videoTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
