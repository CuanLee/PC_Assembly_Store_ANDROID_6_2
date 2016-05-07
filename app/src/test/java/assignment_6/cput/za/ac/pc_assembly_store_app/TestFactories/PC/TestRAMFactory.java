package assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.RAMFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl.RAMFactoryImpl;

/**
 * Created by Cuan on 4/3/2016.
 */
public class TestRAMFactory {
    private RAMFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = RAMFactoryImpl.getInstance();
    }

    @Test
    public void testRAMCreation()
    {
        RAM ram = factory.createRAM(1231321L,"vengance","corsair vengance ram","4GB",400,"Dula Module",22,0);
        Assert.assertEquals(0, ram.isActive().intValue());
    }

    @Test
    public void testRAMCreationUpdate()
    {
        RAM ram = factory.createRAM(1231321L,"vengance","corsair vengance ram","4GB",400,"Dula Module",22,0);
        Assert.assertEquals(0, ram.isActive().intValue());

        RAM updateRAM = new RAM.Builder()
                .copy(ram)
                .active(0)
                .build();

        Assert.assertEquals(0, updateRAM.isActive().intValue());
        Assert.assertEquals("vengance", updateRAM.getCode());
    }
}
