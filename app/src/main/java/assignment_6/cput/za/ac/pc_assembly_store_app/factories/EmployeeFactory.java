package assignment_6.cput.za.ac.pc_assembly_store_app.factories;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.ContactDetails;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.Employee;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.GeographicalDetails;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface EmployeeFactory {
    Employee createEmployee(Long id, String username, String password, String firstName, String lastName, String gender, ContactDetails contactDetails, GeographicalDetails geographicalDetails, String idNumber, Integer loggedIn);
}
