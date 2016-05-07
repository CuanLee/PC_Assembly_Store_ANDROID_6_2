package assignment_6.cput.za.ac.pc_assembly_store_app.RepositoryTests.PC;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Chassis;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.ChassisRepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.ChassisRepositoryImpl;

/**
 * Created by CuanL on 24/04/2016.
 */
public class ChassisRepositoryImplTest extends AndroidTestCase {
    private static final String TAG="Chassis TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ChassisRepository repo = new ChassisRepositoryImpl(this.getContext());

        //Create
        Chassis chassis = new Chassis.Builder()
                .code("7501212")
                .description("Monster Build")
                .hddBays(6)
                .caseFans(4)
                .formFactor("ATX")
                .stock(11)
                .active(1)
                .build();

        Chassis inserttedChassis = repo.save(chassis);
        id = inserttedChassis.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);

        //READ ALL
        Set<Chassis> allHdd = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allHdd.size()>0);

        //READ ENTITY
        Chassis entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Chassis updateEntity = new Chassis.Builder()
                .copy(entity)
                .code("Update Chassis")
                .build();
        repo.update(updateEntity);
        Chassis newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Update Chassis",newEntity.getCode());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Chassis deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
