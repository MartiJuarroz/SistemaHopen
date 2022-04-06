/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Repository.ObraRepository;
import com.hopen.SistemaHopen.entities.Obra;
import com.hopen.SistemaHopen.metodos.ObraJPAController;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martiniano
 */

public class ObraService implements BaseService<Obra> {
    
    private ObraJPAController ObraController = new ObraJPAController();
    private Obra obra = new Obra();
    private String mensaje = "";

    
    @Autowired
    private ObraRepository obraRepository;
    
    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }
    
   
    @Override
    @Transactional
    public List<Obra> findAll() throws Exception {
        try{
            List<Obra> entities = obraRepository.findAll();
            return entities;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Obra findById(long id) throws Exception {
         try{
            Optional<Obra> entityOptional = obraRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Obra save(Obra entity) throws Exception {
         try{
            entity = obraRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Obra update(long id, Obra entity) throws Exception {
         try{
            Optional<Obra> entityOptional = obraRepository.findById(id);
            Obra entityUpdate = entityOptional.get();
            entityUpdate = obraRepository.save(entity);
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    

    @Override
    @Transactional
    public boolean delete(long id) throws Exception {
        try{
            if (obraRepository.existsById(id)){
                obraRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public List<Obra> findByName(String name) throws Exception {
         try{
            List<Obra> entidades = obraRepository.findByName(name);
            return entidades;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
    @Transactional
    public Obra findOneByName(String name) throws Exception {
         try{
            Obra entidad = obraRepository.findOneByName(name);
            return entidad;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
   /* @Transactional
    public List<Obra> buscarObras(String tit){
        Obra obra;
        EntityManager em = ObraController.getEntityManager();
        Query query = em.createQuery("SELECT o FROM obra o WHERE o.titular LIKE :titular");
        query.setParameter("titular", tit+"%");
        List<Obra> obras = query.getResultList();
        if(ObraController.findObra(tit) == null){
                JOptionPane.showMessageDialog(null, "Obra no existente");
        }
        return obras;
        } */
    
/*    @Transactional
    public Obra buscarObra(String tit){
        Obra obra = new Obra();
        EntityManager em = ObraController.getEntityManager();
        try{
            Query query = em.createQuery("SELECT * FROM obra WHERE titular=:titular");
            query.setParameter("titular", tit+"%");
            obra = (Obra) query.getSingleResult();
            if(ObraController.findObra(tit) == null){
                JOptionPane.showMessageDialog(null, "Obra no existente");
            }
        } catch(Exception e){
            
        }
        return obra;
    }*/
    
    public void listarObras(JTable tabla) {
        DefaultTableModel model;
        String [] titulo = {"TITULAR","TOTAL PRESUPUESTO","FECHA PRESUPUESTO"};
        model = new DefaultTableModel(null,titulo);
        
        List<Obra> datos = ObraController.findObraEntities();
                
        String [] datosObra = new String[3];
        for(Obra obras : datos){
               datosObra[0] = obras.getTitular()+"";
               datosObra[1] = obras.getTotalPresupuesto()+"";
               datosObra[2] = obras.getFechaPresupuesto()+"";
               model.addRow(datosObra);
    }
        tabla.setModel(model);
        
    }
   
}
