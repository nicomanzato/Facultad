import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;

import org.json.JSONException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.DaoFactory;

public class MyHandler implements HttpHandler {
	public void handle(HttpExchange t) throws IOException {
		 
		InputStream is = t.getRequestBody();
		
		BufferedReader requestBufferReader = new BufferedReader(
		        new InputStreamReader(is));
		String inputLine;
		StringBuffer request = new StringBuffer();

		while ((inputLine = requestBufferReader.readLine()) != null) {
			request.append(inputLine);
		}
		
		try {
			DaoFactory.getNotificacionDao().insertarNotificacionesFromJson(request.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //read(is); // .. lee el request body
		 String response = "una respuesta";
		 t.sendResponseHeaders(200, response.length());
		 
		 OutputStream os =  t.getResponseBody();
		 
		 os.write(response.getBytes());
		 os.close();
		 
	}
}