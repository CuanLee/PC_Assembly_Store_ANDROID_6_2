package assignment_6.cput.za.ac.pc_assembly_store_app.RepositoryTests.PC;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.RAMRepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.RAMRepository;

/**
 * Created by CuanL on 22/04/2016.
 */
public class RAMRepositoryImplTest extends AndroidTestCase {
    private static final String TAG="RAM TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        RAMRepository repo = new RAMRepositoryImpl(this.getContext());

        //Create
        RAM ram = new RAM.Builder()
                .code("vengance")
                .description("corsair vengance ram")
                .voltage(400)
                .memorySize("4GB")
                .memoryConfiguration("Dula Module")
                .stock(22)
                .active(0)
                .build();

        RAM inserttedRam = repo.save(ram);
        id = inserttedRam.getId();
        Assert.assertNotNull(TAG+" CREATE",inserttedRam);

        //READ ALL
        Set<RAM> allRam = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allRam.size()>0);

        //READ ENTITY
        RAM entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        RAM updateEntity = new RAM.Builder()
                .copy(entity)
                .code("Update RAM")
                .build();
        repo.update(updateEntity);
        RAM newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Update RAM",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        //RAM deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
