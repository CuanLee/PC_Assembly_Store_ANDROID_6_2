package assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl;


import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.PSUFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class PSUFactoryImpl implements PSUFactory {
    private static PSUFactoryImpl factory = null;

    private PSUFactoryImpl(){

    }

    public static PSUFactoryImpl getInstance(){
        if (factory == null)
            factory = new PSUFactoryImpl();

        return factory;
    }

    public PSU createPSU(Long id, String code, String description, Integer watts, Integer four_pin_molex, Integer sata_connectors, Integer floppy_connectors,Integer stock, Integer active){
        PSU ram = new PSU.Builder()
                .id(id)
                .code(code)
                .description(description)
                .watts(watts)
                .four_pin_molex(four_pin_molex)
                .sata_connectors(sata_connectors)
                .floppy_connectors(floppy_connectors)
                .stock(stock)
                .active(active)
                .build();
        return ram;
    }
}
