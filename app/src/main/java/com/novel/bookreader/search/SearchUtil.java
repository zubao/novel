package com.novel.bookreader.search;

import android.content.Context;

import com.lpl.common.util.FileManager;
import com.lpl.common.util.FileUtil;
import com.lpl.common.util.JDLog;
import com.lpl.common.util.PathConfig;
import com.novel.bookreader.download.OKHttpListener;
import com.novel.bookreader.download.OKhttpManager;
import com.novel.bookreader.parse.ParseXml;
import com.novel.bookreader.parse.bqg.ParseBqgSearch;
import com.novel.bookreader.website.Website;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/28.
 * Email:   lipeil4195@gmail.com
 * Author:  lipeilong
 */

public class SearchUtil {

    public static void search(final Context context, final Website website){

        OKhttpManager.getInstance().connectUrl(context, new OKHttpListener(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                handlerResponse(context, response);
            }

            @Override
            public String getLink() {
                return website.searchUrl;
            }
        });
    }

    private static void handlerResponse(Context context, Response response){
        final String searchFileCachePath    = PathConfig.getHttpCacheDir(context) + "/" + PathConfig.SEARCH_FILE;
        if(response != null && response.body() != null){
            byte[] data     = new byte[0];
            try {
                data = response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str      = new String(data);
            JDLog.log(str);
            if(data != null){
                FileManager.writeSearchFile(searchFileCachePath, data);
                List<String> list = ParseXml.createParse(ParseBqgSearch.class).from(searchFileCachePath).parse();
                for(String item : list){
                    JDLog.log(item);
                }
            }

            response.body().close();
        }
    }
}
