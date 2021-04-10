package aplication.codewithdeev.timetable;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CentralStorage {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Context myContext;

    public CentralStorage(Context myContext) {
        this.myContext = myContext;
        preferences= PreferenceManager.getDefaultSharedPreferences(myContext);
        editor=preferences.edit();
    }
    public void setType(String key,String Type) {
        editor.putString(key,Type);
        editor.commit();
    }
    public void setData(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public void setDate(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public void setDateType(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public String getType(String key){
        return preferences.getString(key,"");
    }
    public String getData(String key){
        return preferences.getString(key,"");
    }
    public String getDate(String key){
        return preferences.getString(key,"");
    }
    public String getDateType(String key){
        return preferences.getString(key,"");
    }
    public void removeData(String key){
        editor.remove(key);
        editor.commit();
    }
    public void clearData(){
        editor.clear();
        editor.commit();
    }
}
