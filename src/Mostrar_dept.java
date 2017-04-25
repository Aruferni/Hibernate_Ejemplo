import Clases.*;
import org.hibernate.*;

import Clases.SessionFactoryUtil;

//import org.hibernate.Transaction;
import java.util.Scanner;
public class Mostrar_dept {
	
	public static void main(String[] args) {
		 // se obtiene la sesion creada por el singleton
		 SessionFactory session = SessionFactoryUtil.getSessionFactory();
		 Session s= session.openSession();  
		
        System.out.print("Introduce el código de departamento: ");
        Scanner n_dep = new Scanner(System.in);
        Departments dep = new Departments();
// Si se sabe que el dept existe y no da error se usa load con tratamiento de error, si no se puede usar get
        try {
        dep = (Departments) s.load(Departments.class,  (short)n_dep.nextShort());
		System.out.println("Nombre del dept: "+dep.getDepartmentName());
    	System.out.println("Localidad del dept: "+dep.getLocationId());
        }
    	catch (ObjectNotFoundException e) { 
  		  System.out.println("\n*** No se ha podido encontrar el departamento ***");
    	} 
        catch (Exception e) { 
        	System.out.println(e.toString());
        	System.out.println(e.getMessage());
        	System.out.println(e.getLocalizedMessage());
		  System.out.println("\n*** Lo siento se ha producido un error ***");
        } 
    	finally { 
    		n_dep.close();
    		s.close();
    		session.close();
    	}       
        /*dep = (Departments) s.get(Departments.class,  (Short) n_dep.nextShort());
        if (dep==null) {
        	System.out.println("El departamento no existe");
        }
        else {
        	System.out.println("Nombre del dept: "+dep.getDepartmentName());
        	System.out.println("Localidad del dept: "+dep.getLocationId());
		} 
        s.close();
        session.close(); */ 
	}
}
