package org.example.objs;

import java.util.ArrayList;

public class CuentaCorrienteEmpresa extends CuentaCorriente{
    private String tipoInteresDescubierto; //Guarda una cadena con el tipo de interés al descubierto
    private double maximoDescubierto; //Guarda el máximo al descubierto de la cuenta
    private double comisionFijaDescubierto; //Guarda la comisión fija al descubierto de la instancia

    public CuentaCorrienteEmpresa(String IBAN, double saldo, String tipoCuenta, String tipoInteresDescubierto, double maximoDescubierto, Persona titular, double comisionFijaDescubierto, ArrayList<String> entidadesAutorizadas) { //Método constructor
        super(IBAN, saldo, tipoCuenta, titular, entidadesAutorizadas); //Guardo todos los atributos de la clase padre
        this.tipoInteresDescubierto = tipoInteresDescubierto;
        this.maximoDescubierto = maximoDescubierto;
        this.comisionFijaDescubierto = comisionFijaDescubierto;
        this.titular = titular;
    }


    @Override
    public String devolverInfoString() { //Método actualizado del padre: Añade los atributos de esta clase a la información de la cuenta
        return super.devolverInfoString() +
                "Tipo de Interes por descubierto: " + this.tipoInteresDescubierto + "\n" +
                "Comisión fija por descubierto: " + this.comisionFijaDescubierto + "\n" +
                "Máximo descubierto: " + this.maximoDescubierto;
    }
}
