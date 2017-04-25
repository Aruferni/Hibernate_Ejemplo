
import java.util.Scanner;
import Clases.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Eliminar_Sql{
	public static void main(String[] args) {
	    // se obtiene la sesion creada por el singleton
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();
		Transaction tx = s.beginTransaction();
		System.out.println("Eliminar departamento: ");
		try {
	    Scanner n_dep = new Scanner(System.in);    
		int filasBorr= s.createQuery("delete Departments d where d.departmentId= ?").setInteger(0,n_dep.nextShort()).executeUpdate();
        System.out.println(filasBorr + " filas eliminadas");
        n_dep.close();
        tx.rollback();  //Rollback por pruebas, debería ser commit() 
        System.out.println("operacion  realizada");
		}	
			/*catch (org.hibernate.exception.ConstraintViolationException ex_h) {
				tx.rollback();
        		System.out.println("operacion cancelada");
        		System.out.println("Incumple restriccion de clave foránea" );  
			}*/
			catch (org.hibernate.JDBCException ex_h) {
				tx.rollback();
				System.out.println("operacion cancelada");
				System.out.println(ex_h.getErrorCode());  //Codigo de error de Oracle
				if (ex_h.getErrorCode()==2292) {
					System.out.println("Incumple restriccion de clave foránea " );
				} else
				{ 					
					System.out.println("Incumple restriccion de BBDD "+ ex_h.getMessage() );
				}
			}
		    catch (org.hibernate.HibernateException ex_h) {
            	tx.rollback();
            	ex_h.printStackTrace();
            	System.out.println("operacion anulada");
            	System.out.println("Incumple restriccion de BBDD "+ ex_h.getMessage() );  
            }
			catch (Exception ex) {
				tx.rollback();
				System.out.println("operacion anulada");
				System.out.println("Incumple restriccion de BBDD "+ ex.getMessage() );  
			}
            finally {
               s.close();
              session.close();        
            }
	}
}
