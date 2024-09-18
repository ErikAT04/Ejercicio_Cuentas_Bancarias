package org.example.objs;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.ArrayList;

public class CuentaCorrientePersonal extends CuentaCorriente{
    private double comisionMantenimiento;
    public CuentaCorrientePersonal(String IBAN, double saldo, String tipoCuenta, double comisionMantenimiento, Persona titular) {
        super(IBAN, saldo, tipoCuenta, titular);
        this.comisionMantenimiento = comisionMantenimiento;
        this.entidadesAutorizadas = new ArrayList<>();
    }

    public double getComisionMantenimiento() {
        return comisionMantenimiento;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() +
                "Comisión por Mantenimiento: " + this.comisionMantenimiento;
    }
}
