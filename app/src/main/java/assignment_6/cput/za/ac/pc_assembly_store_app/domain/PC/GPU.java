package assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC;

import java.io.Serializable;

/**
 * Created by Cuan on 4/3/2016.
 */
public class GPU implements Serializable{
    private Long id;
    private String code;
    private String description;
    private Integer bitMemory;
    private Integer memorySize_GB;
    private String memoryType;
    private double memoryClock_MHz;
    private String cardBus;
    private Integer stock;
    private Integer active;

    public GPU() {
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

    public Integer getBitMemory() {
        return bitMemory;
    }

    public Integer getMemorySize_GB() {
        return memorySize_GB;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public double getMemoryClock_MHz() {
        return memoryClock_MHz;
    }

    public String getCardBus() {
        return cardBus;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer isActive() {
        return active;
    }

    private GPU(Builder builder)
    {
        this.id = builder.id;
        this.code = builder.code;
        this.description = builder.description;
        this.bitMemory= builder.bitMemory;
        this.memorySize_GB = builder.memorySize_GB;
        this.memoryType = builder.memoryType;
        this.memoryClock_MHz = builder.memoryClock_MHz;
        this.cardBus = builder.cardBus;
        this.stock = builder.stock;
        this.active = builder.active;
    }

    public static class Builder{
        private Long id;
        private String code;
        private String description;
        private Integer bitMemory;
        private Integer memorySize_GB;
        private String memoryType;
        private double memoryClock_MHz;
        private String cardBus;
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

        public Builder bitMemory(Integer value)
        {
            this.bitMemory = value;
            return this;
        }

        public Builder memorySize_GB(Integer value)
        {
            this.memorySize_GB = value;
            return this;
        }

        public Builder memoryType(String value)
        {
            this.memoryType = value;
            return this;
        }

        public Builder memoryClock_MHz(double value)
        {
            this.memoryClock_MHz = value;
            return this;
        }

        public Builder cardBus(String value)
        {
            this.cardBus = value;
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

        public Builder copy(GPU value)
        {
            this.id = value.id;
            this.code = value.code;
            this.description = value.description;
            this.bitMemory = value.bitMemory;
            this.memorySize_GB = value.memorySize_GB;
            this.memoryType = value.memoryType;
            this.memoryClock_MHz = value.memoryClock_MHz;
            this.cardBus = value.cardBus;
            this.stock = value.stock;
            this.active = value.active;

            return this;
        }

        public GPU build(){
            return new GPU(this);
        }
    }
}
