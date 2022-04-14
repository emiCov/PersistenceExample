package com.emi.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public void save(Employee employee) {
        var conn = Database.instance().getConnection();

        try {
            var stmt = conn.prepareStatement("insert into employee values (null, ?, ?, ?, ?)");

            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getAge());
            stmt.setString(3, employee.getAddress());
            stmt.setDouble(4, employee.getSalary());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee getById(int id) {
        var conn = Database.instance().getConnection();

        var employee = new Employee();

        try {
            var stmt = conn.prepareStatement("select * from employee where id = ?");

            stmt.setInt(1, id);

            stmt.executeQuery();

            var rs = stmt.getResultSet();

            if (rs.next()) {
                employee.setId(id);
                employee.setName(rs.getString(2));
                employee.setAge(rs.getInt(3));
                employee.setAddress(rs.getString(4));
                employee.setSalary(rs.getDouble(5));
//                System.out.println(employee);
                return employee;
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> getAll() {
        var conn = Database.instance().getConnection();
        List<Employee> employees = new ArrayList<>();

        try {
            var stmt = conn.createStatement();

            stmt.executeQuery("select * from employee");

            var rs = stmt.getResultSet();

            while (rs.next()) {
                var employee = new Employee();

                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setAge(rs.getInt(3));
                employee.setAddress(rs.getString(4));
                employee.setSalary(rs.getDouble(5));

                employees.add(employee);

            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public boolean updateById(int id, Employee employee) {
        var conn = Database.instance().getConnection();

        if (this.getById(id) != null) {
            try {
                var stmt = conn.prepareStatement("update employee set " +
                        "name = ?, age = ?, address = ?, salary = ? where id = ?");

                stmt.setString(1, employee.getName());
                stmt.setInt(2, employee.getAge());
                stmt.setString(3, employee.getAddress());
                stmt.setDouble(4, employee.getSalary());
                stmt.setInt(5, id);

                stmt.executeUpdate();

                stmt.close();

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteById(int id) {
        var conn = Database.instance().getConnection();

        if (this.getById(id) != null) {
            try {
                var stmt = conn.prepareStatement("delete from employee where id = ?");

                stmt.setInt(1, id);

                stmt.executeUpdate();

                stmt.close();

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public Employee createEmployee(String name, int age, String address, double salary) {
        Employee employee = new Employee();

        employee.setName(name);
        employee.setAge(age);
        employee.setAddress(address);
        employee.setSalary(salary);

        return employee;
    }
}
