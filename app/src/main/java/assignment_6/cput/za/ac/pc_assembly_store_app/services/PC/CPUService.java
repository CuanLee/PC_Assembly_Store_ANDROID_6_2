package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.CPU;

/**
 * Created by CuanL on 10/05/2016.
 */
public interface CPUService {
    CPU addCPU(CPU cpu );

    boolean duplicateCheck(CPU cpu);

    CPU updateCPU(CPU cpu);

    CPU getCPU(Long gpuId);

    Set<CPU> getAll();

    CPU deleteCPU(CPU cpu);

    int deleteAllCPU();
}

