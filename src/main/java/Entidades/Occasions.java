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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




@Entity
@Table(name = "occasions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Occasions.findAll", query = "SELECT o FROM Occasions o"),
    @NamedQuery(name = "Occasions.findById", query = "SELECT o FROM Occasions o WHERE o.id = :id"),
    @NamedQuery(name = "Occasions.findByOccasion", query = "SELECT o FROM Occasions o WHERE o.occasion = :occasion")})
public class Occasions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "occasion")
    private String occasion;
    @JoinTable(name = "recipes_has_occasions", joinColumns = {
        @JoinColumn(name = "occasions_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "recipes_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Recipe> recipesCollection;

    public Occasions() {
    }

    public Occasions(Integer id) {
        this.id = id;
    }

    public Occasions(Integer id, String occasion) {
        this.id = id;
        this.occasion = occasion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    @XmlTransient
    public Collection<Recipe> getRecipesCollection() {
        return recipesCollection;
    }

    public void setRecipesCollection(Collection<Recipe> recipesCollection) {
        this.recipesCollection = recipesCollection;
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
        if (!(object instanceof Occasions)) {
            return false;
        }
        Occasions other = (Occasions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.Occasions[ id=" + id + " ]";
    }

}
