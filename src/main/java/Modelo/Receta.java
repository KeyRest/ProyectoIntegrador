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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r"),
    @NamedQuery(name = "Receta.findById", query = "SELECT r FROM Receta r WHERE r.id = :id"),
    @NamedQuery(name = "Receta.findByNombre", query = "SELECT r FROM Receta r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Receta.findByDescripcion", query = "SELECT r FROM Receta r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Receta.findByTiempoCoccion", query = "SELECT r FROM Receta r WHERE r.tiempoCoccion = :tiempoCoccion"),
    @NamedQuery(name = "Receta.findByTiempoTotal", query = "SELECT r FROM Receta r WHERE r.tiempoTotal = :tiempoTotal"),
    @NamedQuery(name = "Receta.findByTiempoPreparacion", query = "SELECT r FROM Receta r WHERE r.tiempoPreparacion = :tiempoPreparacion"),
    @NamedQuery(name = "Receta.findByInstrucciones", query = "SELECT r FROM Receta r WHERE r.instrucciones = :instrucciones"),
    @NamedQuery(name = "Receta.findByPorciones", query = "SELECT r FROM Receta r WHERE r.porciones = :porciones")})
public class Receta implements Serializable {
        public static final String[] ETIQUETAS_RECETA = {"id", "nombre", "descripcion", "tiempo_coccion", "tiempo_total","tiempo_preparacion", "instrucciones", "porciones"};

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tiempo_coccion")
    private String tiempoCoccion;
    @Column(name = "tiempo_total")
    private String tiempoTotal;
    @Column(name = "tiempo_preparacion")
    private String tiempoPreparacion;
    @Column(name = "instrucciones")
    private String instrucciones;
    @Lob
    @Column(name = "imagen_receta")
    private String imagenReceta;
    @Column(name = "porciones")
    private String porciones;
    @JoinTable(name = "ocasion_has_receta", joinColumns = {
        @JoinColumn(name = "Receta_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "ocasion_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Ocasion> ocasionCollection;
    @ManyToMany(mappedBy = "recetaCollection")
    private Collection<Consultas> consultasCollection;
    @ManyToMany(mappedBy = "recetaCollection")
    private Collection<Destacada> destacadaCollection;
    @JoinTable(name = "categoria_has_receta", joinColumns = {
        @JoinColumn(name = "Receta_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "categoria_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Categoria> categoriaCollection;
    @JoinTable(name = "receta_has_receta", joinColumns = {
        @JoinColumn(name = "Receta_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Receta_id1", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Receta> recetaCollection;
    @ManyToMany(mappedBy = "recetaCollection")
    private Collection<Receta> recetaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receta")
    private Collection<UsuarioHasReceta> usuarioHasRecetaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receta")
    private Collection<Complejidad> complejidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receta")
    private Collection<RecetaHasMedidas> recetaHasMedidasCollection;

    public Receta() {
    }

    public Receta(String id, String nombre, String descripcion, String tiempoCoccion, String tiempoTotal, String tiempoPreparacion, String instrucciones, String porciones) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setTiempoCoccion(tiempoCoccion);
        setTiempoTotal(tiempoTotal);
        setTiempoPreparacion(tiempoPreparacion);
        setInstrucciones(instrucciones);
        setPorciones(porciones);
    }

    public String setDatosReceta(int indice) {
        switch (indice) {
            case 0:
                return String.valueOf(this.getId());
            case 1:
                return this.getNombre();
            case 2:
                return this.getDescripcion();
            case 3:
                return String.valueOf(this.getTiempoCoccion());
            case 4:
                return String.valueOf(this.getTiempoPreparacion());
            case 5:
                return String.valueOf(this.getTiempoTotal());
            case 6:
                return String.valueOf(this.getInstrucciones());
            case 7:
                return String.valueOf(this.getPorciones());
            case 8:
                return this.getTiempoTotal();
        }
        return null;
    }

    public Receta(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(String tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public String getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(String tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public String getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(String tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getImagenReceta() {
        return imagenReceta;
    }

    public void setImagenReceta(String imagenReceta) {
        this.imagenReceta = imagenReceta;
    }

    public String getPorciones() {
        return porciones;
    }

    public void setPorciones(String porciones) {
        this.porciones = porciones;
    }

    @XmlTransient
    public Collection<Ocasion> getOcasionCollection() {
        return ocasionCollection;
    }

    public void setOcasionCollection(Collection<Ocasion> ocasionCollection) {
        this.ocasionCollection = ocasionCollection;
    }

    @XmlTransient
    public Collection<Consultas> getConsultasCollection() {
        return consultasCollection;
    }

    public void setConsultasCollection(Collection<Consultas> consultasCollection) {
        this.consultasCollection = consultasCollection;
    }

    @XmlTransient
    public Collection<Destacada> getDestacadaCollection() {
        return destacadaCollection;
    }

    public void setDestacadaCollection(Collection<Destacada> destacadaCollection) {
        this.destacadaCollection = destacadaCollection;
    }

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection1() {
        return recetaCollection1;
    }

    public void setRecetaCollection1(Collection<Receta> recetaCollection1) {
        this.recetaCollection1 = recetaCollection1;
    }

    @XmlTransient
    public Collection<UsuarioHasReceta> getUsuarioHasRecetaCollection() {
        return usuarioHasRecetaCollection;
    }

    public void setUsuarioHasRecetaCollection(Collection<UsuarioHasReceta> usuarioHasRecetaCollection) {
        this.usuarioHasRecetaCollection = usuarioHasRecetaCollection;
    }

    @XmlTransient
    public Collection<Complejidad> getComplejidadCollection() {
        return complejidadCollection;
    }

    public void setComplejidadCollection(Collection<Complejidad> complejidadCollection) {
        this.complejidadCollection = complejidadCollection;
    }

    @XmlTransient
    public Collection<RecetaHasMedidas> getRecetaHasMedidasCollection() {
        return recetaHasMedidasCollection;
    }

    public void setRecetaHasMedidasCollection(Collection<RecetaHasMedidas> recetaHasMedidasCollection) {
        this.recetaHasMedidasCollection = recetaHasMedidasCollection;
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
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Receta[ id=" + id + " ]";
    }

}
