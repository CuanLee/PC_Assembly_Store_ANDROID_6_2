package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.util.App;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Motherboard;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.MotherboardRepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.MotherboardRepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.MotherboardService;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.PSUService;

/**
 * Created by CuanL on 10/05/2016.
 */
public class MotherboardServiceImpl extends Service implements MotherboardService{
    final private MotherboardRepository motherboardRepository;

    private static MotherboardServiceImpl service = null;

    public static MotherboardServiceImpl getInstance() {
        if (service == null)
            service = new MotherboardServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public MotherboardServiceImpl getService() {
            return MotherboardServiceImpl.this;
        }
    }

    private MotherboardServiceImpl()
    {
        motherboardRepository = new MotherboardRepositoryImpl(App.getAppContext());
    }

    @Override
    public Motherboard addMotherboard(Motherboard motherboard) {
        if(duplicateCheck(motherboard) == false)
            return motherboardRepository.save(motherboard);
        else
            return null;
    }

    @Override
    public boolean duplicateCheck(Motherboard motherboard) {
        Set<Motherboard> allMotherboard = motherboardRepository.findAll();
        boolean duplicate = false;

        for (Motherboard motherboardRecord: allMotherboard)
        {
            if (motherboard.getCode().equalsIgnoreCase(motherboardRecord.getCode()) && !motherboard.getId().equals(motherboardRecord.getId()))
                duplicate = true;
        }
        return duplicate;

    }

    @Override
    public Motherboard updateMotherboard(Motherboard motherboard) {
        if(duplicateCheck(motherboard) == false)
            return motherboardRepository.update(motherboard);
        else
            return null;
    }

    @Override
    public Motherboard getMotherboard(Long motherboardId) {
        return motherboardRepository.findById(motherboardId);
    }

    @Override
    public Set<Motherboard> getAll() {
        Set<Motherboard> motherboards;
        motherboards = motherboardRepository.findAll();
        return motherboards;
    }

    @Override
    public Motherboard deleteMotherboard(Motherboard motherboard) {
        return motherboardRepository.delete(motherboard);
    }

    @Override
    public int deleteAllMotherboard() {
        return motherboardRepository.deleteAll();
    }
}
