package comunicador.laboratorio.hermes2;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.*;
import java.util.*;
/**
 * Created by josefina on 29/04/16.
 */
public class AlumnoPictogramaDao {

    private SQLiteHelper dbHelper;
    private SQLiteDatabase database;

    public static final String ALUMNO_PICTOGRAMA_TABLE = "alumno_pictograma";
    public static final String id = "_id";
    public static final String idAlumno = "idAlumno";
    public static final String idPictograma = "idPictograma";

    public static final String CREATE_TABLE = "create table " + ALUMNO_PICTOGRAMA_TABLE + "("
            + id + " integer primary key autoincrement,"
            + idAlumno + " integer not null,"
            + idPictograma + " integer not null);";

    public AlumnoPictogramaDao(Context context){

        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    private ContentValues createAlumnoPictograma(Integer idAlumno, Integer idPictograma) {

        ContentValues values = new ContentValues();

        values.put(this.idAlumno, idAlumno);
        values.put(this.idPictograma, idPictograma);

        return values;

    }

    public Cursor getPictogramasAlumnos(int alumno){
        String idp = Integer.toString(alumno);
        String[] columnas = new String[]{id, idAlumno ,idPictograma};
        return database.query(ALUMNO_PICTOGRAMA_TABLE,columnas, null, null, null, null, null);
    }

    public Cursor searchPictogramaAlumno(int alumno){
        String idp = Integer.toString(alumno);
        String[] columnas = new String[]{id,idAlumno,idPictograma};
        return database.query(ALUMNO_PICTOGRAMA_TABLE, columnas, idAlumno + "=?", new String[]{idp}, null, null, null);
    }

    public Cursor searchPictogramaAlumno(int alumno, int pictograma){
        String idp = Integer.toString(pictograma);
        String ida = Integer.toString(alumno);
        String[] columnas = new String[]{id,idAlumno,idPictograma};
        return database.query(ALUMNO_PICTOGRAMA_TABLE, columnas, idAlumno + "=? and " + idPictograma + "=?", new String[]{ida, idp}, null, null, null);
    }

    public void insert(int idAlumno, int idPictograma){

        database.insert(ALUMNO_PICTOGRAMA_TABLE, null, createAlumnoPictograma(idAlumno, idPictograma));

    }

    public void deletePictograma(int idAlumno, int idPictograma) {
        String idp = Integer.toString(idAlumno);
        String idpic = Integer.toString(idPictograma);
        database.delete(ALUMNO_PICTOGRAMA_TABLE, idAlumno + " =? and " + idPictograma + " =? ", new String[]{idp, idpic});
    }

}
