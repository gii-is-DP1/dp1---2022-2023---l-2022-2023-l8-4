package org.springframework.samples.petclinic.user;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	@Query("SELECT user FROM User user INNER JOIN user.authorities a WHERE a.authority like 'admin'")
	public Collection<User> findAllAdmins();
	
}
