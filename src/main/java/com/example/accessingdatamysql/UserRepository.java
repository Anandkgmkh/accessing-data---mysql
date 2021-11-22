package com.example.accessingdatamysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>{
    @Query(" select email from User where name=?1")
	String findByEmail1(String s);

    @Query("select count(*) from User")
	int indByCount();

	User findByName(String s);

	

}
