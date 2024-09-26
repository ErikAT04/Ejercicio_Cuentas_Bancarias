package org.example;

import org.example.objs.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Banco banco = new Banco();

    //He creado estos dos objetos de forma estática y al principio ya que van a ser utilizados en el resto de los métodos de la clase

    public static void main(String[] args) {
        int opt;
        do {
            try { //Introduciendo el control de la excepción dentro del bucle, consigo que no se cierre el programa si hay un error durante la ejecución.
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
                opt = Integer.parseInt(sc.nextLine()); //He parseado la línea que se escriba para que, si en la próxima función se utiliza el Scanner, no haya problemas de lectura. Es más sencillo en esta situación leer líneas y parsear.
            }catch (NumberFormatException e){
                opt = -1; //Si salta la excepción (Se ha introducido una letra), la opción se volverá -1,ya que es un caso que nunca se va a ver en el switch.
            }
            switch (opt){
                case 1:
                    nuevaCuenta();
                    /*
                    Es la función más compleja del código, ya que va desglosándose en distintas ramas y flujos dependiendo de las opciones del usuario.
                    Por temas de orden, he optado por sacar los códigos que sean extensos y necesiten muchas acciones del usuario del main.
                     */
                    break;
                case 2:
                    System.out.println("Cuentas disponibles: ");
                    for (String cuenta : banco.listadoCuentas()){
                        System.out.println(cuenta);
                        System.out.println("------------------------------------"); //Un separador de información de cada cuenta registrada
                    }
                    break;
                case 3:
                    datosCuenta();
                    break;
                case 4, 5:
                    operarEfectivo(opt==4); //Para evitar redundancia de código, he juntado las dos funciones en una misma, llegando a hacer cambios en el flujo del código mediante una booleana que guarda si se ha elegido una opción u otra.
                    break;
                case 6:
                    consultarSaldo();
                    break;
                case 0:
                    System.out.println("Hasta luego!"); //Caso de que se elija 0 para salir del programa
                    break;
                default:
                    System.out.println("Valor no válido");
            }
            System.out.println(); //Un salto de línea por mera estética
        }while (opt != 0);
    }

    public static void nuevaCuenta(){ //Función del caso 1:
        CuentaBancaria cuenta = null; //Declaro el objeto por medio de la clase padre (CuentaBancaria), ya que luego le puedo convertir en cualquiera de sus clases hija
        System.out.println("Necesitamos un nombre de titular: ");
        String nombre = sc.nextLine();
        System.out.println("¿Cuáles son sus apellidos?");
        String apell = sc.nextLine();
        String dni;
        do{
            System.out.println("Escribe el DNI: ");
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
        Este bucle se encarga de conseguir un número real mayor o igual a 0, sin permitir al usuario que se salga del bucle si este introduce un negativo o un valor no numérico.
        Se podría hacer poniendo en la condición del bucle (saldoInicial < 0), e implicaría controlar el saldo en el catch.
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
                    case 1: //Caso de la cuenta de ahorro
                        tipoCuenta = "De Ahorro";
                        String tipoInteres=""; //Declaro el tipo de interés como un string vacío, el cual será utilizado para controlar la condición del bucle.
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
                        }while(tipoInteres.isEmpty()); //El tipo de interés dejará de estar vacío únicamente si se elige el caso 1 o 2
                        cuenta = new CuentaAhorro(iban, saldoInicial, tipoCuenta, tipoInteres, titular); //Creo el objeto ahora con el tipo CuentaAhorro, una de las clases hija de CuentaBancaria.
                        break;
                    case 2:
                        tipoCuenta = "Corriente Personal";
                        ArrayList<String> entidadesPers = introducirEntidades(); //Accede a una nueva función creada para reducir código, ya que la introducción de entidades se ve involucrada en dos casos del código
                        double comisionMantenimiento;
                        do {
                            try {
                                System.out.println("Escribe la comisión por el mantenimiento de la cuenta");
                                comisionMantenimiento = Double.parseDouble(sc.nextLine());
                                if (comisionMantenimiento <= 0){ //No puede haber una comisión negativa o igual a 0
                                    System.out.println("Tiene que haber una comisión");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("El valor ha de ser numérico");
                                comisionMantenimiento = 0; //Se controla que no se pueda salir de la condición si hay una excepción por introducir un valor no numérico. Se iguala a 0 para que no salga del bucle
                            }
                        }while (comisionMantenimiento <= 0);
                        cuenta = new CuentaCorrientePersonal(iban, saldoInicial, tipoCuenta, titular, entidadesPers, comisionMantenimiento); //Crea un objeto de CuentaCorrientePersonal, hija de CuentaCorriente, hija de CuentaBancaria

                        break;
                    case 3:
                        tipoCuenta = "Corriente de Empresa";
                        ArrayList<String> entidadesEmpresa = introducirEntidades(); //Se accede al método de introducción de entidades creado para evitar repetir código
                        double maximoDescubierto;
                        double comisionFijaDescubierto;
                        do { //Bucle del máximo descubierto
                            try {
                                System.out.println("Escribe un máximo descubierto permitido: ");
                                maximoDescubierto = Double.parseDouble(sc.nextLine());
                                if (maximoDescubierto < 0){
                                    System.out.println("El máximo descubierto no puede ser negativo");
                                }
                            }catch (NumberFormatException e){
                                System.out.println("Tienes que introducir un valor numérico");
                                maximoDescubierto = -1; //Se iguala a -1 para que no salga del bucle tras la excepción
                            }
                        }while (maximoDescubierto < 0);
                        System.out.println("Escribe un tipo de interés por descubierto: ");
                        String tipoInteresDescubierto = sc.nextLine();
                        do { //Bucle de la comisión fija
                            try {
                                System.out.println("Indica una comisión fija por descubierto: ");
                                comisionFijaDescubierto = Double.parseDouble(sc.nextLine());
                                if (comisionFijaDescubierto < 0){
                                    System.out.println("La comision tiene que ser mayor o igual a 0");
                                } else if (comisionFijaDescubierto > maximoDescubierto) { //La comisión fija no puede ser mayor al máximo descubierto, por lo que se debería controlar ese caso
                                    System.out.println("La comisión fija no puede ser mayor al máximo");
                                }
                            }catch (NumberFormatException e){
                                System.out.println("La comisión fija tiene que ser un número.");
                                comisionFijaDescubierto = -1; //Se iguala a -1
                            }
                        }while (comisionFijaDescubierto<0 || comisionFijaDescubierto > maximoDescubierto); //Con el operador lógico OR se meten dos condiciones para que no se pueda salir del bucle si la comisión es negativa o si el máximo es menor a dicha comisión
                        cuenta = new CuentaCorrienteEmpresa(iban, saldoInicial, tipoCuenta, tipoInteresDescubierto, maximoDescubierto, titular, comisionFijaDescubierto, entidadesEmpresa); //Se crea un objeto de la clase CuentaCorrienteEmpresa, hija de CuentaCorriente, hija de CuentaBancaria
                        break;
                    default: //Default referente al tipo de cuenta bancaria que se va a crear. Si no es ningún número entre 1 y 3, llegará aquí
                        System.out.println("Número no válido");
                        break;
                }
            }catch (NumberFormatException e){ //En el caso de que meta un valor no entero:
                System.out.println("Elige un valor numérico");
            }
        }while (tipoCuenta.isEmpty());
        /*
        Explicación del while:
        En este bucle hago uso de una variable extra: el entero de opción. El usuario elige un número entre 1 y 3 para decidir su tipo de cuenta.
        Esta elección cambia la String tipoCuenta.
        Si este valor no cambia porque el usuario ha metido, por ejemplo, un 7 o una letra cualquiera, se repite el bucle.
         */
        if(banco.abrirCuenta(cuenta)){ //Introduce la cuenta en el banco, si da fallo la booleana será falsa (En un principio, por la estructura de mi código, es raro que de falso)
            System.out.println("Cuenta creada correctamente");
        } else {
            System.out.println("Ha habido un error con la creación de su cuenta");
        }
    }

    private static void datosCuenta() { //Método del caso 3
        System.out.println("Escribe el iban de la cuenta a consultar: ");
        String iban = sc.nextLine();
        String infoCuenta = banco.informacionCuenta(iban);
        if (infoCuenta!=null){
            System.out.println(infoCuenta);
        } else {
            System.out.println("Cuenta no encontrada");
        }
    }

    private static void operarEfectivo(boolean ingreso) { //Método de los casos 4 y 5
        System.out.println("Escribe el IBAN de la cuenta bancaria: ");
        String iban = sc.nextLine();
        double cantidadOperar;
        if(Validator.validarIBAN(iban)){
            do {
                try {
                    System.out.println("Escribe la cantidad a " + (ingreso ? "ingresar" : "retirar") + ":");
                    cantidadOperar = Double.parseDouble(sc.nextLine()); //Esta variable es común a ambos, que es la cantidad que se quiere usar
                    if (cantidadOperar<=0){
                        System.out.println("No puedes escribir una cantidad menor o igual a 0");
                    } else {
                        if (ingreso){ //Si se ha elegido el caso 4: Ingresar
                            if(banco.ingresoCuenta(iban, cantidadOperar)){ //El método devuelve true si se ha hecho el ingreso, lo que se cumple siempre y cuando existe una cuenta con el iban que se ha dado.
                                System.out.println("Ingreso realizado correctamente");
                            } else {
                                System.out.println("No se ha encontrado la cuenta");
                            }
                        } else {
                            if (banco.retiradaCuenta(iban, cantidadOperar)){ //El método devuelve true si se ha retirado el dinero, para lo que se necesita que haya una cuenta y que la cantidad a sacar sea menor al saldo de la cuente
                                System.out.println("Retirada realizada correctamente");
                            } else {
                                System.out.println("Ha ocurrido un error, puede deberse a que la cuenta no exista o que la cantidad a sacar fuera superior al saldo");
                            }
                        }
                    }
                }catch (NumberFormatException e){
                    System.out.println("Debes introducir un número");
                    cantidadOperar = 0;
                }
            }while (cantidadOperar<=0);
        } else {
            System.out.println("IBAN no válido");
        }
    }

    private static void consultarSaldo() { //Método del caso 6
        System.out.println("Escribe el IBAN de la cuenta bancaria: ");
        String iban = sc.nextLine();
        if(Validator.validarIBAN(iban)){
            double saldo = banco.obtenerSaldo(iban);
            if (saldo!=-1){
                System.out.println("Esta cuenta tiene de saldo " + saldo + "€");
            } else { //La única forma que puede dar -1 de saldo es si, en obtenerSaldo, el iban enviado no existe
                System.out.println("No se ha encontrado ninguna cuenta con ese IBAN");
            }
        } else {
            System.out.println("IBAN no válido");
        }
    }



    public static ArrayList<String> introducirEntidades(){
        ArrayList<String> entidadesAutorizadas = new ArrayList<>();
        int opt;
        do{
            try {
                System.out.println("¿Quieres añadir alguna entidad a esta cuenta?\n1: Si\nCualquier otro número: No");
                opt = Integer.parseInt(sc.nextLine());
                if (opt == 1) {
                    System.out.println("Escribe el nombre de una entidad: ");
                    String entidad = sc.nextLine();
                    entidadesAutorizadas.add(entidad); //Añade la entidad
                }
            }catch (NumberFormatException e) {
                System.out.println("Elige un número\n"); //El salto de linea es más estético que otra cosa
                opt = 1;
            }
        }while (opt == 1);
        return entidadesAutorizadas; //Devuelve el arraylist entero
    }
}