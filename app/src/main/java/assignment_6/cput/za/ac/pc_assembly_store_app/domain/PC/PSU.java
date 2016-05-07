package assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC;

import java.io.Serializable;

/**
 * Created by Cuan on 4/3/2016.
 */
public class PSU implements Serializable {
    private Long id;
    private String code;
    private String description;
    private Integer watts;
    private Integer four_pin_molex;
    private Integer sata_connectors;
    private Integer floppy_connectors;
    private Integer stock;
    private Integer active;

    public PSU() {
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

    public Integer getWatts() {
        return watts;
    }

    public Integer getFour_pin_molex() {
        return four_pin_molex;
    }

    public Integer getSata_connectors() {
        return sata_connectors;
    }

    public Integer getFloppy_connectors() {
        return floppy_connectors;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer isActive() {
        return active;
    }

    private PSU(Builder builder)
    {
        this.id = builder.id;
        this.code = builder.code;
        this.description = builder.description;
        this.watts = builder.watts;
        this.four_pin_molex = builder.four_pin_molex;
        this.sata_connectors = builder.sata_connectors;
        this.floppy_connectors = builder.floppy_connectors;
        this.stock = builder.stock;
        this.active = builder.active;
    }

    public static class Builder{
        private Long id;
        private String code;
        private String description;
        private Integer watts;
        private Integer four_pin_molex;
        private Integer sata_connectors;
        private Integer floppy_connectors;
        private Integer stock;
        private Integer active;

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

        public Builder watts(Integer value)
        {
            this.watts = value;
            return this;
        }

        public Builder four_pin_molex(Integer value)
        {
            this.four_pin_molex = value;
            return this;
        }

        public Builder sata_connectors(Integer value)
        {
            this.sata_connectors = value;
            return this;
        }

        public Builder floppy_connectors(Integer value)
        {
            this.floppy_connectors = value;
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

        public Builder copy(PSU value)
        {
            this.id = value.id;
            this.code = value.code;
            this.description = value.description;
            this.watts = value.watts;
            this.four_pin_molex = value.four_pin_molex;
            this.sata_connectors = value.sata_connectors;
            this.floppy_connectors = value.floppy_connectors;
            this.stock = value.stock;
            this.active = value.active;

            return this;
        }

        public PSU build(){
            return new PSU(this);
        }
    }
}
