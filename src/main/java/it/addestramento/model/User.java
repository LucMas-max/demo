package it.addestramento.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    
    @Column(name = "interesse")
    private String interesse;
    
    @Column(name = "certificato")
    private String certificato;
    
    @Column(name = "codice_fiscale")
    private String codiceFiscale;
    @Column(name = "email")
    private String email;
    @Column(name = "valid_email", columnDefinition="VARCHAR(11) NOT NULL COMMENT '0 NON VALIDATO, 1 VALIDATO'")
    private String validEmail;
    @Column(name = "password")
    private String password;
    @Column(name = "cellulare")
    private String cellulare;
    @Column(name = "indirizzo")
    private String indirizzo;
    @Column(name = "cap")
    private String cap;
    @Column(name = "citta")
    private String citta;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "paese")
    private String paese;
    @Column(name = "stato", columnDefinition="VARCHAR(11) NOT NULL COMMENT '0 CREATO, 1 ABILITATO, 2 DISABILITATO'")
    private String stato;
    @Column(name = "data_nascita")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;
    @Column(name = "citta_nascita")
    private String cittaNascita;
    
    @Column(name = "data_create")
    private LocalDate createUser;
    @Column(name = "data_update")
    private LocalDate updateUser;
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "user_agent_device_type")
    private String userAgentDeviceType;
    @Column(name = "codice_invito")
    private String codiceInvito;
    
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
       name="user_role",
       joinColumns={@JoinColumn(name="id_user", referencedColumnName="id")},
       inverseJoinColumns={@JoinColumn(name="id_role", referencedColumnName="id")})
    @JsonIgnore
    private Set<Role> roles;

}