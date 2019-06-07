package com.todoapp.todoapp.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.todoapp.model.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String>{

}
