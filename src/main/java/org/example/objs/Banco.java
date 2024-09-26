package org.example.objs;

import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }
    public boolean abrirCuenta(CuentaBancaria cuenta){
        return this.cuentas.add(cuenta); //Si se añade correctamente, devuelve true.
    }
    public String[] listadoCuentas(){
        String[] lista = new String[cuentas.size()]; //Como se va a guardar la misma cantidad de cuentas que la que hay en el arrayList de tipo cuenta, hacemos el array del tamaño de dicha lista.
        for (int i = 0; i<cuentas.size(); i++){
            lista[i] = cuentas.get(i).devolverInfoString(); //Introduzco en cada hueco del array el metodo de devolver información en string (una cadena de caracteres que, por medio de un override, guarda la información de la clase)
        }
        return lista;
    }

    public String informacionCuenta(String iban){
        String infoCuenta = null;
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                infoCuenta = c.devolverInfoString();
                break;
            }
        }
        return infoCuenta; //Devuelve nulo si no ha encontrado ninguna cuenta.
    }
    public boolean ingresoCuenta(String iban, double cantidad){
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                c.setSaldo(c.getSaldo() + cantidad);
                return true;
            }
        }
        return false; //Devuelve falso si no se ha encontrado una cuenta, lo que significa que no se ha hecho ningún cambio con el saldo dado
    }

    public boolean retiradaCuenta(String iban, double cantidad){
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                if (c.getSaldo()>=cantidad){
                    c.setSaldo(c.getSaldo()-cantidad);
                    return true;
                }
            }
        }
        return false; //Devuelve falso si no se ha operado con el saldo, ya sea porque no se ha encontrado la cuenta o si el saldo era menor que la cantidad a sacar.
    }
    public double obtenerSaldo(String iban){
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                return c.getSaldo(); //Devuelve el saldo de la cuenta.
            }
        }
        return -1; //Aquí solo llegará en caso de que no haya encontrado ninguna cuenta.
    }
}
