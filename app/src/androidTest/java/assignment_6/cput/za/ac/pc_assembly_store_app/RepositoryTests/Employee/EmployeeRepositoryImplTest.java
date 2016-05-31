package assignment_6.cput.za.ac.pc_assembly_store_app.RepositoryTests.Employee;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.ContactDetails;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.Employee;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.GeographicalDetails;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.Employee.EmployeeRepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.Employee.Impl.EmployeeRepositoryImpl;

/**
 * Created by CuanL on 07/05/2016.
 */
public class EmployeeRepositoryImplTest extends AndroidTestCase {
    private static final String TAG="Employee TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        EmployeeRepository repo = new EmployeeRepositoryImpl(this.getContext());

        GeographicalDetails geographicalDetails = new GeographicalDetails.Builder()
                .country("SA")
                .province("WC")
                .city("Cape Town")
                .suburb("Brackenfell")
                .street("Long Street")
                .houseNumber(55)
                .build();

        ContactDetails contactDetails = new ContactDetails.Builder()
                .telephone("0212101022")
                .email("cuan@g.com")
                .build();

        Employee employee = new Employee.Builder()
                .username("cuanl26")
                .password("1234567")
                .firstName("Cuan")
                .lastName("Lee")
                .gender("Male")
                .contactDetails(contactDetails)
                .geographicalDetails(geographicalDetails)
                .idNumber("6546654564654")
                .loggedIn(1)
                .build();

        Employee inserttedChassis = repo.save(employee);
        id = inserttedChassis.getID();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis.getGeographicalDetails());
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis.getContactDetails());

        //READ ALL
        Set<Employee> allEmployee = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allEmployee.size()>0);

        //READ ENTITY
        Employee entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        Employee updateEntity = new Employee.Builder()
                .copy(entity)
                .username("cuanlee_thatsME")
                .build();
        repo.update(updateEntity);
        Employee newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","cuanlee_thatsME",newEntity.getUsername());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        //Employee deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
