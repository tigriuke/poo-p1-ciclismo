/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Competidor;
import vista.VistaMundial;

/**
 *
 * @author coffe
 */
public class ControladorMundial {
    private VistaMundial vista;
    private Competidor[] competidores;

    public ControladorMundial(VistaMundial vista) {
        this.vista = vista;
    }
    
    
    public void inicio(){
        int numeroDeCompetidores;
        //Se obtiene el numero de competidores y se valida
        do{
            numeroDeCompetidores = vista.ingresarNumeroEntero("Ingrese el numero de competidores:");
            if (numeroDeCompetidores <= 0){
                vista.mostrarError("Debe haber al menos 1 competidor.");
            }
        } while(numeroDeCompetidores <= 0);
        
        competidores = new Competidor[numeroDeCompetidores];
        
        for(int i = 0; i < competidores.length ; i++){
            competidores[i] = registrarCompetidor();
        }
        
        menu();
        vista.mostrarMensaje("==PROGRAMA FINALIZADO==");
        
    }
    public void menu(){
        while(true){
            String respuesta;
            do{
                respuesta = vista.ingresarTexto("Escriba 'actualizar' para actualizar el puntaje, 'buscar' para mostrar la informacion de un competidor o 'salir' para finalizar el programa:").trim();
                if(respuesta.isEmpty()){
                    vista.mostrarError("Respuesta invalida, ingrese 'salir', 'buscar', o 'actualizar'.");
                }
            } while (respuesta.isEmpty());

            if (respuesta.equalsIgnoreCase("salir")){
                return;
            }
            else if (respuesta.equalsIgnoreCase("buscar")){
                //Se obtiene el dato del nombre a buscar
                String nombre;
                //Se valida
                do {
                    nombre = vista.ingresarTexto("Ingrese el nombre del competidor a buscar:");
                    if (nombre.trim().isEmpty()) {
                        vista.mostrarError("El nombre no puede ser vacío.");
                    }
                } while (nombre.trim().isEmpty());

                buscarCompetidor(nombre);
            }
            else if (respuesta.equalsIgnoreCase("actualizar")){
                //Se obtiene el dato del nombre a buscar
                String nombre;
                //Se valida
                do {
                    nombre = vista.ingresarTexto("Ingrese el nombre del competidor para actualizar su puntaje:");
                    if (nombre.trim().isEmpty()) {
                        vista.mostrarError("El nombre no puede ser vacío.");
                    }
                } while (nombre.trim().isEmpty());
                
                
                    
                
                actualizarPuntaje(nombre);
            }
            else{
                vista.mostrarError("Respuesta invalida, ingrese 'salir', 'buscar', o 'actualizar'.");
            }


        }

    }
    public Competidor registrarCompetidor(){
        //Se obtiene el dato del nombre
        String nombre;
        //Se valida
        do {
            nombre = vista.ingresarTexto("Ingrese el nombre del competidor:");
            if (nombre.trim().isEmpty()) {
                vista.mostrarError("El nombre no puede ser vacío.");
            }
        } while (nombre.trim().isEmpty());

        int edad;
        //Se obtiene y valida la edad (entre 15 y 60)
        do {
            edad = vista.ingresarNumeroEntero("Ingrese la edad del competidor:");
            if (edad < 15 || edad > 60) {
                vista.mostrarError("La edad debe estar entre 15 y 60.");
            }
        } while (edad < 15 || edad > 60);

        String pais;
        //Se obtiene y valida el pais
        do {
            pais = vista.ingresarTexto("Ingrese el pais de residencia del competidor:");
            if (pais.trim().isEmpty()) {
                vista.mostrarError("El país no puede ser vacío.");
            }
        } while (pais.trim().isEmpty());

        int ranking;
        //Se obtiene y valida el ranking mundial
        do {
            ranking = vista.ingresarNumeroEntero("Ingrese el ranking mundial inicial del competidor:");
            if (ranking < 1) {
                vista.mostrarError("El ranking debe ser 1 o mayor.");
            }
        } while (ranking < 1);

        double peso;
        //Se obtiene y valida el peso
        do {
            peso = vista.ingresarNumeroDecimal("Ingrese el peso del competidor:");
            if (peso < 40 || peso > 130) {
                vista.mostrarError("El peso debe estar entre 40 y 130 kg.");
            }
        } while (peso < 40 || peso > 130);
        
        double estatura;
        //Se obtiene y valida la estatura
        do {
            estatura = vista.ingresarNumeroDecimal("Ingrese la estatura del competidor en centimetros:");
            if (estatura < 130 || estatura > 220) {
                vista.mostrarError("La estatura debe estar entre 130 y 220cm (o 1.30 y 2.20 m).");
            }
        } while (estatura < 130 || estatura > 220);
       
        
        Competidor c = new Competidor(nombre, edad, pais, ranking, estatura, peso);
        return c;
    }
    
    public void buscarCompetidor(String nombre){
        for(Competidor c : competidores){
            if(c.getNombre().trim().equalsIgnoreCase(nombre)){
                vista.mostrarMensaje(c.toString());
                return;
            }
        }
        vista.mostrarMensaje("Competidor " + nombre + " no encontrado.");
    }
    
    public void actualizarPuntaje(String nombre){
        for(Competidor c : competidores){
            if(c.getNombre().trim().equalsIgnoreCase(nombre)){
                boolean ganoMedalla = vista.preguntarSiNo("¿Este competidor gano alguna medalla?");
                //Se obtienen los puntos y se valida que sean positivos
                int puntos;
                do{
                    puntos = vista.ingresarNumeroEntero("Ingrese el numero de puntos a sumarle a este competidor:");
                    if (puntos <= 0){
                        vista.mostrarError("Los puntos a sumar deben ser mayores a 0.");
                    }
                } while(puntos <= 0);
                
                
                if (ganoMedalla){
                    c.actualizarRanking(puntos, ganoMedalla);
                }
                else{
                    c.actualizarRanking(puntos);
                }
                
                vista.mostrarMensaje(c.toString());
                return;
            }
        }
        vista.mostrarMensaje("Competidor " + nombre + " no encontrado.");
       
        
        
        
    }
}
