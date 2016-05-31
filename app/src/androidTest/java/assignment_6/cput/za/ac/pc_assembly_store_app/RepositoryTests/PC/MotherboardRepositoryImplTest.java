package assignment_6.cput.za.ac.pc_assembly_store_app.RepositoryTests.PC;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Motherboard;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl.MotherboardRepositoryImpl;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.MotherboardRepository;

/**
 * Created by CuanL on 24/04/2016.
 */
public class MotherboardRepositoryImplTest extends AndroidTestCase {
    private static final String TAG="Motherboard TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        MotherboardRepository repo = new MotherboardRepositoryImpl(this.getContext());

        //Create
        Motherboard motherboard = new Motherboard.Builder()
                .code("Asus B85m")
                .description("Asus Golden Series")
                .chipset("1150")
                .sataPorts(2133)
                .usb2(4)
                .usb3(2)
                .formFactor("ATX")
                .stock(89)
                .active(1)
                .build();

        Motherboard inserttedMotherboard = repo.save(motherboard);
        id = inserttedMotherboard.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedMotherboard);

        //READ ALL
        Set<Motherboard> allMotherboard = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allMotherboard.size()>0);

        //READ ENTITY
        Motherboard entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Motherboard updateEntity = new Motherboard.Builder()
                .copy(entity)
                .code("Update Motherboard")
                .build();
        repo.update(updateEntity);
        Motherboard newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Update Motherboard",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        //Motherboard deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }

}
