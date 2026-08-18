package com.aot.service;

import com.aot.bin.Employee;

//SRS documentation for the service layer 
public interface IEmployeeService {
	
		String addEmployee(Employee emp);
		
		Employee searchEmployee(Integer eno);
		
		String updateEmployee(Employee emp);
		
		String deleteEmployee(Integer eno);
}
