package kevin.com.proyecto01.Util;

import android.content.SharedPreferences;

import com.google.firebase.database.FirebaseDatabase;

public class Util {

    private static FirebaseDatabase mDatabase;


    public static String getUserMailPrefs(SharedPreferences preferences){
        return preferences.getString("email", "");
    }

    public static String getUserPassPrefs(SharedPreferences preferences){
        return preferences.getString("pass", "");
    }


    public static void removeSharedPreferences(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("pass");
        editor.apply();
    }

    public static FirebaseDatabase getmDatabase(){
        if(mDatabase == null){
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }

        return mDatabase;
    }


}
