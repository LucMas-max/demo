package it.addestramento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.addestramento.model.User;
import it.addestramento.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User getUserByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}
	
	public User getUserById(String id) {
		return userRepo.getUserById(id);
	}
	
	public User saveOrUpdate(User user) {
		return userRepo.save(user);
	}
	
	public User getUserByAcl(Long acl) {
		return userRepo.getUserByAcl(acl);
	}
	
	public List<User> getUserAll(){
		return userRepo.getUserAll();
	}
	
}
