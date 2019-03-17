package example.bdutra.it.jpa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.bdutra.jpa.person.PersonEntity;
import example.bdutra.jpa.person.PersonRepository;

@RunWith(Arquillian.class)
public class PersonRepositoryIT {

	@SuppressWarnings("rawtypes")
	@Deployment
	public static Archive createDeployment() {

		return ShrinkWrap.create(JavaArchive.class)
						 .addPackages(true, "example.bdutra")
						 .addAsResource("META-INF/beans.xml")
						 .addAsResource("META-INF/it-persistence.xml", "META-INF/persistence.xml")
						 .addAsResource("project-it.yml", "project-defaults.yml");
	}
	
	@Inject
	private PersonRepository personRepository;

	@Test
	@InSequence(1)
	public void testSavePerson() {
		
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
