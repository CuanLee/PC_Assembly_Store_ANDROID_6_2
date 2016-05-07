package assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl;


import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.GPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.GPUFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class GPUFactoryImpl implements GPUFactory {
    private static GPUFactoryImpl factory = null;

    private GPUFactoryImpl(){

    }

    public static GPUFactoryImpl getInstance(){
        if (factory == null)
            factory = new GPUFactoryImpl();

        return factory;
    }

    public GPU createGPU(Long id, String code, String description, Integer bitMemory, Integer memorySize_GB, String memoryType, double memoryClock_MHz, String cardBus,Integer stock, Integer active){
        GPU gpu = new GPU.Builder()
                .id(id)
                .code(code)
                .description(description)
                .bitMemory(bitMemory)
                .memorySize_GB(memorySize_GB)
                .memoryType(memoryType)
                .memoryClock_MHz(memoryClock_MHz)
                .cardBus(cardBus)
                .stock(stock)
                .active(active)
                .build();
        return gpu;
    }
}
