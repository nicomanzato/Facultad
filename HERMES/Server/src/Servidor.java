import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Servidor implements Runnable{
	
	public void run() {
    	
		HttpServer server;
		int puerto;
		
		BufferedReader conf=null;
		
		try {
			
			conf = new BufferedReader(new FileReader("conf.txt"));
			
			puerto = Integer.parseInt(conf.readLine());
			
			server = HttpServer.create(new InetSocketAddress(puerto), 0);
			
		  	server.createContext("/hermes", new MyHandler());

	    	server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());

	    	server.start();  
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		finally {
		
			try {
				conf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
	};
	
}
