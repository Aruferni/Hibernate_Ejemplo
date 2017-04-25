package Clases;
// Generated 01-mar-2017 18:01:12 by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Departments generated by hbm2java
 */
public class Departments implements java.io.Serializable {

	private short departmentId;
	private Employees employees;
	private String departmentName;
	private Short locationId;
	private Set employeeses = new HashSet(0);

	public Departments() {
	}

	public Departments(short departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public Departments(short departmentId, Employees employees, String departmentName, Short locationId,
			Set employeeses) {
		this.departmentId = departmentId;
		this.employees = employees;
		this.departmentName = departmentName;
		this.locationId = locationId;
		this.employeeses = employeeses;
	}

	public short getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(short departmentId) {
		this.departmentId = departmentId;
	}

	public Employees getEmployees() {
		return this.employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Short getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Short locationId) {
		this.locationId = locationId;
	}

	public Set getEmployeeses() {
		return this.employeeses;
	}

	public void setEmployeeses(Set employeeses) {
		this.employeeses = employeeses;
	}

}
