package assignment_6.cput.za.ac.pc_assembly_store_app.factories;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.CPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.GPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.HDD;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Motherboard;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;

import java.util.List;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface MotherboardFactory {
    Motherboard createMotherboard(Long id, String code, String description, String chipset, Integer sataPorts, Integer usb2, Integer usb3, String formFactor,Integer stock, Integer active);
}
