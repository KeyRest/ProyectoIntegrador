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
@Table(name = "users_save_recipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersSaveRecipes.findAll", query = "SELECT u FROM UsersSaveRecipes u"),
    @NamedQuery(name = "UsersSaveRecipes.findByUsersId", query = "SELECT u FROM UsersSaveRecipes u WHERE u.usersSaveRecipesPK.usersId = :usersId"),
    @NamedQuery(name = "UsersSaveRecipes.findByRecipesId", query = "SELECT u FROM UsersSaveRecipes u WHERE u.usersSaveRecipesPK.recipesId = :recipesId"),
    @NamedQuery(name = "UsersSaveRecipes.findByDate", query = "SELECT u FROM UsersSaveRecipes u WHERE u.date = :date")})
public class UsersSaveRecipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersSaveRecipesPK usersSaveRecipesPK;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "recipes_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;
    @JoinColumn(name = "users_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UsersSaveRecipes() {
    }

    public UsersSaveRecipes(UsersSaveRecipesPK usersSaveRecipesPK) {
        this.usersSaveRecipesPK = usersSaveRecipesPK;
    }

    public UsersSaveRecipes(int usersId, int recipesId) {
        this.usersSaveRecipesPK = new UsersSaveRecipesPK(usersId, recipesId);
    }

    public UsersSaveRecipesPK getUsersSaveRecipesPK() {
        return usersSaveRecipesPK;
    }

    public void setUsersSaveRecipesPK(UsersSaveRecipesPK usersSaveRecipesPK) {
        this.usersSaveRecipesPK = usersSaveRecipesPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersSaveRecipesPK != null ? usersSaveRecipesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersSaveRecipes)) {
            return false;
        }
        UsersSaveRecipes other = (UsersSaveRecipes) object;
        if ((this.usersSaveRecipesPK == null && other.usersSaveRecipesPK != null) || (this.usersSaveRecipesPK != null && !this.usersSaveRecipesPK.equals(other.usersSaveRecipesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.UsersSaveRecipes[ usersSaveRecipesPK=" + usersSaveRecipesPK + " ]";
    }

}
