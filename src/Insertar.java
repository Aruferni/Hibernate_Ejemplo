import Clases.*;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class Insertar{
	public static void main(String[] args) {
	    // se obtiene la sesion creada por el singleton
		//Session session = SessionFactoryUtil.getSessionFactory().openSession();
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();
		//SessionFactory s = session.getSessionFactory();
		Transaction tx = s.beginTransaction();
        System.out.println("Insertar un registro en la tabla DEPARTMENTS");
        Scanner n_dep = new Scanner(System.in);
        Departments dep = new Departments();
        dep.setDepartmentId((short) n_dep.nextShort());
        n_dep.close();
        dep.setDepartmentName("RRHH");
        dep.setLocationId(null);
        dep.setEmployees(null);
        s.save(dep);
        System.out.print("Operacion realizada. ");
        try {
        tx.commit();
        }
        catch (org.hibernate.JDBCException ex_sql) {
        	tx.rollback();
        	System.out.println("Incumple restriccion de BBDD " +ex_sql.getMessage());  
        	System.out.println("Error: " +ex_sql.getErrorCode());  
        }
        catch (org.hibernate.HibernateException ex_hib) {
        	tx.rollback();
        	System.out.println("Incumple restriccion de BBDD " +ex_hib.getMessage());  
        }
        catch (Exception ex) {
        	tx.rollback();
        	System.out.println("Incumple restriccion de BBDD " +ex.getMessage());  
        }
        finally {
           s.close();
          session.close();        
        }
	}
}
