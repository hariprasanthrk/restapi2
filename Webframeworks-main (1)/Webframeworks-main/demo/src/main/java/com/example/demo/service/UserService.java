package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private  UserRepo ur;
    public User createUser(User us)
    {
        return ur.save(us);
    }
    public User getUserById(int id)
    {
        return ur.findById(id).orElse(null);
    }
    public List <User> getAlldetails()
    {
        return ur.findAll();
    }
    public boolean updateDetails(int id,User u)
    {
        if(this.getUserById(id)==null)
        {
            return false;
        }
        try{
            ur.save(u);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public boolean deleteUser(int id)
    {
        if(this.getUserById(id) == null)
        {
            return false;
        }
        ur.deleteById(id);
        return true;
    }
    public List<User> getsort(int pageNumber,int pageSize,String field)
    {          
        return ur.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))).getContent();
    }
}
