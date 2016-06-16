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
import com.vimeo.networking.model.Comment.CommentTypeAdapter;
import com.vimeo.networking.model.Paging.PagingTypeAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * Response list for Comments
 * Created by zetterstromk on 7/31/15.
 */
public class CommentList extends BaseResponseList<Comment> {

    private static final long serialVersionUID = 6740876679750722757L;

    @Override
    public Class getModelClass() {
        return Comment.class;
    }

    public static class CommentListTypeAdapter extends BaseResponseTypeAdapter<CommentList> {

        public CommentListTypeAdapter(@NotNull PagingTypeAdapter pagingTypeAdapter,
                                      @NotNull CommentTypeAdapter typeAdapter) {
            super(pagingTypeAdapter, typeAdapter);
        }

        @Override
        protected CommentList createResponseListObject() {
            return new CommentList();
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (CommentList.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter pagingTypeAdapter = gson.getAdapter(Paging.class);
                TypeAdapter commentTypeAdapter = gson.getAdapter(Comment.class);
                if (pagingTypeAdapter instanceof PagingTypeAdapter &&
                    commentTypeAdapter instanceof CommentTypeAdapter) {
                    return (TypeAdapter<T>) new CommentListTypeAdapter((PagingTypeAdapter) pagingTypeAdapter,
                                                                       (CommentTypeAdapter) commentTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
