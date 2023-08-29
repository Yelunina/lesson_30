package ait.employee.test;

import ait.employee.dao.Company;
import ait.employee.dao.CompanyImpl;
import ait.employee.model.Employee;
import ait.employee.model.Manager;
import ait.employee.model.SalesManager;
import ait.employee.model.WageEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Company company;
    Employee[] employees;

    @BeforeEach
    void setUp() {
        company = new CompanyImpl(5);
        employees = new Employee[4];
        employees[0] = new Manager(1000, "John", "Smith", 160, 3000, 5);
        employees[1] = new WageEmployee(2000, "Mary", "Smith", 160, 15);
        employees[2] = new SalesManager(3000, "Peter", "Jackson", 160, 20000, 0.1);
        employees[3] = new SalesManager(4000, "Rabindranate", "Anand", 80, 30000, 0.1);
        for (int i = 0; i < employees.length; i++) {
            company.addEmployee(employees[i]);
        }

    }

    @Test
    void addEmployee() {// добавление объекта Employee

        assertFalse(company.addEmployee(null));
        assertFalse(company.addEmployee(employees[1]));
        Employee employee = new SalesManager(5000, "Rabindranate", "Anand", 80, 30000, 0.1);
        assertTrue(company.addEmployee(employee));
        assertEquals(5, company.quantity());
        employee = new SalesManager(6000, "Rabindranate", "Anand", 80, 30000, 0.1);
        assertFalse(company.addEmployee(employee));
    }

    @Test
    void removeEmployee() {// удаляем объект типа Employee, находим его по id
        Employee employee = company.removeEmployee(3000);
        assertEquals(employees[2], employee);
        assertEquals(3, company.quantity());
        assertEquals(null, company.removeEmployee(3000));

    }

    @Test
    void findEmployee() { //находим объект типа Employee, находим его по id
        Employee employee = company.findEmployee(2000);
        assertEquals(employees[1], employee);
        employee = company.findEmployee(5000);
        assertNull(employee);

    }

    @Test
    void quantity() {// считаем кол-во объектов
       assertEquals(4,company.quantity());

    }

    @Test
    void totalSalary() {// расчет зарплаты
    assertEquals(11200.0,company.totalSalary(),0.01);
    }

    @Test
    void avgSalary() {// расчет  средней зарплаты
        assertEquals(11200.0/4,company.avgSalary(),0.01);
    }

    @Test
    void totalSales() {// расчет обьема продаж
        assertEquals(50_000.0,company.totalSales(),0.01);
    }


    @Test
    void printEmployees() {
        company.printEmployees();
    }

    @Test
    void findEmployeesHoursGreaterThan(){
        Employee[] actual = company.findEmployeesHoursGreaterThan(100);
        Employee[] expected = {employees[0], employees[1], employees[2]};
        assertArrayEquals(expected,actual);
        }
        @Test
    void findEmployeeSalaryRange(){
        Employee[] actual = company.findEmployeeSalaryRange(2000,2500);
        Employee[] expected = {employees[1],employees[2]};
        assertArrayEquals(expected,actual);
        }
    }
