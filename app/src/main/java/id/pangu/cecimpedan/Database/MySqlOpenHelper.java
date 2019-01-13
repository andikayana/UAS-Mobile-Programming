package id.pangu.cecimpedan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static id.pangu.cecimpedan.Database.DatabaseContarct.TABLE_CECIMPEDAN;
import static id.pangu.cecimpedan.Database.DatabaseContarct.CecimpedanColumns.CECIMPEDAN;
import static id.pangu.cecimpedan.Database.DatabaseContarct.CecimpedanColumns.ARTI;

public class MySqlOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_cecimpedan.db";
    private static final int DATABASE_VERSION = 1;

    public MySqlOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static String CREATE_TABLE_CECIMPEDAN = "create table "+ TABLE_CECIMPEDAN
            + " ("+ _ID +" integer primary key autoincrement, "
            + CECIMPEDAN + " text not null, "
            + ARTI +" text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CECIMPEDAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CECIMPEDAN );
        onCreate(db);
    }
}
