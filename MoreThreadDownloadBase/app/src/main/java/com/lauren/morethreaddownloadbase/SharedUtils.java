package com.lauren.morethreaddownloadbase;

import android.content.Context;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;

/**
 * Created by Lauren on 16/12/26.
 */

class SharedUtils {
    public static void setLastPosition(Context mContext, int threadId, int currentThreadPostion) {
        SharedPreferences sharePreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
//        SharedPreferences.Editor edit = sharePreferences.edit();
//        edit.putInt("lastPostion"+threadId, currentThreadPostion);
//        edit.commit();
        sharePreferences.edit().putInt("lastPostion"+threadId, currentThreadPostion).commit();
    }

    public static int getLastPosition(Context mContext, int threadId) {

        SharedPreferences sharePreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharePreferences.getInt("lastPostion"+threadId, -1);
    }
}
