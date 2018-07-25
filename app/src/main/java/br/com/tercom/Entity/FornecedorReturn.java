package br.com.tercom.Entity;

import br.com.tercom.Annotation.BindObject;

public class FornecedorReturn {

    //@BindObject(value = "otherPhone")
    private Phone telefone;
    //@BindObject(value = "algumProvider")
    private Provider p;

    public Phone getTelefone() {
        return telefone;
    }

    public void setTelefone(Phone telefone) {
        this.telefone = telefone;
    }

    public Provider getP() {
        return p;
    }

    public void setP(Provider p) {
        this.p = p;
    }
}
