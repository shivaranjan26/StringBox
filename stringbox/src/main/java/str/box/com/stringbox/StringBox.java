package str.box.com.stringbox;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StringBox {

    private static final String DATABASE_NAME = "stringbox";
    private static final String MY_SHARED_PREFS = "mysharedprefs";

    private static Context mContext;
    private static StringBox mInstance;

    private static SQLiteDatabase mDatabase;

    private StringBox(Context context) {
        mContext = context;
        mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
    }

    public static synchronized StringBox getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new StringBox(context);
        }
        return mInstance;
    }

    public void createTable(String tableName, ArrayList<String> columns) {
        if (!mDatabase.isOpen()) {
            mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        }
        if (!isTableExists(tableName)) {
            StringBuilder mSql = new StringBuilder("create table " + tableName + " " + "(id integer primary key");

            for (int i = 0; i < columns.size(); i++) {
                mSql.append(", " + columns.get(i) + " text");
            }

            mSql.append(")");

            mDatabase.execSQL(mSql.toString());
        }
        mDatabase.close();
    }

    public void addRow(String tableName, HashMap<String, String> data) {
        if (!mDatabase.isOpen()) {
            mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        }
        if (isTableExists(tableName)) {
            ContentValues values = new ContentValues();
            ArrayList keyList = new ArrayList(data.keySet());

            for (int i = keyList.size() - 1; i >= 0; i--) {
                String key = keyList.get(i).toString();
                String value = data.get(key);
                values.put(key, value);
            }

            mDatabase.insert(tableName, null, values);
        } else {
            Toast.makeText(mContext, "Table does not exist", Toast.LENGTH_SHORT).show();
        }
        mDatabase.close();
    }

    public void modifyRow(String tableName, HashMap<String, String> data, int position) {
        position = position + 1;
        if (!mDatabase.isOpen()) {
            mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        }
        if (isTableExists(tableName)) {
            ContentValues values = new ContentValues();
            ArrayList keyList = new ArrayList(data.keySet());

            for (int i = keyList.size() - 1; i >= 0; i--) {
                String key = keyList.get(i).toString();
                String value = data.get(key);
                values.put(key, value);
            }

            mDatabase.update(tableName, values, "id="+position, null);
        } else {
            Toast.makeText(mContext, "Table does not exist", Toast.LENGTH_SHORT).show();
        }
        mDatabase.close();
    }

    public void deleteRow(String tableName, int position) {
        position = position + 1;
        if (!mDatabase.isOpen()) {
            mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        }
        if (isTableExists(tableName)) {
            mDatabase.delete(tableName, "id="+position, null);
        } else {
            Toast.makeText(mContext, "Table does not exist", Toast.LENGTH_SHORT).show();
        }
        mDatabase.close();
    }

    public ArrayList<String> getAllRowsFromColumn(String tableName, String columnName) {
        ArrayList<String> mList = new ArrayList<>();
        if (!mDatabase.isOpen()) {
            mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        }

        if (isTableExists(tableName)) {
            String[] columns = getColumnNamesFromTable(tableName);
            int position = -1;
            for (int i = 0; i < columns.length; i++) {
                if (columns[i].equalsIgnoreCase(columnName)) {
                    position = i;
                }
            }

            if (position != -1) {
                String selectQuery = "SELECT  * FROM " + tableName;

                Cursor cursor = mDatabase.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {
                    do {
                        String mStr = cursor.getString(position);
                        mList.add(mStr);
                    } while (cursor.moveToNext());
                }
            }
        } else {
            Toast.makeText(mContext, "Table does not exist", Toast.LENGTH_SHORT).show();
        }


        mDatabase.close();
        return mList;
    }

    public int getRowsCount(String tableName) {
        ArrayList<String> mList = new ArrayList<>();
        if (!mDatabase.isOpen()) {
            mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        }

        if (isTableExists(tableName)) {
            String[] columns = getColumnNamesFromTable(tableName);
            int position = 1;

            if (position != -1) {
                String selectQuery = "SELECT  * FROM " + tableName;

                Cursor cursor = mDatabase.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {
                    do {
                        String mStr = cursor.getString(position);
                        mList.add(mStr);
                    } while (cursor.moveToNext());
                }
            }
        } else {
            Toast.makeText(mContext, "Table does not exist", Toast.LENGTH_SHORT).show();
        }

        mDatabase.close();
        return mList.size();
    }

    public void saveToPreferences(String key, String value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringPreference(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE);
        String mSavedStr = prefs.getString(key, "");
        return mSavedStr;
    }

    public void saveToPreferences(String key, boolean value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanPreference(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE);
        boolean mSavedBoolean = prefs.getBoolean(key, false);
        return mSavedBoolean;
    }

    public void saveToPreferences(String key, int value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntegerPreference(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE);
        int mSavedInt = prefs.getInt(key, 0);
        return mSavedInt;
    }

    public void saveToPreferences(String key, float value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloatPreference(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE);
        float mSavedFloat = prefs.getFloat(key, 0);
        return mSavedFloat;
    }

    public void saveToPreferences(String key, long value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLongPreference(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE);
        long mSavedLong = prefs.getLong(key, 0);
        return mSavedLong;
    }

    public void saveToPreferences(String key, ArrayList<String> value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            sb.append(value.get(i)).append(",");
        }

        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putString(key, sb.toString());
        editor.apply();
    }

    public ArrayList<String> getStringArrayPreference(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE);
        String mSavedStr = prefs.getString(key, null);
        ArrayList<String> mStrArr = new ArrayList<>();

        if (mSavedStr != null) {
            String[] mSplitStr = mSavedStr.split(",");
            for (int i = 0; i < mSplitStr.length; i++) {
                mStrArr.add(mSplitStr[i]);
            }
        }
        return mStrArr;
    }

    public void saveToPreferences(String key, List<String> value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            sb.append(value.get(i)).append(",");
        }

        SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putString(key, sb.toString());
        editor.apply();
    }

    private static String[] getColumnNamesFromTable(String tableName){
        Cursor dbCursor = mDatabase.query(tableName, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

    private static boolean isTableExists(String tableName) {
        boolean isExist = false;
        Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        return isExist;
    }
}
