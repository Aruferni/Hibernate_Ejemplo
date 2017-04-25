import Clases.*;

import org.hibernate.*;
//import org.hibernate.Transaction;
import java.util.*;
public class Mostrar_emp_dept {
	public static void main(String[] args) {
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();
        System.out.print("Introduce el código de departamento: ");
        Scanner n_dep = new Scanner(System.in);
        Short n =  (short)n_dep.nextShort();
        Departments dep = new Departments();
// Si se sabe con certeza que el dept existe y no da error se usa load con tratamiento de exception, pues busca por Id (puntero)
// si no se sabe con certeza,  se puede usar get() que en caso de no existir se puede comparar con null
        try {
        dep = s.load(Departments.class,  n);
		System.out.println("Nombre del dept: "+dep.getDepartmentName());
    	System.out.println("Localidad del dept: "+dep.getLocationId());
    	System.out.print("Estos son sus empleados: ");	
    	Employees emp = new Employees();
    	Query q = s.createQuery("from Employees e where e.departments.departmentId = :nd");
		q.setShort("nd",  n );
		System.out.println("Nombre del dept: "+dep.getDepartmentName()); 
// Método list(), recomendado si se devuelven pocas filas pues recupera de golpe todos los resultados de la consulta
// Si devuelve muchas filas ha de haber memoria suficiente y el acceso a la BBDD tardará más		
    	System.out.println("Busca resultados usando el metodo list() ");
    	@SuppressWarnings("unchecked")
		List<Employees> lista = q.list();
   	  	Iterator <Employees> it = lista.iterator(); 	  
   		if (lista.size()==0) {
   		    System.out.println("Este departamento no tiene empleados");
   		}
   		else
   		{
   		    System.out.println("Numero de empleados: "+ lista.size());
   		    while (it.hasNext()) {
   				   emp = it.next(); 
   				   System.out.println(emp.getLastName()+ ", "+emp.getSalary());
   			     }
   		    }
// Método iterate() recomendado si se devuelven muchas filas, pues ejecuta la consulta obteniendo solo los ids de entidades
// setFicheSize fija los resultados (Ids) a obtener al hacer Iterator.next. Esto implica más llamadas a la BBDD que list()
   		   System.out.println("Busca resultados usando el metodo iterate() ");
   		   Iterator<?> iter = q.iterate();
   		   q.setFetchSize(10);   // para conectarse a la bd en busca de 10 filas más al usar next si ya ha leido 10
   		   while (iter.hasNext()) {
			   emp = (Employees) iter.next(); 
			   System.out.println(emp.getLastName()+ ", "+emp.getSalary());
		   }
   		   
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
       
	}
}
