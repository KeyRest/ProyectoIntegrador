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
public class ComplejidadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Receta_id")
    private String recetaid;

    public ComplejidadPK() {
    }

    public ComplejidadPK(int id, String recetaid) {
        this.id = id;
        this.recetaid = recetaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        hash += (int) id;
        hash += (recetaid != null ? recetaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplejidadPK)) {
            return false;
        }
        ComplejidadPK other = (ComplejidadPK) object;
        if (this.id != other.id) {
            return false;
        }
        if ((this.recetaid == null && other.recetaid != null) || (this.recetaid != null && !this.recetaid.equals(other.recetaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ComplejidadPK[ id=" + id + ", recetaid=" + recetaid + " ]";
    }

}
