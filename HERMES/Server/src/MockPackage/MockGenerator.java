package MockPackage;

import java.util.Random;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.Categoria;
import entidades.Contexto;
import entidades.Notificacion;

public class MockGenerator {
	
	public static <T> List<T> createMockInstances(Class<T> elClass, int cant) throws Exception{ 
		
		List<T> listaMock = new ArrayList<T>();
		
		if (elClass.isAnnotationPresent(Mock.class)){  
		
			Random randomGenerator = new Random();
			
			for( int i=0 ; i<cant ; i++){
			
				T mock = elClass.newInstance();
				
				for(Field f: elClass.getDeclaredFields()){
					
					if( f.isAnnotationPresent(MockStringAttribute.class) ){
						
						MockStringAttribute a = f.getAnnotation(MockStringAttribute.class);
						
						Constructor constructor = f.getType().getConstructor(String.class);
						
						f.set(mock,constructor.newInstance(a.value()[randomGenerator.nextInt(a.value().length)]));
						
					}
					
					if( f.isAnnotationPresent(MockTodayAttribute.class) ){
						
						MockTodayAttribute a = f.getAnnotation(MockTodayAttribute.class);
						
						Constructor constructor = f.getType().getConstructor(String.class);
						
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						
						f.set(mock,constructor.newInstance(format.format(new Date())));
						
					}
					
				}
				
				listaMock.add(mock);
				
			}
		
		}
		
		return listaMock;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		
		
		
	}
	
};
