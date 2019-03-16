package com.sindhu.springboot.web.springboot.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sindhu.springboot.web.springboot.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{
	public List<Todo> findByUser(String user); 
}
