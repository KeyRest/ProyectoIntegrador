/*
*Keiron Garro M
*C23212
*UCR
 */

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Controladores.DB.LevelsJpaController;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "recipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipes.findAll", query = "SELECT r FROM Recipes r"),
    @NamedQuery(name = "Recipes.findById", query = "SELECT r FROM Recipes r WHERE r.id = :id"),
    @NamedQuery(name = "Recipes.findByName", query = "SELECT r FROM Recipes r WHERE r.name = :name"),
    @NamedQuery(name = "Recipes.findByImage", query = "SELECT r FROM Recipes r WHERE r.image = :image"),
    @NamedQuery(name = "Recipes.findByPreparationTime", query = "SELECT r FROM Recipes r WHERE r.preparationTime = :preparationTime"),
    @NamedQuery(name = "Recipes.findByCookingTime", query = "SELECT r FROM Recipes r WHERE r.cookingTime = :cookingTime"),
    @NamedQuery(name = "Recipes.findByDescription", query = "SELECT r FROM Recipes r WHERE r.description = :description"),
    @NamedQuery(name = "Recipes.findByPreparationInstructions", query = "SELECT r FROM Recipes r WHERE r.preparationInstructions = :preparationInstructions"),
    @NamedQuery(name = "Recipes.findByPortions", query = "SELECT r FROM Recipes r WHERE r.portions = :portions"),
    @NamedQuery(name = "Recipes.findByTotalTime", query = "SELECT r FROM Recipes r WHERE r.totalTime = :totalTime")})
public class Recipes implements Serializable {

    public static final String[] ETIQUETAS_RECETA = {"id", "nombre", "descripcion", "tiempo_coccion", "tiempo_total", "tiempo_preparacion", "instrucciones", "porciones"};
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "preparation_time")
    private float preparationTime;
    @Basic(optional = false)
    @Column(name = "cooking_time")
    private float cookingTime;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "preparation_instructions")
    private String preparationInstructions;
    @Basic(optional = false)
    @Column(name = "portions")
    private int portions;
    @Basic(optional = false)
    @Column(name = "total_time")
    private float totalTime;
    @ManyToMany(mappedBy = "recipesCollection")
    private Collection<Categories> categoriesCollection;
    @ManyToMany(mappedBy = "recipesCollection")
    private Collection<Occasions> occasionsCollection;
    @JoinColumn(name = "levels_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Levels levelsId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private Collection<RecipesHasIngredients> recipesHasIngredientsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private Collection<UsersSaveRecipes> usersSaveRecipesCollection;

    public Recipes() {
    }

    public Recipes(Integer id) {
        this.id = id;
    }

    public Recipes(Integer id, String name, String image, String description, float cookingTime, float totalTime, float preparationTime, String preparationInstructions, int portions, int level) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.description = description;
        this.preparationInstructions = preparationInstructions;
        this.portions = portions;
        this.totalTime = totalTime;
        this.levelsId = new Levels(level);
    }
    
    

    public String setDatosReceta(int indice) {
        switch (indice) {
            case 0:
                return String.valueOf(this.getId());
            case 1:
                return this.getName();
            case 2:
                return this.getDescription();
            case 3:
                return String.valueOf(this.getCookingTime());
            case 4:
                return String.valueOf(this.getPreparationTime());
            case 5:
                return String.valueOf(this.getTotalTime());
            case 6:
                return String.valueOf(this.getPreparationInstructions());
            case 7:
                return String.valueOf(this.getPortions());
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(float preparationTime) {
        this.preparationTime = preparationTime;
    }

    public float getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(float cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparationInstructions() {
        return preparationInstructions;
    }

    public void setPreparationInstructions(String preparationInstructions) {
        this.preparationInstructions = preparationInstructions;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    @XmlTransient
    public Collection<Categories> getCategoriesCollection() {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection) {
        this.categoriesCollection = categoriesCollection;
    }

    @XmlTransient
    public Collection<Occasions> getOccasionsCollection() {
        return occasionsCollection;
    }

    public void setOccasionsCollection(Collection<Occasions> occasionsCollection) {
        this.occasionsCollection = occasionsCollection;
    }

    public Levels getLevelsId() {
        return levelsId;
    }

    public void setLevelsId(Levels levelsId) {
        this.levelsId = levelsId;
    }

    @XmlTransient
    public Collection<RecipesHasIngredients> getRecipesHasIngredientsCollection() {
        return recipesHasIngredientsCollection;
    }

    public void setRecipesHasIngredientsCollection(Collection<RecipesHasIngredients> recipesHasIngredientsCollection) {
        this.recipesHasIngredientsCollection = recipesHasIngredientsCollection;
    }

    @XmlTransient
    public Collection<UsersSaveRecipes> getUsersSaveRecipesCollection() {
        return usersSaveRecipesCollection;
    }

    public void setUsersSaveRecipesCollection(Collection<UsersSaveRecipes> usersSaveRecipesCollection) {
        this.usersSaveRecipesCollection = usersSaveRecipesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipes)) {
            return false;
        }
        Recipes other = (Recipes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.Recipes[ id=" + id + " ]";
    }

}
