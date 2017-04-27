package com.jcoder.app.folink.util.rss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Rory on 2016/10/13.
 */

public class RSSFeed {
    private String title = null;//标题
    private String pubdate = null;//发布日期
    private int itemcount = 0;//用于计算列表数目
    private List<RSSItem> itemlist;//声明一个RSSItem类型的泛型集合类List对象itemlist，用于描述列表 item
    public RSSFeed()
    {
        itemlist = new Vector(0); //构造函数初始化itemlist
    }
    public int addItem(RSSItem item)
    {
        itemlist.add(item);
        itemcount++;
        return itemcount;
    }
    public RSSItem getItem(int location)
    {
        return itemlist.get(location);
    }
    public List getAllItems()
    {
        return itemlist;
    }
    public List getAllItemsForListView(){
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        int size = itemlist.size();
        for(int i=0;i<size;i++){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put(RSSItem.TITLE, itemlist.get(i).getTitle());
            item.put(RSSItem.PUBDATE, itemlist.get(i).getPubDate());
            data.add(item);
        }
        return data;
    }
    int getItemCount()
    {
        return itemcount;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setPubDate(String pubdate)
    {
        this.pubdate = pubdate;
    }
    public String getTitle()
    {
        return title;
    }
    public String getPubDate()
    {
        return pubdate;
    }
}
