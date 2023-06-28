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
public class RecetaHasMedidasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Receta_id")
    private String recetaid;
    @Basic(optional = false)
    @Column(name = "medidas_id")
    private int medidasId;
    @Basic(optional = false)
    @Column(name = "ingredientes_id")
    private int ingredientesId;

    public RecetaHasMedidasPK() {
    }

    public RecetaHasMedidasPK(String recetaid, int medidasId, int ingredientesId) {
        this.recetaid = recetaid;
        this.medidasId = medidasId;
        this.ingredientesId = ingredientesId;
    }

    public String getRecetaid() {
        return recetaid;
    }

    public void setRecetaid(String recetaid) {
        this.recetaid = recetaid;
    }

    public int getMedidasId() {
        return medidasId;
    }

    public void setMedidasId(int medidasId) {
        this.medidasId = medidasId;
    }

    public int getIngredientesId() {
        return ingredientesId;
    }

    public void setIngredientesId(int ingredientesId) {
        this.ingredientesId = ingredientesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recetaid != null ? recetaid.hashCode() : 0);
        hash += (int) medidasId;
        hash += (int) ingredientesId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaHasMedidasPK)) {
            return false;
        }
        RecetaHasMedidasPK other = (RecetaHasMedidasPK) object;
        if ((this.recetaid == null && other.recetaid != null) || (this.recetaid != null && !this.recetaid.equals(other.recetaid))) {
            return false;
        }
        if (this.medidasId != other.medidasId) {
            return false;
        }
        if (this.ingredientesId != other.ingredientesId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.RecetaHasMedidasPK[ recetaid=" + recetaid + ", medidasId=" + medidasId + ", ingredientesId=" + ingredientesId + " ]";
    }

}
