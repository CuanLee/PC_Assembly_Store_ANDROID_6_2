package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.GPU;

/**
 * Created by CuanL on 10/05/2016.
 */
public interface GPUService {
    GPU addGPU(GPU gpu);

    boolean duplicateCheck(GPU gpu);

    GPU updateGPU(GPU gpu);

    GPU getGPU(Long gpuId);

    Set<GPU> getAll();

    GPU deleteGPU(GPU gpu);

    int deleteAllGPU();
}
