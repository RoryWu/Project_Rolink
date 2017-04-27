package com.jcoder.app.folink.util.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Rory on 2016/10/13.
 */

public class RSSHandler extends DefaultHandler
{

    RSSFeed rssFeed;//用于保存解析过程中的channel
    RSSItem rssItem;//用于保存解析过程中的item
    String lastElementName = ""; //标记变量，用于标记在解析过程中我们关心的几个标签，若不是我们关心的标签，记做 0
    final int RSS_TITLE = 1;//若是title标签,记做 1,注意有两个title,但我们都保存在item的title成员变量中
    final int RSS_LINK = 2;//若是link标签,记做 2
    final int RSS_DESCRIPTION = 3;//若是description标签,记做 3
    final int RSS_CATEGORY = 4;//若是category标签,记做 4
    final int RSS_PUBDATE = 5; //若是pubdate标签,记做 5,注意有两个pubdate,但我们都保存在item的pubdate成员变量中
    int currentstate = 0;

    public RSSHandler(){}
    public RSSFeed getFeed()
    {
        return rssFeed;//通过这个方法把解析结果封装在 RSSFeed 对象中并返回
    }

    //下面通过重载 DefaultHandler 的 5 个方法来实现 sax 解析
    public void startDocument() throws SAXException
    {

        //这个方法在解析xml文档的一开始执行,一般我们需要在该方法中初始化解析过程中有可能用到的变量
        rssFeed = new RSSFeed();
        rssItem = new RSSItem();
    }
    public void endDocument() throws SAXException
    {

//这个方法在整个xml文档解析结束时执行,一般需要在该方法中返回或保存整个文档解析解析结果,但由于

        //我们已经在解析过程中把结果保持在rssFeed中,所以这里什么也不做


    }
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
    {
//这个方法在解析标签开始标记时执行,一般我们需要在该方法取得标签属性值,但由于我们的rss文档

        //中并没有任何我们关心的标签属性,因此我们主要在这里进行的是设置标记变量currentstate,以

        //标记我们处理到哪个标签


        if (localName.equals("channel"))
        {//channel这个标签没有任何值得我们关心的内容，所以currentstate置为0
            currentstate = 0;
            return;
        }
        if (localName.equals("item"))
        {
//若是item标签,则重新构造一个RSSItem,从而把已有(已经解析过的)item数据扔掉,当

            //然事先是已经保存到rssFeed的itemlist集合中了


            rssItem = new RSSItem();
            return;
        }
        if (localName.equals("title"))
        {
//若是title标签,置currentstate为1,表明这是我们关心的数据,这样在characters

            //方法中会把元素内容保存到rssItem变量中


            currentstate = RSS_TITLE;
            return;
        }
        if (localName.equals("description"))
        {
//若是description标签,置currentstate为3,表明这是我们关心的数据,这样在characters

            //方法中会把元素内容保存到rssItem变量中


            currentstate = RSS_DESCRIPTION;
            return;
        }
        if (localName.equals("link"))
        {
//若是link标签,置currentstate为2,表明这是我们关心的数据,这样在characters

            //方法中会把元素内容保存到rssItem变量中


            currentstate = RSS_LINK;
            return;
        }
        if (localName.equals("category"))
        {
//若是category标签,置currentstate为4,表明这是我们关心的数据,这样在characters

            //方法中会把元素内容保存到rssItem变量中


            currentstate = RSS_CATEGORY;
            return;
        }
        if (localName.equals("pubDate"))
        {
//若是pubDate标签,置currentstate为5,表明这是我们关心的数据,这样在characters

            //方法中会把元素内容保存到rssItem变量中


            currentstate = RSS_PUBDATE;
            return;
        }

        currentstate = 0;//如果不是上面列出的任何标签,置currentstate为0,我们不关心
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException
    {


//如果解析一个item节点结束，就将rssItem添加到rssFeed中。
        if (localName.equals("item"))
        {
            rssFeed.addItem(rssItem);
            return;

        }
    }

    public void characters(char ch[], int start, int length)
    {//这个方法在解析标签内容(即开始标记－结束标记之间的部分)时执行,一般我们在里这获取元素体内容
        String theString = new String(ch,start,length); //获取元素体内容
        switch (currentstate)
        {//根据currentstate标记判断这个元素体是属于我们关心的哪个元素
            case RSS_TITLE:
                rssItem.setTitle(theString);//若是title元素,放入rssItem的title属性
                currentstate = 0;
                break;
            case RSS_LINK:
                rssItem.setLink(theString);//若是link元素,放入rssItem的link属性
                currentstate = 0;
                break;
            case RSS_DESCRIPTION:
                rssItem.setDescription(theString);
                currentstate = 0;
                break;
            case RSS_CATEGORY:
                rssItem.setCategory(theString);
                currentstate = 0;
                break;
            case RSS_PUBDATE:
                rssItem.setPubDate(theString);
                currentstate = 0;
                break;
            default:
                return;
        }

    }
}