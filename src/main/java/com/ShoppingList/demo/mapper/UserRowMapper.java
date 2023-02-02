package com.ShoppingList.demo.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ShoppingList.demo.dto.UserDTO;

@Component
public class UserRowMapper  implements RowMapper<UserDTO>{
	
	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserDTO usuario = new UserDTO();
		usuario.setUserName(rs.getString(1));
		usuario.setPassword(rs.getString(2));
		
		return usuario;
	}
}
