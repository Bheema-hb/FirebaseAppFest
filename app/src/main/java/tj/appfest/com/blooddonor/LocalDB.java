package tj.appfest.com.blooddonor;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nitin on 28/06/17.
 */

public class LocalDB {
    private Context mContext;
    private SharedPreferences mSharedPreference;
    private static final String USER_ID = "userId";

    public static LocalDB localDB;

    private LocalDB(Context context) {
        this.mContext = context;
        mSharedPreference = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static LocalDB getInstance(Context context) {
        if (localDB == null) {
            localDB = new LocalDB(context);
        }

        return localDB;
    }

    public void setUserId(String userId) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(USER_ID, userId);
        editor.commit();
    }

    public String getUserId() {
        return mSharedPreference.getString(USER_ID, null);
    }

    public void clearDB() {
        mSharedPreference.edit().clear().commit();
    }


}
