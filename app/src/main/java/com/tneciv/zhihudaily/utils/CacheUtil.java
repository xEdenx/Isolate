package com.tneciv.zhihudaily.utils;

import android.content.Context;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Tneciv on 5-8-0008 .
 */
public class CacheUtil {
    public static final int APP_VERSION = 1;
    public static final int VALUE_COUNT = 1;
    public static final long MAX_SIZE = 10 * 1024 * 1024;

    private Context mContext;

    public CacheUtil(Context context) {
        this.mContext = context;
    }

    private File getDiskCacheDir(String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = mContext.getExternalCacheDir().getPath();
        } else {
            cachePath = mContext.getCacheDir().getPath();
        }
        File cacheDir = new File(cachePath + File.separator + uniqueName);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public boolean cacheFiles(String key, String json) {

        try {
            DiskLruCache diskLruCache = getDiskLruCache("json");
            DiskLruCache.Editor edit = diskLruCache.edit(HashUtil.hashKeyForDisk(key));

            if (edit != null) {
                OutputStream outputStream = edit.newOutputStream(0);
                outputStream.write(json.getBytes());
                outputStream.close();
                edit.commit();
            }

            diskLruCache.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public DiskLruCache getDiskLruCache(String type) {
        File jsonCache = new CacheUtil(mContext).getDiskCacheDir(type);
        DiskLruCache diskLruCache = null;
        try {
            diskLruCache = DiskLruCache.open(jsonCache, CacheUtil.APP_VERSION, CacheUtil.VALUE_COUNT, CacheUtil.MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diskLruCache;
    }
}
