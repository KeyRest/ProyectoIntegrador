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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name = "complejidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complejidad.findAll", query = "SELECT c FROM Complejidad c"),
    @NamedQuery(name = "Complejidad.findById", query = "SELECT c FROM Complejidad c WHERE c.complejidadPK.id = :id"),
    @NamedQuery(name = "Complejidad.findByNivel", query = "SELECT c FROM Complejidad c WHERE c.nivel = :nivel"),
    @NamedQuery(name = "Complejidad.findByRecetaid", query = "SELECT c FROM Complejidad c WHERE c.complejidadPK.recetaid = :recetaid")})
public class Complejidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComplejidadPK complejidadPK;
    @Column(name = "nivel")
    private String nivel;
    @JoinColumn(name = "Receta_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;

    public Complejidad() {
    }

    public Complejidad(ComplejidadPK complejidadPK) {
        this.complejidadPK = complejidadPK;
    }

    public Complejidad(int id, String recetaid) {
        this.complejidadPK = new ComplejidadPK(id, recetaid);
    }

    public ComplejidadPK getComplejidadPK() {
        return complejidadPK;
    }

    public void setComplejidadPK(ComplejidadPK complejidadPK) {
        this.complejidadPK = complejidadPK;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complejidadPK != null ? complejidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complejidad)) {
            return false;
        }
        Complejidad other = (Complejidad) object;
        if ((this.complejidadPK == null && other.complejidadPK != null) || (this.complejidadPK != null && !this.complejidadPK.equals(other.complejidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Complejidad[ complejidadPK=" + complejidadPK + " ]";
    }

}
