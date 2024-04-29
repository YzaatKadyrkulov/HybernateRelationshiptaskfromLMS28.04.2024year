package hybernate;

import hybernate.entity.*;
import hybernate.service.*;
import hybernate.service.impl.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Address address = new Address("eldyar@ggmail.com");
        License license = new License("12342134","20.12.2020",2023);
        Category category = new Category("Natura Siberica.");
        Employee employee = new Employee("Beknazar","Joldoshbekov",17);
        customer.setFirst_name("Asan");
        customer.setLast_name("Usonov");
        customer.setAge(22);

        Account account = new Account();
        account.setAccountEmail("Asan@gmail.com");

        customer.setAccount(account);



        AccountService accountService = new AccountServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        LicenseService licenseService = new LicenseServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();

//        System.out.println(accountService.saveAccount(account));
//        System.out.println(accountService.findAccountById(1L));
//        System.out.println(accountService.getAllAccounts());
//        System.out.println(accountService.updateAccount(1L, account));
//        System.out.println(accountService.cleanAccount());
//        System.out.println(accountService.deleteAccountById(1L));

//        System.out.println(customerService.saveCustomer(customer));
//        System.out.println(customerService.findCustomerById(1L));
//        System.out.println(customerService.getAllCustomers());
//        System.out.println(customerService.updateCustomer(1L, customer));
//        System.out.println(customerService.cleanCustomer());
//        System.out.println(customerService.deleteCustomerById(1L));

//        System.out.println(addressService.saveAddress(address));
//        System.out.println(addressService.findAddressById(1L));
//        System.out.println(addressService.getAllAddresses());
//        System.out.println(addressService.updateAddress(1L, address));
//        System.out.println(addressService.cleanAddress());
//        System.out.println(addressService.deleteAddressById(1L));


//        System.out.println(employeeService.saveEmployee(employee));
//        System.out.println(employeeService.findEmployeeById(1L));
//        System.out.println(employeeService.getAllEmployees());
//        System.out.println(employeeService.updateEmployee(1L, employee));
//        System.out.println(employeeService.cleanEmployee());
//        System.out.println(employeeService.deleteEmployeeById(1L));

//        System.out.println(licenseService.saveLicense(license));
//        System.out.println(licenseService.findLicenseById(1L));
//        System.out.println(licenseService.getAllLicenses());
//        System.out.println(licenseService.updateLicense(1L, license));
//        System.out.println(licenseService.cleanLicense());
//        System.out.println(licenseService.deleteLicenseById(1L));
//
//        System.out.println(categoryService.saveCategory(category));
//        System.out.println(categoryService.findCategoryById(1L));
//        System.out.println(categoryService.getAllCategories());
//        System.out.println(categoryService.updateCategory(1L, category));
//        System.out.println(categoryService.cleanCategory());
//        System.out.println(categoryService.deleteCategoryById(1L));



    }
}
