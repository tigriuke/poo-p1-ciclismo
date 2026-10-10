/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author coffe
 */
public class Competidor extends Atleta {
    private int ranking;
    private int puntos;
    private double estatura;
    private double peso;
    
    public Competidor(String nombre, int edad, String pais, int ranking, double estatura, double peso) {
        super(nombre, edad, pais);
        this.puntos = 0;
        this.ranking = ranking;
        this.estatura = estatura;
        this.peso = peso;
        
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    @Override
    public String toString(){
        return super.toString() +
            "\nRanking: " + ranking +
            "\nPuntos: " + puntos + 
            "\nEstatura: " + estatura + " cm" +
            "\nPeso: " + peso + " kg";
    }
    
    public void actualizarRanking(int puntos){
        this.puntos += puntos;
        if (puntos >= 30){
            if(ranking == 1) {
                return;
            }
            else{
                ranking -= 1;
            }
        }
        
    }
    
   public void actualizarRanking(int puntos, boolean ganoMedalla) {
    if (ganoMedalla) {
        int total = puntos + 15;
        this.puntos += total;
        if (total >= 30) {
            if (ranking >= 3) {
                ranking -= 2;
            }
            else if(ranking == 2){
                ranking -= 1;
            }
        }
    } else {
        actualizarRanking(puntos);
    }
}
}
