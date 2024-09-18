package org.example.objs;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

public class CuentaAhorro extends CuentaBancaria{
    private String tipoInteresAnual;
    public CuentaAhorro(String IBAN, double saldo, String tipoCuenta, String tipoInteresAnual, Persona titular) {
        super(IBAN, saldo, tipoCuenta, titular);
        this.tipoInteresAnual = tipoInteresAnual;
    }

    public String getTipoInteresAnual() {
        return tipoInteresAnual;
    }

    public void setTipoInteresAnual(String tipoInteresAnual) {
        this.tipoInteresAnual = tipoInteresAnual;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + "Tipo de interés anual: " + this.tipoInteresAnual;
    }

}
