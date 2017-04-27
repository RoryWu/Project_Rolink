package com.jcoder.app.folink.api;

import com.jcoder.app.folink.model.ABList;
import com.jcoder.app.folink.model.NewsList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rory on 2016/10/8.
 */

public interface FolinkNewsApi {

//    @GET("/api/4/news/latest")
//    Observable<FNews> getLastDaily();

    // TODO: 16/8/17 string or int
    @GET("http://c.m.163.com/nc/article/headline/T1348647909107/{id}-20.html")
    Observable<NewsList> getNews(@Path("id") int id );

    @GET("http://c.m.163.com/nc/article/{id}/full.html")
    Observable<String> getNewsDetail(@Path("id") String id);

    @GET("getNews?")
    Observable<ABList> getNews2(
            @Query("page") String id
    );



}
