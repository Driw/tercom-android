package br.com.tercom.Entity;

public class LoginTercom extends GenericEntity {
    private TercomEmployee tercomEmployee;

    public TercomEmployee getTercomEmployee(){
        return this.tercomEmployee;
    }

    public void setTercomEmployee(TercomEmployee tercomEmployee) {
        this.tercomEmployee = tercomEmployee;
    }

    public int getTercomEmployeeId(){
        return this.tercomEmployee.getId();
    }
}
