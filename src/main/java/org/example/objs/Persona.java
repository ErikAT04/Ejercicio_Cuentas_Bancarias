package org.example.objs;

public class Persona implements Imprimible {
    private String nombre; //Nombre del titular de la cuenta
    private String apellidos; //Apellidos del titular
    private String dni; //DNI del titular

    public Persona(String nombre, String apellidos, String dni) { //Método constructor
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    @Override
    public String devolverInfoString() { //Método de la interfaz Imprimible, encargado de devolver una cadena de caracteres con toda la información del propietario de la cuenta
        return "[Propietario: '" + this.nombre + " " + this.apellidos + "', de DNI: '" + this.dni + "]" ;
    }

    //En esta clase no necesito ningún tipo de getter ni setter, ya que no edito información más allá de a la hora de crear el objeto.
}
