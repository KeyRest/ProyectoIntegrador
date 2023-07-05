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
public class UsersSaveRecipesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "users_id")
    private int usersId;
    @Basic(optional = false)
    @Column(name = "recipes_id")
    private int recipesId;

    public UsersSaveRecipesPK() {
    }

    public UsersSaveRecipesPK(int usersId, int recipesId) {
        this.usersId = usersId;
        this.recipesId = recipesId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
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
        hash += (int) usersId;
        hash += (int) recipesId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersSaveRecipesPK)) {
            return false;
        }
        UsersSaveRecipesPK other = (UsersSaveRecipesPK) object;
        if (this.usersId != other.usersId) {
            return false;
        }
        if (this.recipesId != other.recipesId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.UsersSaveRecipesPK[ usersId=" + usersId + ", recipesId=" + recipesId + " ]";
    }

}
