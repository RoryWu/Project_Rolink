package com.jcoder.app.folink.util;

import com.jcoder.app.folink.model.Topic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Rory on 2016/10/10.
 */

public class YahooALBNewsToday {

    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String URL = "http://news.yahoo.co.jp/list/";

    public static final String URL2 = "https://en-maktoob.yahoo.com/";

    /**
     * 指定した日付の Topics を取得します
     *
     * @param date 対象日付
     * @return Topic のリスト
     */
    public static List<Topic> parseAll(Date date) {
        String dateStr = new SimpleDateFormat(DATE_FORMAT).format(date);

        try {
            Document doc = Jsoup.connect(URL2).data("d", dateStr).get();
            Elements topicElements = doc.select("ul.list li");
            List<Topic> topicList = new ArrayList<Topic>();
            for (Element element : topicElements) {
                topicList.add(parse(element));
            }
            return topicList;
        } catch (IOException e) {
            System.out.println("Something wrong.");
            return Collections.emptyList();
        }
    }

    /**
     * １トピックの内容を取得します
     *
     * @param element
     * @return Topic オブジェクト
     */
    private static Topic parse(Element element) {
        String title = element.select("span.ttl").first().text();
        String url = element.select("a").first().attr("href");
        String category = element.select("span.cate").first().text();
        String date = element.select("span.date").first().text();

        return new Topic(title, url, category, date);
    }
}
