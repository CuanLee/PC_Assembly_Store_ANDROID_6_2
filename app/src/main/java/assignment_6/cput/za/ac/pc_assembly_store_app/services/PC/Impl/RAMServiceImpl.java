package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.Impl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;
import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.database.GlobalContext;
import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.util.App;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.RAMRepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.RAMRepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.RAMService;

/**
 * Created by CuanL on 08/05/2016.
 */
public class RAMServiceImpl extends Service implements RAMService{
    final private RAMRepository ramRepository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static RAMServiceImpl service = null;

    public static RAMServiceImpl getInstance() {
        if (service == null)
            service = new RAMServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public RAMServiceImpl getService() {
            return RAMServiceImpl.this;
        }
    }

    private RAMServiceImpl()
    {
        ramRepository = new RAMRepositoryImpl(GlobalContext.getAppContext());
    }

    @Override
    public RAM addRam(RAM ram) {
        return ramRepository.save(ram);
    }

    @Override
    public RAM updateRam(RAM ram) {
        return ramRepository.update(ram);
    }

    @Override
    public RAM getRam(Long ramId) {
        return ramRepository.findById(ramId);
    }

    @Override
    public Set<RAM> getAll() {
        Set<RAM> ram;
        ram = ramRepository.findAll();
        return ram;
    }

    @Override
    public RAM deleteRam(RAM ram) {
        return ramRepository.delete(ram);
    }

    @Override
    public int deleteAllRam() {
        return ramRepository.deleteAll();
    }

    @Override
    public boolean duplicateCheck(RAM ram) {
        Set<RAM> allRam = ramRepository.findAll();
        boolean duplicate = false;

        for (RAM ramRecord: allRam)
        {
            if (ram.getCode().equalsIgnoreCase(ramRecord.getCode()) && !ram.getId().equals(ramRecord.getId()))
                duplicate = true;
        }
        return duplicate;

    }
}
