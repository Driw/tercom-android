package br.com.tercomfuncionario.Interface;

import br.com.tercomfuncionario.Entity.Manufacture;
import br.com.tercomfuncionario.Entity.Provider;

public interface iNewOrderItem {
    public int getId();
    public String getName();
    public Provider getProvider();
    public boolean isBetterPrice();
    public String getObservations();
    public Manufacture getManufacturer();
    public boolean isProduct();

}
