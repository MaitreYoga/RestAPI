package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inscription database table.
 * 
 */
@Entity
@NamedQuery(name="Inscription.findAll", query="SELECT i FROM Inscription i")
public class Inscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="idevent")
	private Event event;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="idmember")
	private Member member;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="idstate")
	private State state;

	public Inscription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}