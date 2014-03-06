package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the EscursioniPers database table.
 * 
 */
@Entity
@Table(name="EscursioniPers")
@NamedQuery(name="EscursioniPers.findAll", query="SELECT e FROM EscursioniPers e")
public class EscursioniPers implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EscursioniPersPK id;

	private boolean acquistato;

	private boolean inGiftList;

	private boolean regalato;

	//bi-directional many-to-one association to PacchettoPersonalizzato
	@ManyToOne
	@JoinColumn(name="Pacchetto")
	private PacchettoPersonalizzato pacchettoPersonalizzato;

	//bi-directional many-to-one association to Escursione
	@ManyToOne
	@JoinColumn(name="IdEscursione")
	private Escursione escursione;

	public EscursioniPers() {
	}

	public EscursioniPers(EscursionePersDTO epDTO){
		this.id=new EscursioniPersPK();
		this.escursione=Escursione.fromDTO(epDTO.getEscursione());
		this.id.setPacchetto(epDTO.getIdPacchetto());
		this.id.setIdEscursione(epDTO.getIdEscursione());
		this.inGiftList=epDTO.isInGiftList();
		this.acquistato=epDTO.isAcquistato();
		this.regalato=epDTO.isRegalato();
	}
	
	public EscursioniPers(EscursioneDTO eDTO, PacchettoPersonalizzato pPersonalizzato, boolean inGiftList, boolean acquistato, boolean regalato){
		this.escursione=Escursione.fromDTO(eDTO);
		this.id = new EscursioniPersPK();
		this.id.setIdEscursione(eDTO.getIdEscursione());
		this.pacchettoPersonalizzato = pPersonalizzato;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	
	/**
	 * Ritorna la conversione in DTO di questa EscursionePers, compresa l'istanza di Escursione
	 * in essa contenuta, gia' convertita in DTO anch'essa
	 * */
	public EscursionePersDTO toDTO(){
		/*
		EscursionePersDTO e = new EscursionePersDTO(escursione.toDTO(), inGiftList, acquistato, regalato)
		e.setIdPacchetto(id.getPacchetto());
		
		*/
		return new EscursionePersDTO(id.getPacchetto(),escursione.toDTO(), inGiftList, acquistato, regalato);
	}
	
	
	public EscursioniPersPK getId() {
		return this.id;
	}

	public void setId(EscursioniPersPK id) {
		this.id = id;
	}

	public boolean getAcquistato() {
		return this.acquistato;
	}

	public void setAcquistato(boolean acquistato) {
		this.acquistato = acquistato;
	}

	public boolean getInGiftList() {
		return this.inGiftList;
	}

	public void setInGiftList(boolean inGiftList) {
		this.inGiftList = inGiftList;
	}

	public boolean getRegalato() {
		return this.regalato;
	}

	public void setRegalato(boolean regalato) {
		this.regalato = regalato;
	}

	public PacchettoPersonalizzato getPacchettoPersonalizzato() {
		return this.pacchettoPersonalizzato;
	}

	public void setPacchettoPersonalizzato(PacchettoPersonalizzato pacchettoPersonalizzato) {
		this.pacchettoPersonalizzato = pacchettoPersonalizzato;
	}

	public Escursione getEscursione() {
		return this.escursione;
	}

	public void setEscursione(Escursione escursione) {
		this.escursione = escursione;
	}

}