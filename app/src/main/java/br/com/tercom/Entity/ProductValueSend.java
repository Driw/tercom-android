package br.com.tercom.Entity;

public class ProductValueSend {

    private int id;
    private int idProvider;
    private  int idManufacture;
    private int idProductPackage;
    private int idProductType;
    private String name;
    private int amount;
    private float price;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public int getIdManufacture() {
        return idManufacture;
    }

    public void setIdManufacture(int idManufacture) {
        this.idManufacture = idManufacture;
    }

    public int getIdProductPackage() {
        return idProductPackage;
    }

    public void setIdProductPackage(int idProductPackage) {
        this.idProductPackage = idProductPackage;
    }

    public int getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(int idProductType) {
        this.idProductType = idProductType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
