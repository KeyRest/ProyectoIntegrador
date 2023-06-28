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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;




@Embeddable
public class UsuarioHasRecetaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "usuario_id")
    private String usuarioId;
    @Basic(optional = false)
    @Column(name = "Receta_id")
    private String recetaid;

    public UsuarioHasRecetaPK() {
    }

    public UsuarioHasRecetaPK(String usuarioId, String recetaid) {
        this.usuarioId = usuarioId;
        this.recetaid = recetaid;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRecetaid() {
        return recetaid;
    }

    public void setRecetaid(String recetaid) {
        this.recetaid = recetaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        hash += (recetaid != null ? recetaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasRecetaPK)) {
            return false;
        }
        UsuarioHasRecetaPK other = (UsuarioHasRecetaPK) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        if ((this.recetaid == null && other.recetaid != null) || (this.recetaid != null && !this.recetaid.equals(other.recetaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.UsuarioHasRecetaPK[ usuarioId=" + usuarioId + ", recetaid=" + recetaid + " ]";
    }

}
