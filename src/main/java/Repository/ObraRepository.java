/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repository;

import com.hopen.SistemaHopen.entities.Obra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martiniano
 */
@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {
    
    @Query(value = "SELECT * FROM obra o WHERE o.titular LIKE %nombre", nativeQuery = true) //aca hay que poner una variable que no me acuerdo como 
    List<Obra> findByName(String nombre);
    
}
