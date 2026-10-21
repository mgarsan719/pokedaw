package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.PokeEntity;

public interface PokeRepository extends JpaRepository<PokeEntity, Long>{

}
