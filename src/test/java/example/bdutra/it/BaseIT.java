package example.bdutra.it;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import example.bdutra.jpa.person.PersonEntity;

public class BaseIT {

	@PersistenceContext(unitName = "ArquillianPU")
	public EntityManager em;

	@Inject
	private UserTransaction utx;

	public void clearDB() {

		deleteEntity(PersonEntity.class);

	}

	private void deleteEntity(Class<?> entityClass) {

		try {

			utx.begin();
			Query query = em.createQuery(String.format("delete from %s", entityClass.getSimpleName()));
			query.executeUpdate();
			utx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
