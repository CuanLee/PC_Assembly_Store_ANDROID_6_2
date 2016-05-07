package assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.PC;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.PSUFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl.PSUFactoryImpl;

/**
 * Created by Cuan on 4/3/2016.
 */
public class TestPSUFactory {
    private PSUFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = PSUFactoryImpl.getInstance();
    }

    @Test
    public void testPSUCreation()
    {
        PSU psu = factory.createPSU(1231321L, "11234", "Raidmax Gold Series", 750, 6, 4, 0,55, 1);
        Assert.assertEquals("Raidmax Gold Series", psu.getDescription());
    }

    @Test
    public void testPSUCreationUpdate()
    {
        PSU psu = factory.createPSU(1231321L, "11234", "Raidmax Gold Series", 750, 6, 4, 0,55, 1);
        Assert.assertEquals("Raidmax Gold Series", psu.getDescription());

        PSU updatePSU = new PSU.Builder()
                .copy(psu)
                .id(111111L)
                .four_pin_molex(7)
                .build();

        Assert.assertEquals(7, updatePSU.getFour_pin_molex().intValue());
        Assert.assertEquals(111111L, updatePSU.getId().longValue());
    }
}
