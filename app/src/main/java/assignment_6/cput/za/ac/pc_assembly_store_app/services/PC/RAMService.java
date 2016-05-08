package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC;

import android.content.Context;

import java.util.List;
import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;

/**
 * Created by CuanL on 08/05/2016.
 */
public interface RAMService {
    RAM addRam(RAM ram);

    RAM updateRam(RAM ram);

    RAM getRam(Long ramId);

    Set<RAM> getAll();

    RAM deleteRam(RAM ram);

    int deleteAllRam();
}
