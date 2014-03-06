package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Entity implementation class for Entity: UserEntity
 *
 */
@Entity
@Table(name="Utente")
@NamedQueries({
	@NamedQuery(name=Cliente.FIND_ALL,
			query="SELECT c FROM Cliente c")
})
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "User.findAll";

	@Id
	private String mail;

	private String nome;

	private String cognome;

	private String password; //sha-256 + hex

	private String numCartaCredito;

	private String indirizzoFatturazione;

	//bi-directional many-to-one association to PacchettoPersonalizzato
	@OneToMany(mappedBy="utente")
	private List<PacchettoPersonalizzato> pacchettiPersonalizzati;
	
	@ElementCollection(targetClass = Gruppo.class)
	@CollectionTable(name = "GruppoUtente",
	joinColumns = @JoinColumn(name = "Mail"))
	@Enumerated(EnumType.STRING)
	@Column(name="Gruppo")
	private List<Gruppo> gruppi;

	public Cliente() {
		super();
	}

	public Cliente(UserDTO user){
		this.mail        = user.getEmail();
		this.nome    = user.getNome();
		this.cognome     = user.getCognome();        
		this.password     = DigestUtils.sha256Hex(user.getPassword());
		this.numCartaCredito  = user.getNumCartaCredito();
		this.indirizzoFatturazione = user.getIndirizzoFatturazione();
	}

	public static Cliente fromDTO(UserDTO userDTO){
		Cliente cliente = new Cliente();
		
		cliente.mail = userDTO.getEmail();
		cliente.nome = userDTO.getNome();
		cliente.cognome = userDTO.getCognome();        
		cliente.password = userDTO.getPassword();
		cliente.numCartaCredito = userDTO.getNumCartaCredito();
		cliente.indirizzoFatturazione = userDTO.getIndirizzoFatturazione();
		
		return cliente;
	}
	
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
		return mail;
	}

	public void setEmail(String email) {
		this.mail = email;
	}


	public String getNumCartaCredito() {
		return numCartaCredito;
	}

	public void setNumCartaCredito(String numCartaCredito) {
		this.numCartaCredito = numCartaCredito;
	}

	public String getIndirizzofatturazione() {
		return indirizzoFatturazione;
	}

	public void setIndirizzofatturazione(String indirizzofatturazione) {
		this.indirizzoFatturazione = indirizzofatturazione;
	}

	/**
	 * @return the password in SHA256 HEX representation
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Gruppo> getGruppi() {
		return gruppi;
	}

	public void setGruppi(List<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}

	@Override
	public String toString() {
		return "User [email=" + mail + ", firstName=" + nome
				+ ", lastName=" + cognome + ", password=" + password
				+ ", groups=" + gruppi + "]";
	}
}
