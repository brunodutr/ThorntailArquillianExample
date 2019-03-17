package example.bdutra.jpa.person;

import example.bdutra.jpa.Repository;

public class PersonRepository extends Repository<PersonEntity> {

	public PersonRepository() {
		super(PersonEntity.class);
	}
}
