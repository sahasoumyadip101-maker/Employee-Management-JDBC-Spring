package com.aot.dao;

import com.aot.bin.Employee;
//SRS documentation for the dao layer 
public interface IEmployeeDao {

		String add(Employee emp);
		Employee search(Integer eno);
		String update(Employee emp);
		String delete(Integer eno);
		
}
