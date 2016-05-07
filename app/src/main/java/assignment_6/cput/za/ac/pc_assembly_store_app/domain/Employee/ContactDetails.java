package assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee;

import java.io.Serializable;

/**
 * Created by Cuan on 4/3/2016.
 */
public class ContactDetails implements Serializable{
    private String telephone;
    private String email;

    public ContactDetails(){

    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public ContactDetails(Builder builder){
        telephone = builder.telephone;
        email = builder.email;
    }

    public static class Builder{
        private String telephone;
        private String email;

        public Builder telephone(String telephone){
            this.telephone = telephone;
            return this;
        }

        public Builder email(String value){
            this.email = value;
            return this;
        }

        public Builder copy(ContactDetails value)
        {
            this.telephone = value.telephone;
            this.email = value.email;

            return this;
        }

        public ContactDetails build(){return new ContactDetails(this);}
    }

}
