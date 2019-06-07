package com.todoapp.todoapp.controller;

import java.util.List;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.function.Function;

import com.todoapp.todoapp.Repository.TodoRepository;
import com.todoapp.todoapp.model.Todo;

@RestController
@RequestMapping("/todos")
@CrossOrigin("*")

public class TodoController {
	
	@Autowired
	TodoRepository todoRepository;
	
	//("/todos")
	@GetMapping
	public List<Todo> getAllTodos(){
		Sort SortByCreatedAtDec = new Sort(Sort.Direction.DESC,"createdAt");
		return todoRepository.findAll(SortByCreatedAtDec);
		
	}
	
	@PostMapping
	//("/todos")
	public Todo createTodo(@Valid @RequestBody Todo todo) {
		System.out.println("PostMapping called");
		todo.setCompleted(false);
		return todoRepository.save(todo);
		
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable("id") String id){
		
		return todoRepository.findById(id)
				.map(todo ->ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value="/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @Valid 
			@RequestBody Todo todo){
		
		return todoRepository.findById(id)
				.map(tododata ->{
					tododata.setCompleted(todo.getCompleted());
					tododata.setTitle(todo.getTitle());
					Todo updatedTodo = todoRepository.save(tododata);
					
					return ResponseEntity.ok().body(updatedTodo);
					
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value="/todos/{id}")
	public void deleteTodo(@PathVariable("id")  String id){
		
		todoRepository.deleteById(id);		
//		Optional<Todo> t =todoRepository.findById(id);
//		Todo temp = t.get();

//		return todoRepository.findById(id)
//				.map(todo ->{
//					todoRepository.delete(todo);
//					ResponseEntity.ok().build();
//				})
//				.orElse(ResponseEntity.notFound().build());
	}
	
}
