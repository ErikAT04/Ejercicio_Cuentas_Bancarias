package org.example.objs;

import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }
    public boolean abrirCuenta(CuentaBancaria cuenta){
        return false;
    }
    public ArrayList<String> listadoCuentas(){
        return null;
    }
    public String informacionCuenta(String iban){
        return null;
    }
    public boolean ingresoCuenta(String iban, double ingreso){
        return false;
    }
    public boolean retiradaCuenta(String iban, double retirada){
        return false;
    }
    public double obtenerSaldo(String iban){
        return 0;
    }

    public ArrayList<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
}
