package it.addestramento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.addestramento.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "select * from users where USERNAME = :username", nativeQuery = true)
	public User getUserByUsername(@Param("username") String username);
	
	@Query(value = "select * from users where EMAIL = :email AND STATO = '1'", nativeQuery = true)
	public User getUserByEmail(String email);
	
	@Query(value = "select * from users where STATO = '1' AND CODICE_FISCALE = :codiceFiscale", nativeQuery = true)
	public User getUserByCodiceFiscale(String codiceFiscale);
	
	@Query(value = "select * from users where ID = :id", nativeQuery = true)
	public User getUserById(String id);
	
	@Query(value = "select * from users where acl = :acl", nativeQuery = true)
	public User getUserByAcl(Long acl);
	
	@Query(value = "select * from users ORDER BY DATA_UPDATE DESC", nativeQuery = true)
	public List<User> getUserAll();
	
	@Query(value = "SELECT us.* FROM users us, ORDINE o WHERE us.ID = o.ID_USER AND o.id_user = us.id AND o.certificato = '1'", nativeQuery = true)
	public List<User> elencoUtentiCertificati();
	
	@Query(value = "SELECT us.* FROM users us, ORDINE o WHERE us.ID = o.ID_USER AND o.STATO=1 AND ACL IN(4) ORDER BY DATA_UPDATE DESC", nativeQuery = true)
	public List<User> elencoCorsisti();
	
	@Query(value = "SELECT * FROM users WHERE ACL = '2'", nativeQuery = true)
	public List<User> elencoOperatori();
	
	@Query(value = "select * from users where STATO = '0'", nativeQuery = true)
	public List<User> getusersRegistrazioneNonCompleta();
	
	@Query(value = "select * from users where CODICE_INVITO = :codiceInvito", nativeQuery = true)
	public User getUserByCodiceInvito(String codiceInvito);
	
	 Optional<User> findByEmail(String email);
	
}
