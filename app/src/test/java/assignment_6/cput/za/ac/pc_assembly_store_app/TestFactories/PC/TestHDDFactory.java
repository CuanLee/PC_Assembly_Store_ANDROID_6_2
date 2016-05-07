package assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.HDD;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.HDDFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl.HDDFactoryImpl;

/**
 * Created by Cuan on 4/3/2016.
 */
public class TestHDDFactory {
    private HDDFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = HDDFactoryImpl.getInstance();
    }

    @Test
    public void testPSUCreation()
    {
        HDD hdd = factory.createHDD(564546L, "testCode","testDesc",1232,2331,0,32,1);
        Assert.assertEquals("testCode", hdd.getCode());
    }

    @Test
    public void testPSUCreationUpdate()
    {
        HDD hdd = factory.createHDD(564546L, "testCode", "testDesc", 1232, 2331, 0,32, 1);
        Assert.assertEquals("testCode", hdd.getCode());

        HDD updateHDD = new HDD.Builder()
                .copy(hdd)
                .active(1)
                .code("UpdateTestCode")
                .build();

        Assert.assertEquals(1, updateHDD.isActive().intValue());
        Assert.assertEquals("UpdateTestCode", updateHDD.getCode());
    }
}
