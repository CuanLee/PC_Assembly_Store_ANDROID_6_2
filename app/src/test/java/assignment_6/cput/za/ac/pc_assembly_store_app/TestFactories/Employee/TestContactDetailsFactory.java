package assignment_6.cput.za.ac.pc_assembly_store_app.TestFactories.Employee;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.ContactDetails;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.ContactDetailsFactory;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl.ContactDetailsFactoryImpl;

/**
 * Created by Cuan on 4/3/2016.
 */
public class TestContactDetailsFactory {
    private ContactDetailsFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = ContactDetailsFactoryImpl.getInstance();
    }

    @Test
    public void testContactCreation()
    {
        ContactDetails contactDetails = factory.createContactDetails("021312123","cuan@g.com");
        Assert.assertEquals("cuan@g.com", contactDetails.getEmail());
    }

    @Test
    public void testContactCreationUpdate()
    {
        ContactDetails contactDetails = factory.createContactDetails("021312123", "cuan@g.com");
        Assert.assertEquals("cuan@g.com", contactDetails.getEmail());

        ContactDetails updateContact = new ContactDetails.Builder()
                .copy(contactDetails)
                .email("bbbb")
                .build();

        Assert.assertEquals("bbbb", updateContact.getEmail());
        Assert.assertEquals("021312123", updateContact.getTelephone());
    }
}
