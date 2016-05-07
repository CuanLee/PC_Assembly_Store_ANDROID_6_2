package assignment_6.cput.za.ac.pc_assembly_store_app.factories;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.GeographicalDetails;

/**
 * Created by Cuan on 4/3/2016.
 */
public interface GeographicalDetailsFactory {
    GeographicalDetails createGeographicalDetails(String country, String province, String city, String suburb, String street, Integer houseNumber);
}
