package whimsicalgl.knowledgebank.app;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import whimsicalgl.knowledgebank.db.DataBaseHelper;
import whimsicalgl.knowledgebank.db.dao.DaoFactory;

/**
 * Created by lilei on 2015/11/9.
 */
public class MyApplication extends Application {
    /*线程池*/
    public static ExecutorService executorService = null;

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newScheduledThreadPool(5);
        initDataBase();
        initFactory();
    }

    private void initDataBase() {
        Log.i("MyApplication", "初始化数据库");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        try {
            dataBaseHelper.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MyApplication", "初始化数据库失败！");
        }

    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    private void initFactory() {
        DaoFactory.initFactory(this);
    }
}
