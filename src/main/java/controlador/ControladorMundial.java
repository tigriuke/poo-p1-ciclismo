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
            numeroDeCompetidores = vista.cuantosCompetidores();
            if (numeroDeCompetidores <= 0){
                vista.mostrarError("Debe haber al menos 1 competidor.");
            }
        } while(numeroDeCompetidores <= 0);
        
        competidores = new Competidor[numeroDeCompetidores];
        
        for(int i = 0; i < competidores.length ; i++){
            competidores[i] = registrarCompetidor();
        }
        
        menu();
        
        
    }
    public void menu(){
        while(true){
            String respuesta;
            do{
                respuesta = vista.obtenerRespuesta().trim();
                if(respuesta.isEmpty()){
                    vista.mostrarError("Respuesta invalida, ingrese 'salir', 'buscar', o 'sumar' )");
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
                    nombre = vista.pedirNombreABuscar();
                    if (nombre.trim().isEmpty()) {
                        vista.mostrarError("El nombre no puede ser vacío");
                    }
                } while (nombre.trim().isEmpty());

                buscarCompetidor(nombre);
            }
            else if (respuesta.equalsIgnoreCase(sumar)){
                //Se obtiene el dato del nombre a buscar
                String nombre;
                //Se valida
                do {
                    nombre = vista.pedirNombreABuscar();
                    if (nombre.trim().isEmpty()) {
                        vista.mostrarError("El nombre no puede ser vacío");
                    }
                } while (nombre.trim().isEmpty());
                
                boolean ganoMedalla;
                ganoMedalla = vista.preguntarSiONo("¿Este competidor gano alguna medalla?");
                    
                
                actualizarPuntaje(nombre, ganoMedalla);
            }


        }

    }
    public Competidor registrarCompetidor(){
        //Se obtiene el dato del nombre
        String nombre;
        //Se valida
        do {
            nombre = vista.pedirNombre();
            if (nombre.trim().isEmpty()) {
                vista.mostrarError("El nombre no puede ser vacío");
            }
        } while (nombre.trim().isEmpty());

        int edad;
        //Se obtiene y valida la edad (entre 15 y 60)
        do {
            edad = vista.pedirEdad();
            if (edad < 15 || edad > 60) {
                vista.mostrarError("La edad debe estar entre 15 y 60");
            }
        } while (edad < 15 || edad > 60);

        String pais;
        //Se obtiene y valida el pais
        do {
            pais = vista.pedirPais();
            if (pais.trim().isEmpty()) {
                vista.mostrarError("El país no puede ser vacío");
            }
        } while (pais.trim().isEmpty());

        int ranking;
        //Se obtiene y valida el ranking mundial
        do {
            ranking = vista.pedirRanking();
            if (ranking < 1) {
                vista.mostrarError("El ranking debe ser 1 o mayor");
            }
        } while (ranking < 1);

        double peso;
        //Se obtiene y valida el peso
        do {
            peso = vista.pedirPeso();
            if (peso < 40 || peso > 130) {
                vista.mostrarError("El peso debe estar entre 40 y 130 kg");
            }
        } while (peso < 40 || peso > 120);
        
        double estatura;
        //Se obtiene y valida la estatura
        do {
            estatura = vista.pedirEstatura();
            if (estatura < 140 || estatura > 220) {
                vista.mostrarError("La estatura debe estar entre 130 y 220cm (o 1.40 y 2.20 m)");
            }
        } while (estatura < 140 || estatura > 220);
       
        
        Competidor c = new Competidor(nombre, edad, pais, ranking, estatura, peso);
        return c;
    }
    
    public void buscarCompetidor(String nombre){
        for(Competidor c : competidores){
            if(c.getNombre().trim().equalsIgnoreCase(nombre)){
                System.out.println(c.toString());
            }
        }
        System.out.println("Competidor " + nombre + " no encontrado.");
    }
    
    public void actualizarPuntaje(String nombre, boolean medalla){
        //Se obtienen los puntos y se valida que sean positivos
        int puntos;
        do{
            puntos = vista.pedirPuntos();
            if (puntos <= 0){
                vista.mostrarError("Los puntos a sumar deben ser mayores a 0.");
            }
        } while(puntos <= 0);
        
        
        for(Competidor c : competidores){
            if(c.getNombre().trim().equalsIgnoreCase(nombre)){
                if (medalla){
                c.actualizarRanking(puntos, medalla);
                }
                else{c.actualizarRanking(puntos);}
                
                System.out.println(c.toString());
            }
        }
        System.out.println("Competidor " + nombre + " no encontrado.");
    }
}
