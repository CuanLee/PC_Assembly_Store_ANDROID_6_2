package assignment_6.cput.za.ac.pc_assembly_store_app.factories.impl;


import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.GeographicalDetails;
import assignment_6.cput.za.ac.pc_assembly_store_app.factories.GeographicalDetailsFactory;

/**
 * Created by Cuan on 4/3/2016.
 */
public class GeographicalDetailsFactoryImpl implements GeographicalDetailsFactory {
    private static GeographicalDetailsFactoryImpl factory = null;

    private GeographicalDetailsFactoryImpl(){

    }

    public static GeographicalDetailsFactoryImpl getInstance(){
        if (factory == null)
            factory = new GeographicalDetailsFactoryImpl();

        return factory;
    }

    public GeographicalDetails createGeographicalDetails(String country, String province, String city, String suburb, String street, Integer houseNumber){
        GeographicalDetails geographicalDetails = new GeographicalDetails.Builder()
                .country(country)
                .province(province)
                .city(city)
                .suburb(suburb)
                .street(street)
                .houseNumber(houseNumber)
                .build();
        return geographicalDetails;
    }
}
