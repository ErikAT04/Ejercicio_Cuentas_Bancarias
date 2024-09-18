package org.example.objs;

import java.util.ArrayList;

public abstract class CuentaCorriente extends CuentaBancaria {

    protected ArrayList<String> entidadesAutorizadas;

    public CuentaCorriente(String IBAN, double saldo, String tipoCuenta, Persona titular) {
        super(IBAN, saldo, tipoCuenta, titular);
    }

    public ArrayList<String> getEntidadesAutorizadas() {
        return entidadesAutorizadas;
    }

    public void setEntidadesAutorizadas(ArrayList<String> entidadesAutorizadas) {
        this.entidadesAutorizadas = entidadesAutorizadas;
    }

    @Override
    public String devolverInfoString() {
        StringBuilder listaEntidades = new StringBuilder();
        for (String s : this.entidadesAutorizadas){
            listaEntidades.append(s);
            if (entidadesAutorizadas.iterator().hasNext()){
                listaEntidades.append(", ");
            }
        }
        return super.devolverInfoString() + "Lista de entidades autorizadas: [" + listaEntidades + "]\n";
    }
}
