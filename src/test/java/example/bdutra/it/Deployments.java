package example.bdutra.it;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

@ArquillianSuiteDeployment
public class Deployments {

	@SuppressWarnings("rawtypes")
	@Deployment
	public static Archive createDeployment() {

		return ShrinkWrap.create(JavaArchive.class)
				.addPackages(true, "example.bdutra")
				.addAsResource("META-INF/beans.xml")
				.addAsResource("META-INF/it-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("project-it.yml", "project-defaults.yml")
				.addAsResource("META-INF/arquillian.xml");
	}

}
