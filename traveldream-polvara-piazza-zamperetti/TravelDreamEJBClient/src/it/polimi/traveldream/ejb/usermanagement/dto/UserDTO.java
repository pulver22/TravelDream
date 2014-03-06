package it.polimi.traveldream.ejb.usermanagement.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
	
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="invalid email")
	private String email;
	
	@NotEmpty
    private String nome;
	
	@NotEmpty
    private String cognome;
	
	@NotEmpty
    private String password;
     
	private String numCartaCredito;
	
	private String indirizzoFatturazione;
	
	private String gruppo;
	
    public String getNome() {
        return nome;
    }
     
    public void setNome(String nome) {
        this.nome = nome;
    }
  
    public String getCognome() {
        return cognome;
    }
  
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
  
    public String getEmail() {
        return email;
    } 
 
    public void setEmail(String email) {
        this.email = email;
    }
  
    public String getPassword() {
        return password;
    }
     
    public void setPassword(String password) {
        this.password = password;
    }

	public String getNumCartaCredito() {
		return numCartaCredito;
	}

	public void setNumCartaCredito(String numCartaCredito) {
		this.numCartaCredito = numCartaCredito;
	}

	public String getIndirizzoFatturazione() {
		return indirizzoFatturazione;
	}

	public void setIndirizzoFatturazione(String indirizzoFatturazione) {
		this.indirizzoFatturazione = indirizzoFatturazione;
	}

	public String getGruppo() {
		return gruppo;
	}

	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}

}
