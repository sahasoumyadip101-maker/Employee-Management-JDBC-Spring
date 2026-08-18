package com.aot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aot.bin.Employee;
import com.aot.dao.IEmployeeDao;

@Service
public class EmployeeServiceImpl
        implements IEmployeeService {

    private final IEmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @Override
    public String addEmployee(Employee emp) {
        return employeeDao.add(emp);
    }


    @Override
    public Employee searchEmployee(Integer eno) {
        return employeeDao.search(eno);
    }


    @Override
    public String updateEmployee(Employee emp) {
        return employeeDao.update(emp);
    }


    @Override
    public String deleteEmployee(Integer eno) {
        return employeeDao.delete(eno);
    }
}