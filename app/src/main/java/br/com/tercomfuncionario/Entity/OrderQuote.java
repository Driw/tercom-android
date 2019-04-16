package br.com.tercomfuncionario.Entity;

import br.com.tercomfuncionario.Annotation.BindObject;

public class OrderQuote extends GenericEntity {
    private int id;
    @BindObject
    private OrderRequest orderRequest;
    @BindObject
    private Register register;

    public int getId() {
            return id;
        }

    public void setId(int id) {
            this.id = id;
        }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }
}
