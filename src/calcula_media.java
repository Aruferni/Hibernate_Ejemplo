
import org.hibernate.*;

import Clases.SessionFactoryUtil;
//import org.hibernate.Transaction;
//import java.util.*;
public class calcula_media {
	
	public static void main(String[] args) {
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();
        System.out.println("Calcula la media de salarios de los empleados ");
        Query cons = s.createQuery("select avg(em.salary) from Employees as em");
        Double media = (Double) cons.uniqueResult();  // Se usa cuando se tiene la certeza que se devuelve una fila
     // si intervienen varias columnas, recogerlo en un objeto de ese tipo, creado por nosotros en paquete primero p.e
     // o creando un Object[] en tiempo de ejecución   
        System.out.println("El promedio de salarios es  "+ media);
        s.close();
        session.close(); 
	}
}
