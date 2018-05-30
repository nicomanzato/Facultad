package bd2.util;

import bd2.model.*;

public class QueriesRunner {
	
	private static void TestQuery_1(){
		
		for(Documento documento : Queries.query_1()){
			
			System.out.println("Documento: "+documento);
			
		}
		
	}
	
	private static void TestQuery_2(){
		
		for(Moderador moderador : Queries.query_2()){
			
			System.out.println("Email: "+moderador.getEmail());
			
		}
		
	}
	
	private static void TestQuery_3(){
		
		for(Usuario usuario : Queries.query_3()){
			
			System.out.println(usuario);
			
		}
		
	}
	
	private static void TestQuery_4(){
		
		for(Usuario usuario : Queries.query_4()){
			
			System.out.println(usuario);
			
		}
		
	}
	
	private static void TestQuery_5(){
		
		for(Traduccion traduccion : Queries.query_5()){
			
			System.out.println(traduccion.getDescripcion());
			
		}
		
	}
	
	private static void TestQuery_6(){
		
		for(Usuario usuario : Queries.query_6()){
			
			System.out.println(usuario.getEmail());
			
		}
		
	}
	
	private static void TestQuery_7(){
		
		for(Idioma idioma : Queries.query_7()){
			
			System.out.println(idioma.getNombre());
			
		}
		
	}
	
	private static void TestQuery_8(){
		
		for(Documento documento : Queries.query_8()){
			
			System.out.println(documento.getNombre());
			
		}
		
	}
	
	private static void TestQuery_9(){
		
		for(Documento documento : Queries.query_9()){
			
			System.out.println("El documento "+documento.getNombre()+" no esta totalmente traducido");
			
		}
		
	}
	
	public static void main(String[] args) {
		
		//TestQuery_1();
		
		//TestQuery_2();
		
		//TestQuery_3();
		
		//TestQuery_4();
		
		//TestQuery_5();
		
		TestQuery_6();
		
		//TestQuery_7();
		
		//TestQuery_8();
		
		//TestQuery_9();
	}

}
