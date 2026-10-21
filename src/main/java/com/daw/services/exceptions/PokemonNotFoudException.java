package com.daw.services.exceptions;

public class PokemonNotFoudException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6238185977954761337L;
	
	public PokemonNotFoudException(String message) {
		super(message);
	}
}
