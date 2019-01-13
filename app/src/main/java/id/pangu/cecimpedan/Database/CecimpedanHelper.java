package id.pangu.cecimpedan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import id.pangu.cecimpedan.Model.CecimpedanItem;
import static android.provider.BaseColumns._ID;
import static id.pangu.cecimpedan.Database.DatabaseContarct.TABLE_CECIMPEDAN;
import static id.pangu.cecimpedan.Database.DatabaseContarct.CecimpedanColumns.CECIMPEDAN;
import static id.pangu.cecimpedan.Database.DatabaseContarct.CecimpedanColumns.ARTI;

public class CecimpedanHelper {

    private Context context;
    private MySqlOpenHelper sqlOpenHelper;
    private SQLiteDatabase database;

    public CecimpedanHelper(Context context) {
        this.context = context;
    }

    public CecimpedanHelper open() throws SQLException {
        sqlOpenHelper = new MySqlOpenHelper(context);
        database = sqlOpenHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        sqlOpenHelper.close();
    }

    public ArrayList<CecimpedanItem> getAllData() {
        ArrayList<CecimpedanItem> arrayList = new ArrayList<>();
        CecimpedanItem item;

        Cursor cursor = database.query(TABLE_CECIMPEDAN, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                item = new CecimpedanItem();
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                item.setCecimpedan(cursor.getString(cursor.getColumnIndexOrThrow(CECIMPEDAN)));
                item.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));
                arrayList.add(item);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(CecimpedanItem item) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CECIMPEDAN, item.getCecimpedan());
        initialValues.put(ARTI, item.getArti());
        return database.insert(TABLE_CECIMPEDAN, null, initialValues);
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void insertTransaction(CecimpedanItem item) {
        String sql = "INSERT INTO " + TABLE_CECIMPEDAN + " (" + CECIMPEDAN + ", " + ARTI
                + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, item.getCecimpedan());
        stmt.bindString(2, item.getArti());
        stmt.execute();
        stmt.clearBindings();
    }

    public int update(CecimpedanItem item) {
        ContentValues args = new ContentValues();
        args.put(CECIMPEDAN, item.getCecimpedan());
        args.put(ARTI, item.getArti());
        return database.update(TABLE_CECIMPEDAN, args, _ID + "= '" + item.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(TABLE_CECIMPEDAN, _ID + " = '"+id+"'", null);
    }
}
