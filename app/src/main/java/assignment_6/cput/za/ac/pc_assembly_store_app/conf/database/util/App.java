package assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by CuanL on 09/05/2016.
 */
public class App extends Application{
    private static Context context;

    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();


    public static synchronized App getInstance() {
        return singleton;
    }
}
