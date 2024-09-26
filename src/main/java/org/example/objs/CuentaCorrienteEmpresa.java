package org.example.objs;

import java.util.ArrayList;

public class CuentaCorrienteEmpresa extends CuentaCorriente{
    private String tipoInteresDescubierto;
    private double maximoDescubierto;
    private double comisionFijaDescubierto;
    public CuentaCorrienteEmpresa(String IBAN, double saldo, String tipoCuenta, String tipoInteresDescubierto, double maximoDescubierto, Persona titular, double comisionFijaDescubierto, ArrayList<String> entidadesAutorizadas) {
        super(IBAN, saldo, tipoCuenta, titular, entidadesAutorizadas);
        this.tipoInteresDescubierto = tipoInteresDescubierto;
        this.maximoDescubierto = maximoDescubierto;
        this.comisionFijaDescubierto = comisionFijaDescubierto;
        this.titular = titular;
    }


    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() +
                "Tipo de Interes por descubierto: " + this.tipoInteresDescubierto + "\n" +
                "Comisión fija por descubierto: " + this.comisionFijaDescubierto + "\n" +
                "Máximo descubierto: " + this.maximoDescubierto;
    }
}
