package org.example.objs;

public class CuentaAhorro extends CuentaBancaria{
    private String tipoInteresAnual;
    public CuentaAhorro(String IBAN, double saldo, String tipoCuenta, String tipoInteresAnual, Persona titular) {
        super(IBAN, saldo, tipoCuenta, titular);
        this.tipoInteresAnual = tipoInteresAnual;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + "Tipo de interés anual: " + this.tipoInteresAnual;
    }

}
