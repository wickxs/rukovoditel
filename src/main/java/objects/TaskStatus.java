package objects;

public enum TaskStatus {
    NEW("46"),
    OPEN("47"),
    WAITING("48"),
    DONE("49"),
    CLOSED("50"),
    PAID("51"),
    CANCELED("52");

    private String valueId;
    TaskStatus(String valueId) {
        this.valueId = valueId;
    }

    public String getValueId() {
        return valueId;
    }
}
