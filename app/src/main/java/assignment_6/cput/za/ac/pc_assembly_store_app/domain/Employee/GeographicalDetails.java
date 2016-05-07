package assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee;

import java.io.Serializable;

/**
 * Created by Cuan on 4/3/2016.
 */
public class GeographicalDetails implements Serializable{
    private String country;
    private String province;
    private String city;
    private String suburb;
    private String street;
    private Integer houseNumber;

    public GeographicalDetails() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    private GeographicalDetails(Builder builder)
    {
        this.country = builder.country;
        this.province = builder.province;
        this.city = builder.city;
        this.suburb = builder.suburb;
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
    }

    public static class Builder{
        private String country;
        private String province;
        private String city;
        private String suburb;
        private String street;
        private Integer houseNumber;

        public Builder country(String value)
        {
            this.country = value;
            return this;
        }

        public Builder province(String value)
        {
            this.province = value;
            return this;
        }

        public Builder city(String value)
        {
            this.city = value;
            return this;
        }

        public Builder suburb(String value)
        {
            this.suburb = value;
            return this;
        }

        public Builder street(String value)
        {
            this.street = value;
            return this;
        }

        public Builder houseNumber(Integer value)
        {
            this.houseNumber = value;
            return this;
        }

        public Builder copy(GeographicalDetails value)
        {
            this.country = value.country;
            this.province = value.province;
            this.city = value.city;
            this.suburb = value.suburb;
            this.street = value.street;
            this.houseNumber = value.houseNumber;

            return this;
        }

        public GeographicalDetails build(){
            return new GeographicalDetails(this);
        }
    }
}
