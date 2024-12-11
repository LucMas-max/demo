package it.addestramento.repository;

import org.springframework.stereotype.Repository;

import it.addestramento.model.Rubrica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RubricaRepository extends JpaRepository<Rubrica, Long>{
	@Query(value = "select * from rubrica", nativeQuery = true)
	public List<Rubrica> getAllRubrica();
	

}
