package assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC;

import java.io.Serializable;

/**
 * Created by Cuan on 4/3/2016.
 */
public class Chassis implements Serializable{
    private Long id;
    private String code;
    private String description;
    private Integer hddBays;
    private Integer caseFans;
    private String formFactor;
    private Integer stock;
    private Integer active;

    public Chassis() {
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Integer isActive() {
        return active;
    }

    public Integer getHddBays() {
        return hddBays;
    }

    public Integer getCaseFans() {
        return caseFans;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public Integer getStock() {
        return stock;
    }

    private Chassis(Builder builder)
    {
        this.id = builder.id;
        this.code = builder.code;
        this.description = builder.description;
        this.hddBays = builder.hddBays;
        this.caseFans = builder.caseFans;
        this.formFactor = builder.formFactor;
        this.stock = builder.stock;
        this.active = builder.active;
    }

    public static class Builder{
        private Long id;
        private String code;
        private String description;
        private Integer active;
        private Integer hddBays;
        private Integer caseFans;
        private String formFactor;
        private Integer stock;

        public Builder id(Long value)
        {
            this.id = value;
            return this;
        }

        public Builder code(String value)
        {
            this.code = value;
            return this;
        }

        public Builder description(String value)
        {
            this.description = value;
            return this;
        }

        public Builder hddBays(Integer value)
        {
            this.hddBays = value;
            return this;
        }

        public Builder caseFans(Integer value)
        {
            this.caseFans = value;
            return this;
        }

        public Builder formFactor(String value)
        {
            this.formFactor = value;
            return this;
        }


        public Builder stock(Integer value)
        {
            this.stock = value;
            return this;
        }

        public Builder active(Integer value)
        {
            this.active = value;
            return this;
        }

        public Builder copy(Chassis value)
        {
            this.id = value.id;
            this.code = value.code;
            this.description = value.description;
            this.hddBays = value.hddBays;
            this.caseFans = value.caseFans;
            this.formFactor = value.formFactor;
            this.stock = value.stock;
            this.active = value.active;

            return this;
        }

        public Chassis build(){
            return new Chassis(this);
        }
    }
}
