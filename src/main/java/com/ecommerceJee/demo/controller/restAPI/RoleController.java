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
import com.ecommerceJee.demo.domaine.RoleVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.service.RoleService;
 
@RestController
@RequestMapping("/roles")
public class RoleController
{
    @Autowired
    RoleService service;
 
    @GetMapping
    public ResponseEntity<List<RoleVo>> getAllRoleVos() {
        List<RoleVo> list = service.getAllRoles();
 
        return new ResponseEntity<List<RoleVo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<RoleVo> getRoleVoById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        RoleVo entity = service.getRoleById(id);
 
        return new ResponseEntity<RoleVo>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<RoleVo> createOrUpdateRoleVo(RoleVo role)
                                                    throws RecordNotFoundException {
        RoleVo updated = service.createOrUpdateRole(role);
        return new ResponseEntity<RoleVo>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteRoleVoById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	RoleVo empVoFound = service.getRoleById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("RoleVo doesn't exist", HttpStatus.OK);

        service.deleteRoleById(id);
		return new ResponseEntity<>("RoleVo is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<RoleVo>> sortBy(@PathVariable String fieldName) {
       	 List<RoleVo> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<RoleVo>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<RoleVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllRoles(pageid, size);
   	}
 
}