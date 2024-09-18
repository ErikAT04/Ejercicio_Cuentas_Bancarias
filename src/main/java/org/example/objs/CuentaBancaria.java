package org.example.objs;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public abstract class CuentaBancaria implements Imprimible {
    protected String IBAN;
    protected double saldo;
    protected String tipoCuenta;
    protected Persona titular;

    public CuentaBancaria(String IBAN, double saldo, String tipoCuenta, Persona titular) {
        this.IBAN = IBAN;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.titular = titular;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String devolverInfoString(){
        return  "Cuenta de IBAN " + this.IBAN + ":\n" +
                 (this.titular.devolverInfoString()) + "\n" +
                "Saldo: " + this.saldo + "€\n" +
                "Tipo de cuenta: " + this.tipoCuenta +"\n";
    }
}
