package hybernate.service;

import hybernate.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Employee findEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    String updateEmployee(Long employeeId, Employee newEmployeeName);

    String deleteEmployeeById(Long employeeId);

    String cleanEmployee();
}

