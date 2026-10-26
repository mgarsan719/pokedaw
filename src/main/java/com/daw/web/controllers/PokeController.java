package com.daw.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.PokeEntity;
import com.daw.services.PokeService;
import com.daw.services.exceptions.PokeException;
import com.daw.services.exceptions.PokemonNotFoudException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokeController {

	private final PokeService pokeService;

	@GetMapping
	public ResponseEntity<List<PokeEntity>> list() {
		return ResponseEntity.ok(this.pokeService.findAll());
	}

	@GetMapping("/{idPokemon}")
	public ResponseEntity<?> findById(@PathVariable long idPokemon) {
		try {
			return ResponseEntity.ok(this.pokeService.findById(idPokemon));
		}

		catch (PokemonNotFoudException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody PokeEntity pokemon) {
		try {
			PokeEntity nuevoPokemon = this.pokeService.create(pokemon);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPokemon);
		}

		catch (PokeException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PutMapping("/{idPokemon}")
	public ResponseEntity<?> update(@PathVariable long idPokemon, @RequestBody PokeEntity pokemon) {
		try {
			return ResponseEntity.ok(this.pokeService.update(idPokemon, pokemon));
		}

		catch (PokemonNotFoudException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}

		catch (PokeException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@DeleteMapping("/{idPokemon}")
	public ResponseEntity<?> delete(@PathVariable long idPokemon) {
		try {
			this.pokeService.delete(idPokemon);
			return ResponseEntity.ok().build();
		} 
		
		catch (PokemonNotFoudException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@GetMapping("/pokedex")
	public ResponseEntity<?> findByNumeroPokedex(@RequestParam int numero) {
		try {
			return ResponseEntity.ok(this.pokeService.findByNumeroPokedex(numero));
		}

		catch (PokemonNotFoudException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@GetMapping("/fecha")
	public ResponseEntity<List<PokeEntity>> findByFechaRango(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
		return ResponseEntity.ok(this.pokeService.findByFechaCapturaBetween(inicio, fin));
	}

	@GetMapping("/tipo")
	public ResponseEntity<?> findByTipo(@RequestParam String tipo) {
		try {
			return ResponseEntity.ok(this.pokeService.findByTipo(tipo));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo indicado no existe");
		}
	}
}
