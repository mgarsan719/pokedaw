package com.daw.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.PokeEntity;
import com.daw.services.PokeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokeController {
	
	private final PokeService pokeService;
	
	@GetMapping
	public ResponseEntity<List<PokeEntity>> list(){
		return ResponseEntity.ok(this.pokeService.findAll());
	}

}
