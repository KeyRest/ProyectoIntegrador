/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Admin
 */
public class RegistroRecetas {

    private ArrayList<Recipe> listaReceta;
    private JSONObject baseJSONReceta;
    private File archivo;

    public RegistroRecetas() {
        this.listaReceta = new ArrayList();
        this.archivo = new File("Recetas.json");
        this.leerJSON();
    }

    public void escribirJSON() {
        JSONArray arregloRecetas = new JSONArray();
        this.baseJSONReceta = new JSONObject();
        for (Recipe recipe : listaReceta) {
            JSONObject objJSONReceta = new JSONObject();
            objJSONReceta.put("id", recipe.getId());
            objJSONReceta.put("nombre", recipe.getName());
            objJSONReceta.put("descripcion", recipe.getDescription());
            objJSONReceta.put("tiempoCoccion", recipe.getCookingTime());
            objJSONReceta.put("tiempoPreparacion", recipe.getPreparationTime());
            objJSONReceta.put("tiempoTotal", recipe.getTotalTime());
            objJSONReceta.put("instrucciones", recipe.getPreparationInstructions());
            objJSONReceta.put("porciones", recipe.getPortions());
            arregloRecetas.add(objJSONReceta);

        }
        this.baseJSONReceta.put("listaRecetas", arregloRecetas);
        try {
            FileWriter escribir = new FileWriter(this.archivo);
            escribir.write(this.baseJSONReceta.toJSONString());
            escribir.flush();
            escribir.close();
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo");
        }
    }

    public void leerJSON() {
        JSONParser parser = new JSONParser();
        try {
            FileReader leer = new FileReader(this.archivo);
            Object obj = parser.parse(leer);
            this.baseJSONReceta = (JSONObject) obj;
            JSONArray arregloJSON = (JSONArray) this.baseJSONReceta.get("listaRecetas");
            for (Object object : arregloJSON) {
                JSONObject objUsuario = (JSONObject) object;
                Recipe receta = new Recipe();
                receta.setId(Integer.parseInt((String) objUsuario.get("id")));
                receta.setName(objUsuario.get("nombre").toString());
                receta.setDescription(objUsuario.get("descripcion").toString());
                receta.setCookingTime(Float.parseFloat((String) objUsuario.get("tiempoCoccion")));
                receta.setPreparationTime(Float.parseFloat((String) objUsuario.get("tiempoPreparacion")));
                receta.setTotalTime(Float.parseFloat((String) objUsuario.get("tiempoTotal")));
                receta.setPreparationInstructions(objUsuario.get("instrucciones").toString());
                receta.setPortions(Integer.parseInt((String) objUsuario.get("porciones")));

                this.listaReceta.add(receta);

            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer");
        } catch (IOException ex) {
            System.err.println("Error al leer");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public String agregar(Object obj) {
        Recipe receta = (Recipe) obj;
        if (this.buscar(receta.getId().toString()) == null) {
            if (this.listaReceta.add(receta)) {
                this.escribirJSON();
                return "La receta se ha agregado con exito";
            } else {
                return "Error al registrar la receta";
            }

        }
        return "La receta ya se encuentra registrada";
    }

    public String eliminar(Object receta) {
        if (this.listaReceta.remove((Recipe) receta)) {
            this.escribirJSON();
            return "La receta ha sido eliminada";
        } else {
            return "Error al eliminar la receta";
        }
    }

    public Object buscar(String id) {
        for (int indice = 0; indice < this.listaReceta.size(); indice++) {
            if (this.listaReceta.get(indice).getId().toString().equalsIgnoreCase(id)) {
                return (Object) this.listaReceta.get(indice);
            }

        }
        return null;
    }

    public String toString() {
        String salida = "Lista de Recetas: \n";
        Iterator it = this.listaReceta.iterator();
        while (it.hasNext()) {
            salida += it.next() + "\n";
        }
        return salida;
    }

    public String[][] getDatosTabla() {
        String[][] matrizTabla = new String[this.listaReceta.size()][User.ETIQUETAS_USUARIO.length];
        for (int f = 0; f < this.listaReceta.size(); f++) {
            for (int c = 0; c < matrizTabla[0].length; c++) {
                matrizTabla[f][c] = this.listaReceta.get(f).setDatosReceta(c);
            }
        }
        return matrizTabla;
    }

}