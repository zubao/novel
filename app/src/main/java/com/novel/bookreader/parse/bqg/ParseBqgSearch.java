package com.novel.bookreader.parse.bqg;

import com.novel.bookreader.parse.ParseXml;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 *  笔趣阁搜索
 */
public class ParseBqgSearch extends ParseXml {
    @Override
    public ArrayList<String> parse() {
        ArrayList<String> list = new ArrayList<>();
        Elements elements = document.select("[class=result c-container ]");
        for(Element element : elements){
            String linkHref = element.select("a[href]").attr("href");
            list.add(linkHref);
        }

        return list;
    }
}
