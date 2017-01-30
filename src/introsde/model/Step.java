package introsde.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the steps database table.
 * 
 */
@Entity
@Table(name="steps")
@NamedQuery(name="Step.findAll", query="SELECT s FROM Step s")
public class Step implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String date;
	private int number;
	private Person people;

	public Step() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	//bi-directional many-to-one association to People
	/*@ManyToOne
	@JoinColumn(name="personId")
	public Person getPeople() {
		return this.people;
	}

	public void setPeople(Person people) {
		this.people = people;
	}*/

}