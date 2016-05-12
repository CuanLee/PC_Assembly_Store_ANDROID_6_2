package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;

/**
 * Created by CuanL on 10/05/2016.
 */
public interface PSUService {
    PSU addPsu(PSU psu);

    boolean duplicateCheck(PSU psu);

    PSU updatePsu(PSU psu);

    PSU getPsu(Long psuId);

    Set<PSU> getAll();

    PSU deleteRam(PSU psu);

    int deleteAllRam();
}
