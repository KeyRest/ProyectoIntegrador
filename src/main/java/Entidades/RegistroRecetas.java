/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Controladores.DB.IngredientsJpaController;
import Controladores.DB.RecipesHasIngredientsJpaController;
import Controladores.DB.RecipesJpaController;
import Controladores.DB.exceptions.IllegalOrphanException;
import Controladores.DB.exceptions.NonexistentEntityException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Admin
 */
public class RegistroRecetas {

    private ArrayList<Recipes> listaReceta;
    private JSONObject baseJSONReceta;
    private File archivo;
    private RecipesJpaController recipesJpaController;
    private IngredientsJpaController ingredientsJpaController;

    public RegistroRecetas() {
        this.listaReceta = new ArrayList();
        this.archivo = new File("Recetas.json");

    }

    public void escribirJSON() {
        JSONArray arregloRecetas = new JSONArray();
        this.baseJSONReceta = new JSONObject();
        for (Recipes recipe : listaReceta) {
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
                JSONObject objReceta = (JSONObject) object;
                Recipes receta = new Recipes();
                receta.setId(Integer.parseInt((String) objReceta.get("id")));
                receta.setName(objReceta.get("nombre").toString());
                receta.setDescription(objReceta.get("descripcion").toString());
                receta.setCookingTime(Float.parseFloat((String) objReceta.get("tiempoCoccion")));
                receta.setPreparationTime(Float.parseFloat((String) objReceta.get("tiempoPreparacion")));
                receta.setTotalTime(Float.parseFloat((String) objReceta.get("tiempoTotal")));
                receta.setPreparationInstructions(objReceta.get("instrucciones").toString());
                receta.setPortions(Integer.parseInt((String) objReceta.get("porciones")));

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

    public String agregar(Object obj, ArrayList<Ingredients> ingredients) throws Exception {
        Recipes receta = (Recipes) obj;
        if (this.buscar(receta.getId().toString()) == null) {
            if (this.listaReceta.add(receta)) {
                this.escribirJSON();
                this.recipesJpaController = new RecipesJpaController();
                this.recipesJpaController.create(receta);
                RecipesHasIngredientsJpaController rhi = new RecipesHasIngredientsJpaController();

                for (Ingredients ingredient : ingredients) {
                    RecipesHasIngredients recipesHasIngredients = new RecipesHasIngredients(receta.getId(), ingredient.getId(), 1);
                    rhi.create(recipesHasIngredients);
                }

                return "La receta se ha agregado con exito";
            } else {
                return "Error al registrar la receta";
            }

        }
        return "La receta ya se encuentra registrada";
    }

    public void modificar(Object obj) throws NonexistentEntityException, Exception {
        this.recipesJpaController = new RecipesJpaController();
        this.recipesJpaController.edit((Recipes) obj);
    }

    public String eliminar(Object receta) throws IllegalOrphanException, NonexistentEntityException {
        if (this.listaReceta.remove((Recipes) receta)) {
            this.escribirJSON();
            this.recipesJpaController = new RecipesJpaController();
            this.recipesJpaController.destroy(((Recipes) receta).getId());
            return "La receta ha sido eliminada";
        } else {
            return "Error al eliminar la receta";
        }
    }

    public ArrayList<Recipes> getListaRecetas() {
        this.recipesJpaController = new RecipesJpaController();
        return (ArrayList<Recipes>) this.recipesJpaController.findRecipesEntities();
    }

    public ArrayList<Object> getListaIngredientes() {
        this.ingredientsJpaController = new IngredientsJpaController();
        ArrayList<Ingredients> a = (ArrayList<Ingredients>) this.ingredientsJpaController.findIngredientsEntities();
        RecipesHasIngredientsJpaController controllerRHI = new RecipesHasIngredientsJpaController();
        ArrayList<RecipesHasIngredients> b = (ArrayList<RecipesHasIngredients>) controllerRHI.findRecipesHasIngredientsEntities();

        ArrayList<Object> c = new ArrayList<>();
        c.addAll(a);
        c.addAll(b);

        return c;
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
        this.listaReceta = getListaRecetas();
        String[][] matrizTabla = new String[this.listaReceta.size()][Recipes.ETIQUETAS_RECETA.length];
        for (int f = 0; f < this.listaReceta.size(); f++) {
            for (int c = 0; c < matrizTabla[0].length; c++) {
                matrizTabla[f][c] = this.listaReceta.get(f).setDatosReceta(c);
            }
        }
        return matrizTabla;
    }

}
