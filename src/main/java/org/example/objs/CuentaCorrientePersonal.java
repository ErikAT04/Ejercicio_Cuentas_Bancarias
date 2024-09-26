package org.example.objs;

import java.util.ArrayList;

public class CuentaCorrientePersonal extends CuentaCorriente{
    private double comisionMantenimiento; //Guarda la comisión por mantenimiento de la cuenta

    public CuentaCorrientePersonal(String IBAN, double saldo, String tipoCuenta, Persona titular, ArrayList<String> entidadesAutorizadas, double comisionMantenimiento) { //Método constructor
        super(IBAN, saldo, tipoCuenta, titular, entidadesAutorizadas); //Guarda los atributos de la clase padre
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public String devolverInfoString() { //Método sobrecargado de la clase padre: Añade la comisión por mantenimiento del objeto
        return super.devolverInfoString() +
                "Comisión por Mantenimiento: " + this.comisionMantenimiento;
    }
}
