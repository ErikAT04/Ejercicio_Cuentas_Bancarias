package org.example.objs;

import java.util.ArrayList;

public class CuentaCorrientePersonal extends CuentaCorriente{
    private double comisionMantenimiento;

    public CuentaCorrientePersonal(String IBAN, double saldo, String tipoCuenta, Persona titular, ArrayList<String> entidadesAutorizadas, double comisionMantenimiento) {
        super(IBAN, saldo, tipoCuenta, titular, entidadesAutorizadas);
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() +
                "Comisión por Mantenimiento: " + this.comisionMantenimiento;
    }
}
