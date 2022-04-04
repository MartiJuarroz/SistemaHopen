/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Repository.ObraRepository;
import com.hopen.SistemaHopen.entities.Obra;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martiniano
 */
@Service
public class ObraService implements BaseService<Obra> {
    
    @Autowired
    private ObraRepository obraRepository;
    
   
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
    
    @Override
    @Transactional
    public List<Obra> findByName(String name) throws Exception {
         try{
            List<Obra> entidades = obraRepository.findByName(name);
            return entidades;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
}
