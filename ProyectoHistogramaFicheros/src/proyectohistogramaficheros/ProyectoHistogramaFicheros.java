/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectohistogramaficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class ProyectoHistogramaFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] numPalabras = new int[15];
        Vector palabras = new Vector();
        String ruta;
        Scanner teclado = new Scanner(System.in);
        iniciarArrayACero(numPalabras);

        File arch = new File("lorem");
        if (arch.exists()) {
            procesaFichero(arch, numPalabras, palabras);
            buscarModa(palabras);
        } else {
            System.out.println("No existe el fichero introducido");
        }
    }

    public static void procesaFichero(File arch, int[] numPalabras, Vector palabras) {
        try {
            Scanner sc = new Scanner(arch);
            String palabra;
            int numCar;
            while (sc.hasNext()) {
                palabra = sc.next();
                numCar = palabra.length();
                numPalabras[numCar] = numPalabras[numCar] + 1;
                palabras.add(palabra);
            }
            recorreVector(numPalabras);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoHistogramaFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void iniciarArrayACero(int[] numPalabras) {
        for (int i = 0; i < numPalabras.length; i++) {
            numPalabras[i] = 0;
        }
    }

    public static void recorreVector(int[] numPalabras) {
        for (int i = 0; i < numPalabras.length; i++) {
            if (numPalabras[i] > 0) {
                imprimirAsteristicos(numPalabras[i], i);
                imprimirAsteristicos(numPalabras[i], i);
            } else {
                System.out.println("[" + i + "]");
                System.out.println("[" + i + "]");
            }
        }
    }

    public static void imprimirAsteristicos(int numVeces, int numArray) {
        boolean unaVez = false;
        for (int i = 0; i < numVeces; i++) {
            if (unaVez == false) {
                System.out.print("[" + numArray + "]");
            }
            unaVez = true;
            System.out.print("*");

        }
        System.out.println("");
    }

    public static void buscarModa(Vector palabras) {
        String palabraMasRepetida = "";
        int numVecesRepetida = 0;
        int contadorReps=0;
        for (int i = 0; i < palabras.size(); i++) {
            for (int j = 1; j < palabras.size() - 1; j++) {
                
                if (palabras.get(i).equals(palabras.get(j))) {
                    contadorReps = contadorReps + 1;
                    palabras.remove(j);
                }
            }
            if (contadorReps > numVecesRepetida) {
                palabraMasRepetida = (String) palabras.get(i);
                numVecesRepetida = contadorReps;
            }
            contadorReps=0;
        }
        System.out.println("La palabra mas repetida es " + palabraMasRepetida + " y se repite " + numVecesRepetida);
    }

}
