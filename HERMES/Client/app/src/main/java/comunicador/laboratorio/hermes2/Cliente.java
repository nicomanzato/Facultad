package comunicador.laboratorio.hermes2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolasmanzato on 2/5/16.
 */
public class Cliente extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params){

        Notificacion notificacion = (Notificacion) params[0];
        String ip = (String) params[1];
        String puerto = Integer.toString((int)params[2]);
        Context contexto = (Context) params[3];

        boolean envioExitoso = false;

        try {

            enviarNotificacion(notificacion,ip,puerto);

            List<Notificacion> notificaciones = DaoFactory.getNotificacionDao(contexto).getNotificaciones();

            for (Notificacion n :notificaciones){

                enviarNotificacion(n,ip,puerto);

            }

        }catch(Exception e) {

            DaoFactory.getNotificacionDao(contexto).insert(notificacion.getContenido(),notificacion.getContexto(),notificacion.getCategoria(),notificacion.getPaciente());

            System.out.println("No se pudo enviar la notificacion, por lo que se almaceno en la base de datos");

        }

        return null;
    }

    private void enviarNotificacion(Notificacion notificacion, String ip, String puerto) throws ConnectException, Exception{

        List<Notificacion> listaNotificacion = new ArrayList<Notificacion>();

        listaNotificacion.add(notificacion);
        String notificacionesJson = new Gson().toJson(listaNotificacion);


        //String url = "http://192.168.0.10:8001/hermes";
        String url = "http://"+ip+":"+puerto+"/hermes";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send post request
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(notificacionesJson);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result
        System.out.println(response.toString());


    }


}
