package example.bdutra.it.cdi;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.bdutra.cdi.Service;

@RunWith(Arquillian.class)
public class ServiceIT {

	@Inject
	private Service servico;

	@Test
	public void testCDI() {
		assertNotNull(servico);
	}

}
