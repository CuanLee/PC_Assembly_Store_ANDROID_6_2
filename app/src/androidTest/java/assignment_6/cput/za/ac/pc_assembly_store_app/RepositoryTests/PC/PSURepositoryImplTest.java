package assignment_6.cput.za.ac.pc_assembly_store_app.RepositoryTests.PC;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.PSURepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.PSURepository;

/**
 * Created by CuanL on 22/04/2016.
 */
public class PSURepositoryImplTest extends AndroidTestCase{
    private static final String TAG="PSU TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PSURepository repo = new PSURepositoryImpl(this.getContext());

        //Create
        PSU psu = new PSU.Builder()
                .code("11234")
                .description("Raidmax Gold Series")
                .watts(750)
                .four_pin_molex(0)
                .sata_connectors(4)
                .floppy_connectors(6)
                .stock(55)
                .active(1)
                .build();

        PSU inserttedPSU = repo.save(psu);
        id = inserttedPSU.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedPSU);

        //READ ALL
        Set<PSU> allPsu = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allPsu.size()>0);

        //READ ENTITY
        PSU entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        PSU updateEntity = new PSU.Builder()
                .copy(entity)
                .code("Update PSU")
                .build();
        repo.update(updateEntity);
        PSU newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Update PSU",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        //PSU deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
