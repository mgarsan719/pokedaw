package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PokeEntity;
import com.daw.persistence.entities.enums.Pokeball;
import com.daw.persistence.entities.enums.Tipo;
import com.daw.persistence.repositories.PokeRepository;
import com.daw.services.exceptions.PokeException;
import com.daw.services.exceptions.PokemonNotFoudException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokeService {

	private final PokeRepository pokeRepository;

	public List<PokeEntity> findAll() {
		return this.pokeRepository.findAll();
	}

	public PokeEntity findById(long id) {
		return this.pokeRepository.findById(id)
				.orElseThrow(() -> new PokemonNotFoudException(String.format("El pokemon con id %d no existe", id)));
	}

	public PokeEntity create(PokeEntity p) {
		if (p.getCapturado() == null) {
			p.setCapturado(Pokeball.POKEBALL);
		}

		if (p.getTipo2() == null) {
			p.setTipo2(Tipo.NINGUNO);
		}

		if (p.getTipo1() != Tipo.NINGUNO && p.getTipo1().equals(p.getTipo2())) {
			throw new PokeException("No se puede tener un pokemon con dos mismos tipos");
		}

		return this.pokeRepository.save(p);
	}

	public PokeEntity update(long id, PokeEntity p) {
		PokeEntity pokemonExistente = this.findById(id);

		if (p.getTipo2() == null) {
			p.setTipo2(Tipo.NINGUNO);
		}

		if (p.getTipo1() != Tipo.NINGUNO && p.getTipo1().equals(p.getTipo2())) {
			throw new PokeException("No se puede tener un pokemon con dos mismos tipos");
		}

		p.setFechaCaptura(pokemonExistente.getFechaCaptura());
		p.setId(pokemonExistente.getId());

		return this.pokeRepository.save(p);
	}

	public void delete(long id) {
		PokeEntity pokemonExistente = this.findById(id);
		this.pokeRepository.delete(pokemonExistente);
	}

	public PokeEntity findByNumeroPokedex(int numeroPokedex) {
		return this.pokeRepository.findByNumeroPokedex(numeroPokedex).orElseThrow(() -> new PokemonNotFoudException(
				String.format("El pokemon con número de pokedex %d no existe", numeroPokedex)));
	}

	public List<PokeEntity> findByFechaCapturaBetween(LocalDate inicio, LocalDate fin) {
		return this.pokeRepository.findByFechaCapturaBetween(inicio, fin);
	}

	public List<PokeEntity> findByTipo(Tipo tipo) {
		return this.pokeRepository.findByTipo1OrTipo2(tipo, tipo);
	}

}
