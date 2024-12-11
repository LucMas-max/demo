package it.addestramento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "rubrica")
public class Rubrica {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
    
	@Column(name = "nome")
    private String nome;

	@Column(name = "cognome")
    private String cognome;

	@Column(name = "telefono")
    private String telefono;
	
	@Column(name = "email")
    private String email;


}
