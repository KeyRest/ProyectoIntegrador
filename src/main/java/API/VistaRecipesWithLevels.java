/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aaron
 */
@Entity
@Table(name = "vista_recipes_with_levels")
@NamedQueries({
    @NamedQuery(name = "VistaRecipesWithLevels.findAll", query = "SELECT v FROM VistaRecipesWithLevels v"),
    @NamedQuery(name = "VistaRecipesWithLevels.findById", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.id = :id"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByName", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.name = :name"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByImage", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.image = :image"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByPreparationTime", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.preparationTime = :preparationTime"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByCookingTime", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.cookingTime = :cookingTime"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByDescription", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.description = :description"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByPreparationInstructions", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.preparationInstructions = :preparationInstructions"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByPortions", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.portions = :portions"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByTotalTime", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.totalTime = :totalTime"),
    @NamedQuery(name = "VistaRecipesWithLevels.findByLevel", query = "SELECT v FROM VistaRecipesWithLevels v WHERE v.level = :level")})

public class VistaRecipesWithLevels implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id")
    @Id
    private int id;
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
    @Basic(optional = false)
    @Column(name = "level")
    private String level;

    public VistaRecipesWithLevels() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
}
