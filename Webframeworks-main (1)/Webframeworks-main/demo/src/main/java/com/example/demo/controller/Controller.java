package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.service.MenuService;
import com.example.demo.service.UserService;
@RestController
public class Controller {
    @Autowired
MenuService ms;
@Autowired
UserService us;
@PostMapping("/postm")
public ResponseEntity<Menu>add(@RequestBody Menu m){
    Menu newmenu = ms.create(m);
   return new ResponseEntity<>(newmenu,HttpStatus.CREATED); 
}
@PostMapping("/postm/{byId}")
public ResponseEntity<Menu> addbyid(@RequestBody Menu m,@PathVariable("byId") int id)
{
    User obj=us.getUserById(id);
    m.setUser(obj);
    return new ResponseEntity<>(ms.create(m),HttpStatus.CREATED);
}
@GetMapping("/getm")
public ResponseEntity <List<Menu>>showinfo(){
    List<Menu>obj = ms.getAlldetails();
    return new ResponseEntity<>(obj,HttpStatus.OK);
}
@PutMapping("/putm/{menuId}")
    public ResponseEntity<Menu> putMethodName(@PathVariable("menuId") int id, @RequestBody Menu employee) {
        if(ms.updateDetails(id,employee))
        {
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delm/{menuId}")
    public ResponseEntity<Boolean> delete(@PathVariable("menuId") int id)
    {
        if(ms.deleteMenu(id))
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }
    @GetMapping("/sortBy/{offset}/{pagesize}/{field}")
    public List<Menu> getsorting(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
    {
        return ms.getsort(offset,pagesize,field);
    }

    @GetMapping("getmenu/{price}")
	public List <Menu> displayAll( @PathVariable("price") Long i){
		return ms.getDetailsByPrice(i);
	}
	
	@DeleteMapping("/deletenew/{id}")
	public String deleteeInfo(@PathVariable("id") int s){
		return ms.deletemenudetails(s)+"Deleted";
	}
	
	@PutMapping("/update/{id}/{id1}")
	public String updateeInfo(@PathVariable("id") int s,@PathVariable("id1") int s1){
		return ms.updatemenudetails(s,s1)+"Updated";
	}
}