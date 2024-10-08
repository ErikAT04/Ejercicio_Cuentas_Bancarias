package org.example.objs;

import java.util.ArrayList;

public abstract class CuentaCorriente extends CuentaBancaria {

    protected ArrayList<String> entidadesAutorizadas; //Lista de nombres de las entidades autorizadas de la cuenta corriente

    public CuentaCorriente(String IBAN, double saldo, String tipoCuenta, Persona titular, ArrayList<String> entidadesAutorizadas) {
        super(IBAN, saldo, tipoCuenta, titular); //Sentencia que guarda los atributos heredados
        this.entidadesAutorizadas = entidadesAutorizadas;
    }

    @Override
    public String devolverInfoString() { //Método actualizado de la herencia: Añade la lista de entidades a la cadena de valores de la cuenta
        StringBuilder listaEntidades = new StringBuilder(); //Creo un objeto de tipo StringBuilder. Este cumple la misma función que un String, salvo que en este caso se utiliza su función append(String), el cual hace la función de añadir al final del string otra cadena de caracteres
        for (String s : this.entidadesAutorizadas){
            listaEntidades.append(s);
            if (entidadesAutorizadas.iterator().hasNext()){ //Si en la lista quedan más entidades, se añade ", " para separarlos. Es mera estética pero queda mejor visualmente que ponerlo de cualquier forma
                listaEntidades.append(", ");
            }
        }
        return super.devolverInfoString() + "Lista de entidades autorizadas: [" + listaEntidades + "]\n"; //El StringBuilder no necesita ser convertido a String de vuelta con el .toString, simplemente se puede concatenar.
    }
}
