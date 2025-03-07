package ecommerce.example.ecommerce.models;

public enum Status {
    PENDING("PENDING"),
    REJECT("REJECT"),
    COMPLETED("COMPLETED");
    private String status;

    Status(String status) {
        this.status = status;
    }

    // Getter method to retrieve the status value
    public String getStatus() {
        return status;
    }
}
