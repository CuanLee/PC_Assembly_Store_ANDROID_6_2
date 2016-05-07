package assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee;

import java.io.Serializable;
import java.util.List;

import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Chassis;

/**
 * Created by Cuan on 4/3/2016.
 */
public class Employee implements IPerson,Serializable {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private ContactDetails contactDetails;
    private GeographicalDetails geographicalDetails;
    private String idNumber;

    public Employee() {
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public GeographicalDetails getGeographicalDetails() {
        return geographicalDetails;
    }

    public String getIdNumber() {
        return idNumber;
    }

    private Employee(Builder builder)
    {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.contactDetails = builder.contactDetails;
        this.geographicalDetails = builder.geographicalDetails;
        this.idNumber = builder.idNumber;
    }

    public static class Builder{
        private Long id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String gender;
        private ContactDetails contactDetails;
        private GeographicalDetails geographicalDetails;
        private String idNumber;

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder username(String value)
        {
            this.username = value;
            return this;
        }

        public Builder password(String value)
        {
            this.password = value;
            return this;
        }

        public Builder firstName(String value)
        {
            this.firstName = value;
            return this;
        }

        public Builder lastName(String value)
        {
            this.lastName = value;
            return this;
        }

        public Builder gender(String value)
        {
            this.gender = value;
            return this;
        }

        public Builder contactDetails(ContactDetails value)
        {
            this.contactDetails = value;
            return this;
        }

        public Builder geographicalDetails(GeographicalDetails value)
        {
            this.geographicalDetails = value;
            return this;
        }

        public Builder idNumber(String value)
        {
            this.idNumber = value;
            return this;
        }

        public Builder copy(Employee value)
        {
            this.id = value.id;
            this.username = value.username;
            this.password = value.password;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.gender = value.gender;
            this.contactDetails = value.contactDetails;
            this.geographicalDetails = value.geographicalDetails;
            this.idNumber = value.idNumber;

            return this;
        }

        public Employee build(){
            return new Employee(this);
        }
    }
}
