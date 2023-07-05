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
@Table(name = "users_vote_recipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersVoteRecipes.findAll", query = "SELECT u FROM UsersVoteRecipes u"),
    @NamedQuery(name = "UsersVoteRecipes.findByUsersId", query = "SELECT u FROM UsersVoteRecipes u WHERE u.usersVoteRecipesPK.usersId = :usersId"),
    @NamedQuery(name = "UsersVoteRecipes.findByRecipesId", query = "SELECT u FROM UsersVoteRecipes u WHERE u.usersVoteRecipesPK.recipesId = :recipesId"),
    @NamedQuery(name = "UsersVoteRecipes.findByDate", query = "SELECT u FROM UsersVoteRecipes u WHERE u.date = :date")})
public class UsersVoteRecipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersVoteRecipesPK usersVoteRecipesPK;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "recipes_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;
    @JoinColumn(name = "users_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UsersVoteRecipes() {
    }

    public UsersVoteRecipes(UsersVoteRecipesPK usersVoteRecipesPK) {
        this.usersVoteRecipesPK = usersVoteRecipesPK;
    }

    public UsersVoteRecipes(UsersVoteRecipesPK usersVoteRecipesPK, Date date) {
        this.usersVoteRecipesPK = usersVoteRecipesPK;
        this.date = date;
    }

    public UsersVoteRecipes(int usersId, int recipesId) {
        this.usersVoteRecipesPK = new UsersVoteRecipesPK(usersId, recipesId);
    }

    public UsersVoteRecipesPK getUsersVoteRecipesPK() {
        return usersVoteRecipesPK;
    }

    public void setUsersVoteRecipesPK(UsersVoteRecipesPK usersVoteRecipesPK) {
        this.usersVoteRecipesPK = usersVoteRecipesPK;
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
        hash += (usersVoteRecipesPK != null ? usersVoteRecipesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersVoteRecipes)) {
            return false;
        }
        UsersVoteRecipes other = (UsersVoteRecipes) object;
        if ((this.usersVoteRecipesPK == null && other.usersVoteRecipesPK != null) || (this.usersVoteRecipesPK != null && !this.usersVoteRecipesPK.equals(other.usersVoteRecipesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.UsersVoteRecipes[ usersVoteRecipesPK=" + usersVoteRecipesPK + " ]";
    }

}
