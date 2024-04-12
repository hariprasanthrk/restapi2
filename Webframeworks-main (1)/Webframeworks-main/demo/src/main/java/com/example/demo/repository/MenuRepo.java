package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Menu;
@Repository
public interface MenuRepo extends JpaRepository<Menu,Integer>{
    // @Query(value="select * from Menu where id=:i or name=:n",nativeQuery = true)
	/*List<Menu> findByPrice(Long price);/* */
	// //List<Menu> findByIdOrName(int id,String name);
    /*List<Menu> findByPrice(Long price);/* */
	
    @Query(value = "select p from Menu p where p.price <= :price order by price")
    public List<Menu> filterByprice(Long price);
	

	@Modifying
	@Transactional
	@Query(value="delete from Menu where id=:s",nativeQuery=true)
	public Integer deletemenuInfo(@Param("s") int id);
    @Modifying
	@Transactional
	@Query(value="update Menu m set m.id =?1 where m.id=?2",nativeQuery=true)
	public Integer updatemenuInfo(int id,int newid);

    
}
