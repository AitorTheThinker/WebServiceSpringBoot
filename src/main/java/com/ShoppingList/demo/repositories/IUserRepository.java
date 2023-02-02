package com.ShoppingList.demo.repositories;

import com.ShoppingList.demo.dto.UserDTO;

public interface IUserRepository {

	public UserDTO findByUsername(String username );

}
