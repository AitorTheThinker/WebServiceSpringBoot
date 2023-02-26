package com.ShoppingList.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ShoppingList.demo.dto.UserDTO;
import com.ShoppingList.demo.mapper.UserRowMapper;

@Repository
public class UserRepository implements IUserRepository{


	 @Autowired
	 @Qualifier("jdbcTemplateDB1")
	  private JdbcTemplate jdbcTemplate;
	 @Autowired
	 
	 @Qualifier("jdbcTemplateDB2")
	  private JdbcTemplate jdbcTemplate2;

	  public UserRepository(@Qualifier("jdbcTemplateDB2") JdbcTemplate jdbcTemplateDB2) {
	    this.jdbcTemplate = jdbcTemplateDB2;
	  }


	@Override
	public UserDTO findByUsername(String username) {
		String sql = String.format("SELECT username,  password from users where username = '%s'", username);
		return jdbcTemplate2.queryForObject(sql, new UserRowMapper());
	}
}
