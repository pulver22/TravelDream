package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the VoliPers database table.
 * 
 */
@Entity
@Table(name="VoliPers")
@NamedQuery(name="VoliPers.findAll", query="SELECT v FROM VoliPers v")
public class VoliPers implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VoliPersPK id;

	private boolean acquistato;

	private boolean inGiftList;

	private boolean regalato;

	//bi-directional many-to-one association to PacchettoPersonalizzato
	@ManyToOne
	@JoinColumn(name="Pacchetto")
	private PacchettoPersonalizzato pacchettoPersonalizzato;

	//bi-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="IdVolo")
	private Volo volo;

	public VoliPers() {
	}

	public VoliPers(VoloPersDTO vpDTO){
		this.id=new VoliPersPK();
		this.volo=Volo.fromDTO(vpDTO.getVolo());
		this.id.setPacchetto(vpDTO.getIdPacchetto());
		this.id.setIdVolo(vpDTO.getIdVolo());
		this.inGiftList=vpDTO.isInGiftList();
		this.acquistato=vpDTO.isAcquistato();
		this.regalato=vpDTO.isRegalato();
	}
	
	public VoliPers(VoloDTO vDTO, PacchettoPersonalizzato pPersonalizzato, boolean inGiftList, boolean acquistato, boolean regalato){
		this.volo=Volo.fromDTO(vDTO);
		this.id = new VoliPersPK();
		this.id.setIdVolo(vDTO.getIdVolo());
		this.pacchettoPersonalizzato = pPersonalizzato;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	
	/**
	 * Ritorna la conversione in DTO di questa VoloPers, compresa l'istanza di Volo
	 * in essa contenuta, gia' convertita in DTO anch'essa
	 * */
	public VoloPersDTO toDTO(){
		/*
		VoloPersDTO v = new VoloPersDTO(volo.toDTO(), inGiftList, acquistato, regalato);
		v.setIdPacchetto(id.getPacchetto());
		*/
		return new VoloPersDTO(id.getPacchetto(),volo.toDTO(), inGiftList, acquistato, regalato);
	}
	
	public VoliPersPK getId() {
		return this.id;
	}

	public void setId(VoliPersPK id) {
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

	public Volo getVolo() {
		return this.volo;
	}

	public void setVolo(Volo volo) {
		this.volo = volo;
	}

}