# Employee Management System — JDBC + Spring Core

A Java-based Employee Management System developed using **Core Java, JDBC, Spring Core, Maven, and Oracle Database**.

The project demonstrates database connectivity, CRUD operations, layered architecture, Spring IoC, Dependency Injection, and JDBC APIs such as `Connection`, `PreparedStatement`, and `ResultSet`.

---

## Technologies Used

- Java 17
- Spring Core
- JDBC
- Oracle Database 21c XE
- Maven
- Spring Tools for Eclipse (STS)

---

## Features

- Add employee
- Search employee
- Update employee
- Delete employee
- Display employee information
- Oracle database connectivity
- JDBC-based database operations
- Spring IoC and Dependency Injection
- Layered architecture

---

## Architecture

The application follows a layered architecture where each layer has a specific responsibility.

```text
                    Test / Application
                           |
                           v
                    Service Layer
                           |
                           v
                      DAO Layer
                           |
                           v
                  Connection Factory
                           |
                           v
                         JDBC
                           |
                           v
                  Oracle Database
```

### Detailed Flow

```text
                         Spring Container
                                |
                        @ComponentScan
                                |
              ┌─────────────────┴─────────────────┐
              |                                   |
              v                                   v
   EmployeeServiceImpl                    EmployeeDaoImpl
        @Service                            @Repository
              |                                   |
              | Constructor DI                    |
              | @Autowired                        |
              v                                   |
       IEmployeeDao  ─────────────────────────────┘
              |
              v
      ConnectionFactory
              |
              v
       JDBC Connection
              |
              v
       PreparedStatement
              |
              v
       Oracle Database
```

---

## Project Structure

```text
src/main/java
└── com.aot
    │
    ├── bin
    │   └── Employee.java
    │
    ├── config
    │   └── AppConfig.java
    │
    ├── dao
    │   ├── IEmployeeDao.java
    │   └── EmployeeDaoImpl.java
    │
    ├── factory
    │   └── ConnectionFactory.java
    │
    ├── service
    │   ├── IEmployeeService.java
    │   └── EmployeeServiceImpl.java
    │
    └── test
        └── Test.java
```

### Package Responsibilities

**`bin`**

Contains the `Employee` class representing employee data.

**`config`**

Contains the Spring configuration class.

**`dao`**

Contains the Data Access Object layer responsible for database operations.

- `IEmployeeDao`
- `EmployeeDaoImpl`

**`factory`**

Contains `ConnectionFactory`, which provides JDBC database connections.

**`service`**

Contains the business/service layer.

- `IEmployeeService`
- `EmployeeServiceImpl`

**`test`**

Contains the `Test` class used to run and test the application.

---

# Spring Core Implementation

The upgraded version uses **Spring Core** for object management and Dependency Injection.

## Spring Configuration

The application uses the following configuration:

```java
@Configuration
@ComponentScan("com.aot")
public class AppConfig {
}
```

### `@Configuration`

`@Configuration` indicates that the class is used as a source of Spring configuration.

### `@ComponentScan`

```java
@ComponentScan("com.aot")
```

instructs Spring to scan the `com.aot` package and its subpackages for Spring-managed components.

This allows Spring to discover classes annotated with components such as:

- `@Service`
- `@Repository`

---

## Service Layer

`EmployeeServiceImpl` is annotated with `@Service`.

```java
@Service
public class EmployeeServiceImpl implements IEmployeeService
```

The service receives its DAO dependency through constructor injection:

```java
@Autowired
public EmployeeServiceImpl(IEmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
}
```

The service depends on the `IEmployeeDao` interface rather than directly creating `EmployeeDaoImpl`.

This reduces direct coupling between the service and the concrete DAO implementation.

---

## DAO Layer

`EmployeeDaoImpl` is annotated with `@Repository`.

```java
@Repository
public class EmployeeDaoImpl implements IEmployeeDao
```

The DAO layer is responsible for communicating with the Oracle database through JDBC and performing database operations.

---

## IoC — Inversion of Control

In a traditional approach, classes may manually create their dependencies:

```text
EmployeeServiceImpl
        |
        v
new EmployeeDaoImpl()
```

With Spring, object creation and dependency management are handled by the Spring container:

```text
                 Spring Container
                        |
                 Creates/manages
                      Beans
                        |
              ┌─────────┴─────────┐
              v                   v
       Service Bean          DAO Bean
              |
              | Dependency
              v
        IEmployeeDao
```

Therefore, control over object creation and dependency management is transferred from the application code to the Spring container.

