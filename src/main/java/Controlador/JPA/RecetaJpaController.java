/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.IllegalOrphanException;
import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Ocasion;
import java.util.ArrayList;
import java.util.Collection;
import Modelo.Consultas;
import Modelo.Destacada;
import Modelo.Categoria;
import Modelo.Receta;
import Modelo.UsuarioHasReceta;
import Modelo.Complejidad;
import Modelo.RecetaHasMedidas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class RecetaJpaController implements Serializable {

    public RecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Receta receta) throws PreexistingEntityException, Exception {
        if (receta.getOcasionCollection() == null) {
            receta.setOcasionCollection(new ArrayList<Ocasion>());
        }
        if (receta.getConsultasCollection() == null) {
            receta.setConsultasCollection(new ArrayList<Consultas>());
        }
        if (receta.getDestacadaCollection() == null) {
            receta.setDestacadaCollection(new ArrayList<Destacada>());
        }
        if (receta.getCategoriaCollection() == null) {
            receta.setCategoriaCollection(new ArrayList<Categoria>());
        }
        if (receta.getRecetaCollection() == null) {
            receta.setRecetaCollection(new ArrayList<Receta>());
        }
        if (receta.getRecetaCollection1() == null) {
            receta.setRecetaCollection1(new ArrayList<Receta>());
        }
        if (receta.getUsuarioHasRecetaCollection() == null) {
            receta.setUsuarioHasRecetaCollection(new ArrayList<UsuarioHasReceta>());
        }
        if (receta.getComplejidadCollection() == null) {
            receta.setComplejidadCollection(new ArrayList<Complejidad>());
        }
        if (receta.getRecetaHasMedidasCollection() == null) {
            receta.setRecetaHasMedidasCollection(new ArrayList<RecetaHasMedidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ocasion> attachedOcasionCollection = new ArrayList<Ocasion>();
            for (Ocasion ocasionCollectionOcasionToAttach : receta.getOcasionCollection()) {
                ocasionCollectionOcasionToAttach = em.getReference(ocasionCollectionOcasionToAttach.getClass(), ocasionCollectionOcasionToAttach.getId());
                attachedOcasionCollection.add(ocasionCollectionOcasionToAttach);
            }
            receta.setOcasionCollection(attachedOcasionCollection);
            Collection<Consultas> attachedConsultasCollection = new ArrayList<Consultas>();
            for (Consultas consultasCollectionConsultasToAttach : receta.getConsultasCollection()) {
                consultasCollectionConsultasToAttach = em.getReference(consultasCollectionConsultasToAttach.getClass(), consultasCollectionConsultasToAttach.getId());
                attachedConsultasCollection.add(consultasCollectionConsultasToAttach);
            }
            receta.setConsultasCollection(attachedConsultasCollection);
            Collection<Destacada> attachedDestacadaCollection = new ArrayList<Destacada>();
            for (Destacada destacadaCollectionDestacadaToAttach : receta.getDestacadaCollection()) {
                destacadaCollectionDestacadaToAttach = em.getReference(destacadaCollectionDestacadaToAttach.getClass(), destacadaCollectionDestacadaToAttach.getId());
                attachedDestacadaCollection.add(destacadaCollectionDestacadaToAttach);
            }
            receta.setDestacadaCollection(attachedDestacadaCollection);
            Collection<Categoria> attachedCategoriaCollection = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionCategoriaToAttach : receta.getCategoriaCollection()) {
                categoriaCollectionCategoriaToAttach = em.getReference(categoriaCollectionCategoriaToAttach.getClass(), categoriaCollectionCategoriaToAttach.getId());
                attachedCategoriaCollection.add(categoriaCollectionCategoriaToAttach);
            }
            receta.setCategoriaCollection(attachedCategoriaCollection);
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : receta.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getId());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            receta.setRecetaCollection(attachedRecetaCollection);
            Collection<Receta> attachedRecetaCollection1 = new ArrayList<Receta>();
            for (Receta recetaCollection1RecetaToAttach : receta.getRecetaCollection1()) {
                recetaCollection1RecetaToAttach = em.getReference(recetaCollection1RecetaToAttach.getClass(), recetaCollection1RecetaToAttach.getId());
                attachedRecetaCollection1.add(recetaCollection1RecetaToAttach);
            }
            receta.setRecetaCollection1(attachedRecetaCollection1);
            Collection<UsuarioHasReceta> attachedUsuarioHasRecetaCollection = new ArrayList<UsuarioHasReceta>();
            for (UsuarioHasReceta usuarioHasRecetaCollectionUsuarioHasRecetaToAttach : receta.getUsuarioHasRecetaCollection()) {
                usuarioHasRecetaCollectionUsuarioHasRecetaToAttach = em.getReference(usuarioHasRecetaCollectionUsuarioHasRecetaToAttach.getClass(), usuarioHasRecetaCollectionUsuarioHasRecetaToAttach.getUsuarioHasRecetaPK());
                attachedUsuarioHasRecetaCollection.add(usuarioHasRecetaCollectionUsuarioHasRecetaToAttach);
            }
            receta.setUsuarioHasRecetaCollection(attachedUsuarioHasRecetaCollection);
            Collection<Complejidad> attachedComplejidadCollection = new ArrayList<Complejidad>();
            for (Complejidad complejidadCollectionComplejidadToAttach : receta.getComplejidadCollection()) {
                complejidadCollectionComplejidadToAttach = em.getReference(complejidadCollectionComplejidadToAttach.getClass(), complejidadCollectionComplejidadToAttach.getComplejidadPK());
                attachedComplejidadCollection.add(complejidadCollectionComplejidadToAttach);
            }
            receta.setComplejidadCollection(attachedComplejidadCollection);
            Collection<RecetaHasMedidas> attachedRecetaHasMedidasCollection = new ArrayList<RecetaHasMedidas>();
            for (RecetaHasMedidas recetaHasMedidasCollectionRecetaHasMedidasToAttach : receta.getRecetaHasMedidasCollection()) {
                recetaHasMedidasCollectionRecetaHasMedidasToAttach = em.getReference(recetaHasMedidasCollectionRecetaHasMedidasToAttach.getClass(), recetaHasMedidasCollectionRecetaHasMedidasToAttach.getRecetaHasMedidasPK());
                attachedRecetaHasMedidasCollection.add(recetaHasMedidasCollectionRecetaHasMedidasToAttach);
            }
            receta.setRecetaHasMedidasCollection(attachedRecetaHasMedidasCollection);
            em.persist(receta);
            for (Ocasion ocasionCollectionOcasion : receta.getOcasionCollection()) {
                ocasionCollectionOcasion.getRecetaCollection().add(receta);
                ocasionCollectionOcasion = em.merge(ocasionCollectionOcasion);
            }
            for (Consultas consultasCollectionConsultas : receta.getConsultasCollection()) {
                consultasCollectionConsultas.getRecetaCollection().add(receta);
                consultasCollectionConsultas = em.merge(consultasCollectionConsultas);
            }
            for (Destacada destacadaCollectionDestacada : receta.getDestacadaCollection()) {
                destacadaCollectionDestacada.getRecetaCollection().add(receta);
                destacadaCollectionDestacada = em.merge(destacadaCollectionDestacada);
            }
            for (Categoria categoriaCollectionCategoria : receta.getCategoriaCollection()) {
                categoriaCollectionCategoria.getRecetaCollection().add(receta);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            for (Receta recetaCollectionReceta : receta.getRecetaCollection()) {
                recetaCollectionReceta.getRecetaCollection().add(receta);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            for (Receta recetaCollection1Receta : receta.getRecetaCollection1()) {
                recetaCollection1Receta.getRecetaCollection().add(receta);
                recetaCollection1Receta = em.merge(recetaCollection1Receta);
            }
            for (UsuarioHasReceta usuarioHasRecetaCollectionUsuarioHasReceta : receta.getUsuarioHasRecetaCollection()) {
                Receta oldRecetaOfUsuarioHasRecetaCollectionUsuarioHasReceta = usuarioHasRecetaCollectionUsuarioHasReceta.getReceta();
                usuarioHasRecetaCollectionUsuarioHasReceta.setReceta(receta);
                usuarioHasRecetaCollectionUsuarioHasReceta = em.merge(usuarioHasRecetaCollectionUsuarioHasReceta);
                if (oldRecetaOfUsuarioHasRecetaCollectionUsuarioHasReceta != null) {
                    oldRecetaOfUsuarioHasRecetaCollectionUsuarioHasReceta.getUsuarioHasRecetaCollection().remove(usuarioHasRecetaCollectionUsuarioHasReceta);
                    oldRecetaOfUsuarioHasRecetaCollectionUsuarioHasReceta = em.merge(oldRecetaOfUsuarioHasRecetaCollectionUsuarioHasReceta);
                }
            }
            for (Complejidad complejidadCollectionComplejidad : receta.getComplejidadCollection()) {
                Receta oldRecetaOfComplejidadCollectionComplejidad = complejidadCollectionComplejidad.getReceta();
                complejidadCollectionComplejidad.setReceta(receta);
                complejidadCollectionComplejidad = em.merge(complejidadCollectionComplejidad);
                if (oldRecetaOfComplejidadCollectionComplejidad != null) {
                    oldRecetaOfComplejidadCollectionComplejidad.getComplejidadCollection().remove(complejidadCollectionComplejidad);
                    oldRecetaOfComplejidadCollectionComplejidad = em.merge(oldRecetaOfComplejidadCollectionComplejidad);
                }
            }
            for (RecetaHasMedidas recetaHasMedidasCollectionRecetaHasMedidas : receta.getRecetaHasMedidasCollection()) {
                Receta oldRecetaOfRecetaHasMedidasCollectionRecetaHasMedidas = recetaHasMedidasCollectionRecetaHasMedidas.getReceta();
                recetaHasMedidasCollectionRecetaHasMedidas.setReceta(receta);
                recetaHasMedidasCollectionRecetaHasMedidas = em.merge(recetaHasMedidasCollectionRecetaHasMedidas);
                if (oldRecetaOfRecetaHasMedidasCollectionRecetaHasMedidas != null) {
                    oldRecetaOfRecetaHasMedidasCollectionRecetaHasMedidas.getRecetaHasMedidasCollection().remove(recetaHasMedidasCollectionRecetaHasMedidas);
                    oldRecetaOfRecetaHasMedidasCollectionRecetaHasMedidas = em.merge(oldRecetaOfRecetaHasMedidasCollectionRecetaHasMedidas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReceta(receta.getId()) != null) {
                throw new PreexistingEntityException("Receta " + receta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Receta receta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta persistentReceta = em.find(Receta.class, receta.getId());
            Collection<Ocasion> ocasionCollectionOld = persistentReceta.getOcasionCollection();
            Collection<Ocasion> ocasionCollectionNew = receta.getOcasionCollection();
            Collection<Consultas> consultasCollectionOld = persistentReceta.getConsultasCollection();
            Collection<Consultas> consultasCollectionNew = receta.getConsultasCollection();
            Collection<Destacada> destacadaCollectionOld = persistentReceta.getDestacadaCollection();
            Collection<Destacada> destacadaCollectionNew = receta.getDestacadaCollection();
            Collection<Categoria> categoriaCollectionOld = persistentReceta.getCategoriaCollection();
            Collection<Categoria> categoriaCollectionNew = receta.getCategoriaCollection();
            Collection<Receta> recetaCollectionOld = persistentReceta.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = receta.getRecetaCollection();
            Collection<Receta> recetaCollection1Old = persistentReceta.getRecetaCollection1();
            Collection<Receta> recetaCollection1New = receta.getRecetaCollection1();
            Collection<UsuarioHasReceta> usuarioHasRecetaCollectionOld = persistentReceta.getUsuarioHasRecetaCollection();
            Collection<UsuarioHasReceta> usuarioHasRecetaCollectionNew = receta.getUsuarioHasRecetaCollection();
            Collection<Complejidad> complejidadCollectionOld = persistentReceta.getComplejidadCollection();
            Collection<Complejidad> complejidadCollectionNew = receta.getComplejidadCollection();
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionOld = persistentReceta.getRecetaHasMedidasCollection();
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionNew = receta.getRecetaHasMedidasCollection();
            List<String> illegalOrphanMessages = null;
            for (UsuarioHasReceta usuarioHasRecetaCollectionOldUsuarioHasReceta : usuarioHasRecetaCollectionOld) {
                if (!usuarioHasRecetaCollectionNew.contains(usuarioHasRecetaCollectionOldUsuarioHasReceta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioHasReceta " + usuarioHasRecetaCollectionOldUsuarioHasReceta + " since its receta field is not nullable.");
                }
            }
            for (Complejidad complejidadCollectionOldComplejidad : complejidadCollectionOld) {
                if (!complejidadCollectionNew.contains(complejidadCollectionOldComplejidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Complejidad " + complejidadCollectionOldComplejidad + " since its receta field is not nullable.");
                }
            }
            for (RecetaHasMedidas recetaHasMedidasCollectionOldRecetaHasMedidas : recetaHasMedidasCollectionOld) {
                if (!recetaHasMedidasCollectionNew.contains(recetaHasMedidasCollectionOldRecetaHasMedidas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RecetaHasMedidas " + recetaHasMedidasCollectionOldRecetaHasMedidas + " since its receta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ocasion> attachedOcasionCollectionNew = new ArrayList<Ocasion>();
            for (Ocasion ocasionCollectionNewOcasionToAttach : ocasionCollectionNew) {
                ocasionCollectionNewOcasionToAttach = em.getReference(ocasionCollectionNewOcasionToAttach.getClass(), ocasionCollectionNewOcasionToAttach.getId());
                attachedOcasionCollectionNew.add(ocasionCollectionNewOcasionToAttach);
            }
            ocasionCollectionNew = attachedOcasionCollectionNew;
            receta.setOcasionCollection(ocasionCollectionNew);
            Collection<Consultas> attachedConsultasCollectionNew = new ArrayList<Consultas>();
            for (Consultas consultasCollectionNewConsultasToAttach : consultasCollectionNew) {
                consultasCollectionNewConsultasToAttach = em.getReference(consultasCollectionNewConsultasToAttach.getClass(), consultasCollectionNewConsultasToAttach.getId());
                attachedConsultasCollectionNew.add(consultasCollectionNewConsultasToAttach);
            }
            consultasCollectionNew = attachedConsultasCollectionNew;
            receta.setConsultasCollection(consultasCollectionNew);
            Collection<Destacada> attachedDestacadaCollectionNew = new ArrayList<Destacada>();
            for (Destacada destacadaCollectionNewDestacadaToAttach : destacadaCollectionNew) {
                destacadaCollectionNewDestacadaToAttach = em.getReference(destacadaCollectionNewDestacadaToAttach.getClass(), destacadaCollectionNewDestacadaToAttach.getId());
                attachedDestacadaCollectionNew.add(destacadaCollectionNewDestacadaToAttach);
            }
            destacadaCollectionNew = attachedDestacadaCollectionNew;
            receta.setDestacadaCollection(destacadaCollectionNew);
            Collection<Categoria> attachedCategoriaCollectionNew = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionNewCategoriaToAttach : categoriaCollectionNew) {
                categoriaCollectionNewCategoriaToAttach = em.getReference(categoriaCollectionNewCategoriaToAttach.getClass(), categoriaCollectionNewCategoriaToAttach.getId());
                attachedCategoriaCollectionNew.add(categoriaCollectionNewCategoriaToAttach);
            }
            categoriaCollectionNew = attachedCategoriaCollectionNew;
            receta.setCategoriaCollection(categoriaCollectionNew);
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getId());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            receta.setRecetaCollection(recetaCollectionNew);
            Collection<Receta> attachedRecetaCollection1New = new ArrayList<Receta>();
            for (Receta recetaCollection1NewRecetaToAttach : recetaCollection1New) {
                recetaCollection1NewRecetaToAttach = em.getReference(recetaCollection1NewRecetaToAttach.getClass(), recetaCollection1NewRecetaToAttach.getId());
                attachedRecetaCollection1New.add(recetaCollection1NewRecetaToAttach);
            }
            recetaCollection1New = attachedRecetaCollection1New;
            receta.setRecetaCollection1(recetaCollection1New);
            Collection<UsuarioHasReceta> attachedUsuarioHasRecetaCollectionNew = new ArrayList<UsuarioHasReceta>();
            for (UsuarioHasReceta usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach : usuarioHasRecetaCollectionNew) {
                usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach = em.getReference(usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach.getClass(), usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach.getUsuarioHasRecetaPK());
                attachedUsuarioHasRecetaCollectionNew.add(usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach);
            }
            usuarioHasRecetaCollectionNew = attachedUsuarioHasRecetaCollectionNew;
            receta.setUsuarioHasRecetaCollection(usuarioHasRecetaCollectionNew);
            Collection<Complejidad> attachedComplejidadCollectionNew = new ArrayList<Complejidad>();
            for (Complejidad complejidadCollectionNewComplejidadToAttach : complejidadCollectionNew) {
                complejidadCollectionNewComplejidadToAttach = em.getReference(complejidadCollectionNewComplejidadToAttach.getClass(), complejidadCollectionNewComplejidadToAttach.getComplejidadPK());
                attachedComplejidadCollectionNew.add(complejidadCollectionNewComplejidadToAttach);
            }
            complejidadCollectionNew = attachedComplejidadCollectionNew;
            receta.setComplejidadCollection(complejidadCollectionNew);
            Collection<RecetaHasMedidas> attachedRecetaHasMedidasCollectionNew = new ArrayList<RecetaHasMedidas>();
            for (RecetaHasMedidas recetaHasMedidasCollectionNewRecetaHasMedidasToAttach : recetaHasMedidasCollectionNew) {
                recetaHasMedidasCollectionNewRecetaHasMedidasToAttach = em.getReference(recetaHasMedidasCollectionNewRecetaHasMedidasToAttach.getClass(), recetaHasMedidasCollectionNewRecetaHasMedidasToAttach.getRecetaHasMedidasPK());
                attachedRecetaHasMedidasCollectionNew.add(recetaHasMedidasCollectionNewRecetaHasMedidasToAttach);
            }
            recetaHasMedidasCollectionNew = attachedRecetaHasMedidasCollectionNew;
            receta.setRecetaHasMedidasCollection(recetaHasMedidasCollectionNew);
            receta = em.merge(receta);
            for (Ocasion ocasionCollectionOldOcasion : ocasionCollectionOld) {
                if (!ocasionCollectionNew.contains(ocasionCollectionOldOcasion)) {
                    ocasionCollectionOldOcasion.getRecetaCollection().remove(receta);
                    ocasionCollectionOldOcasion = em.merge(ocasionCollectionOldOcasion);
                }
            }
            for (Ocasion ocasionCollectionNewOcasion : ocasionCollectionNew) {
                if (!ocasionCollectionOld.contains(ocasionCollectionNewOcasion)) {
                    ocasionCollectionNewOcasion.getRecetaCollection().add(receta);
                    ocasionCollectionNewOcasion = em.merge(ocasionCollectionNewOcasion);
                }
            }
            for (Consultas consultasCollectionOldConsultas : consultasCollectionOld) {
                if (!consultasCollectionNew.contains(consultasCollectionOldConsultas)) {
                    consultasCollectionOldConsultas.getRecetaCollection().remove(receta);
                    consultasCollectionOldConsultas = em.merge(consultasCollectionOldConsultas);
                }
            }
            for (Consultas consultasCollectionNewConsultas : consultasCollectionNew) {
                if (!consultasCollectionOld.contains(consultasCollectionNewConsultas)) {
                    consultasCollectionNewConsultas.getRecetaCollection().add(receta);
                    consultasCollectionNewConsultas = em.merge(consultasCollectionNewConsultas);
                }
            }
            for (Destacada destacadaCollectionOldDestacada : destacadaCollectionOld) {
                if (!destacadaCollectionNew.contains(destacadaCollectionOldDestacada)) {
                    destacadaCollectionOldDestacada.getRecetaCollection().remove(receta);
                    destacadaCollectionOldDestacada = em.merge(destacadaCollectionOldDestacada);
                }
            }
            for (Destacada destacadaCollectionNewDestacada : destacadaCollectionNew) {
                if (!destacadaCollectionOld.contains(destacadaCollectionNewDestacada)) {
                    destacadaCollectionNewDestacada.getRecetaCollection().add(receta);
                    destacadaCollectionNewDestacada = em.merge(destacadaCollectionNewDestacada);
                }
            }
            for (Categoria categoriaCollectionOldCategoria : categoriaCollectionOld) {
                if (!categoriaCollectionNew.contains(categoriaCollectionOldCategoria)) {
                    categoriaCollectionOldCategoria.getRecetaCollection().remove(receta);
                    categoriaCollectionOldCategoria = em.merge(categoriaCollectionOldCategoria);
                }
            }
            for (Categoria categoriaCollectionNewCategoria : categoriaCollectionNew) {
                if (!categoriaCollectionOld.contains(categoriaCollectionNewCategoria)) {
                    categoriaCollectionNewCategoria.getRecetaCollection().add(receta);
                    categoriaCollectionNewCategoria = em.merge(categoriaCollectionNewCategoria);
                }
            }
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    recetaCollectionOldReceta.getRecetaCollection().remove(receta);
                    recetaCollectionOldReceta = em.merge(recetaCollectionOldReceta);
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    recetaCollectionNewReceta.getRecetaCollection().add(receta);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
                }
            }
            for (Receta recetaCollection1OldReceta : recetaCollection1Old) {
                if (!recetaCollection1New.contains(recetaCollection1OldReceta)) {
                    recetaCollection1OldReceta.getRecetaCollection().remove(receta);
                    recetaCollection1OldReceta = em.merge(recetaCollection1OldReceta);
                }
            }
            for (Receta recetaCollection1NewReceta : recetaCollection1New) {
                if (!recetaCollection1Old.contains(recetaCollection1NewReceta)) {
                    recetaCollection1NewReceta.getRecetaCollection().add(receta);
                    recetaCollection1NewReceta = em.merge(recetaCollection1NewReceta);
                }
            }
            for (UsuarioHasReceta usuarioHasRecetaCollectionNewUsuarioHasReceta : usuarioHasRecetaCollectionNew) {
                if (!usuarioHasRecetaCollectionOld.contains(usuarioHasRecetaCollectionNewUsuarioHasReceta)) {
                    Receta oldRecetaOfUsuarioHasRecetaCollectionNewUsuarioHasReceta = usuarioHasRecetaCollectionNewUsuarioHasReceta.getReceta();
                    usuarioHasRecetaCollectionNewUsuarioHasReceta.setReceta(receta);
                    usuarioHasRecetaCollectionNewUsuarioHasReceta = em.merge(usuarioHasRecetaCollectionNewUsuarioHasReceta);
                    if (oldRecetaOfUsuarioHasRecetaCollectionNewUsuarioHasReceta != null && !oldRecetaOfUsuarioHasRecetaCollectionNewUsuarioHasReceta.equals(receta)) {
                        oldRecetaOfUsuarioHasRecetaCollectionNewUsuarioHasReceta.getUsuarioHasRecetaCollection().remove(usuarioHasRecetaCollectionNewUsuarioHasReceta);
                        oldRecetaOfUsuarioHasRecetaCollectionNewUsuarioHasReceta = em.merge(oldRecetaOfUsuarioHasRecetaCollectionNewUsuarioHasReceta);
                    }
                }
            }
            for (Complejidad complejidadCollectionNewComplejidad : complejidadCollectionNew) {
                if (!complejidadCollectionOld.contains(complejidadCollectionNewComplejidad)) {
                    Receta oldRecetaOfComplejidadCollectionNewComplejidad = complejidadCollectionNewComplejidad.getReceta();
                    complejidadCollectionNewComplejidad.setReceta(receta);
                    complejidadCollectionNewComplejidad = em.merge(complejidadCollectionNewComplejidad);
                    if (oldRecetaOfComplejidadCollectionNewComplejidad != null && !oldRecetaOfComplejidadCollectionNewComplejidad.equals(receta)) {
                        oldRecetaOfComplejidadCollectionNewComplejidad.getComplejidadCollection().remove(complejidadCollectionNewComplejidad);
                        oldRecetaOfComplejidadCollectionNewComplejidad = em.merge(oldRecetaOfComplejidadCollectionNewComplejidad);
                    }
                }
            }
            for (RecetaHasMedidas recetaHasMedidasCollectionNewRecetaHasMedidas : recetaHasMedidasCollectionNew) {
                if (!recetaHasMedidasCollectionOld.contains(recetaHasMedidasCollectionNewRecetaHasMedidas)) {
                    Receta oldRecetaOfRecetaHasMedidasCollectionNewRecetaHasMedidas = recetaHasMedidasCollectionNewRecetaHasMedidas.getReceta();
                    recetaHasMedidasCollectionNewRecetaHasMedidas.setReceta(receta);
                    recetaHasMedidasCollectionNewRecetaHasMedidas = em.merge(recetaHasMedidasCollectionNewRecetaHasMedidas);
                    if (oldRecetaOfRecetaHasMedidasCollectionNewRecetaHasMedidas != null && !oldRecetaOfRecetaHasMedidasCollectionNewRecetaHasMedidas.equals(receta)) {
                        oldRecetaOfRecetaHasMedidasCollectionNewRecetaHasMedidas.getRecetaHasMedidasCollection().remove(recetaHasMedidasCollectionNewRecetaHasMedidas);
                        oldRecetaOfRecetaHasMedidasCollectionNewRecetaHasMedidas = em.merge(oldRecetaOfRecetaHasMedidasCollectionNewRecetaHasMedidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = receta.getId();
                if (findReceta(id) == null) {
                    throw new NonexistentEntityException("The receta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta receta;
            try {
                receta = em.getReference(Receta.class, id);
                receta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UsuarioHasReceta> usuarioHasRecetaCollectionOrphanCheck = receta.getUsuarioHasRecetaCollection();
            for (UsuarioHasReceta usuarioHasRecetaCollectionOrphanCheckUsuarioHasReceta : usuarioHasRecetaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Receta (" + receta + ") cannot be destroyed since the UsuarioHasReceta " + usuarioHasRecetaCollectionOrphanCheckUsuarioHasReceta + " in its usuarioHasRecetaCollection field has a non-nullable receta field.");
            }
            Collection<Complejidad> complejidadCollectionOrphanCheck = receta.getComplejidadCollection();
            for (Complejidad complejidadCollectionOrphanCheckComplejidad : complejidadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Receta (" + receta + ") cannot be destroyed since the Complejidad " + complejidadCollectionOrphanCheckComplejidad + " in its complejidadCollection field has a non-nullable receta field.");
            }
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionOrphanCheck = receta.getRecetaHasMedidasCollection();
            for (RecetaHasMedidas recetaHasMedidasCollectionOrphanCheckRecetaHasMedidas : recetaHasMedidasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Receta (" + receta + ") cannot be destroyed since the RecetaHasMedidas " + recetaHasMedidasCollectionOrphanCheckRecetaHasMedidas + " in its recetaHasMedidasCollection field has a non-nullable receta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ocasion> ocasionCollection = receta.getOcasionCollection();
            for (Ocasion ocasionCollectionOcasion : ocasionCollection) {
                ocasionCollectionOcasion.getRecetaCollection().remove(receta);
                ocasionCollectionOcasion = em.merge(ocasionCollectionOcasion);
            }
            Collection<Consultas> consultasCollection = receta.getConsultasCollection();
            for (Consultas consultasCollectionConsultas : consultasCollection) {
                consultasCollectionConsultas.getRecetaCollection().remove(receta);
                consultasCollectionConsultas = em.merge(consultasCollectionConsultas);
            }
            Collection<Destacada> destacadaCollection = receta.getDestacadaCollection();
            for (Destacada destacadaCollectionDestacada : destacadaCollection) {
                destacadaCollectionDestacada.getRecetaCollection().remove(receta);
                destacadaCollectionDestacada = em.merge(destacadaCollectionDestacada);
            }
            Collection<Categoria> categoriaCollection = receta.getCategoriaCollection();
            for (Categoria categoriaCollectionCategoria : categoriaCollection) {
                categoriaCollectionCategoria.getRecetaCollection().remove(receta);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            Collection<Receta> recetaCollection = receta.getRecetaCollection();
            for (Receta recetaCollectionReceta : recetaCollection) {
                recetaCollectionReceta.getRecetaCollection().remove(receta);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            Collection<Receta> recetaCollection1 = receta.getRecetaCollection1();
            for (Receta recetaCollection1Receta : recetaCollection1) {
                recetaCollection1Receta.getRecetaCollection().remove(receta);
                recetaCollection1Receta = em.merge(recetaCollection1Receta);
            }
            em.remove(receta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Receta> findRecetaEntities() {
        return findRecetaEntities(true, -1, -1);
    }

    public List<Receta> findRecetaEntities(int maxResults, int firstResult) {
        return findRecetaEntities(false, maxResults, firstResult);
    }

    private List<Receta> findRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Receta findReceta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receta> rt = cq.from(Receta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
