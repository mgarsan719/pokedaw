package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PokeEntity;
import com.daw.persistence.repositories.PokeRepository;
import com.daw.services.exceptions.PokeException;
import com.daw.services.exceptions.PokemonNotFoudException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokeService {

	private final PokeRepository pokeRepository;
	
	public List<PokeEntity> findAll(){
		return this.pokeRepository.findAll();
	}
	
	public PokeEntity findById(long id){
		return this.pokeRepository.findById(id)
				.orElseThrow(() -> new PokemonNotFoudException(String.format("El pokemon con id %d no existe", id)));
	}
}
