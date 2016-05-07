package assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.Employee.TestContactDetailsFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.Employee.TestEmployeeFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.Employee.TestGeographicalDetailsFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC.TestCPUFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC.TestChassisFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC.TestGPUFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC.TestHDDFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC.TestMotherboardFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC.TestPSUFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC.TestRAMFactory;

/**
 * Created by CuanL on 14/04/2016. Testing Commits
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestChassisFactory.class,
        TestContactDetailsFactory.class,
        TestCPUFactory.class,
        TestEmployeeFactory.class,
        TestGeographicalDetailsFactory.class,
        TestGPUFactory.class,
        TestHDDFactory.class,
        TestMotherboardFactory.class,
        TestPSUFactory.class,
        TestRAMFactory.class
})
public class TestSuite {
}
