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
public class FeaturedRecipePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "recipes_id")
    private int recipesId;

    public FeaturedRecipePK() {
    }

    public FeaturedRecipePK(int id, int recipesId) {
        this.id = id;
        this.recipesId = recipesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(int recipesId) {
        this.recipesId = recipesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) recipesId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeaturedRecipePK)) {
            return false;
        }
        FeaturedRecipePK other = (FeaturedRecipePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.recipesId != other.recipesId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.FeaturedRecipePK[ id=" + id + ", recipesId=" + recipesId + " ]";
    }

}
