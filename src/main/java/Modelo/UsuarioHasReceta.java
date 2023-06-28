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
@Table(name = "usuario_has_receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioHasReceta.findAll", query = "SELECT u FROM UsuarioHasReceta u"),
    @NamedQuery(name = "UsuarioHasReceta.findByUsuarioId", query = "SELECT u FROM UsuarioHasReceta u WHERE u.usuarioHasRecetaPK.usuarioId = :usuarioId"),
    @NamedQuery(name = "UsuarioHasReceta.findByRecetaid", query = "SELECT u FROM UsuarioHasReceta u WHERE u.usuarioHasRecetaPK.recetaid = :recetaid"),
    @NamedQuery(name = "UsuarioHasReceta.findByFechaVoto", query = "SELECT u FROM UsuarioHasReceta u WHERE u.fechaVoto = :fechaVoto")})
public class UsuarioHasReceta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioHasRecetaPK usuarioHasRecetaPK;
    @Column(name = "fecha_voto")
    @Temporal(TemporalType.DATE)
    private Date fechaVoto;
    @JoinColumn(name = "Receta_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public UsuarioHasReceta() {
    }

    public UsuarioHasReceta(UsuarioHasRecetaPK usuarioHasRecetaPK) {
        this.usuarioHasRecetaPK = usuarioHasRecetaPK;
    }

    public UsuarioHasReceta(String usuarioId, String recetaid) {
        this.usuarioHasRecetaPK = new UsuarioHasRecetaPK(usuarioId, recetaid);
    }

    public UsuarioHasRecetaPK getUsuarioHasRecetaPK() {
        return usuarioHasRecetaPK;
    }

    public void setUsuarioHasRecetaPK(UsuarioHasRecetaPK usuarioHasRecetaPK) {
        this.usuarioHasRecetaPK = usuarioHasRecetaPK;
    }

    public Date getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(Date fechaVoto) {
        this.fechaVoto = fechaVoto;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioHasRecetaPK != null ? usuarioHasRecetaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasReceta)) {
            return false;
        }
        UsuarioHasReceta other = (UsuarioHasReceta) object;
        if ((this.usuarioHasRecetaPK == null && other.usuarioHasRecetaPK != null) || (this.usuarioHasRecetaPK != null && !this.usuarioHasRecetaPK.equals(other.usuarioHasRecetaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.UsuarioHasReceta[ usuarioHasRecetaPK=" + usuarioHasRecetaPK + " ]";
    }

}
