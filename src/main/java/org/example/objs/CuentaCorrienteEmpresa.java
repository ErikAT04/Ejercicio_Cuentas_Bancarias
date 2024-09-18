package org.example.objs;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.ArrayList;

public class CuentaCorrienteEmpresa extends CuentaCorriente{
    private String tipoInteresDescubierto;
    private double maximoDescubierto;
    private double comisionFijaDescubierto;
    public CuentaCorrienteEmpresa(String IBAN, double saldo, String tipoCuenta, String tipoInteresDescubierto, double maximoDescubierto, Persona titular, double comisionFijaDescubierto) {
        super(IBAN, saldo, tipoCuenta, titular);
        this.tipoInteresDescubierto = tipoInteresDescubierto;
        this.maximoDescubierto = maximoDescubierto;
        this.entidadesAutorizadas = new ArrayList<>();
        this.comisionFijaDescubierto = comisionFijaDescubierto;
        this.titular = titular;
    }

    public void setComisionFijaDescubierto(double comisionFijaDescubierto) {
        this.comisionFijaDescubierto = comisionFijaDescubierto;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() +
                "Tipo de Interes por descubierto: " + this.tipoInteresDescubierto + "\n" +
                "Comisión fija por descubierto: " + this.comisionFijaDescubierto + "\n" +
                "Máximo descubierto: " + this.maximoDescubierto;
    }
}
