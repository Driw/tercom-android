package br.com.tercomfuncionario.Entity;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderQuote extends GenericEntity {
    private int id;
    private float budget;
    private String statusDescription;
    private String statusMessage;
    @BindObject
    private Register register;
    @BindObject
    private Expiration expiration;
    @BindObject
    private CustomerEmployee customerEmployee;
    @BindObject
    private TercomEmployee tercomEmployee;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getBudget() {
            return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Expiration getExpiration() {
        return expiration;
    }

    public void setExpiration(Expiration expiration) {
        this.expiration = expiration;
    }

    public CustomerEmployee getCustomerEmployee() {
        return customerEmployee;
    }

    public void setCustomerEmployee(CustomerEmployee customerEmployee) {
        this.customerEmployee = customerEmployee;
    }

    public TercomEmployee getTercomEmployee() {
        return tercomEmployee;
    }

    public void setTercomEmployee(TercomEmployee tercomEmployee) {
        this.tercomEmployee = tercomEmployee;
    }
}
