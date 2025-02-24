package br.com.tercomfuncionario.Entity;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderRequest extends GenericEntity {
    public final Float MIN_BUDGET = 0f;
    public static final int ORS_NONE = 0;
    public static final int ORS_CANCEL_BY_CUSTOMER = 1;
    public static final int ORS_CANCEL_BY_TERCOM = 2;
    public static final int ORS_QUEUED = 3;
    public static final int ORS_QUOTING = 4;
    public static final int ORS_QUOTED = 5;
    public static final int ORS_DONE = 6;

    private int id;
    private Float budget;
    private int status;
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

    public OrderRequest setId(int id) {
        this.id = id;
        return this;
    }

    public Float getBudget() {
        return budget;
    }

    public OrderRequest setBudget(Float budget) {
        if(budget < MIN_BUDGET)
            throw new NumberFormatException(String.format("Orçamento não pode ser inferior a R$ %.2f", MIN_BUDGET));
        this.budget = budget;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public OrderRequest setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getStatusMessage() {
        switch(status){
            default:
            case ORS_NONE:
                return "nada acontecendo";
            case ORS_CANCEL_BY_CUSTOMER:
                return "cancelado pelo cliente";
            case ORS_CANCEL_BY_TERCOM:
                return "cancelado pela Tercom";
            case ORS_QUEUED:
                return "Na fila";
            case ORS_QUOTING:
                return "Em cotação";
            case ORS_QUOTED:
                return "Pedido Cotado";
            case ORS_DONE:
                return "Pedido Finalizado";

        }
    }
    public OrderRequest setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
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
        if(customerEmployee == null) {
            customerEmployee = new CustomerEmployee();
            customerEmployee.setId(0);
        }
        return customerEmployee;
    }

    public OrderRequest setCustomerEmployee(CustomerEmployee customerEmployee) {
        this.customerEmployee = customerEmployee;
        return this;
    }

    public TercomEmployee getTercomEmployee() {
        if(tercomEmployee == null) {
            tercomEmployee = new TercomEmployee();
            tercomEmployee.setId(0);
        }
        return tercomEmployee;
    }

    public OrderRequest setTercomEmployee(TercomEmployee tercomEmployee) {
        this.tercomEmployee = tercomEmployee;
        return this;
    }
}
