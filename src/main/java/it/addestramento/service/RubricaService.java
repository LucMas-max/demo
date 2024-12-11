package it.addestramento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.addestramento.model.Rubrica;
import it.addestramento.repository.RubricaRepository;

@Service
public class RubricaService {

	@Autowired
	public RubricaRepository rubricaRepo;

	public Rubrica saveOrUpdate(Rubrica rubrica) {
		return rubricaRepo.save(rubrica);
	
	}
	public List<Rubrica> getAllRubrica(){
		return rubricaRepo.getAllRubrica();
	}
	public void delete(Long id) {
		rubricaRepo.deleteById(id);
	}
	
}
