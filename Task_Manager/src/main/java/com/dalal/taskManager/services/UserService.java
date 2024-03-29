package com.dalal.taskManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dalal.taskManager.models.User;
import com.dalal.taskManager.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
@Service
public class UserService {
	 private final UserRepository userRepository;
	 
	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
	    
	    
	    public User registerUser(User user) {
	        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(hashed);
	        return userRepository.save(user);
	    }
	    
	    // find user by email
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	 // find user by id
	    public User findUserById(Long id) {
	        Optional<User> optionalUser = userRepository.findById(id);
	        if(optionalUser.isPresent()) {
	            return optionalUser.get();
	        } else {
	            return null;
	        }
	    }
	    
	    public boolean authenticateUser(String email, String password) {
	        // first find the user by email
	        User user = userRepository.findByEmail(email);
	        // if we can't find it by email, return false
	        if(user == null) {
	            return false;
	        } else {
	            // if the passwords match, return true, else, return false
	            if(BCrypt.checkpw(password, user.getPassword())) {
	                return true;
	            } else {
	                return false;
	            }
	        }
	    }
	    
	    public List<User> allUsers() {
	        return userRepository.findAll();
	    }
	    
	
}
