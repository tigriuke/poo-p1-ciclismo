/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author coffe
 */
public class VistaMundial {

    private Scanner scanner = new Scanner(System.in);

    // Pide un texto por consola. La validación (vacío, etc.) la hace el controlador.
    public String ingresarTexto(String mensaje) {
        System.out.print(mensaje + " ");
        return scanner.nextLine().trim();
    }

    // Repite la pregunta hasta que el usuario escriba un entero válido.
    public int ingresarNumeroEntero(String mensaje) {
        while (true) {
            String texto = ingresarTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                mostrarError("Debe ingresar un número entero válido.");
            }
        }
    }

    // Repite la pregunta hasta que el usuario escriba un decimal válido.
    // Acepta coma o punto como separador (1,75 o 1.75).
    public double ingresarNumeroDecimal(String mensaje) {
        while (true) {
            String texto = ingresarTexto(mensaje).replace(',', '.');
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                mostrarError("Debe ingresar un número decimal válido.");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
       System.err.println("[ERROR] "+ mensaje);
    }

    // true = s, false = n
    public boolean preguntarSiNo(String mensaje) {
        while(true){
          String respuesta = ingresarTexto(mensaje + " (s/n): ").toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")){
                return true;
            }else if (respuesta.equals("n") || respuesta.equals("no")){
                return false;
            }else{
                mostrarError("Respuesta inválida. Por favor ingrese 's' o 'n'.");
            }
        }
    }
}
