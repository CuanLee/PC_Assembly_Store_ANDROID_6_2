package assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl;



import java.util.List;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.CPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.GPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.HDD;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Motherboard;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.MotherboardFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class MotherboardFactoryImpl implements MotherboardFactory {
    private static MotherboardFactoryImpl factory = null;

    private MotherboardFactoryImpl(){

    }

    public static MotherboardFactoryImpl getInstance(){
        if (factory == null)
            factory = new MotherboardFactoryImpl();

        return factory;
    }

    public Motherboard createMotherboard(Long id, String code, String description, String chipset, Integer sataPorts, Integer usb2, Integer usb3, String formFactor,Integer stock, Integer active){
        Motherboard motherboard = new Motherboard.Builder()
                .id(id)
                .code(code)
                .description(description)
                .chipset(chipset)
                .sataPorts(sataPorts)
                .usb2(usb2)
                .usb3(usb3)
                .formFactor(formFactor)
                .stock(stock)
                .active(active)
                .build();
        return motherboard;
    }
}
