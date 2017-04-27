package com.jcoder.app.folink.api;

import com.jcoder.app.folink.model.ABList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rory on 2016/10/8.
 */

public interface FolinkImagesApi  {

    @GET("getNews?")
    Observable<ABList> getNews3(
            @Query("page") String id

    );
    @GET("getNews?")
    Observable<ABList> getAbNewsItem(
            @Query("page") String id
    );

    @GET("getNews?")
    Observable<String> loadRepo(@Query("page") String id, Callback<Response> Response);

    @GET("/album")
    Observable<ABList> getAbNewsItem();
}
