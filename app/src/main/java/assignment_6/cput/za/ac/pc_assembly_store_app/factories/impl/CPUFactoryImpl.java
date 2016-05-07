package assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl;


import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.CPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.CPUFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class CPUFactoryImpl implements CPUFactory {
    private static CPUFactoryImpl factory = null;

    private CPUFactoryImpl(){

    }

    public static CPUFactoryImpl getInstance(){
        if (factory == null)
            factory = new CPUFactoryImpl();

        return factory;
    }

    public CPU createCPU(Long id, String code, String description, Integer socket, String processorBrand, double speed_Ghz, double cache_MB, Integer cores,Integer stock, Integer active){
        CPU cpu = new CPU.Builder()
                .id(id)
                .code(code)
                .description(description)
                .socket(socket)
                .processorBrand(processorBrand)
                .speed_Ghz(speed_Ghz)
                .cache_MB(cache_MB)
                .cores(cores)
                .stock(stock)
                .active(active)
                .build();
        return cpu;
    }
}
