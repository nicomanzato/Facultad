package comunicador.laboratorio.hermes2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by josefina on 29/04/16.
 */
public class ConfigDao {
    private SQLiteHelper dbHelper;

    private SQLiteDatabase database;

    public final static String CONFIG_TABLE="Config";

    public final static String id="_id";
    public final static String port="port";
    public final static String ip_address="ip_address";

    public static final String CREATE_TABLE = "create table " + CONFIG_TABLE + "("

            + id + " integer primary key autoincrement,"
            + port + " text not null,"
            + ip_address + " text not null);";

    public ConfigDao(Context context){

        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    private ContentValues createConfig(String port, String ip_address) {

        ContentValues values = new ContentValues();

        values.put(this.port, port);
        values.put(this.ip_address, ip_address);

        return values;

    }

    public long insert(String port, String ip_address){

        return database.insert(CONFIG_TABLE, null, createConfig(port, ip_address));

    }

    public long update(String port, String ip_address){

        String id1 = Integer.toString(1);
        return database.update(CONFIG_TABLE, createConfig(port, ip_address), id + "=?", new String[]{id1});

    }

    public Config getConfig(){
        String[] columnas = new String[]{id, port, ip_address};
        Cursor mCursor = database.query(CONFIG_TABLE, columnas, null, null, null, null, null);

        Config config = new Config();

        while(mCursor.moveToNext()){

            config.setIp(mCursor.getString(mCursor.getColumnIndex(ip_address)));
            config.setPuerto(mCursor.getInt(mCursor.getColumnIndex(port)));

        }

        return config;
    }
}
