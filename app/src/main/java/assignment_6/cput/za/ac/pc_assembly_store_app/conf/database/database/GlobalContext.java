package assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.database;

import android.app.Application;
import android.content.Context;

/**
 * Created by CuanL on 31/05/2016.
 */
public class GlobalContext extends Application{
    public static Context context;

    private static GlobalContext singleton;

    public void onCreate() {
        super.onCreate();
        GlobalContext.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return GlobalContext.context;
    }

    public static synchronized GlobalContext getInstance() {
        return singleton;
    }
}
