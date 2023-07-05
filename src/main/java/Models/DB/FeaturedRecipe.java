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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name = "featured_recipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeaturedRecipe.findAll", query = "SELECT f FROM FeaturedRecipe f"),
    @NamedQuery(name = "FeaturedRecipe.findById", query = "SELECT f FROM FeaturedRecipe f WHERE f.featuredRecipePK.id = :id"),
    @NamedQuery(name = "FeaturedRecipe.findByStartDate", query = "SELECT f FROM FeaturedRecipe f WHERE f.startDate = :startDate"),
    @NamedQuery(name = "FeaturedRecipe.findByEndDate", query = "SELECT f FROM FeaturedRecipe f WHERE f.endDate = :endDate"),
    @NamedQuery(name = "FeaturedRecipe.findByRecipesId", query = "SELECT f FROM FeaturedRecipe f WHERE f.featuredRecipePK.recipesId = :recipesId")})
public class FeaturedRecipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FeaturedRecipePK featuredRecipePK;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "recipes_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;

    public FeaturedRecipe() {
    }

    public FeaturedRecipe(FeaturedRecipePK featuredRecipePK) {
        this.featuredRecipePK = featuredRecipePK;
    }

    public FeaturedRecipe(FeaturedRecipePK featuredRecipePK, Date startDate, Date endDate) {
        this.featuredRecipePK = featuredRecipePK;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FeaturedRecipe(int id, int recipesId) {
        this.featuredRecipePK = new FeaturedRecipePK(id, recipesId);
    }

    public FeaturedRecipePK getFeaturedRecipePK() {
        return featuredRecipePK;
    }

    public void setFeaturedRecipePK(FeaturedRecipePK featuredRecipePK) {
        this.featuredRecipePK = featuredRecipePK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        hash += (featuredRecipePK != null ? featuredRecipePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeaturedRecipe)) {
            return false;
        }
        FeaturedRecipe other = (FeaturedRecipe) object;
        if ((this.featuredRecipePK == null && other.featuredRecipePK != null) || (this.featuredRecipePK != null && !this.featuredRecipePK.equals(other.featuredRecipePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.FeaturedRecipe[ featuredRecipePK=" + featuredRecipePK + " ]";
    }

}
