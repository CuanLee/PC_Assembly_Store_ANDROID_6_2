package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.util.App;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Chassis;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.ChassisRepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.ChassisRepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.ChassisService;

/**
 * Created by CuanL on 10/05/2016.
 */
public class ChassisServiceImpl extends Service implements ChassisService{
    final private ChassisRepository chassisRepository;

    private static ChassisServiceImpl service = null;

    public static ChassisServiceImpl getInstance() {
        if (service == null)
            service = new ChassisServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public ChassisServiceImpl getService() {
            return ChassisServiceImpl.this;
        }
    }

    private ChassisServiceImpl()
    {
        chassisRepository = new ChassisRepositoryImpl(App.getAppContext());
    }

    @Override
    public Chassis addChassis(Chassis chassis) {
        if(duplicateCheck(chassis) == false)
            return chassisRepository.save(chassis);
        else
            return null;
    }

    @Override
    public boolean duplicateCheck(Chassis chassis) {
        Set<Chassis> allChassis = chassisRepository.findAll();
        boolean duplicate = false;

        for (Chassis chassisRecord: allChassis)
        {
            if (chassis.getCode().equalsIgnoreCase(chassisRecord.getCode()) && !chassis.getId().equals(chassisRecord.getId()))
                duplicate = true;
        }
        return duplicate;
    }

    @Override
    public Chassis updateChassis(Chassis chassis) {
        if(duplicateCheck(chassis) == false)
            return chassisRepository.update(chassis);
        else
            return null;
    }

    @Override
    public Chassis getChassis(Long chassisId) {
        return chassisRepository.findById(chassisId);
    }

    @Override
    public Set<Chassis> getAll() {
        Set<Chassis> chassis;
        chassis = chassisRepository.findAll();
        return chassis;
    }

    @Override
    public Chassis deleteChassis(Chassis chassis) {
        return chassisRepository.delete(chassis);
    }

    @Override
    public int deleteAllChassis() {
        return chassisRepository.deleteAll();
    }
}
