import Clases.*;

import org.hibernate.*;

import Clases.SessionFactoryUtil;

import java.util.*;
//import org.hibernate.Transaction;

public class Consultar_varias_tablas {
	
	public static void main(String[] args) {
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession();
		System.out.println("Consulta  datos de 2 tablas ");
          Query cons = s.createQuery("from Departments d, Employees e where d.departmentId=e.departments.departmentId order by e.lastName");
		cons.setFetchSize(10);
        Iterator<?> q =cons.iterate(); //<?> Pues se desconoce el tipo de datos a iterar
        while (q.hasNext()) {
        	Object[] par = (Object[]) q.next();
        	Departments de = (Departments) par[0];
        	Employees em = (Employees) par[1];
        	System.out.println(em.getLastName()+" trabaja en "+de.getDepartmentName());
        }
        s.close();
        session.close(); 
	}
}
