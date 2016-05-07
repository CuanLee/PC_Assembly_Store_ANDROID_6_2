package assignment_6.cput.za.ac.pc_assembly_store_app.RepositoryTests.PC;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.CPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.CPURepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.CPURepositoryImpl;

/**
 * Created by CuanL on 24/04/2016.
 */
public class CPURepositoryImplTest extends AndroidTestCase{
    private static final String TAG="CPU TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        CPURepository repo = new CPURepositoryImpl(this.getContext());

        //Create
        CPU cpu = new CPU.Builder()
                .code("5351AA")
                .description("Intel Skylake")
                .socket(115)
                .processorBrand("Intel")
                .speed_Ghz(132323)
                .cache_MB(123)
                .cores(8)
                .stock(22)
                .active(1)
                .build();

        CPU inserttedCPU = repo.save(cpu);
        id = inserttedCPU.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedCPU);

        //READ ALL
        Set<CPU> allCpu = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allCpu.size()>0);

        //READ ENTITY
        CPU entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        CPU updateEntity = new CPU.Builder()
                .copy(entity)
                .code("Update CPU")
                .build();
        repo.update(updateEntity);
        CPU newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Update CPU",newEntity.getCode());

        // DELETE ENTITY
        repo.delete(updateEntity);
        CPU deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
