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

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




@Entity
@Table(name = "measurement_units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasurementUnits.findAll", query = "SELECT m FROM MeasurementUnits m"),
    @NamedQuery(name = "MeasurementUnits.findById", query = "SELECT m FROM MeasurementUnits m WHERE m.id = :id"),
    @NamedQuery(name = "MeasurementUnits.findByMeasurementUnit", query = "SELECT m FROM MeasurementUnits m WHERE m.measurementUnit = :measurementUnit")})
public class MeasurementUnits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "measurement_unit")
    private String measurementUnit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measurementUnits")
    private Collection<RecipesHasIngredients> recipesHasIngredientsCollection;

    public MeasurementUnits() {
    }

    public MeasurementUnits(Integer id) {
        this.id = id;
    }

    public MeasurementUnits(Integer id, String measurementUnit) {
        this.id = id;
        this.measurementUnit = measurementUnit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @XmlTransient
    public Collection<RecipesHasIngredients> getRecipesHasIngredientsCollection() {
        return recipesHasIngredientsCollection;
    }

    public void setRecipesHasIngredientsCollection(Collection<RecipesHasIngredients> recipesHasIngredientsCollection) {
        this.recipesHasIngredientsCollection = recipesHasIngredientsCollection;
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
        if (!(object instanceof MeasurementUnits)) {
            return false;
        }
        MeasurementUnits other = (MeasurementUnits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.MeasurementUnits[ id=" + id + " ]";
    }

}
