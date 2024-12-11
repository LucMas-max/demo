package it.addestramento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.addestramento.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query(value = "select * from roles where NOME = :nome", nativeQuery = true)
	public Role getRoleByNome(String nome);
	
}
