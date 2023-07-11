/*
*Keiron Garro M
*C23212
*UCR
 */

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controladores.DB.IngredientsJpaController;
import Controladores.DB.RecipesHasIngredientsJpaController;
import Entidades.Ingredients;
import Entidades.RecipesHasIngredients;
import Entidades.RegistroRecetas;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws IOException {
        //new VistaRecipesWithLevelsJpaController();

        IngredientsJpaController controller = new IngredientsJpaController();
        RecipesHasIngredientsJpaController controller2 = new RecipesHasIngredientsJpaController();

        ArrayList<Ingredients> a = (ArrayList<Ingredients>) controller.findIngredientsEntities();
        ArrayList<RecipesHasIngredients> b = (ArrayList<RecipesHasIngredients>) controller2.findRecipesHasIngredientsEntities();

        for (RecipesHasIngredients ingredients : b) {
            System.out.println(ingredients.getAmount());
            System.out.println(ingredients.getMeasurementUnits().getMeasurementUnit());
        }
        for (Ingredients ingredients : a) {
            System.out.println(ingredients.getDescription());
        }
        
       

    }
}
