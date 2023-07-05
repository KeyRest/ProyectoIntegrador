/*
*Keiron Garro M
*C23212
*UCR
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models.DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;




@Embeddable
public class RecipesHasIngredientsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "recipes_id")
    private int recipesId;
    @Basic(optional = false)
    @Column(name = "ingredients_id")
    private int ingredientsId;
    @Basic(optional = false)
    @Column(name = "measurement_units_id")
    private int measurementUnitsId;

    public RecipesHasIngredientsPK() {
    }

    public RecipesHasIngredientsPK(int recipesId, int ingredientsId, int measurementUnitsId) {
        this.recipesId = recipesId;
        this.ingredientsId = ingredientsId;
        this.measurementUnitsId = measurementUnitsId;
    }

    public int getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(int recipesId) {
        this.recipesId = recipesId;
    }

    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public int getMeasurementUnitsId() {
        return measurementUnitsId;
    }

    public void setMeasurementUnitsId(int measurementUnitsId) {
        this.measurementUnitsId = measurementUnitsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipesId;
        hash += (int) ingredientsId;
        hash += (int) measurementUnitsId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipesHasIngredientsPK)) {
            return false;
        }
        RecipesHasIngredientsPK other = (RecipesHasIngredientsPK) object;
        if (this.recipesId != other.recipesId) {
            return false;
        }
        if (this.ingredientsId != other.ingredientsId) {
            return false;
        }
        if (this.measurementUnitsId != other.measurementUnitsId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.RecipesHasIngredientsPK[ recipesId=" + recipesId + ", ingredientsId=" + ingredientsId + ", measurementUnitsId=" + measurementUnitsId + " ]";
    }

}
