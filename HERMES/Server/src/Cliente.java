import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import MockPackage.MockGenerator;
import entidades.Notificacion;

public class Cliente {
	public static void main(String[] args) throws Exception {
		

		
		Cliente http = new Cliente();
		
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}
	
	// HTTP POST request
	private void sendPost() throws Exception {

		
		 	String notificacionesJson = new Gson().toJson(MockGenerator.createMockInstances(Notificacion.class, 40));
		
			String url = "http://localhost:8001/hermes";
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
