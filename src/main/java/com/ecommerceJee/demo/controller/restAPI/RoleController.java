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
import com.ecommerceJee.demo.model.Role;
import com.ecommerceJee.demo.service.RoleService;
 
@RestController
@RequestMapping("/roles")
public class RoleController
{
    @Autowired
    RoleService service;
 
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> list = service.getAllRoles();
 
        return new ResponseEntity<List<Role>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        Role entity = service.getRoleById(id);
 
        return new ResponseEntity<Role>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Role> createOrUpdateRole(Role role)
                                                    throws RecordNotFoundException {
        Role updated = service.createOrUpdateRole(role);
        return new ResponseEntity<Role>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteRoleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	Role empVoFound = service.getRoleById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("Role doesn't exist", HttpStatus.OK);

        service.deleteRoleById(id);
		return new ResponseEntity<>("Role is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<Role>> sortBy(@PathVariable String fieldName) {
       	 List<Role> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<Role>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<Role> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllRoles(pageid, size);
   	}
 
}