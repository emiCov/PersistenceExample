package com.emi.model;

public interface EmployeeDao extends Dao<Employee>{
    Employee createEmployee(String name, int age, String address, double salary);
}
