package carRental.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="car")
public class CarInfo {

    @Id
    @GeneratedValue
    private long carID;
    @NotEmpty
    private String type;
    private boolean available;

    public CarInfo(){}

    public CarInfo(String type, boolean available){
        super();
        this.type = type;
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    @Override
    public String toString() {
        return "CarInfo [carId=" + carID +
                ", type=" + type + "]";
    }
}
