package com.aot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.aot.bin.Employee;
import com.aot.factory.ConnectionFactory;

//DAO layer implementation


@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
	
	public static final String SELECT_QUERY ="SELECT ENO,ENAME,ESAL,EADDR FROM EMPLOYEE WHERE ENO=?";
	public static final String INSERT_QUERY ="INSERT INTO EMPLOYEE(ENO,ENAME,ESAL,EADDR) VALUES(?,?,?,?)";
	public static final String UPDATE_QUERY ="UPDATE EMPLOYEE SET ENAME=?,ESAL=?,EADDR=? WHERE ENO=?";
	public static final String DELETE_QUERY ="DELETE FROM EMPLOYEE WHERE ENO=?";

	@Override
	public String add(Employee emp) {
		//every time newly we are opening the connection for each method unlike prev
		//Implements AutoClosable interface 
		try(Connection con=ConnectionFactory.getConnection();
				PreparedStatement selectpst=con.prepareStatement(SELECT_QUERY)
		){
			selectpst.setInt(1, emp.getEno());
			
			try(ResultSet rs=selectpst.executeQuery()){
				if(rs.next()) {
					return "existed";
				}
			}
			
			
			try(PreparedStatement insertpst=con.prepareStatement(INSERT_QUERY)){
				insertpst.setInt(1, emp.getEno());
				insertpst.setString(2,emp.getEname());
				insertpst.setFloat(3,emp.getEsal());
				insertpst.setString(4, emp.getEaddr());
				
				int count=insertpst.executeUpdate();
				
				return count>0?"success":"failure";
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return "failure";
		}
	}

	
	@Override
    public Employee search(Integer eno) {

        try (
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement pst =
                    con.prepareStatement(SELECT_QUERY)
        ) {

            pst.setInt(1, eno);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    Employee emp = new Employee();

                    emp.setEno(rs.getInt("ENO"));
                    emp.setEname(rs.getString("ENAME"));
                    emp.setEsal(rs.getFloat("ESAL"));
                    emp.setEaddr(rs.getString("EADDR"));

                    return emp;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    @Override
    public String update(Employee emp) {

        try (
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement pst =
                    con.prepareStatement(UPDATE_QUERY)
        ) {

            pst.setString(1, emp.getEname());
            pst.setFloat(2, emp.getEsal());
            pst.setString(3, emp.getEaddr());
            pst.setInt(4, emp.getEno());

            int count = pst.executeUpdate();

            return count > 0 ? "success" : "failure";

        } catch (SQLException e) {

            e.printStackTrace();
            return "failure";
        }
    }


    @Override
    public String delete(Integer eno) {

        try (
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement pst =
                    con.prepareStatement(DELETE_QUERY)
        ) {

            pst.setInt(1, eno);

            int count = pst.executeUpdate();

            return count > 0 ? "success" : "failure";

        } catch (SQLException e) {

            e.printStackTrace();
            return "failure";
        }
    }
}