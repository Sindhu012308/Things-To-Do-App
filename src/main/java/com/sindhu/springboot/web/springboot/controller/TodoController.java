package com.sindhu.springboot.web.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sindhu.springboot.web.springboot.model.Todo;
import com.sindhu.springboot.web.springboot.service.TodoRepository;
//import com.sindhu.springboot.web.springboot.service.LoginService;
import com.sindhu.springboot.web.springboot.service.TodoService;

@Controller
//@SessionAttributes("name")
public class TodoController {
	@Autowired
	TodoService todoService;
	
	@Autowired
	TodoRepository repository;
	//When we update any todo the targetDate field contains the string, we need to convert it to Date format 
	//So we use initBinder to covert that string into required date format
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Date - dd/MM/yyyy
		//to specify date format we use SimpleDateFormat
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//to convert the string into date we use WebDataBinder
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(path="/list-todo", method=RequestMethod.GET)
	public String listTodo(ModelMap model) {
		//String name = getLoggedinUserName(model);
		String name = getLoggedinUserName();
		model.addAttribute("todos", repository.findByUser(name));
		//model.addAttribute("todos", todoService.retrieveTodos(name));
		return "list-todo";
	}

	/*private String getLoggedinUserName(ModelMap model) {
		return (String) model.get("name");
	}*/
	
	private String getLoggedinUserName() {                                                           
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();    
		if(principal instanceof UserDetails) {                                                       
			return ((UserDetails)principal).getUsername();                                           
		}                                                                                            
		return principal.toString();                                                                 
	                                                                                                 
	}                                                                                                
	
	@RequestMapping(path="/add-todo", method=RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
	//	model.addAttribute("todo", new Todo(0,getLoggedinUserName(model),"Default Desc",new Date(), false));
		model.addAttribute("todo", new Todo(0,getLoggedinUserName(),"Default Desc",new Date(), false));
		return "todo";
	}
	
	@RequestMapping(path="/delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		//if(id==1)
		//	throw new RuntimeException("Something went wrong");
		repository.deleteById(id);
		//todoService.DeleteTodo(id);
		return "redirect:/list-todo";
	}
	
	@RequestMapping(path="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		//Todo todo = todoService.retrieveTodoById(id);
		Todo todo = repository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(path="/update-todo", method=RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, ModelMap model, BindingResult result ) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		//todo.setUser(getLoggedinUserName(model));
		todo.setUser(getLoggedinUserName());
		repository.save(todo);
		//todoService.updateTodo(todo);
		return "redirect:/list-todo";
	}
	
	@RequestMapping(path="/add-todo", method=RequestMethod.POST)
	//@Valid is used for the validation i.e., spring form validation on desc of todo
	//@BindingResult is used to check whether the validation is performed and has any errors and what to do if errors occurs.
	public String addTodo(@Valid Todo todo, ModelMap model, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		//todoService.addTodo(getLoggedinUserName(model), todo.getDesc(), todo.getTargetDate(), false);
		todo.setUser(getLoggedinUserName());
		repository.save(todo);
		//todoService.addTodo(getLoggedinUserName(), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todo";
	}
	
}
