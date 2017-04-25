import Clases.*;
import org.hibernate.*;
//import org.hibernate.Transaction;
import java.util.*;
public class Mostrar_empdoses_dept {
	public static void main(String[] args) {
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession(); 
        System.out.print("Introduce el código de departamento: ");
        Scanner n_dep = new Scanner(System.in);
        Short n =  (short)n_dep.nextShort();
        Departments dep = new Departments();
// Si se sabe que el dept existe y no da error se usa load con tratamiento de error, si no se puede usar get
        try {
        	dep = (Departments) s.load(Departments.class,  n);
        	System.out.println("Nombre del dept: "+dep.getDepartmentName());
    	System.out.println("Localidad del dept: "+dep.getLocationId());
    	System.out.print("Estos son sus empleados: ");	 	   
     	Employees emp = new Employees(); 
		System.out.println("Nombre del dept: "+dep.getDepartmentName()); 
	   	System.out.println("Busca resultados usando el metodo list() ");
	   	@SuppressWarnings("unchecked")
		Set<Employees> listaemple = dep.getEmployeeses();  //Se obtienen los empleados en forma de lista
	   	Iterator <Employees> it = listaemple.iterator();
   		if (listaemple.size()==0) {
   			   System.out.println("Este departamento no tiene empleados");
   		}
   		else
   		{
   		   System.out.println("Numero de empleados: "+ listaemple.size());
   		   while (it.hasNext()) {
   				   emp = (Employees) it.next(); 
   				   System.out.println(emp.getLastName()+ ", "+emp.getSalary());
   		   }
   		}
   		// Metodo recomendado por si se devuelven muchas filas, pues la lista no trabaja con iterate
   		System.out.println("Busca resultados usando el metodo iterate() ");
   		Query q = s.createQuery("from Employees e where e.departments.departmentId = :nd");
   		q.setShort("nd",  n );
   		  // Query q = session.createQuery("from Empleados");
   		   Iterator<?> iter = q.iterate();
   		   q.setFetchSize(10);   // para conetarse a la bd en busca de 10 filas más al usar next si ya ha leido 10 
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
