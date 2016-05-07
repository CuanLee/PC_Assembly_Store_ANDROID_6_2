package assignment_6.cput.za.ac.pc_assembly_store_app.factories;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Chassis;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Motherboard;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface ChassisFactory {
    Chassis createComputer(Long id, String code, String description, Integer active, Integer hddBays, Integer caseFans,Integer stock, String formFactor);
}
