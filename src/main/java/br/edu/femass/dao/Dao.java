package br.edu.femass.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Dao<E> {

    protected static EntityManagerFactory emf;
    protected EntityManager em;
    protected Class<E> entity;

    static {
        emf = Persistence.createEntityManagerFactory("jpa");
    }
    public Dao(Class<E> entity) {
        em = emf.createEntityManager();
        this.entity = entity;
    }
    public Dao<E> create(E entity) {
        abrir();
        em.persist(entity);
        fechar();
        return this;
    }

    public Dao<E> abrir() {
        em.getTransaction().begin();
        return this;
    }

    public Dao<E> fechar() {
        em.getTransaction().commit();
        return this;
    }

    public List<E> findAll() {
        Query q = em.createQuery("From " + entity.getName());
        return q.getResultList();
    }

    public E findById(Object id) {
        return em.find(entity, id);
    }
    public Dao<E> delete (Object id) {
        Dao<E> dao = new Dao<E>(entity);
        E encontrado = dao.findById(id);
        abrir();
        if (encontrado==null) return null;
        em.remove(em.merge(encontrado));
        fechar();

        return this;
    }

    public Dao<E> update (E object) {

        abrir();
        em.merge(object);
        fechar();

        return this;
    }
}