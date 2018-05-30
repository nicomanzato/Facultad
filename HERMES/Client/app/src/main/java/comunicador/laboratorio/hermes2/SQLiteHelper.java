package comunicador.laboratorio.hermes2;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by nicolasmanzato on 23/4/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Hermes";

    private static final int DATABASE_VERSION = 2;

    public SQLiteHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db){

        db.execSQL(AlumnoDao.CREATE_TABLE);
        db.execSQL(PictogramaDao.CREATE_TABLE);
        db.execSQL(ConfigDao.CREATE_TABLE);
        db.execSQL(AlumnoPictogramaDao.CREATE_TABLE);
        db.execSQL(NotificacionDao.CREATE_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS "+ AlumnoDao.ALUMNO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ PictogramaDao.PICTOGRAMA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ ConfigDao.CONFIG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ AlumnoPictogramaDao.ALUMNO_PICTOGRAMA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ NotificacionDao.CREATE_TABLE);
        this.onCreate(db);

    }


}
