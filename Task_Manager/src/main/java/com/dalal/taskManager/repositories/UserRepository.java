package com.dalal.taskManager.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dalal.taskManager.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findAll();
	User findByEmail(String email);
	
//    // previous methods removed for brevity
//    //...
//    
//    // Select all, we get a list of Dojo objects back.
//    @Query(value="SELECT * from dojos", nativeQuery=true)
//    List<Dojo> findAllDojosWithNativeQuery();
//    
//    // get all the names of the dojos with id. If we want to select specific column, we will get a list of Object arrays because it is no longer Dojo objects. Each index of the array will be the column selected respectively. Therefore 0 = id column, 1 = name column
//    @Query(value="SELECT id, name from dojos", nativeQuery=true)
//    List<Object[]> findAllDojosNamesWithId2();
//    
//    // get one dojo
//    @Query(value="SELECT * FROM dojos WHERE id = ?1", nativeQuery=true)
//    Dojo getDojoWhereId(Long id);
//    
//    
//    @Query("SELECT d FROM Dojo d JOIN d.ninjas n")
//    List<Dojo> joinDojosAndNinjas();
//    
//    // inner join retrieving dojos and ninjas
//    @Query("SELECT d, n FROM Dojo d JOIN d.ninjas n")
//    List<Object[]> joinDojosAndNinjas2();
}
