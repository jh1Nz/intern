package com.summitthai.cr.apprentice.person.dao;

import java.util.List;

import com.summitthai.cr.apprentice.person.entity.Person;
import com.summitthai.cr.apprentice.person.model.PersonRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface PersonDao extends OrmDao<Person>{
	public List<Person> findByReq(PersonRequest req);
}
