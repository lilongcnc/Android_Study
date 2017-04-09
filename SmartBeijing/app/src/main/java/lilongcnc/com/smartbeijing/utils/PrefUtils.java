package lilongcnc.com.smartbeijing.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lauren on 2017/4/9.
 */

public class PrefUtils {

    //boolean
    public static void setBoolean(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, defValue).commit();
    }


    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    //String
    public static void setString(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, defValue).commit();
    }


    public static String getString(Context ctx, String key, String defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }


    //int
    public static void setInt(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, defValue).commit();
    }


    public static int getInt(Context ctx, String key, int defValue) {
        SharedPreferences sp = ctx.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }


}
