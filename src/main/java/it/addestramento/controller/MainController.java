package it.addestramento.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.addestramento.model.Rubrica;
import it.addestramento.service.RubricaService;

@Controller
public class MainController {
	private Logger log = LogManager.getLogger(MainController.class.getName());
    @Autowired
    private RubricaService rubricaService;
	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/")
	public String pagineIniziale() {

		return "index";

	}
	@GetMapping("/cancella")
	public String cancellaContatto(@RequestParam(value = "id", required = false) Long id) {
		rubricaService.delete(id);
		return "redirect:/rubrica";
	}

	@GetMapping("/rubrica")
	public String rubrica(Model model) {
    List<Rubrica> rubrica = rubricaService.getAllRubrica();
    model.addAttribute("listRubrica", rubrica);
		return "rubrica";

	}


	@GetMapping("/registrazione")
	public String registrazione(Model model) {
   
		return "registrazione";

	}
	@PostMapping("/params")
	public String ottieniParametri(Model model, @RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cognome", required = false) String cognome) {
		log.info("parametro1: " + nome);
		log.info("parametro2: " + cognome);
		model.addAttribute("nome", nome);
		model.addAttribute("cognome", cognome);
		return "risultato";
	}

	@PostMapping("/salvaUtente")
	public String salvaUtente(Model model, 
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cognome", required = false) String cognome,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password) {
		log.info("parametro1: " + nome);
		log.info("parametro2: " + cognome);
		model.addAttribute("nome", nome);
		model.addAttribute("cognome", cognome);
		return "risultato";
	}
	@PostMapping("/salva")
	public String salvaRubrica(Model model, @RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cognome", required = false) String cognome,
			@RequestParam(value = "telefono", required = false) String telefono,
			@RequestParam(value = "email", required = false) String email) {
		Rubrica rubrica = new Rubrica();
		rubrica.setNome(nome);
		rubrica.setCognome(cognome);
		rubrica.setTelefono(telefono);
		rubrica.setEmail(email);
		rubricaService.saveOrUpdate(rubrica);
		return "redirect:/rubrica";
	}
}
