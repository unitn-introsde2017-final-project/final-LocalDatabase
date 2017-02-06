package introsde.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import introsde.dao.LifeCoachDao;


/**
 * The persistent class for the steps database table.
 * 
 */
@Entity
@Table(name="steps")
@NamedQueries({
    @NamedQuery(name="Step.findAll",
                query="SELECT s FROM Step s"),
//    @NamedQuery(name="Step.findForPerson",
//                query="SELECT s FROM Step s WHERE s.personId = ?1"),
})
/*@NamedQuery(name="Step.findAll", query="SELECT s FROM Step s")
@NamedQuery(name="Step.findForPerson", query="SELECT s FROM Step s WHERE s.personId = ?1")*/
public class Step implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String date;
	private int number;
	//private int personId;
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
	
//	public int getPersonId() {
//		return this.personId;
//	}
//
//	public void setPersonId(int id) {
//		this.personId = id;
//	}


	//bi-directional many-to-one association to People
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="personId")
//	//@Transient
//	public Person getPeople() {
//		return this.people;
//	}
//
//	
//	public void setPeople(Person people) {
//		this.people = people;
//	}
	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Step getStepById(int stepId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Step step = em.find(Step.class, stepId);
		LifeCoachDao.instance.closeConnections(em);
		return step;
	}
	
	public static List<Step> getStepsForPerson(int personId) {
		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the steps for person with id = "+personId+"...");
	    List<Step> list = em.createNamedQuery("Steps.findForPerson", Step.class).setParameter(1, personId).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	/*public static List<Person> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Person> list = em.createNamedQuery("People.findAll", Person.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}*/
	
	public static Step saveStep(Step step) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(step);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return step;
	}
	
	public static Step updateStep(Step step) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		step=em.merge(step);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return step;
	}
	
	/*public static void removePerson(Person p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}*/

}