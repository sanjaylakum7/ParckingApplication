public class Car {
    private String registrationNumber;
    private String ownerName;
    private boolean isStaffMember;

    public Car(String registrationNumber, String ownerName, boolean isStaffMember) {
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
        this.isStaffMember = isStaffMember;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean isStaffMember() {
        return isStaffMember;
    }
}
