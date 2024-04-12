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

import com.example.demo.model.User;

import com.example.demo.service.UserService;
@RestController
public class UserController {
    @Autowired
UserService us;
@PostMapping("/postu")
public ResponseEntity<User>add(@RequestBody User u){
    User newuser = us.createUser(u);
   return new ResponseEntity<>(newuser,HttpStatus.CREATED); 
}
@GetMapping("/getu")
public ResponseEntity <List<User>>showinfo(){
    List<User>obj = us.getAlldetails();
    return new ResponseEntity<>(obj,HttpStatus.OK);
}
@PutMapping("/putu/{userId}")
    public ResponseEntity<User> putMethodName(@PathVariable("userId") int id, @RequestBody User employee) {
        if(us.updateDetails(id,employee))
        {
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delu/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable("userId") int id)
    {
        if(us.deleteUser(id))
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }
    
@GetMapping("/api/user/{offset}/{pagesize}/{field}")
public List<User> getsorting(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
{
    return us.getsort(offset,pagesize,field);
}
}