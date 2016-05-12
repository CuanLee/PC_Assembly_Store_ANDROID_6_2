package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.util.App;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.CPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.CPURepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.CPURepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.CPUService;

/**
 * Created by CuanL on 10/05/2016.
 */
public class CPUServiceImpl extends Service implements CPUService{
    final private CPURepository cpuRepository;

    private static CPUServiceImpl service = null;

    public static CPUServiceImpl getInstance() {
        if (service == null)
            service = new CPUServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public CPUServiceImpl getService() {
            return CPUServiceImpl.this;
        }
    }

    private CPUServiceImpl()
    {
        cpuRepository = new CPURepositoryImpl(App.getAppContext());
    }

    @Override
    public CPU addCPU(CPU cpu) {
        if(duplicateCheck(cpu) == false)
            return cpuRepository.save(cpu);
        else
            return null;
    }

    @Override
    public boolean duplicateCheck(CPU cpu) {
        Set<CPU> allCpu = cpuRepository.findAll();
        boolean duplicate = false;

        for (CPU cpuRecord: allCpu)
        {
            if (cpu.getCode().equalsIgnoreCase(cpuRecord.getCode()) && !cpu.getId().equals(cpuRecord.getId()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public CPU updateCPU(CPU cpu) {
        if(duplicateCheck(cpu) == false)
            return cpuRepository.update(cpu);
        else
            return null;
    }

    @Override
    public CPU getCPU(Long gpuId) {
        return cpuRepository.findById(gpuId);
    }

    @Override
    public Set<CPU> getAll() {
        Set<CPU> cpu;
        cpu = cpuRepository.findAll();
        return cpu;
    }

    @Override
    public CPU deleteCPU(CPU cpu) {
        return cpuRepository.delete(cpu);
    }

    @Override
    public int deleteAllCPU() {
        return cpuRepository.deleteAll();
    }
}
