package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.HDD;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;

/**
 * Created by CuanL on 10/05/2016.
 */
public interface HDDService {
    HDD addHDD(HDD hdd);

    boolean duplicateCheck(HDD hdd);

    HDD updateHDD(HDD hdd);

    HDD getHDD(Long hddId);

    Set<HDD> getAll();

    HDD deleteHDD(HDD hdd);

    int deleteAllHDD();
}
