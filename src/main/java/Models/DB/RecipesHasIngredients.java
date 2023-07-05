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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name = "recipes_has_ingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipesHasIngredients.findAll", query = "SELECT r FROM RecipesHasIngredients r"),
    @NamedQuery(name = "RecipesHasIngredients.findByRecipesId", query = "SELECT r FROM RecipesHasIngredients r WHERE r.recipesHasIngredientsPK.recipesId = :recipesId"),
    @NamedQuery(name = "RecipesHasIngredients.findByIngredientsId", query = "SELECT r FROM RecipesHasIngredients r WHERE r.recipesHasIngredientsPK.ingredientsId = :ingredientsId"),
    @NamedQuery(name = "RecipesHasIngredients.findByMeasurementUnitsId", query = "SELECT r FROM RecipesHasIngredients r WHERE r.recipesHasIngredientsPK.measurementUnitsId = :measurementUnitsId"),
    @NamedQuery(name = "RecipesHasIngredients.findByAmount", query = "SELECT r FROM RecipesHasIngredients r WHERE r.amount = :amount")})
public class RecipesHasIngredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipesHasIngredientsPK recipesHasIngredientsPK;
    @Basic(optional = false)
    @Column(name = "amount")
    private float amount;
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredients ingredients;
    @JoinColumn(name = "measurement_units_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MeasurementUnits measurementUnits;
    @JoinColumn(name = "recipes_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;

    public RecipesHasIngredients() {
    }

    public RecipesHasIngredients(RecipesHasIngredientsPK recipesHasIngredientsPK) {
        this.recipesHasIngredientsPK = recipesHasIngredientsPK;
    }

    public RecipesHasIngredients(RecipesHasIngredientsPK recipesHasIngredientsPK, float amount) {
        this.recipesHasIngredientsPK = recipesHasIngredientsPK;
        this.amount = amount;
    }

    public RecipesHasIngredients(int recipesId, int ingredientsId, int measurementUnitsId) {
        this.recipesHasIngredientsPK = new RecipesHasIngredientsPK(recipesId, ingredientsId, measurementUnitsId);
    }

    public RecipesHasIngredientsPK getRecipesHasIngredientsPK() {
        return recipesHasIngredientsPK;
    }

    public void setRecipesHasIngredientsPK(RecipesHasIngredientsPK recipesHasIngredientsPK) {
        this.recipesHasIngredientsPK = recipesHasIngredientsPK;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public MeasurementUnits getMeasurementUnits() {
        return measurementUnits;
    }

    public void setMeasurementUnits(MeasurementUnits measurementUnits) {
        this.measurementUnits = measurementUnits;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipesHasIngredientsPK != null ? recipesHasIngredientsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipesHasIngredients)) {
            return false;
        }
        RecipesHasIngredients other = (RecipesHasIngredients) object;
        if ((this.recipesHasIngredientsPK == null && other.recipesHasIngredientsPK != null) || (this.recipesHasIngredientsPK != null && !this.recipesHasIngredientsPK.equals(other.recipesHasIngredientsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.RecipesHasIngredients[ recipesHasIngredientsPK=" + recipesHasIngredientsPK + " ]";
    }

}
