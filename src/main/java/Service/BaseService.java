/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import java.util.List;

/**
 *
 * @author Martiniano
 */
public interface BaseService<E> {
    
    public List<E> findAll() throws Exception;
    public E findById(long id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(long id, E entity) throws Exception;
    public boolean delete(long id) throws Exception;
}
