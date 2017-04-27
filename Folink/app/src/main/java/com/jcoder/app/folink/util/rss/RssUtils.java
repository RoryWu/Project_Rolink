package com.jcoder.app.folink.util.rss;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Rory on 2016/10/13.
 */

public class RssUtils {

    private RSSFeed getFeed(String urlString)
    {
        try
        {
            URL url = new URL(urlString);
            SAXParserFactory factory = SAXParserFactory.newInstance();  // 构建Sax解析工厂
            SAXParser parser = factory.newSAXParser(); // 使用Sax解析工厂构建Sax解析器
            XMLReader xmlreader = parser.getXMLReader();   // 使用Sax解析器构建xml Reader

            RSSHandler rssHandler = new RSSHandler(); // 构建自定义的RSSHandler作为xml Reader的处理器（或代理）
            xmlreader.setContentHandler(rssHandler);     // 构建自定义的RSSHandler作为xml Reader的处理器（或代理）


            InputSource is = new InputSource(url.openStream());      // 使用url打开流,并将流作为xml Reader的输入源并解析
            xmlreader.parse(is);

            return rssHandler.getFeed();     // 将解析结果作为 RSSFeed 对象返回
        }
        catch (Exception ee)
        {
            ee.printStackTrace();
            return null;
        }
    }
}