This is the fundamental idea behind **Inversion of Control (IoC)**.

---

## Dependency Injection

Dependency Injection is the process by which Spring supplies an object's required dependencies.

In this project:

```java
private final IEmployeeDao employeeDao;

@Autowired
public EmployeeServiceImpl(IEmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
}
```

`EmployeeServiceImpl` requires an `IEmployeeDao`.

Instead of creating the DAO manually, the Spring container supplies the dependency.

This is **constructor-based Dependency Injection**.

---

# JDBC Implementation

The DAO layer communicates with Oracle Database using JDBC.

The basic flow is:

```text
EmployeeServiceImpl
        |
        v
IEmployeeDao
        |
        v
EmployeeDaoImpl
        |
        v
ConnectionFactory
        |
        v
Connection
        |
        v
PreparedStatement
        |
        v
SQL Query
        |
        v
Oracle Database
        |
        v
ResultSet
```

---

## ConnectionFactory

`ConnectionFactory` is responsible for creating JDBC database connections.

The DAO obtains a connection through:

```java
ConnectionFactory.getConnection();
```

This separates the database connection creation logic from the DAO operations.

---

# PreparedStatement

`PreparedStatement` is used to execute parameterized SQL statements.

For example:

```java
PreparedStatement ps =
    con.prepareStatement(INSERT_QUERY);
```

Values are supplied using methods such as:

```java
ps.setInt(...);
ps.setString(...);
ps.setDouble(...);
```

Using parameterized statements separates SQL structure from input values and helps protect against SQL injection.

---

# ResultSet

For `SELECT` operations, JDBC returns a `ResultSet`.

The cursor can be moved through the returned rows using:

```java
rs.next();
```

Column values can then be retrieved using methods such as:

```java
rs.getInt(...);
rs.getString(...);
rs.getDouble(...);
```

The retrieved database values can then be used to populate `Employee` objects.

---

# Try-With-Resources

The DAO uses Java's **try-with-resources** mechanism for JDBC resources.

For example:

```java
try (Connection con = ConnectionFactory.getConnection()) {
    // database operation
}
```

Try-with-resources automatically closes resources after the try block finishes.

This is particularly useful for JDBC resources such as:

- `Connection`
- `PreparedStatement`
- `ResultSet`

It also ensures resources are closed when an exception occurs.

---

# CRUD Operations

The DAO contains SQL operations for employee management.

```text
Create
   |
   v
INSERT

Read
   |
   v
SELECT

Update
   |
   v
UPDATE

Delete
   |
   v
DELETE
```

The service layer coordinates the operations while the DAO layer handles database communication.

---

# SQL Queries

The DAO maintains separate SQL statements for the major database operations:

```text
SELECT_QUERY
INSERT_QUERY
UPDATE_QUERY
DELETE_QUERY
```

This keeps the SQL statements organized within the DAO implementation.

---

# Database Schema

## EMPLOYEE

| Column | Data Type | Constraint |
|---|---|---|
| ENO | NUMBER | NOT NULL |
| ENAME | VARCHAR2(50) | — |
| ESAL | NUMBER(6,2) | — |
| EADDR | VARCHAR2(100) | — |

The `EMPLOYEE` table stores the employee information used by the application.

---

# Maven

The project uses **Maven** for project configuration and dependency management.

The project's dependencies and build configuration are defined in:

```text
pom.xml
```

---

# How to Run

1. Install Java 17 or a compatible JDK.
2. Install and configure Oracle Database.
3. Configure the local database credentials.
4. Import the project as a Maven project into STS/Eclipse.
5. Allow Maven to download the required dependencies.
6. Verify the Oracle database connection.
7. Run the application through the `Test` class.

> **Note:** Database credentials are environment-specific and should be configured locally. Real credentials should not be committed to the repository.

---

# Key Concepts Demonstrated

This project demonstrates practical usage of:

- Core Java
- Object-Oriented Programming
- Interfaces
- Abstraction
- Encapsulation
- Exception Handling
- JDBC
- SQL
- CRUD operations
- PreparedStatement
- ResultSet
- Try-with-resources
- Maven
- Spring Core
- Spring Beans
- IoC
- Dependency Injection
- Constructor Injection
- `@Configuration`
- `@ComponentScan`
- `@Service`
- `@Repository`
- DAO Pattern
- Service Layer
- Layered Architecture

---

# Purpose

This project was developed as a practical demonstration of Java backend fundamentals, JDBC database programming, and the use of Spring Core for IoC and Dependency Injection.

It represents the transition from manually managed object dependencies toward a Spring-managed application architecture.
