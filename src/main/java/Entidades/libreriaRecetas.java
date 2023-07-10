/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author leoulateh
 */
import java.util.ArrayList;

public class libreriaRecetas {
    private ArrayList<Recipes> recetas;

    public libreriaRecetas() {
        recetas = new ArrayList<>();
    }

    public void agregarReceta(Recipes receta) {
        recetas.add(receta);
    }

    public void removerReceta(Recipes receta) {
        recetas.remove(receta);
    }
    
    
    
    public String[][] getDatosTabla() {
        String[][] matrizTabla = new String[this.recetas.size()][Recipes.ETIQUETAS_RECETA.length];
        for (int f = 0; f < this.recetas.size(); f++) {
            for (int c = 0; c < matrizTabla[0].length; c++) {
                matrizTabla[f][c] = this.recetas.get(f).setDatosReceta(c);
            }
        }
        return matrizTabla;
    }
    
    

    public void imprimirRecetas() {
        for (Recipes receta : recetas) {
            System.out.println(receta.toString());
        }
    }
}

