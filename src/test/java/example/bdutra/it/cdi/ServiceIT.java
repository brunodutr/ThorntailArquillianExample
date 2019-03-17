package example.bdutra.it.cdi;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.bdutra.cdi.Service;

@RunWith(Arquillian.class)
@Ignore
public class ServiceIT {

	@SuppressWarnings("rawtypes")
	@Deployment
	public static Archive createDeployment() {

		return ShrinkWrap.create(JavaArchive.class)
						 .addPackages(true, "example.bdutra")
						 .addAsResource("META-INF/beans.xml");
	}

	@Inject
	private Service servico;

	@Test
	public void testCDI() {
		assertNotNull(servico);
	}

}
