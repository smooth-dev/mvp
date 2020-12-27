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
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.service.PanierService;
 
@RestController
@RequestMapping("/paniers")
public class PanierController
{
    @Autowired
    PanierService service;
 
    @GetMapping
    public ResponseEntity<List<Panier>> getAllPaniers() {
        List<Panier> list = service.getAllPaniers();
 
        return new ResponseEntity<List<Panier>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanierById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        Panier entity = service.getPanierById(id);
 
        return new ResponseEntity<Panier>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Panier> createOrUpdatePanier(Panier article)
                                                    throws RecordNotFoundException {
        Panier updated = service.createOrUpdatePanier(article);
        return new ResponseEntity<Panier>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deletePanierById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	Panier empVoFound = service.getPanierById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("Panier doesn't exist", HttpStatus.OK);

        service.deletePanierById(id);
		return new ResponseEntity<>("Panier is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<Panier>> sortBy(@PathVariable String fieldName) {
       	 List<Panier> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<Panier>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<Panier> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllPaniers(pageid, size);
   	}
 
}