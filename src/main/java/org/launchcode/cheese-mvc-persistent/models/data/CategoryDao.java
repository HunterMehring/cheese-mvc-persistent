package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


//this object will be the mechanism for which we interat with the objects that we store in the data
@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
