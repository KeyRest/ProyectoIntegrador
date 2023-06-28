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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




@Entity
@Table(name = "destacada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destacada.findAll", query = "SELECT d FROM Destacada d"),
    @NamedQuery(name = "Destacada.findById", query = "SELECT d FROM Destacada d WHERE d.id = :id"),
    @NamedQuery(name = "Destacada.findByFechaInicio", query = "SELECT d FROM Destacada d WHERE d.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Destacada.findByFechaFinal", query = "SELECT d FROM Destacada d WHERE d.fechaFinal = :fechaFinal")})
public class Destacada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @JoinTable(name = "receta_has_destacada", joinColumns = {
        @JoinColumn(name = "Destacada_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Receta_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Receta> recetaCollection;

    public Destacada() {
    }

    public Destacada(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
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
        if (!(object instanceof Destacada)) {
            return false;
        }
        Destacada other = (Destacada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Destacada[ id=" + id + " ]";
    }

}
