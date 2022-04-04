/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hopen.SistemaHopen.metodos;

import com.hopen.SistemaHopen.entities.Obra;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Martiniano
 */
public class ObraJPAController implements Serializable{
    
    public ObraJPAController (EntityManagerFactory emf){
       this.emf = emf;
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Obra");
    
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
     
     
    
    
    
}
