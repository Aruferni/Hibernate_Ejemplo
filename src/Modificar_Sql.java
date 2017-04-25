
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Clases.SessionFactoryUtil;
public class Modificar_Sql{
	public static void main(String[] args) {
	    // se obtiene la sesion creada por el singleton
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();    
		Transaction tx = s.beginTransaction();
		System.out.println("Aumentar salario 10%: ");
		try {
		int filasModif= s.createQuery("update Employees e set e.salary = e.salary *1.10").executeUpdate();
        System.out.print("Operacion realizada. "+ filasModif + " filas cambiadas");
        tx.rollback();       
		}
            catch (Exception ex) {
            	tx.rollback();
            	System.out.println("Incumple restriccion de BBDD " );  
            }
            finally {
               s.close();
              session.close();        
            }
        
	}
}
