package it.addestramento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.addestramento.model.Role;
import it.addestramento.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;
	
	public Role getRoleByNome(String nome) {
		return roleRepo.getRoleByNome(nome);
	}
	
}
