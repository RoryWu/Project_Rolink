package com.jcoder.app.folink.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jcoder.app.folink.R;
import com.jcoder.app.folink.api.FolinkApiManager;
import com.jcoder.app.folink.model.ABList;
import com.jcoder.app.folink.model.NG;
import com.jcoder.app.folink.model.Topic;
import com.jcoder.app.folink.util.Jloger;
import com.jcoder.app.folink.util.JsonConverterFactory;
import com.jcoder.app.folink.util.YahooALBNewsToday;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rory on 2016/10/8.
 */

public class FolinkAboutActivity extends BaseActivity {

    private static final int SIZE = 10;
    private ArrayList<NG> mList = new ArrayList<>();

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_folink_about);
        ButterKnife.bind(this);

//        initialViews();
        getFNewsData();

    }

    private void initialViews() {

        mToolbarTitle.setText("About");
    }


    public void testYahoo() {

        Document doc = null;
        String url = "https://maktoob.yahoo.com/";
        try {
            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

            connection.header("Accept", "*/*");
            connection.header("Accept-Encoding", "gzip,deflate,sdch,br");
            connection.header("Accept-Language", "ar-sa,ar-jo;q=0.8");
            connection.header("Connection", "Keep-Alive");
            //必须 填写  表单提交
            connection.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.header("Referer", "https://maktoob.yahoo.com/");
            connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36");

            doc = connection.get();
            Jloger.e("lang = " + doc.html());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get first news tab one
        Element t1 = doc.getElementById("Stream");
        Jloger.e("lang = " + t1.attr("lang"));
        Elements elements = t1.select("li");
        Jloger.e("size:" + elements.size());

        List<NG> topicList = new ArrayList<NG>();
        for (Element element : elements) {
            NG ng = parse(element);
            if (ng != null) {
                topicList.add(ng);

            }
        }
        Jloger.e("topicList size:" + topicList.size());


//        //get news title
//        Elements newsTitle = t1.select("a[href] > span");
//
//        //print size
//        Jloger.e("size:" + newsTitle.size());
//
//        //print news title
//        for(Element e:newsTitle){
//            Jloger.e("title:" + e.text());
//        }

    }


    private NG parse(Element element) {
        try {


            String title = element.select("h3").first().text();
            String url = element.select("a").first().attr("href");
            String imgUrl = element.select("img").first().attr("src");
            String content = element.select("p").first().text();

//            Jloger.e("title : " + title);
//            Jloger.e("url : " + url);
//            Jloger.e("imgUrl : " + imgUrl);
//            Jloger.e("content : " + content);
            Log.e("jjj", "\n ------------------------- \n");

            return new NG(title, url, imgUrl, content);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    private void getWebData() {

        new Thread() {
            @Override
            public void run() {
                testYahoo();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                Date today = new Date();
                for (Topic topic : YahooALBNewsToday.parseAll(today)) {

                    Log.e("jjj", topic.toJSON());
                    Log.e("jjj", "\n ------------------------- \n" + topic);
                }
            }
        }.start();


    }

    private void getWebData2() {

        Observable
                .create(new Observable.OnSubscribe<Document>() {
                    @Override
                    public void call(Subscriber<? super Document> subscriber) {
                        try {

//                            Log.e("jjj",""+Constants.NG_BASE_URL);
//                            Document document = Jsoup.connect(Constants.NG_BASE_URL).get();
//                            Log.e("jjj",""+document.toString());
//                            subscriber.onNext(document);
                        } catch (Exception e) {
                            e.printStackTrace();
                            subscriber.onNext(null);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnNext(new Action1<Document>() {
                    @Override
                    public void call(Document document) {
                        mList.clear();
                        if (document == null) {
                            return;
                        }
                        Log.e("jjj", document.ownText());
//                    Element contents = document.getElementById("ajaxBox");
//                    Elements list = contents.getElementsByClass("ajax_list");
//                    for (int i = 0; i < SIZE && i < list.size(); i++) {
//                        Element element = list.get(i);
//                        NG nG = new NG();
//                        Element imageA = element.select("dd").first().select("a").first();
//                        nG.url = pass(imageA.attr("href"));
//                        nG.imgUrl = pass(imageA.select("img").first().attr("src"));
//                        nG.title = pass(imageA.select("img").first().attr("alt"));
//                        nG.content = pass(element.getElementsByClass("ajax_dd_text").first().ownText());
//
//                        Log.i("xyz ", "ngfragment " + nG.content + nG.imgUrl + nG.url + nG.title);
//                        mList.add(nG);
//                    }
//                    if (mList.size() > 0) {
//                        PreferencesHelper preferencesHelper = new PreferencesHelper(App.getInstance());
//                        //设置封面图,set Cover image
//                        preferencesHelper.setCoverImage(mList.get(0).imgUrl);
//
//                        // setHeadImages
//                        JSONArray jsonArray = new JSONArray();
//                        for (int i = 0; i < Math.min(mList.size(), 6); i++)
//                            jsonArray.add(mList.get(i).imgUrl);
//                        preferencesHelper.setNgImages(jsonArray.toJSONString());
//                    }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<Document>() {
                    @Override
                    public void call(Document document) {
                        showList();
                    }
                }
        )
//                .subscribe(document1 -> {
//                    NGDbHelper zcoolDbHelper = new NGDbHelper(mList, mRealm);
//                    zcoolDbHelper.saveToDatabase();
//                    showList();

//                })

        ;
    }

    private void showList() {
    }

    protected String pass(String string) {
        if (string == null) string = "";
        return string;
    }

    protected String pass(Integer integer) {
        if (integer == null) return "";
        return "" + integer;
    }

    private class WebClient extends WebViewClient {
    }

    private void getFNewsData() {


        Subscription subscription = FolinkApiManager.getmApiManager().getFolinkImageService().getNews3("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ABList>() {
                    @Override
                    public void onCompleted() {
                        Jloger.e(">>>>>>>>>>>>>onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Jloger.e(">>>>>>>>>>>>>onError");
                    }

                    @Override
                    public void onNext(ABList list) {
                        if (list != null) {

                            Jloger.e(">>>>>>>>>>>>>onNext"+list.getAbNewsList().size());
                        }else {
                            Jloger.e(">>>>>>>>>>>>>onNext is null");
                        }
                    }
                });

        CompositeSubscription compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(subscription);




//        retrofit2.Call<AbNewsItem> requst = api.getAbNewsItem("1");
//        requst.enqueue(new Callback<AbNewsItem>() {
//            @Override
//            public void onResponse(Call<AbNewsItem> call, Response<AbNewsItem> response) {
//                Jloger.e(call.toString());
//            }
//
//            @Override
//            public void onFailure(Call<AbNewsItem> call, Throwable t) {
//
//            }
//        });


//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Retrofit retrofit = initRetrofit();
//
//                    FolinkImagesApi api = retrofit.create(FolinkImagesApi.class);
//                    Call<JSONObject> call = api.loadRepo("1");
//                    retrofit2.Response<JSONObject> response = call.execute();
//                    Jloger.e("=========" + response.body());
//
//
//                } catch (
//                        IOException e
//                        )
//
//                {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        call.enqueue(new Callback<JSONObject>(){
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//
//            }
//
//            @Override
//            public void onResponse(Response<JSONObject> response){
//                //从response.body()中获取结果
//            }
//            @Override
//            public void onFailure(Throwable t){
//
//            }
//        });
    }

    private static Retrofit initRetrofit() {
        OkHttpClient httpClient = new OkHttpClient();
//        、
        return new Retrofit.Builder()
                .baseUrl("http://198.58.103.210:8088/xcode/")
                .addConverterFactory(JsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

}
