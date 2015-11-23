package whimsicalgl.knowledgebank.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by lilei on 2015/11/22.
 */
public class MyCache {

    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences("lilie", Context.MODE_PRIVATE);

    }

    public static int getLast(String key) {
        Log.i("MyCache", key);
        return sharedPreferences.getInt(key, 0);
    }

    public static void setLast(String key, int index) {
        boolean ok=sharedPreferences.edit().putInt(key, index).commit();

        Log.i("MyCache", key+"--"+ok);
    }
}
