package comunicador.laboratorio.hermes2;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.*;
import java.util.*;

/**
 * Created by nicolasmanzato on 23/4/16.
 */
public class AlumnoDao {

    private SQLiteHelper dbHelper;

    private SQLiteDatabase database;

    public final static String ALUMNO_TABLE="Alumno";

    public final static String idAlumno="_id";
    public final static String nombreAlumno="nombreAlumno";
    public final static String apellidoAlumno="apellidoAlumno";
    public final static String sexoAlumno="sexoAlumno";
    public final static String tamanoPictogramas="tamanoPictogramas";
    public final static String emocionesHabilitada="emocionesHabilitada";
    public final static String pistaHabilitada="pistaHabilitada";
    public final static String necesidadesHabilitada="necesidadesHabilitada";
    public final static String establoHabilitada="establoHabilitada";

    public static final String CREATE_TABLE = "create table " + ALUMNO_TABLE + "("

            + idAlumno + " integer primary key autoincrement,"
            + nombreAlumno + " text not null,"
            + apellidoAlumno + " text not null,"
            + sexoAlumno + " text not null,"
            + tamanoPictogramas + " text not null,"
            + emocionesHabilitada + " text not null,"
            + pistaHabilitada + " text not null,"
            + necesidadesHabilitada + " text not null,"
            + establoHabilitada + " text not null);";


    public AlumnoDao(Context context){

        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    private ContentValues crearAlumno(String nombreAlumno, String apellidoAlumno, String sexoAlumno, String tamanoPicictogramas, String emocionesHabilitada, String pistaHabilitada, String necesidadesHabilitada, String establoHabilitada) {

        ContentValues values = new ContentValues();

        values.put(this.nombreAlumno, nombreAlumno);
        values.put(this.apellidoAlumno, apellidoAlumno);
        values.put(this.sexoAlumno, sexoAlumno);
        values.put(this.tamanoPictogramas, tamanoPicictogramas);
        values.put(this.emocionesHabilitada, emocionesHabilitada);
        values.put(this.pistaHabilitada, pistaHabilitada);
        values.put(this.necesidadesHabilitada, necesidadesHabilitada);
        values.put(this.establoHabilitada, establoHabilitada);

        return values;

    }

    private ContentValues crearAlumno(String nombreAlumno, String apellidoAlumno, String sexoAlumno, int tamanoPicictogramas, int emocionesHabilitada, int pistaHabilitada, int necesidadesHabilitada, int establoHabilitada) {

        ContentValues values = new ContentValues();

        values.put(this.nombreAlumno, nombreAlumno);
        values.put(this.apellidoAlumno, apellidoAlumno);
        values.put(this.sexoAlumno, sexoAlumno);
        values.put(this.tamanoPictogramas, tamanoPicictogramas);
        values.put(this.emocionesHabilitada, emocionesHabilitada);
        values.put(this.pistaHabilitada, pistaHabilitada);
        values.put(this.necesidadesHabilitada, necesidadesHabilitada);
        values.put(this.establoHabilitada, establoHabilitada);

        return values;

    }

    public long insertar(String nombreAlumno, String apellidoAlumno, String sexoAlumno, String tamanoPicictogramas, String emocionesHabilitada, String pistaHabilitada, String necesidadesHabilitada, String establoHabilitada){

        return database.insert(ALUMNO_TABLE, null, crearAlumno(nombreAlumno,apellidoAlumno,sexoAlumno,tamanoPicictogramas,emocionesHabilitada,pistaHabilitada,necesidadesHabilitada,establoHabilitada));
    }

    public List<Alumno> getAlumnos() {


        String[] cols = new String[] {idAlumno, nombreAlumno, apellidoAlumno, sexoAlumno, tamanoPictogramas, emocionesHabilitada, pistaHabilitada, necesidadesHabilitada, establoHabilitada};
        Cursor mCursor = database.query(ALUMNO_TABLE,cols,null, null, null, null, null, null);

        List<Alumno> list = new ArrayList<Alumno>();

        while(mCursor.moveToNext()){

            list.add(new Alumno(
                    mCursor.getInt(mCursor.getColumnIndex(idAlumno)),
                    mCursor.getString(mCursor.getColumnIndex(nombreAlumno)),
                    mCursor.getString(mCursor.getColumnIndex(apellidoAlumno)),
                    mCursor.getString(mCursor.getColumnIndex(sexoAlumno)),
                    mCursor.getInt(mCursor.getColumnIndex(tamanoPictogramas)),
                    mCursor.getInt(mCursor.getColumnIndex(emocionesHabilitada)),
                    mCursor.getInt(mCursor.getColumnIndex(pistaHabilitada)),
                    mCursor.getInt(mCursor.getColumnIndex(necesidadesHabilitada)),
                    mCursor.getInt(mCursor.getColumnIndex(establoHabilitada))));

        }

        return list;


    }

    public void updateAlumno(Alumno alumno){

        System.out.println(alumno.getNombre()+" tiene el id "+alumno.getId());

        database.update(ALUMNO_TABLE,crearAlumno(alumno.getNombre(), alumno.getApellido(), alumno.getSexo(), alumno.getTamanoPictogramas(), alumno.getEmocionesHabilitada(), alumno.getPistaHabilitada(), alumno.getNecesidadHabilitada(), alumno.getEstabloHabilitada()), idAlumno + "=?", new String[]{Integer.toString(alumno.getId())});

    }

    public void deleteAlumno(int idAlumno){

        database.delete(ALUMNO_TABLE, this.idAlumno + "=?", new String[]{Integer.toString(idAlumno)});
    }

    public Alumno getAlumno(int alumno){

        String idp = Integer.toString(alumno);
        String[] cols = new String[] {idAlumno, nombreAlumno, apellidoAlumno, sexoAlumno, tamanoPictogramas, emocionesHabilitada, pistaHabilitada, necesidadesHabilitada, establoHabilitada};
        Cursor mCursor = database.query(ALUMNO_TABLE, cols, idAlumno + "=?", new String[]{idp}, null, null, null);

        Alumno alum = null;

        while(mCursor.moveToNext()){

            alum = new Alumno(
                    mCursor.getInt(mCursor.getColumnIndex(idAlumno)),
                    mCursor.getString(mCursor.getColumnIndex(nombreAlumno)),
                    mCursor.getString(mCursor.getColumnIndex(apellidoAlumno)),
                    mCursor.getString(mCursor.getColumnIndex(sexoAlumno)),
                    mCursor.getInt(mCursor.getColumnIndex(tamanoPictogramas)),
                    mCursor.getInt(mCursor.getColumnIndex(emocionesHabilitada)),
                    mCursor.getInt(mCursor.getColumnIndex(pistaHabilitada)),
                    mCursor.getInt(mCursor.getColumnIndex(necesidadesHabilitada)),
                    mCursor.getInt(mCursor.getColumnIndex(establoHabilitada)));

        }

        return alum;

    }
}
