import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarPark {
    private List<ParkingSlot> slots;

    public CarPark(int staffSlots, int visitorSlots) {
        slots = new ArrayList<>();
        initializeSlots(staffSlots, visitorSlots);
    }

    private void initializeSlots(int staffSlots, int visitorSlots) {
        for (int i = 1; i <= staffSlots; i++) {
            slots.add(new ParkingSlot(String.format("S%02d", i), true));
        }
        for (int i = 1; i <= visitorSlots; i++) {
            slots.add(new ParkingSlot(String.format("V%02d", i), false));
        }
    }

    public void addSlot(String slotID, boolean isForStaff) {
        if (getSlotById(slotID) == null) {
            slots.add(new ParkingSlot(slotID, isForStaff));
            System.out.println("Slot " + slotID + " added.");
        } else {
            System.out.println("Slot ID already exists.");
        }
    }

    public void deleteSlot(String slotID) {
        ParkingSlot slot = getSlotById(slotID);
        if (slot != null && !slot.isOccupied()) {
            slots.remove(slot);
            System.out.println("Slot " + slotID + " deleted.");
        } else if (slot != null) {
            System.out.println("Cannot delete occupied slot.");
        } else {
            System.out.println("Slot not found.");
        }
    }

    public void listAllSlots() {
        for (ParkingSlot slot : slots) {
            System.out.println(slot);
        }
    }

    public void deleteUnoccupiedSlots() {
        Iterator<ParkingSlot> iterator = slots.iterator();
        while (iterator.hasNext()) {
            ParkingSlot slot = iterator.next();
            if (!slot.isOccupied()) {
                iterator.remove();
                System.out.println("Unoccupied slot " + slot.getSlotID() + " deleted.");
            }
        }
    }

    public void parkCar(String slotID, Car car) {
        ParkingSlot slot = getSlotById(slotID);
        if (slot != null) {
            if (!slot.isOccupied()) {
                if ((car.isStaffMember() && slot.isForStaff()) || (!car.isStaffMember() && !slot.isForStaff())) {
                    slot.parkCar(car);
                    System.out.println("Car parked in slot " + slotID + ".");
                } else {
                    System.out.println("Car cannot be parked in this slot type.");
                }
            } else {
                System.out.println("Slot is already occupied.");
            }
        } else {
            System.out.println("Slot not found.");
        }
    }

    public void findCar(String registrationNumber) {
        for (ParkingSlot slot : slots) {
            if (slot.isOccupied() && slot.getCar().getRegistrationNumber().equals(registrationNumber)) {
                System.out.println("Car found in slot " + slot.getSlotID() + ".");
                System.out.println(slot);
                return;
            }
        }
        System.out.println("Car not found.");
    }

    public void removeCar(String registrationNumber) {
        for (ParkingSlot slot : slots) {
            if (slot.isOccupied() && slot.getCar().getRegistrationNumber().equals(registrationNumber)) {
                slot.removeCar();
                System.out.println("Car removed from slot " + slot.getSlotID() + ".");
                return;
            }
        }
        System.out.println("Car not found.");
    }

    private ParkingSlot getSlotById(String slotID) {
        for (ParkingSlot slot : slots) {
            if (slot.getSlotID().equals(slotID)) {
                return slot;
            }
        }
        return null;
    }
}
