package com.dalal.taskManager.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
//import org.springframework.data.annotation.Transient;



@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
//    @Size(min = 8, max = 20)
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @Size(min=0, max=3)
    private List<Task> tasks;
//    private List<Task> tasks = new ArrayList<>(3); 
    
    public User() {
    }
    
    public List<Task> getTasks(){
    	return tasks;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public String getPasswordConfirmation() {
    	return this.passwordConfirmation;
    }
    
    public void setId(Long id) {
   	 this.id=id;
   }
    
    public void setName(String name) {
   	 this.name=name;
   }
   
    
    public void setEmail(String email) {
   	 this.email=email;
   }
    
    public void setPassword(String password) {
   	 this.password=password;
   }
    
    public void setPasswordConfirmation(String passwordConfirmation) {
   	 this.passwordConfirmation=passwordConfirmation;
   }
   

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
