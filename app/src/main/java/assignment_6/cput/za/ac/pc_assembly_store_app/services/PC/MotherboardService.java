package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Motherboard;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;

/**
 * Created by CuanL on 10/05/2016.
 */
public interface MotherboardService {
    Motherboard addMotherboard(Motherboard motherboard);

    boolean duplicateCheck(Motherboard motherboard);

    Motherboard updateMotherboard(Motherboard motherboard);

    Motherboard getMotherboard(Long motherboardId);

    Set<Motherboard> getAll();

    Motherboard deleteMotherboard(Motherboard motherboard);

    int deleteAllMotherboard();
}
