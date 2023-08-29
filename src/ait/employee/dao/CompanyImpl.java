package ait.employee.dao;

import ait.employee.model.Employee;
import ait.employee.model.SalesManager;

import java.util.function.Predicate;

public class CompanyImpl implements Company {
    private Employee[] employees;
    private int size;

    public CompanyImpl(int capacity) {
        employees = new Employee[capacity];
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null || employees.length == size || findEmployee(employee.getId()) != null) {
            return false;
        }
        employees[size++] = employee;
        return true;
    }

    @Override
    public Employee removeEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == id) {
                Employee victim = employees[i];
                employees[i] = employees[--size];
                employees[size] = null;
                return victim;
            }
        }
        return null;
    }

    @Override
    public Employee findEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        return null;
    }

    @Override
    public int quantity() {// считаем кол-во объектов
        // ожидаем правильные значения при изменении кол-ва объектов
        return size;
    }

    @Override
    public double totalSalary() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += employees[i].calcSalary();
        }
        return sum;
    }


    @Override
    public double totalSales() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i] instanceof SalesManager) {
                SalesManager sm = (SalesManager) employees[i];
                sum += sm.getSalesValue();

            }
        }
        return sum;
    }


    @Override
    public void printEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);

        }

    }

    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        Predicate<Employee> predicate = e -> e.getHours() > hours;

        return findEmployeesByPredicate(predicate);
    }

    @Override
    public Employee[] findEmployeeSalaryRange(int minSalary, int maxSalary) {
        Predicate<Employee> predicate = new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.calcSalary() >= minSalary && employee.calcSalary() < maxSalary;
            }
        };
        return findEmployeesByPredicate(predicate);
    }

    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(employees[i])) {
                count++;
            }
        }
        Employee[] res = new Employee[count];
        for (int i = 0, j = 0; j < res.length; i++) {
            if (predicate.test(employees[i])) {
                res[j] = employees[i];
                j++;
            }
        }
        return res;


    }
}













