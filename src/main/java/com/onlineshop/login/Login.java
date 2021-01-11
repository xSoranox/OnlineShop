package com.onlineshop.login;

import java.io.Serializable;

import lombok.Data;

@Data
public class Login implements Serializable {

	private static final long serialVersionUID = 1;
	private String username;
	private String password;
}
