import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingSlot {
    private String slotID;
    private boolean isForStaff;
    private Car car;
    private LocalDateTime parkingStartTime;

    public ParkingSlot(String slotID, boolean isForStaff) {
        this.slotID = slotID;
        this.isForStaff = isForStaff;
        this.car = null;
        this.parkingStartTime = null;
    }

    public String getSlotID() {
        return slotID;
    }

    public boolean isForStaff() {
        return isForStaff;
    }

    public boolean isOccupied() {
        return car != null;
    }

    public Car getCar() {
        return car;
    }

    public void parkCar(Car car) {
        this.car = car;
        this.parkingStartTime = LocalDateTime.now();
    }

    public void removeCar() {
        this.car = null;
        this.parkingStartTime = null;
    }

    public Duration getParkingDuration() {
        if (isOccupied() && parkingStartTime != null) {
            return Duration.between(parkingStartTime, LocalDateTime.now());
        }
        return Duration.ZERO;
    }

    public double calculateParkingFee() {
        Duration duration = getParkingDuration();
        long hours = duration.toHours() + (duration.toMinutes() % 60 == 0 ? 0 : 1);
        return hours * 5.0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Slot ID: ").append(slotID)
                .append(" | Type: ").append(isForStaff ? "Staff" : "Visitor")
                .append(" | Occupied: ").append(isOccupied() ? "Yes" : "No");

        if (isOccupied()) {
            sb.append(" | Car Reg: ").append(car.getRegistrationNumber())
                    .append(" | Owner: ").append(car.getOwnerName())
                    .append(" | Parked for: ").append(getParkingDuration().toMinutes()).append(" minutes")
                    .append(" | Fee: $").append(calculateParkingFee());
        }

        return sb.toString();
    }
}