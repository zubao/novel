package com.lpl.common.util;

import android.content.Context;
import android.text.TextUtils;

public class PathConfig {

    private static String sMediaCachePath;
    private static String sMediaSavePath;
    private static String sHttpCache;

    public static final String SEARCH_FILE  = "search_file.html";

    // PS：注意程序中所有出现的路径，结尾都不带 "/"




    /**
     *  获取Http缓存目录
     *
     * @return
     */
    public static String getHttpCacheDir(Context context){
        if (!TextUtils.isEmpty(sHttpCache)) {
            return sHttpCache;
        }

        String dir      = PathUtil.getCacheDir(context) + "/http_cache";
        FileUtil.ensureDir(dir);
        if(FileUtil.isDirExist(dir)){
            sHttpCache  = dir;
            return sHttpCache;
        }
        sHttpCache  = PathUtil.getCacheDir(context);
        return sHttpCache;
    }







}
