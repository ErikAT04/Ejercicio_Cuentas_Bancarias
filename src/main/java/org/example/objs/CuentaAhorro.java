package org.example.objs;

public class CuentaAhorro extends CuentaBancaria{
    private String tipoInteresAnual; //Un String que sirve para diferenciar su tipo de interés
    public CuentaAhorro(String IBAN, double saldo, String tipoCuenta, String tipoInteresAnual, Persona titular) {
        super(IBAN, saldo, tipoCuenta, titular); //Dentro del constructor, esta sentencia actualiza los atributos que recibe de la clase padre
        this.tipoInteresAnual = tipoInteresAnual;
    }

    @Override
    public String devolverInfoString() { //Clase heredada actualizada con sobrecarga: Recibe el string del padre y añade el interés anual
        return super.devolverInfoString() + "Tipo de interés anual: " + this.tipoInteresAnual;
    }

}
