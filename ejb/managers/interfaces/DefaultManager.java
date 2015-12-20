package managers.interfaces;

import javax.ejb.Stateless;

@Stateless
public interface DefaultManager<T> {
	
	/**
	 * Get all objects of the corresponding table in the database with all fields
	 * @return Returns a list of objects
	 */
	Iterable<T> getAll();
	
	/**
	 * Get all objects of the corresponding table in the database with specific fields
	 * @param fields Fields to get
	 * @return Returns a list of objects of the corresponding table in the database with specific fields
	 */
	Iterable<T> getAll(String ... fields);
	
	/**
	 * Get row count
	 * @return Returns the number of found rows
	 */
	int getRowCount();

	/**
	 * Get an object in the database by its id with all fields
	 * @param id Id of the object
	 * @return Returns the object corresponding to the given id
	 */
	T get(int id);
	
	/**
	 * Get an object in the database by its id with specific fields
	 * @param id Id of the object
	 * @param fields Fields to get
	 * @return Returns the object corresponding to the given id with specific fields
	 */
	T get(int id, String ... fields);

	/**
	 * 
	 * @param id
	 * @return
	 */
	T getReference(int id);

	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 * @throws NoSuchFieldException
	 */
	<U> T getSingleBy(String attributeName, U attributeValue) throws NoSuchFieldException;
	
	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @param fields
	 * @return
	 * @throws NoSuchFieldException
	 */
	<U> T getSingleBy(String attributeName, U attributeValue, String ... fields) throws NoSuchFieldException;

	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 * @throws NoSuchFieldException
	 */
	<U> Iterable<T> getAllBy(String attributeName, U attributeValue) throws NoSuchFieldException;
	
	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @param fields
	 * @return
	 * @throws NoSuchFieldException
	 */
	<U> Iterable<T> getAllBy(String attributeName, U attributeValue, String ... fields) throws NoSuchFieldException;

	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @param from
	 * @param to
	 * @return
	 * @throws NoSuchFieldException
	 */
	<U> Iterable<T> getSomeBy(String attributeName, U attributeValue, int from, int to) throws NoSuchFieldException;
	
	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @param from
	 * @param to
	 * @param fields
	 * @return
	 * @throws NoSuchFieldException
	 */
	<U> Iterable<T> getSomeBy(String attributeName, U attributeValue, int from, int to, String ... fields) throws NoSuchFieldException;

	/**
	 * 
	 * @param values
	 * @return
	 */
	T create(Object ... values);

	/**
	 * 
	 * @param managedObject
	 * @return
	 */
	boolean update(T managedObject);

	/**
	 * 
	 * @param managedObject
	 * @return
	 */
	boolean delete(T managedObject);

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	Iterable<T> getSome(int from, int to);
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param fields
	 * @return
	 */
	Iterable<T> getSome(int from, int to, String ... fields);
}
