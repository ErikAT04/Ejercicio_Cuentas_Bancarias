package org.example.objs;

public abstract class CuentaBancaria implements Imprimible {
    protected String IBAN;
    protected double saldo;
    protected String tipoCuenta;
    protected Persona titular;

    //Los atributos son protected, mostrando que son "privados", pero sirven para que las clases hija las hereden

    public CuentaBancaria(String IBAN, double saldo, String tipoCuenta, Persona titular) {
        this.IBAN = IBAN;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.titular = titular;
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String devolverInfoString(){
        return  "Cuenta de IBAN " + this.IBAN + ":\n" +
                 (this.titular.devolverInfoString()) + "\n" +
                "Saldo: " + this.saldo + "€\n" +
                "Tipo de cuenta: " + this.tipoCuenta +"\n";
    }
}
