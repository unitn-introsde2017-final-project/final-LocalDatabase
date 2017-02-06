package introsde.resources;

import introsde.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonConnected extends Person {
	private static final long serialVersionUID = 1L;
	private List<Step> steps;
	
	public PersonConnected(Person person) {
		this.setId(person.getId());
		this.setBirthdate(person.getBirthdate());
		this.setCity(person.getCity());
		this.setName(person.getName());
		this.setStepGoal(person.getStepGoal());
		this.steps = Step.getStepsForPerson(this.getId());
		System.out.println("###---> PersonConnected runs");
	}
	
	@JsonProperty("steps")
	public List<Step> getSteps() {
		return this.steps;
	}
	
	public void setSteps() {
		this.steps = new ArrayList<Step>();
	}

}
