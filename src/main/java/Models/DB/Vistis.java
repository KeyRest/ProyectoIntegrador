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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name = "vistis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vistis.findAll", query = "SELECT v FROM Vistis v"),
    @NamedQuery(name = "Vistis.findById", query = "SELECT v FROM Vistis v WHERE v.id = :id"),
    @NamedQuery(name = "Vistis.findByDate", query = "SELECT v FROM Vistis v WHERE v.date = :date"),
    @NamedQuery(name = "Vistis.findByUsersId1", query = "SELECT v FROM Vistis v WHERE v.usersId1 = :usersId1")})
public class Vistis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "users_id1")
    private Integer usersId1;
    @JoinColumn(name = "recipes_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Recipes recipesId;

    public Vistis() {
    }

    public Vistis(Integer id) {
        this.id = id;
    }

    public Vistis(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUsersId1() {
        return usersId1;
    }

    public void setUsersId1(Integer usersId1) {
        this.usersId1 = usersId1;
    }

    public Recipes getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(Recipes recipesId) {
        this.recipesId = recipesId;
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
        if (!(object instanceof Vistis)) {
            return false;
        }
        Vistis other = (Vistis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.Vistis[ id=" + id + " ]";
    }

}
