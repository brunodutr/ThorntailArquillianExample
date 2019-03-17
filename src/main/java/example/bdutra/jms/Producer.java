package example.bdutra.jms;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@TransactionAttribute(REQUIRES_NEW)
public class Producer {

	public static final String QUEUE = "java:/jms/queue/arquillianQueue";

	@Resource
	private ConnectionFactory connectionFactory;

	@Resource(lookup = QUEUE)
	private Queue queue;

	public void send(Serializable object) {
		try (JMSContext context = connectionFactory.createContext()) {
			context.createProducer().send(queue, object);
		}
	}
}
