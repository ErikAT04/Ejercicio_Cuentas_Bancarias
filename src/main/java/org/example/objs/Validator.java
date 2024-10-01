package org.example.objs;

public class Validator {
    public static boolean validarIBAN(String iban) { //Método de validación del IBAN
        return iban.matches("ES[0-9]{20}"); //Se encarga de que el iban sea ES seguido de 20 números
    }
    public static boolean validarDNI(String dni){ //Método de validación del DNI
        if (dni.matches("[0-9]{8}[A-Z]")){ //Se encarga de que el DNI tenga el valor 'NNNNNNNNX', siendo N un número y X una letra
            int numDNI = Integer.parseInt(dni.substring(0, 8)); //Aquí cogemos el número del DNI. Como los strings al igual que los arrays y colecciones, empieza en una posición 0, sabiendo que hay 8 dígitos, cogemos hasta el de la posición 7
            char letraDNI = switch (numDNI%23) { //Fórmula para sacar la letra del dni: DNI/23 y el resto se asigna a un número específico
                case 0 -> 'T';
                case 1 -> 'R';
                case 2 -> 'W';
                case 3 -> 'A';
                case 4 -> 'G';
                case 5 -> 'M';
                case 6 -> 'Y';
                case 7 -> 'F';
                case 8 -> 'P';
                case 9 -> 'D';
                case 10 -> 'X';
                case 11 -> 'B';
                case 12 -> 'N';
                case 13 -> 'J';
                case 14 -> 'Z';
                case 15 -> 'S';
                case 16 -> 'Q';
                case 17 -> 'V';
                case 18 -> 'H';
                case 19 -> 'L';
                case 20 -> 'C';
                case 21 -> 'K';
                default -> 'E'; //El único caso que no cubría el switch es si el resto fuera 22, por lo que declaramos ese caso como el Default
            };
            return letraDNI == dni.charAt(8); //Si la letra del DNI coincide con la de la fórmula, devuelve true. En el caso opuesto, devuelve false
        } else {
            return false; //Si la condición de dni.matches no se cumple, no comprueba la otra condición y devuelve falso directamente, puesto que no es un dni.
        }
    }
}
