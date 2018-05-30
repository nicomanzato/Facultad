package comunicador.laboratorio.hermes2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolas on 5/8/16.
 */
public class NotificacionDao {

    private SQLiteHelper dbHelper;

    private SQLiteDatabase database;

    public final static String NOTIFICACION_TABLE="Notificacion";

    public final static String id="_id";
    public final static String contenido="contenido";
    public final static String contexto="contexto";
    public final static String categoria="categoria";
    public final static String paciente="paciente";

    public static final String CREATE_TABLE = "create table " + NOTIFICACION_TABLE + "("

            + id + " integer primary key autoincrement,"
            + contenido + " text not null,"
            + contexto + " text not null,"
            + categoria + " text not null,"
            + paciente + " text not null);";


    public NotificacionDao(Context context){

        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    private ContentValues createNotificacion(String contenido, String contexto, String categoria, String paciente) {

        ContentValues values = new ContentValues();

        values.put(this.contenido, contenido);
        values.put(this.contexto, contexto);
        values.put(this.categoria, categoria);
        values.put(this.paciente, paciente);

        return values;

    }

    public long insert(String contenido, String contexto, String categoria, String paciente) {

        return database.insert(NOTIFICACION_TABLE, null, createNotificacion(contenido, contexto, categoria, paciente));

    }

    public List<Notificacion> getNotificaciones() {


        String[] cols = new String[] {contenido, contexto, categoria, paciente};
        Cursor mCursor = database.query(NOTIFICACION_TABLE,cols,null, null, null, null, null, null);

        List<Notificacion> list = new ArrayList<Notificacion>();

        while(mCursor.moveToNext()){

            list.add(new Notificacion(
                    mCursor.getString(mCursor.getColumnIndex(contenido)),
                    mCursor.getString(mCursor.getColumnIndex(categoria)),
                    mCursor.getString(mCursor.getColumnIndex(paciente))));

        }

        database.delete(NOTIFICACION_TABLE,"1=?", new String[]{Integer.toString(1)});

        return list;


    }

}
