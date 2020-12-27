package com.ecommerceJee.demo.controller.restAPI;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.service.UserService;
 
@RestController
@RequestMapping("/Users")
public class UserController
{
    @Autowired
    UserService service;
 
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = service.getAllUsers();
 
        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        User entity = service.getUserById(id);
 
        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(User user)
                                                    throws RecordNotFoundException {
        User updated = service.createOrUpdateUser(user);
        return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteUserById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	User empVoFound = service.getUserById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("User doesn't exist", HttpStatus.OK);

        service.deleteUserById(id);
		return new ResponseEntity<>("User is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<User>> sortBy(@PathVariable String fieldName) {
       	 List<User> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<User> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllUsers(pageid, size);
   	}
 
}