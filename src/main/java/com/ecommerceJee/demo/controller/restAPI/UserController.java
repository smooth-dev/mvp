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
import com.ecommerceJee.demo.domaine.UserVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.service.UserService;
 
@RestController
@RequestMapping("/UserVos")
public class UserController
{
    @Autowired
    UserService service;
 
    @GetMapping
    public ResponseEntity<List<UserVo>> getAllUserVos() {
        List<UserVo> list = service.getAllUsers();
 
        return new ResponseEntity<List<UserVo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<UserVo> getUserVoById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        UserVo entity = service.getUserById(id);
 
        return new ResponseEntity<UserVo>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<UserVo> createOrUpdateUserVo(UserVo user)
                                                    throws RecordNotFoundException {
        UserVo updated = service.createOrUpdateUser(user);
        return new ResponseEntity<UserVo>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteUserVoById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	UserVo empVoFound = service.getUserById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("UserVo doesn't exist", HttpStatus.OK);

        service.deleteUserById(id);
		return new ResponseEntity<>("UserVo is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<UserVo>> sortBy(@PathVariable String fieldName) {
       	 List<UserVo> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<UserVo>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<UserVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllUsers(pageid, size);
   	}
 
}