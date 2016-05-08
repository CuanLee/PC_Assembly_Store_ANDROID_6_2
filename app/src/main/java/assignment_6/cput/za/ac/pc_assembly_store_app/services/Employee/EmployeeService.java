package assignment_6.cput.za.ac.pc_assembly_store_app.services.Employee;

import java.util.List;
import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.Employee;

/**
 * Created by CuanL on 08/05/2016.
 */
public interface EmployeeService {
    String updatePassword(Employee employee);

    boolean userAuthenticated(Employee employee);

    Set <Employee> getAllAuthenticatedUsers();

    boolean duplicateUsername(Employee employee);
}
