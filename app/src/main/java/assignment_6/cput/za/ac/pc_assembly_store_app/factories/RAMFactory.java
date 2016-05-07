package assignment_6.cput.za.ac.pc_assembly_store_app.factories;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface RAMFactory {
    RAM createRAM(Long id, String code, String description, String memorySize, double voltage, String memoryConfiguration,Integer stock, Integer active);
}
