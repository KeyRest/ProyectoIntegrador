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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name = "receta_has_medidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecetaHasMedidas.findAll", query = "SELECT r FROM RecetaHasMedidas r"),
    @NamedQuery(name = "RecetaHasMedidas.findByRecetaid", query = "SELECT r FROM RecetaHasMedidas r WHERE r.recetaHasMedidasPK.recetaid = :recetaid"),
    @NamedQuery(name = "RecetaHasMedidas.findByMedidasId", query = "SELECT r FROM RecetaHasMedidas r WHERE r.recetaHasMedidasPK.medidasId = :medidasId"),
    @NamedQuery(name = "RecetaHasMedidas.findByIngredientesId", query = "SELECT r FROM RecetaHasMedidas r WHERE r.recetaHasMedidasPK.ingredientesId = :ingredientesId")})
public class RecetaHasMedidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecetaHasMedidasPK recetaHasMedidasPK;
    @JoinColumn(name = "ingredientes_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredientes ingredientes;
    @JoinColumn(name = "medidas_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medidas medidas;
    @JoinColumn(name = "Receta_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;

    public RecetaHasMedidas() {
    }

    public RecetaHasMedidas(RecetaHasMedidasPK recetaHasMedidasPK) {
        this.recetaHasMedidasPK = recetaHasMedidasPK;
    }

    public RecetaHasMedidas(String recetaid, int medidasId, int ingredientesId) {
        this.recetaHasMedidasPK = new RecetaHasMedidasPK(recetaid, medidasId, ingredientesId);
    }

    public RecetaHasMedidasPK getRecetaHasMedidasPK() {
        return recetaHasMedidasPK;
    }

    public void setRecetaHasMedidasPK(RecetaHasMedidasPK recetaHasMedidasPK) {
        this.recetaHasMedidasPK = recetaHasMedidasPK;
    }

    public Ingredientes getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Ingredientes ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Medidas getMedidas() {
        return medidas;
    }

    public void setMedidas(Medidas medidas) {
        this.medidas = medidas;
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
        hash += (recetaHasMedidasPK != null ? recetaHasMedidasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaHasMedidas)) {
            return false;
        }
        RecetaHasMedidas other = (RecetaHasMedidas) object;
        if ((this.recetaHasMedidasPK == null && other.recetaHasMedidasPK != null) || (this.recetaHasMedidasPK != null && !this.recetaHasMedidasPK.equals(other.recetaHasMedidasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.RecetaHasMedidas[ recetaHasMedidasPK=" + recetaHasMedidasPK + " ]";
    }

}
