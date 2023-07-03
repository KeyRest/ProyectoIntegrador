/*
*Keiron Garro M
*C23212
*UCR
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByPais", query = "SELECT u FROM Usuario u WHERE u.pais = :pais"),
    @NamedQuery(name = "Usuario.findByContrase\u00f1a", query = "SELECT u FROM Usuario u WHERE u.contrase\u00f1a = :contrase\u00f1a")})
public class Usuario implements Serializable {
    public static final String[] ETIQUETAS_USUARIO = {"ID", "Nombre", "Nombre_usuario", "Pais", "Correo", "Contras\u00f1a","perfilCollection","usuarioHasRecetaCollection","consultasCollection"};
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "correo")
    private String correo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "pais")
    private String pais;
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @ManyToMany(mappedBy = "usuarioCollection")
    private Collection<Consultas> consultasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<UsuarioHasReceta> usuarioHasRecetaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Perfil> perfilCollection;

    public Usuario() {
    }
    public String setDatosUsuario(int indice) {
        switch (indice) {
            case 0:
                return String.valueOf(this.getId());
            case 1:
                return this.getNombre();
            case 2:
                return this.getNombreUsuario();
            case 3:
                return this.getPais();
            case 4:
                return this.getCorreo();
            case 5:
                return this.getContraseña();
        }
        return null;
    }

    public Usuario(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @XmlTransient
    public Collection<Consultas> getConsultasCollection() {
        return consultasCollection;
    }

    public void setConsultasCollection(Collection<Consultas> consultasCollection) {
        this.consultasCollection = consultasCollection;
    }

    @XmlTransient
    public Collection<UsuarioHasReceta> getUsuarioHasRecetaCollection() {
        return usuarioHasRecetaCollection;
    }

    public void setUsuarioHasRecetaCollection(Collection<UsuarioHasReceta> usuarioHasRecetaCollection) {
        this.usuarioHasRecetaCollection = usuarioHasRecetaCollection;
    }

    @XmlTransient
    public Collection<Perfil> getPerfilCollection() {
        return perfilCollection;
    }

    public void setPerfilCollection(Collection<Perfil> perfilCollection) {
        this.perfilCollection = perfilCollection;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Usuario[ id=" + id + " ]";
    }

}
