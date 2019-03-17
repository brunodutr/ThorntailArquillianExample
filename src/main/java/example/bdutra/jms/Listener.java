package example.bdutra.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import example.bdutra.jpa.person.PersonEntity;
import example.bdutra.jpa.person.PersonRepository;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/arquillianQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1") })
public class Listener implements MessageListener {

	@Inject
	private PersonRepository personRepository;

	@Override
	public void onMessage(Message message) {

		try {
	
			ObjectMessage objeto = (ObjectMessage) message;

			PersonEntity person = (PersonEntity) objeto.getObject();
			
			personRepository.create(person);

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
