package com.app.blog.services;

import java.util.List;

import com.app.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserByID(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}