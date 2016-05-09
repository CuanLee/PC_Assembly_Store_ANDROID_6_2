package assignment_6.cput.za.ac.pc_assembly_store_app.services.Employee.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.util.App;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.Employee;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.Employee.EmployeeRepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.Employee.Impl.EmployeeRepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.Employee.EmployeeService;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.Impl.RAMServiceImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.RAMService;

/**
 * Created by CuanL on 08/05/2016.
 */
public class EmployeeServiceImpl extends Service implements EmployeeService {
    final private EmployeeRepository employeeRepository;

    private static EmployeeServiceImpl service = null;

    public static EmployeeServiceImpl getInstance() {
        if (service == null)
            service = new EmployeeServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private Employee repo;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public EmployeeServiceImpl getService() {
            return EmployeeServiceImpl.this;
        }
    }

    private EmployeeServiceImpl()
    {
        employeeRepository = new EmployeeRepositoryImpl(App.getAppContext());
    }

    @Override
    public String updatePassword(Employee employee) {
        return null;
    }

    @Override
    public boolean userAuthenticated(Employee employee) {
        Employee employeeSearched = employeeRepository.findById(employee.getID());

        if (employeeSearched.getLoggedIn() == 1)
            return true;
        else
            return false;

    }

    @Override
    public Set<Employee> getAllAuthenticatedUsers() {
        return null;
        /* Set<Employee> employeeHashSet = new HashSet<>();

        Set<Employee> allEmployee = employeeRepository.findAll();

        for(int i=0; i < allEmployee.size(); i++){
            if(allEmployee)
        }*/
    }

    @Override
    public boolean duplicateUsername(Employee employee) {
        return false;
    }
}
