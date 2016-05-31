package assignment_6.cput.za.ac.pc_assembly_store_app.RepositoryTests.PC;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.GPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.GPURepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.GPURepositoryImpl;

/**
 * Created by CuanL on 23/04/2016.
 */
public class GPURepositoryImplTest extends AndroidTestCase {
    private static final String TAG="GPU TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        GPURepository repo = new GPURepositoryImpl(this.getContext());

        //Create
        GPU gpu = new GPU.Builder()
                .code("gpuCode")
                .description("gpuDescription")
                .bitMemory(132)
                .memorySize_GB(121)
                .memoryType("GDDR5")
                .memoryClock_MHz(132123)
                .cardBus("PCIE3")
                .stock(16)
                .active(1)
                .build();

        GPU inserttedGPU = repo.save(gpu);
        id = inserttedGPU.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedGPU);

        //READ ALL
        Set<GPU> allGpu = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allGpu.size()>0);

        //READ ENTITY
        GPU entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        GPU updateEntity = new GPU.Builder()
                .copy(entity)
                .code("Update GPU")
                .build();
        repo.update(updateEntity);
        GPU newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Update GPU",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        //GPU deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
