package com.daw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.PokeEntity;
import com.daw.persistence.entities.enums.Tipo;

public interface PokeRepository extends JpaRepository<PokeEntity, Long> {

	Optional<PokeEntity> findByNumeroPokedex(int numeroPokedex);

	List<PokeEntity> findByFechaCapturaBetween(LocalDate inicio, LocalDate fin);

	List<PokeEntity> findByTipo1OrTipo2(Tipo tipo1, Tipo tipo2);

}
