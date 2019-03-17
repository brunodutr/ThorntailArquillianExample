package example.bdutra.it.jpa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.bdutra.it.BaseIT;
import example.bdutra.jpa.person.PersonEntity;
import example.bdutra.jpa.person.PersonRepository;

@RunWith(Arquillian.class)
public class PersonRepositoryIT extends BaseIT {
	
	@Inject
	private PersonRepository personRepository;

	@Test
	@InSequence(1)
	public void testSavePerson() {
		
		super.clearDB();
		
		PersonEntity person = new PersonEntity();
		
		person.setName("Bruno");
		person.setSurname("Dutra");
		
		personRepository.create(person);
	}
	
	@Test
	@InSequence(2)
	public void testListPersons() {
		
		List<PersonEntity> persons = personRepository.findAll();
		
		assertEquals(persons.size(), 1);
		
	}
	
}
