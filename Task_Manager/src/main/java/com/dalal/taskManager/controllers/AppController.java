package com.dalal.taskManager.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dalal.taskManager.models.Task;
import com.dalal.taskManager.models.User;
import com.dalal.taskManager.services.TaskService;
import com.dalal.taskManager.services.UserService;

@Controller
public class AppController {
	
	 private final UserService userService;
	 private final TaskService taskService;
	    
	 public AppController(UserService userService,TaskService taskService) {
	        this.userService = userService;
	        this.taskService = taskService;
	    }
	 
	//show registration and login form 
	  @RequestMapping("/")
	  public String newApp(@Valid @ModelAttribute("user") User user) { 	
      return "/views/index.jsp";
  }
	  
	// register user
	    @RequestMapping(value="/registration", method=RequestMethod.POST)
	    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
	      
	    	if(!user.getPassword().contentEquals(user.getPasswordConfirmation())) {
	        	model.addAttribute("registration_error", "password must be match");
	    		return "/views/index.jsp";
	    	}
	    	
	    	if(user.getPassword().length() <8) {
	    	  	model.addAttribute("registration_error", "password must be at least 8 characters");
	    		return "/views/index.jsp";
	    	}
	    	if(result.hasErrors()) {
	        	return "/views/index.jsp";
	        }
	        User theuser = userService.registerUser(user);
	        session.setAttribute("userId", theuser.getId());
	        return "redirect:/tasks";
	    }
	    
	    
	 // login user 
	    @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
	        boolean flag = userService.authenticateUser(email, password);

	        if(flag) {
	        	User theuser = userService.findByEmail(email);
	            session.setAttribute("userId", theuser.getId());
	            return "redirect:/tasks";
	        }
	        else {
	        	model.addAttribute("error", "try again");
	        	return "/views/index.jsp";
	        }
	    }
	  
	    // show all tasks
	    @RequestMapping("/tasks")
	    public String showTasks(Model model,HttpSession session) {	
	    	
	    	 Long userID = (Long) session.getAttribute("userId");
	         User theuser = userService.findUserById(userID);
	         model.addAttribute("user", theuser);
	         
	        List<Task> tasks = taskService.allTasks();
	        model.addAttribute("tasks", tasks);
	        return "/views/tasks.jsp";
	    }
	    // show all tasks in ascending w
	    @RequestMapping("/tasks/Asc")
	    public String showTasksAsc(Model model,HttpSession session) {	
	    	
	    	 Long userID = (Long) session.getAttribute("userId");
	         User theuser = userService.findUserById(userID);
	         model.addAttribute("user", theuser);
	         
	        List<Task> tasks = taskService.allTasksAsc();
	       // Collections.sort(tasks);
	        
	        model.addAttribute("tasks", tasks);
	        return "/views/tasks.jsp";
	    }
	    
	    // show all tasks in descending w
	    @RequestMapping("/tasks/Dsc")
	    public String showTasksDes(Model model,HttpSession session) {	
	    	
	    	 Long userID = (Long) session.getAttribute("userId");
	         User theuser = userService.findUserById(userID);
	         model.addAttribute("user", theuser);
	         
	        List<Task> tasks = taskService.allTasksDesc();
	       // Collections.sort(tasks, Collections.reverseOrder());
	        model.addAttribute("tasks", tasks);
	        return "/views/tasks.jsp";
	    }
 
	    
	    //create new task
	    @RequestMapping("/tasks/new")
	    public String createTask(Model model,HttpSession session,@Valid @ModelAttribute("task") Task task) {	
	    	
	    	 Long userID = (Long) session.getAttribute("userId");
	         User theuser = userService.findUserById(userID);
	         model.addAttribute("user", theuser);
	        List<User> users = userService.allUsers();
		      model.addAttribute("users", users);  
	        
	        return "/views/new.jsp";
	    }
	    
	    
	    @RequestMapping(value="/tasks/create", method=RequestMethod.POST)
	    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result) {
	        if (result.hasErrors()) {
	            return "/views/new.jsp";
	        } else {
	        	taskService.createTask(task);
	            return "redirect:/tasks";
	        }
	  
	    }
	    
	  
	    //show task info
	    @RequestMapping("/tasks/{id}")
	    public String showTask(@PathVariable("id")Long id,Model model,HttpSession session) {
	    	
		   Task task = taskService.findTask(id);
	        model.addAttribute("task", task);
	         Long userID = (Long) session.getAttribute("userId");
	         User theuser = userService.findUserById(userID);
	         model.addAttribute("user", theuser);
	        return "/views/info.jsp";
	    }
	    
	    
	  //edit the task
	    @RequestMapping("/tasks/{id}/edit")
	    public String editTask(@PathVariable("id")Long id,Model model,HttpSession session) {
	    	
		   Task task = taskService.findTask(id);
	        model.addAttribute("task", task);
	         Long userID = (Long) session.getAttribute("userId");
	         User theuser = userService.findUserById(userID);
	         model.addAttribute("user", theuser);
	         
	         List <User> users = userService.allUsers();
	         model.addAttribute("users", users);
	        if(theuser.getName().equals(task.getCreator())) {
	        	  return "/views/edit.jsp";
	        }
	        return "redirect:/tasks";
	    }
	    
	    
	    @RequestMapping(value="/tasks/{id}/editTask", method=RequestMethod.POST)
	    public String update(@PathVariable("id") Long id,@Valid @ModelAttribute("task") Task task, BindingResult result) {
	        Task temp_task = taskService.updateTask(task); 
	        if(result.hasErrors()) {
	        	return "/views/edit.jsp";
	        }
	        return "redirect:/tasks";
	    }
	   
	  //delete the task if it is completed
	    
	    @RequestMapping(value="/tasks/{id}/deleteTask")
	    public String destroy(@PathVariable("id") Long id) {
	    	taskService.deleteTask(id);
	    	return "redirect:/tasks";
	    }
	    
	    //logout the user
	    @RequestMapping("/logout")
	    public String logout(HttpSession session) {
	    	session.invalidate();
	    	return"redirect:/";
	    }
	  
	  
}
