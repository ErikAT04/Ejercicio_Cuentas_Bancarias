package org.example;

import org.example.objs.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Banco banco = new Banco();

    //He creado estos dos objetos arriba ya que van a ser utilizados en el resto de los métodos de la clase

    public static void main(String[] args) {
        int opt;
        CuentaBancaria cuenta;
        do {
            try {
                System.out.println("""
                        Selecciona una opción:
                        1. Abrir cuenta nueva
                        2. Ver cuentas disponibles
                        3. Obtener datos de una cuenta
                        4. Ingresar efectivo
                        5. Retirar efectivo
                        6. Consultar saldo de una cuenta
                        0. Salir
                        """);
                opt = Integer.parseInt(sc.nextLine()); //He parseado la línea que se escriba para que, si en el próximo método se utiliza el Scanner
            }catch (NumberFormatException e){
                opt = -1;
            }
            switch (opt){
                case 1:
                    nuevaCuenta();
                    break;
                case 2: //En este caso, no necesito hacer una nueva clase, ya que voy guardando las cuentas en el arraylist estático
                    System.out.println("Cuentas disponibles: ");
                    for (CuentaBancaria cuentaBancaria : banco.getCuentas()){
                        System.out.println(cuentaBancaria.devolverInfoString());
                        System.out.println("------------------------------------"); //Un separador de información de cada cuenta registrada
                    }
                    break;
                case 3:
                    String iban;
                    do{
                        System.out.println("Escribe el IBAN de la cuenta bancaria: ");
                        iban = sc.nextLine();
                        if(Validator.validarIBAN(iban)){
                            cuenta = buscarCuenta(iban);
                            if (cuenta!=null){
                                System.out.println("Datos de la cuenta: ");
                                System.out.println(cuenta.devolverInfoString());
                            } else {
                                System.out.println("No se ha encontrado ninguna cuenta con ese IBAN");
                            }
                        } else {
                            System.out.println("IBAN no válido");
                            iban = "";
                        }
                    }while (iban.isEmpty());
                    break;
                case 4:
                    System.out.println("Escribe el IBAN de la cuenta bancaria: ");
                    iban = sc.nextLine();
                    if(Validator.validarIBAN(iban)){
                        cuenta = buscarCuenta(iban);
                        if (cuenta!=null){
                            ingresarEfectivo(cuenta);
                        } else {
                            System.out.println("No se ha encontrado ninguna cuenta con ese IBAN");
                        }
                    } else {
                        System.out.println("IBAN no válido");
                        iban = "";
                    }
                    break;
                case 5:
                    System.out.println("Escribe el IBAN de la cuenta bancaria: ");
                    iban = sc.nextLine();
                    if(Validator.validarIBAN(iban)){
                        cuenta = buscarCuenta(iban);
                        if (cuenta!=null){
                            retirarEfectivo();
                        } else {
                            System.out.println("No se ha encontrado ninguna cuenta con ese IBAN");
                        }
                    } else {
                        System.out.println("IBAN no válido");
                        iban = "";
                    }
                    break;
                case 6:
                    System.out.println("Escribe el IBAN de la cuenta bancaria: ");
                    iban = sc.nextLine();
                    if(Validator.validarIBAN(iban)){
                        cuenta = buscarCuenta(iban);
                        if (cuenta!=null){
                            System.out.println("Esta cuenta tiene de saldo " + cuenta.getSaldo() + "€");
                        } else {
                            System.out.println("No se ha encontrado ninguna cuenta con ese IBAN");
                        }
                    } else {
                        System.out.println("IBAN no válido");
                        iban = "";
                    }
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Valor no válido");
            }
            System.out.println(); //Un salto de línea por mera estética
        }while (opt != 0);
    }
    public static void nuevaCuenta(){
        CuentaBancaria cuenta = null;
        System.out.println("Necesitamos un nombre de titular: ");
        String nombre = sc.nextLine();
        System.out.println("¿Cuáles son sus apellidos?");
        String apell = sc.nextLine();
        String dni;
        do{
            System.out.println("Escribe tu DNI: ");
            dni = sc.nextLine();
            if (!Validator.validarDNI(dni)){
                System.out.println("DNI no válido\n");//El salto de línea extra es para que haya algo de separación, mera estética.
                dni = ""; //Vaciamos el DNI, ya que no nos vale. De esta forma, hacemos que no se salga del bucle
            }
        }while (dni.isEmpty());
        /*
        Podría haber hecho que no se saliera hasta que cumpliera la condición, pero llamar dos veces a una clase ajena, una para comprobar un if y otra para comprobar un bucle me parecía excesivo
        Por ende, he hecho un bucle que solo se cierra cuando el string está vacío, cosa que solo ocurre si el dni introducido no es válido.
         */
        Persona titular = new Persona(nombre, apell, dni);
        String iban = "";
        while (!Validator.validarIBAN(iban)) {
            System.out.println("Escriba el número de cuenta (Recuerde que debe ser 'ES' seguido de 20 números):");
            iban = sc.nextLine();
            if (!Validator.validarIBAN(iban)){
                System.out.println("No cumple con los requisitos marcados");
            }
        }
        double saldoInicial;
        do {
            try {
                System.out.println("Escribe la cantidad que quieres ingresar para empezar");
                saldoInicial = Double.parseDouble(sc.nextLine());
                if (saldoInicial >= 0) {
                    break;
                } else {
                    System.out.println("No puedes introducir un saldo negativo");
                }
            }catch (NumberFormatException e){
                System.out.println("Necesitamos que sea un valor numérico. Inténtelo de nuevo.");
            }
        }while (true);
        /*
        Este bucle se encarga de conseguir un número real mayor o igual a 0, sin permitir al usuario que se salga del bucle si este introduce un negativo o un valor no numérico
         */
        String tipoCuenta = "";
        do{
           try{
               System.out.println("""
                       Elige el tipo de cuenta:
                       1. De ahorro
                       2. Cuenta corriente personal
                       3. Cuenta corriente de una empresa""");
               int opt = Integer.parseInt(sc.nextLine());
               switch (opt){
                   case 1:
                       tipoCuenta = "De Ahorro";
                       String tipoInteres="";
                       do{
                           try {
                               System.out.println("""
                                               Define el tipo de interés que quiere:
                                               1. Normal (TIN)
                                               2. Tasa Anual Equivalente (TAE)""");
                               opt = Integer.parseInt(sc.nextLine());
                               switch (opt){
                                   case 1:
                                       tipoInteres = "TIN";
                                       break;
                                   case 2:
                                       tipoInteres = "TAE";
                                       break;
                                   default:
                                       System.out.println("No hay esa opción");
                                       break;
                               }
                           }catch (NumberFormatException e){
                               System.out.println("Escribe un valor numérico");
                           }
                       }while(tipoInteres.isEmpty());
                       cuenta = new CuentaAhorro(iban, saldoInicial, tipoCuenta, tipoInteres, titular);
                       break;
                   case 2:
                       tipoCuenta = "Corriente Personal";
                       ArrayList<String> entidadesPers = introducirEntidades();
                       double comisionMantenimiento = 0;
                       do {
                           try {
                               System.out.println("Escribe la comisión por el mantenimiento de la cuenta");
                               comisionMantenimiento = Double.parseDouble(sc.nextLine());
                               if (comisionMantenimiento >= 0){ //No puede haber una comisión negativa o igual a 0
                                   System.out.println("Tiene que haber una comisión");
                               }
                           } catch (NumberFormatException e) {
                               System.out.println("El valor ha de ser numérico");
                           }
                       }while (comisionMantenimiento >= 0);
                       cuenta = new CuentaCorrientePersonal(iban, saldoInicial, tipoCuenta, comisionMantenimiento, titular);
                       break;
                   case 3:
                       tipoCuenta = "Corriente de Empresa";
                       ArrayList<String> entidadesEmpresa = introducirEntidades();
                       System.out.println("Escribe un máximo descubierto permitido: ");
                       double maximoDescubierto = Double.parseDouble(sc.nextLine());
                       System.out.println("Escribe un tipo de interés por descubierto: ");
                       String tipoInteresDescubierto = sc.nextLine();
                       System.out.println("Indica una comisión fija por descubierto: ");
                       double comisionFijaDescubierto = Double.parseDouble(sc.nextLine());

                       cuenta = new CuentaCorrienteEmpresa(iban, saldoInicial, tipoCuenta, tipoInteresDescubierto, maximoDescubierto, titular, comisionFijaDescubierto);
                       break;
                   default:
                       System.out.println("Número no válido");
                       break;
               }
           }catch (NumberFormatException e){
               System.out.println("Elige un valor numérico");
           }
        }while (tipoCuenta.isEmpty());
        /*
        En este bucle hago uso de una variable extra: el entero de opción. El usuario elige un número entre 1 y 3 para decidir su tipo de cuenta.
        Esta elección cambia la String tipoCuenta.
        Si este valor no cambia porque el usuario ha metido, por ejemplo, un 7 o una letra cualquiera, se repite el bucle.
         */
        banco.getCuentas().add(cuenta); //Una vez acaba, añade la cuenta creada
    }
    public static CuentaBancaria buscarCuenta(String iban){
        CuentaBancaria cuentaABuscar = null;
        for (CuentaBancaria cuenta : banco.getCuentas()){
            if (iban.equals(cuenta.getIBAN())){
                cuentaABuscar = cuenta;
                break;
            }
        }
        return cuentaABuscar;
    }
    public static void ingresarEfectivo(CuentaBancaria cuenta){

    }
    public static void retirarEfectivo(){

    }
    public static ArrayList<String> introducirEntidades(){
        System.out.println("Escribe las entidades autorizadas para cobrar los recibos: ");
        ArrayList<String> entidadesAutorizadas = new ArrayList<>();
        int opt = 0;
        do{
            try {
                System.out.println("¿Quieres añadir alguna entidad?\n1: Si\n Cualquier otro número: No");
                opt = Integer.parseInt(sc.nextLine());
                if (opt == 1) {
                    System.out.println("Escribe el nombre de una entidad: ");
                    String entidad = sc.nextLine();
                    entidadesAutorizadas.add(entidad);
                }
            }catch (NumberFormatException e) {
                System.out.println("Elige un número\n"); //El salto de linea es más estético que otra cosa
                opt = 1;
            }
        }while (opt == 1);
        return entidadesAutorizadas;
    }
}