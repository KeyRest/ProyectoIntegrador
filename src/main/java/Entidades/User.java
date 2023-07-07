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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByCountry", query = "SELECT u FROM Users u WHERE u.country = :country"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")})
public class User implements Serializable {

    public static final String[] ETIQUETAS_USUARIO = {"ID", "Nombre", "Pais", "Correo", "Contras\u00f1a"};

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
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<UsersVoteRecipes> usersVoteRecipesCollection;
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Profile profileId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<UsersSaveRecipes> usersSaveRecipesCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String lastName, String country, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.email = email;
        this.password = password;
    }

    public String setDatosUsuario(int indice) {
        switch (indice) {
            case 0:
                return String.valueOf(this.getId());
            case 1:
                return this.getName();
            case 2:
                return this.getCountry();
            case 3:
                return this.getEmail();
            case 4:
                return this.getPassword();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<UsersVoteRecipes> getUsersVoteRecipesCollection() {
        return usersVoteRecipesCollection;
    }

    public void setUsersVoteRecipesCollection(Collection<UsersVoteRecipes> usersVoteRecipesCollection) {
        this.usersVoteRecipesCollection = usersVoteRecipesCollection;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DB.Users[ id=" + id + " ]";
    }

}
