import java.util.Scanner;

import Clases.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class Modificar{
	public static void main(String[] args) {
	    // se obtiene la sesion creada por el singleton
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();  
		Transaction tx = s.beginTransaction();
		System.out.print("Introduce el código de departamento a modificar: ");
	    Scanner n_dep = new Scanner(System.in);
	    Departments dep = new Departments();
	    dep = (Departments) s.load(Departments.class,  (short)n_dep.nextShort());
		System.out.println("Localidad actual del departamento: " + dep.getLocationId());
		System.out.print("Introduce la nueva localidad: ");
	    Scanner loc = new Scanner(System.in);
	    dep.setLocationId((short) loc.nextShort());
        s.update(dep);
        try {
        	System.out.print("Operacion realizada.");
            tx.commit();
            }
            catch (Exception ex) {
            	tx.rollback();
            	System.out.println("Incumple restriccion de BBDD " );  
            }
            finally {
               n_dep.close();
               loc.close();
               s.close();
              session.close();        
            }
	}
}
