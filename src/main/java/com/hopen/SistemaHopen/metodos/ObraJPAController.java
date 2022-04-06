/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hopen.SistemaHopen.metodos;

import com.hopen.SistemaHopen.entities.Obra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martiniano
 */
 public class ObraJPAController implements Serializable{
    
     @Autowired
     EntityManagerFactory emf;
    
    public ObraJPAController (EntityManagerFactory emf){
       this.emf = emf;
    }
    
 //   private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.hopen_SistemaHopen_jar_0.0.1-SNAPSHOTPU");
    
    EntityManager entitymanager = emf.createEntityManager( );
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public ObraJPAController(){
    }
     
    public void create(Obra obra){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(obra);
            em.getTransaction().commit();
        }finally{
            if (em != null){
                em.close();
            }
        }
    }
    
    public Obra findObra(String tit){
            EntityManager em = getEntityManager();
            try{
                return em.find(Obra.class, tit);
            } finally{
                em.close();
            }
        }
    
    public List<Obra> findObraEntities(){
        return findObraEntities(true,-1,-1);
    }
    
    public List<Obra> findObraEntities(int maxResult, int firstResult){
        return findObraEntities(false,maxResult,firstResult);       
    }
    
    public List<Obra> findObraEntities(boolean all,int maxResult, int firstResult){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Obra.class));
            Query q = em.createQuery(cq);
            if(!all){
                q.setMaxResults(maxResult);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }finally{
            em.close();
        }
    
}
        
    
}
