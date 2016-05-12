package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.util.App;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.PSURepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.PSURepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.services.PC.PSUService;

/**
 * Created by CuanL on 10/05/2016.
 */
public class PSUServiceImpl extends Service implements PSUService {
    final private PSURepository psuRepository;

    private static PSUServiceImpl service = null;

    public static PSUServiceImpl getInstance() {
        if (service == null)
            service = new PSUServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class PSUServiceLocalBinder extends Binder {
        public PSUServiceImpl getService() {
            return PSUServiceImpl.this;
        }
    }

    public class ActivateServiceLocalBinder extends Binder {
        public PSUServiceImpl getService() {
            return PSUServiceImpl.this;
        }
    }

    private PSUServiceImpl()
    {
        psuRepository = new PSURepositoryImpl(App.getAppContext());
    }

    @Override
    public PSU addPsu(PSU psu) {
        if(duplicateCheck(psu) == false)
            return psuRepository.save(psu);
        else
            return null;
    }

    @Override
    public PSU updatePsu(PSU psu) {
        if(duplicateCheck(psu) == false)
            return psuRepository.update(psu);
        else
            return null;
    }

    @Override
    public PSU getPsu(Long psuId) {
        return psuRepository.findById(psuId);
    }

    @Override
    public Set<PSU> getAll() {
        Set<PSU> psu;
        psu = psuRepository.findAll();
        return psu;
    }

    @Override
    public PSU deleteRam(PSU psu) {
        return psuRepository.delete(psu);
    }

    @Override
    public int deleteAllRam() {
        return psuRepository.deleteAll();
    }

    @Override
    public boolean duplicateCheck(PSU psu) {
        Set<PSU> allPsu = psuRepository.findAll();
        boolean duplicate = false;

        for (PSU psuRecord: allPsu)
        {
            if (psu.getCode().equalsIgnoreCase(psuRecord.getCode()) && !psu.getId().equals(psuRecord.getId()))
                duplicate = true;
        }
        return duplicate;
    }
}
