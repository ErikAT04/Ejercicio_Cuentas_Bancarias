package org.example.objs;

public abstract class CuentaBancaria implements Imprimible {
    protected String IBAN; //Guarda el número de cuenta
    protected double saldo; //Guarda el saldo de la cuenta
    protected String tipoCuenta; //Guarda un string para diferenciar la cuenta que tiene
    protected Persona titular; //Un objeto de tipo Persona que guarda los datos del titular de la cuenta

    //Los atributos son protected, mostrando que son "privados", pero sirven para que las clases hija las hereden

    public CuentaBancaria(String IBAN, double saldo, String tipoCuenta, Persona titular) { //Método constructor
        this.IBAN = IBAN;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.titular = titular;
    }

    public String getIBAN() { //Getter del IBAN: guarda el iban y lo pasa como valor
        return IBAN;
    }

    public double getSaldo() { //Getter del saldo
        return saldo;
    }

    public void setSaldo(double saldo) { //Setter del saldo: Recibe un valor que sustituye al saldo de la instancia
        this.saldo = saldo;
    }

    public String devolverInfoString(){ //Método de la interfaz imprimible: Devuelve un string con toda la información de la instancia ordenada
        return  "Cuenta de IBAN " + this.IBAN + ":\n" +
                 (this.titular.devolverInfoString()) + "\n" +
                "Saldo: " + this.saldo + "€\n" +
                "Tipo de cuenta: " + this.tipoCuenta +"\n";
    }
}
