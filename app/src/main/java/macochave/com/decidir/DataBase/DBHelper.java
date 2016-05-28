package macochave.com.decidir.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by MacoChave on 27/05/2016.
 */
public class DBHelper extends SQLiteAssetHelper {

    private static final String DBNAME = "dbDesicion.sqlite";
    private static final int DBSCHEME = 1;

    public DBHelper(Context context) {
        super(context, DBNAME, null, DBSCHEME);
    }
}
