package com.jcoder.app.folink.api;

import com.jcoder.app.folink.FolinkApp;
import com.jcoder.app.folink.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rory on 2016/10/8.
 */

public class FolinkApiManager {

    private static FolinkApiManager mApiManager;


    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(FolinkApp.getContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

        }
    };


    public static FolinkApiManager getmApiManager() {
        if (mApiManager == null) {
            synchronized (FolinkApiManager.class) {
                mApiManager = new FolinkApiManager();
            }
        }
        return mApiManager;
    }


    private static File httpCacheDirectory = new File(FolinkApp.getContext().getCacheDir(), "FolinkCache");
    private static int CacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(httpCacheDirectory, CacheSize);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .build()
            ;


    private FolinkNewsApi mFolinkNewsApi;
    private Object FolinkObj = new Object();
    public FolinkNewsApi getFolinkNewsService() {
        if (mFolinkNewsApi == null) {
            synchronized (FolinkObj) {
                mFolinkNewsApi = new Retrofit.Builder()
//                        .baseUrl("http://c.m.163.com")
                        .baseUrl("http://198.58.103.210:8088/xcode/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(FolinkNewsApi.class);

            }
        }
        return mFolinkNewsApi;
    }

//    public FolinkNewsApi getFolinkImagedataService() {
//        if (FolinkImagesApi == null) {
//            synchronized (FolinkObj) {
//                mFolinkNewsApi = new Retrofit.Builder()
////                        .baseUrl("http://c.m.163.com")
//                        .baseUrl("http://api.yixi.tv/api/v1/album")
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                        .client(client)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build().create(FolinkImagesApi.class);
//
//            }
//        }
//        return mFolinkNewsApi;
//    }


    private FolinkImagesApi mImagesApi;
    private Object FolinkImg = new Object();
    public FolinkImagesApi getFolinkImageService() {
        if (mImagesApi == null) {
            synchronized (FolinkImg) {
                mImagesApi = new Retrofit.Builder()
                        .baseUrl("http://api.yixi.tv/api/v1/album")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(FolinkImagesApi.class);

            }
        }
        return mImagesApi;
    }

}
