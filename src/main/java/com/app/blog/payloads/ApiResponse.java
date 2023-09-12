package com.app.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//this class is for return a message during runtime Exception
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse { 
	private String message;
	private boolean success;

}
