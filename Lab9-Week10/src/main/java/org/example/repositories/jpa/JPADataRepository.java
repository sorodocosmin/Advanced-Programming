package org.example.repositories.jpa;

import jakarta.persistence.EntityManager;
import org.example.entities.AbstractEntity;
import org.example.connections.ManagerConnectionJPA;
import org.example.repositories.DataRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class JPADataRepository
<T extends AbstractEntity, IdType extends Serializable>
        implements DataRepository<T, IdType> {
    protected EntityManager em = ManagerConnectionJPA.getEntityManager();

    public void create (T entity){

        try {
            System.out.println("In create Abstractt");
            em.getTransaction().begin();

            System.out.println("Before Persist");
            em.persist(entity);
            System.out.println("After Persist");

            em.getTransaction().commit();

        }
        catch (Exception e){
            System.out.println("Exception: at creating Entity : " + entity.getClass().getName() + "\n Name : " + entity.toString() + "\n Error : " + e.getMessage());
            em.getTransaction().rollback();
        }

    }

    public Optional<T> findById(Class<T> classEntity, IdType id){

        T findEntity = em.find(classEntity, id);

        return findEntity == null ? Optional.empty() : Optional.of(findEntity);
    }
    public Optional<List<T>> findAll (Class<T> classEntity){

        return Optional.of(em.createNamedQuery(classEntity.getSimpleName() + ".findAll", classEntity).getResultList());

    }
    public void delete (T entity){
        try{

            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public EntityManager getConnectionFromRepo(){
        return this.em;
    }

}
