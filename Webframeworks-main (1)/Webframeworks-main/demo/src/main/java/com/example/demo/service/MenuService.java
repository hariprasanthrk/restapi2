package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepo;

@Service
public class MenuService {
    @Autowired
    private  MenuRepo mr;
    public Menu create(Menu m)
    {
        return mr.save(m);
    }
    public Menu getMenuById(int id)
    {
        return mr.findById(id).orElse(null);
    }
    public List <Menu> getAlldetails()
    {
        return mr.findAll();
    }
    public boolean updateDetails(int id,Menu m)
    {
        if(this.getMenuById(id)==null)
        {
            return false;
        }
        try{
            mr.save(m);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public boolean deleteMenu(int id)
    {
        if(this.getMenuById(id) == null)
        {
            return false;
        }
        mr.deleteById(id);
        return true;
    }
    public List<Menu> getsort(int pageNumber,int pageSize,String field)
    {          
        return mr.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))).getContent();
    }
    // public List <Menu> getDetails(int s, String s1){
	// 	return er.getmenuinfo(s,s1);
	// }
	// public List <Menu> getDetails(int s, String s1){
    //     return mr.findByIdOrName(s,s1);
    // }
	public List <Menu> getDetailsByPrice(Long i)
    {
        return mr.filterByprice(i);
    }
    public Integer deletemenudetails(int s){
        return mr.deletemenuInfo(s);
    }
    public Integer updatemenudetails(int s,int s1){
        return mr.updatemenuInfo(s,s1);
    }
}
