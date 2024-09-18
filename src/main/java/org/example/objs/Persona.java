package org.example.objs;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Persona implements Imprimible {
    private String nombre;
    private String apellidos;
    private String dni;

    public Persona(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    @Override
    public String devolverInfoString() {
        return "[Propietario: '" + this.nombre + " " + this.apellidos + "', de DNI: '" + this.dni + "]" ;
    }

    //En esta clase no necesito ningún tipo de getter ni setter, ya que no edito información más allá de a la hora de crear el objeto.
}
