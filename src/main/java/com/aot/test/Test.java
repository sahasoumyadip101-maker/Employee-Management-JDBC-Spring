package com.aot.test;

import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aot.bin.Employee;
import com.aot.config.AppConfig;
import com.aot.service.IEmployeeService;

public class Test {

    public static void main(String[] args) {

        try (
            Scanner sc = new Scanner(System.in);

            AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(AppConfig.class)
        ) {

            IEmployeeService empService =
                    context.getBean(IEmployeeService.class);

            while (true) {

                System.out.println();
                System.out.println("========== EMPLOYEE MANAGEMENT SYSTEM ==========");
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice : ");

                int choice = sc.nextInt();
                System.out.println();

                switch (choice) {

                    case 1:

                        System.out.println(
                                "========== Employee Add Module =========="
                        );

                        System.out.print("Employee Number : ");
                        int eno = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Employee Name : ");
                        String ename = sc.nextLine();

                        System.out.print("Employee Salary : ");
                        float esal = sc.nextFloat();
                        sc.nextLine();

                        System.out.print("Employee Address : ");
                        String eaddr = sc.nextLine();

                        Employee emp = new Employee();

                        emp.setEno(eno);
                        emp.setEname(ename);
                        emp.setEsal(esal);
                        emp.setEaddr(eaddr);

                        String status =
                                empService.addEmployee(emp);

                        if (status.equalsIgnoreCase("success")) {

                            System.out.println(
                                    "Status : Employee Inserted Successfully"
                            );

                        } else if (status.equalsIgnoreCase("existed")) {

                            System.out.println(
                                    "Status : Employee Already Exists"
                            );

                        } else {

                            System.out.println(
                                    "Status : Employee Insertion Failed"
                            );
                        }

                        break;


                    case 2:

                        System.out.println(
                                "========== Employee Search Module =========="
                        );

                        System.out.print("Employee Number : ");
                        eno = sc.nextInt();

                        emp =
                                empService.searchEmployee(eno);

                        if (emp == null) {

                            System.out.println(
                                    "Status : Employee Not Found"
                            );

                        } else {

                            System.out.println(
                                    "Status : Employee Found"
                            );

                            System.out.println(
                                    "Employee Number : " +
                                    emp.getEno()
                            );

                            System.out.println(
                                    "Employee Name : " +
                                    emp.getEname()
                            );

                            System.out.println(
                                    "Employee Salary : " +
                                    emp.getEsal()
                            );

                            System.out.println(
                                    "Employee Address : " +
                                    emp.getEaddr()
                            );
                        }

                        break;


                    case 3:

                        System.out.println(
                                "========== Employee Update Module =========="
                        );

                        System.out.print("Employee Number : ");
                        eno = sc.nextInt();
                        sc.nextLine();

                        emp =
                                empService.searchEmployee(eno);

                        if (emp == null) {

                            System.out.println(
                                    "Status : Employee Not Found"
                            );

                            break;
                        }

                        Employee updatedEmp =
                                new Employee();

                        updatedEmp.setEno(eno);

                        System.out.print(
                                "Employee Name [OLD: " +
                                emp.getEname() +
                                "] New: "
                        );

                        String value = sc.nextLine();

                        if (value.isBlank()) {
                            updatedEmp.setEname(emp.getEname());
                        } else {
                            updatedEmp.setEname(value);
                        }


                        System.out.print(
                                "Employee Salary [OLD: " +
                                emp.getEsal() +
                                "] New: "
                        );

                        value = sc.nextLine();

                        if (value.isBlank()) {
                            updatedEmp.setEsal(emp.getEsal());
                        } else {
                            updatedEmp.setEsal(
                                    Float.parseFloat(value)
                            );
                        }


                        System.out.print(
                                "Employee Address [OLD: " +
                                emp.getEaddr() +
                                "] New: "
                        );

                        value = sc.nextLine();

                        if (value.isBlank()) {
                            updatedEmp.setEaddr(emp.getEaddr());
                        } else {
                            updatedEmp.setEaddr(value);
                        }


                        status =
                                empService.updateEmployee(
                                        updatedEmp
                                );

                        if (status.equalsIgnoreCase("success")) {

                            System.out.println(
                                    "Status : Updated Successfully"
                            );

                        } else {

                            System.out.println(
                                    "Status : Updation Failed"
                            );
                        }

                        break;


                    case 4:

                        System.out.println(
                                "========== Employee Delete Module =========="
                        );

                        System.out.print("Employee Number : ");
                        eno = sc.nextInt();

                        status =
                                empService.deleteEmployee(eno);

                        if (status.equalsIgnoreCase("success")) {

                            System.out.println(
                                    "Status : Employee " +
                                    eno +
                                    " Deleted Successfully"
                            );

                        } else {

                            System.out.println(
                                    "Status : Deletion Failed"
                            );
                        }

                        break;


                    case 5:

                        System.out.println(
                                "Application closed."
                        );

                        return;


                    default:

                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Application Error : " +
                    e.getMessage()
            );
        }
    }
}