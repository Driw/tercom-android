package br.com.tercomfuncionario.Entity;

import br.com.tercomfuncionario.Annotation.BindObject;
import br.com.tercomfuncionario.Interface.iNewOrderItem;

public class OrderItemProduct extends GenericEntity implements iNewOrderItem {
    public final int MAX_OBSERVATION_LENS = 128;

    private int id;
    @BindObject
    private Product product;
    @BindObject
    private Provider provider;
    @BindObject
    private Manufacture manufacturer;
    private boolean betterPrice;
    private String observations;

    public OrderItemProduct(){
        id = 0;
        betterPrice = false;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        if (product != null) {
            return product.getName();
        } else {
            return "";
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        if(product == null) {
            product = new Product();
            product.setId(0);
        }
        return product;
    }

    public int getProductId(){
        return getProduct().getId();
    }

    @Override
    public int getItemId(){
        return getProduct().getId();
    }

    public OrderItemProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Provider getProvider() {
        if(provider == null){
            provider = new Provider();
            provider.setId(0);
        }
        return provider;
    }

    public OrderItemProduct setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public int getProviderId(){
        return provider.getId();
    }

    public Manufacture getManufacturer() {
        return manufacturer;
    }

    @Override
    public boolean isProduct() {
        return true;
    }

    public OrderItemProduct setManufacturer(Manufacture manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public boolean isBetterPrice() {
        return betterPrice;
    }

    public OrderItemProduct setBetterPrice(boolean betterPrice) {
        this.betterPrice = betterPrice;
        return this;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        if(observations != null && observations.length() > MAX_OBSERVATION_LENS)
            throw new StringIndexOutOfBoundsException(String.format("Observações deve possuir até %s", String.valueOf(MAX_OBSERVATION_LENS)));
        this.observations = observations;
    }
}
