import java.util.Scanner;
import Clases.*;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Clases.SessionFactoryUtil;
public class Eliminar{
	public static void main(String[] args) {
	    // se obtiene la sesion creada por el singleton
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();
		Transaction tx = s.beginTransaction();
		System.out.print("Introduce el código de departamento a borrar: ");
	    Scanner n_dep = new Scanner(System.in);
	    Departments dep = new Departments();
	    dep = (Departments) s.load(Departments.class,  (short)n_dep.nextShort());
        s.delete(dep);
        System.out.print("Operacion realizada. ");
        try {
            tx.commit();
            }
            catch (Exception ex) {
            	tx.rollback();
            	System.out.println("Incumple restriccion de BBDD " );  
            }
            finally {
            	n_dep.close();
               s.close();
              session.close();        
            }
	}
}
