//import Clases.*;
import org.hibernate.*;

import Clases.SessionFactoryUtil;

//import org.hibernate.Transaction;
import java.util.*;
public class Funciones_de_grupo {
	
	public static void main(String[] args) {
		SessionFactory session = SessionFactoryUtil.getSessionFactory();
		Session s= session.openSession(); 
        System.out.println("Calculo de  funciones de grupo ");        
        Query cons = s.createQuery("select em.departments.departmentId, count(em.employeeId), coalesce(avg(em.salary),0) from Employees as em group by em.departments.departmentId");
        @SuppressWarnings("unchecked")
		List<Object[]> filas = cons.list(); 
        for (int i = 0; i < filas.size();i++) {
        	Object[] fila = filas.get(i);
        System.out.println("Cod_dept: "+ fila[0]+" "+"Num_emp: "+ fila[1]+" "+"Media sueldo: "+ fila[2]);
        }
        s.close();
        session.close(); 
	}
}
