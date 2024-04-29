package hybernate.service.impl;

import hybernate.dao.EmployeeDao;
import hybernate.dao.impl.EmployeeDaoImpl;
import hybernate.entity.Employee;
import hybernate.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    @Override
    public Employee findEmployeeById(Long employeeId) {
        return employeeDao.findEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public String updateEmployee(Long employeeId, Employee newEmployeeName) {
        return employeeDao.updateEmployee(employeeId,newEmployeeName);
    }

    @Override
    public String deleteEmployeeById(Long employeeId) {
        return employeeDao.deleteEmployeeById(employeeId);
    }

    @Override
    public String cleanEmployee() {
        return employeeDao.cleanEmployee();
    }
}
