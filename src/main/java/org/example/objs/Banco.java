package org.example.objs;

import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas; //Este atributo es lista de cuentas que hay guardadas en el banco

    public Banco() { //Método constructor
        cuentas = new ArrayList<>();
    }
    public boolean abrirCuenta(CuentaBancaria cuenta){ //Método utilizado para guardar cuentas en la lista
        return this.cuentas.add(cuenta); //Si se añade correctamente, devuelve true.
    }
    public String[] listadoCuentas(){ //Método utilizado para obtener la lista de las cuentas.
        String[] lista = new String[cuentas.size()]; //Como se va a guardar la misma cantidad de cuentas que la que hay en el arrayList de tipo cuenta, hacemos el array del tamaño de dicha lista.
        for (int i = 0; i<cuentas.size(); i++){
            lista[i] = cuentas.get(i).devolverInfoString(); //Introduzco en cada hueco del array el metodo de devolver información en string (una cadena de caracteres que, por medio de un override, guarda la información de la clase)
        }
        return lista; //Devuelve el array con las cuentas.
    }

    public String informacionCuenta(String iban){ //Método que recibe el iban y busca una cuenta para dar su información
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                return c.devolverInfoString(); //Si se encuentra la cuenta, devuelve la cadena de caracteres con la información de la cuenta, terminado así la función

            }
        }
        return null; //Devuelve nulo si no ha encontrado ninguna cuenta.
    }
    public boolean ingresoCuenta(String iban, double cantidad){ //Método que recibe un iban y una cantidad para realizar un ingreso
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                c.setSaldo(c.getSaldo() + cantidad); //Suma el saldo y la cantidad, y actualiza el saldo si encuentra la cuenta, además de devolver true y terminar el método
                return true;
            }
        }
        return false; //Devuelve falso si no se ha encontrado una cuenta, lo que significa que no se ha hecho ningún cambio con el saldo dado
    }

    public boolean retiradaCuenta(String iban, double cantidad){ //Método que recibe un iban y una cantidad para realizar una retirada de dinero
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                if (c.getSaldo()>=cantidad){ //Para sacar el dinero necesita cumplir dos condiciones: Que la cuenta exista y que el saldo sea mayor a lo que desea sacar
                    c.setSaldo(c.getSaldo()-cantidad); //Se resta la cantidad a sacar del sueldo, se actualiza y devuelve true
                    return true;
                }
            }
        }
        return false; //Devuelve falso si no se ha operado con el saldo, ya sea porque no se ha encontrado la cuenta o si el saldo era menor que la cantidad a sacar.
    }
    public double obtenerSaldo(String iban){ //Método que recibe el iban, busca la cuenta y devuelve el saldo si este existe
        for (CuentaBancaria c : cuentas){
            if (c.getIBAN().equals(iban)){
                return c.getSaldo(); //Devuelve el saldo de la cuenta.
            }
        }
        return -1; //Aquí solo llegará en caso de que no haya encontrado ninguna cuenta.
    }
}
