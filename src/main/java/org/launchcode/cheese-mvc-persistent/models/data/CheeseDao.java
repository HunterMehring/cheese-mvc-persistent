package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


//this class will be touching the database so we don't have to. It is storing Cheese objects and Integers.
//CrudRepository is a part of the spring framework data package. it is a parameterized interface, so we gave the parameters Cheese and Integer
// We are not able to use primitive types within the context of generics or collections.
// all in all this interface will allow us to interact with the database.
@Repository//tells spring that this is an actual repository and it should manage it for us
@Transactional//specifies that all the methods in this interface should be wrapped by a transactional database.
public interface CheeseDao extends CrudRepository<Cheese, Integer> {

}
