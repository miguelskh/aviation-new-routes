package com.comit.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comit.model.Route;

@Repository
public interface RoutesRepository extends JpaRepository<Route, Long>{
    
       List<Route> findByDeleted(Boolean deleted);
       
}
