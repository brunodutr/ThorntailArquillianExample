package example.bdutra.it.jms;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.bdutra.it.jmx.ServiceJMX;
import example.bdutra.jms.Producer;
import example.bdutra.jpa.person.PersonEntity;
import example.bdutra.jpa.person.PersonRepository;

@RunWith(Arquillian.class)
public class MessageIT {

	@SuppressWarnings("rawtypes")
	@Deployment
	public static Archive createDeployment() {

		return ShrinkWrap.create(JavaArchive.class).addPackages(true, "example.bdutra")
				.addAsResource("META-INF/beans.xml")
				.addAsResource("META-INF/it-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("project-it.yml", "project-defaults.yml");
	}

	@Inject
	private Producer producer;

	@Inject
	private PersonRepository repository;

	@Test
	@InSequence(1)
	public void sendMessages() throws Exception {

		PersonEntity person1 = new PersonEntity();
		PersonEntity person2 = new PersonEntity();
		PersonEntity person3 = new PersonEntity();

		person1.setName("Name 1");
		person2.setName("Name 2");
		person3.setName("Name 3");

		person1.setSurname("Surname 1");
		person2.setSurname("Surname 2");
		person3.setSurname("Surname 3");

		List<PersonEntity> persons = Arrays.asList(person1, person2, person3);

		for (PersonEntity p : persons) {
			producer.send(p);
		}

	}

	@Test
	@RunAsClient
	@InSequence(2)
	public void waitQueue() throws Exception {

		ServiceJMX.waitQueue("arquillianQueue");

	}

	@Test
	@InSequence(3)
	public void verify() {

		List<PersonEntity> persons = repository.findAll();

		assertEquals(persons.size(), 3);
	}

}
