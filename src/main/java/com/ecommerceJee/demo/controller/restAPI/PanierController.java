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
import com.ecommerceJee.demo.domaine.PanierVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.service.PanierService;
 
@RestController
@RequestMapping("/paniers")
public class PanierController
{
    @Autowired
    PanierService service;
 
    @GetMapping
    public ResponseEntity<List<PanierVo>> getAllPanierVos() {
        List<PanierVo> list = service.getAllPaniers();
 
        return new ResponseEntity<List<PanierVo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<PanierVo> getPanierVoById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        PanierVo entity = service.getPanierById(id);
 
        return new ResponseEntity<PanierVo>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<PanierVo> createOrUpdatePanierVo(PanierVo article)
                                                    throws RecordNotFoundException {
        PanierVo updated = service.createOrUpdatePanier(article);
        return new ResponseEntity<PanierVo>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deletePanierVoById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	PanierVo empVoFound = service.getPanierById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("PanierVo doesn't exist", HttpStatus.OK);

        service.deletePanierById(id);
		return new ResponseEntity<>("PanierVo is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<PanierVo>> sortBy(@PathVariable String fieldName) {
       	 List<PanierVo> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<PanierVo>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<PanierVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllPaniers(pageid, size);
   	}
 
}