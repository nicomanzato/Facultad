package comunicador.laboratorio.hermes2;

import android.content.Context;

/**
 * Created by nicolasmanzato on 25/4/16.
 */
public class DaoFactory {

    private static AlumnoDao alumnoDao;
    private static PictogramaDao pictogramaDao;
    private static AlumnoPictogramaDao alumnoPictogramaDao;
    private static ConfigDao configDao;
    private static NotificacionDao notificacionDao;

    public static AlumnoDao getAlumnoDao(Context context){

        if (alumnoDao == null){
            alumnoDao = new AlumnoDao(context);
        }
        return alumnoDao;
    }

    public static NotificacionDao getNotificacionDao(Context context){

        if (notificacionDao == null){
            notificacionDao = new NotificacionDao(context);
        }
        return notificacionDao;
    }

    public static PictogramaDao getPictogramaDao(Context context){

        if (pictogramaDao == null){
            pictogramaDao = new PictogramaDao(context);
        }
        return pictogramaDao;
    }

    public static AlumnoPictogramaDao getAlumnoPictogramaDao(Context context){

        if (alumnoPictogramaDao == null){
            alumnoPictogramaDao = new AlumnoPictogramaDao(context);
        }
        return alumnoPictogramaDao;
    }

    public static ConfigDao getConfigDao(Context context){

        if (configDao == null){
            configDao = new ConfigDao(context);
        }
        return configDao;
    }

}
