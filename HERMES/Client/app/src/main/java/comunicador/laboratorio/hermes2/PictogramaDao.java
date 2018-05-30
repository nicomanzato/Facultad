package comunicador.laboratorio.hermes2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josefina on 29/04/16.
 */

public class PictogramaDao {
    private SQLiteHelper dbHelper;

    private SQLiteDatabase database;

    public final static String PICTOGRAMA_TABLE="Pictograma";

    public final static String id="_id";
    public final static String description="description";
    public final static String category="category";

    public static final String CREATE_TABLE = "create table " + PICTOGRAMA_TABLE + "("
            + id + " integer primary key autoincrement,"
            + description + " text not null,"
            + category + " text not null);";

    public PictogramaDao(Context context){

        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    private ContentValues createPictograma(String descripcion, Integer categoria) {

        ContentValues values = new ContentValues();

        values.put(this.description, descripcion);
        values.put(this.category, categoria);

        return values;

    }

    private ContentValues createPictograma(String descripcion) {

        ContentValues values = new ContentValues();

        values.put(this.description, descripcion);

        return values;

    }

    public long insert(String descripcion, Integer categoria){

        return database.insert(PICTOGRAMA_TABLE, null, createPictograma(descripcion, categoria));

    }

    public long insert(String descripcion){

        return database.insert(PICTOGRAMA_TABLE, null, createPictograma(descripcion));

    }

    public Cursor getPictogramaById(int searchedId){

        String f = Integer.toString(searchedId);
        String[] columnas = new String[]{ id, description, category };
        return database.query(PICTOGRAMA_TABLE, columnas, id + "=?", new String[]{f}, null, null, null);

    }

    public Cursor getPictogramaByName(String name){

        String[] columnas = new String[]{ id, description, category };
        return database.query(PICTOGRAMA_TABLE, columnas, description + "=?", new String[]{name}, null, null, null);

    }

}
