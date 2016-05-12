package assignment_6.cput.za.ac.pc_assembly_store_app.services.PC;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Chassis;

/**
 * Created by CuanL on 10/05/2016.
 */
public interface ChassisService {
    Chassis addChassis(Chassis chassis);

    boolean duplicateCheck(Chassis chassis);

    Chassis updateChassis(Chassis chassis);

    Chassis getChassis(Long chassisId);

    Set<Chassis> getAll();

    Chassis deleteChassis(Chassis chassis);

    int deleteAllChassis();
}
