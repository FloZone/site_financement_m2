package managers.hibernate;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.Collections;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import managers.interfaces.DefaultManager;


@Stateless
public abstract class AbstractEJBManagerHibernate<T> implements DefaultManager<T> {
	private final Class<T> TYPE;

	@PersistenceContext(unitName="monUnite")
	protected EntityManager entityManager;

	public AbstractEJBManagerHibernate(Class<T> type) {
		this.TYPE = type;
	}
	
	@Override 
	public int getRowCount() {
		Query q = entityManager.createQuery ("SELECT count(x) FROM " + TYPE.getName() + " x");
		return ((Number) q.getSingleResult()).intValue();
	}

	@Override
	public Iterable<T> getAll() {
		Query q = entityManager.createQuery("from " + TYPE.getName());
		
		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}
	
	@Override
	public Iterable<T> getAll(String ... fields) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(TYPE);
		
		Root<T> root = cq.from(TYPE);
		
		setFetchCriteria(root, fields);
		
		TypedQuery<T> q = entityManager.createQuery(cq);
		
		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}

	@Override
	public T get(int id) {
		T found = entityManager.find(TYPE, id);
		detach(found);
		return found;
	}
	
	@Override
	public T get(int id, String ... fields) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(TYPE);

		Root<T> root = cq.from(TYPE);

		setFetchCriteria(root, fields);

		cq.where(cb.equal(root.get("id"), id));
		TypedQuery<T> query = entityManager.createQuery(cq);
		
		T found = query.getSingleResult();
		detach(found);
		return found;
	}

	@Override
	public T getReference(int id) {
		return entityManager.getReference(TYPE, id);
	}

	@Override
	public <U> T getSingleBy(String attributeName, U attributeValue) throws NoSuchFieldException, NoResultException {
		TypedQuery<T> q = createQueryBy(attributeName, attributeValue);
		T found = q.getSingleResult();
		detach(found);
		return found;
	}
	
	@Override
	public <U> T getSingleBy(String attributeName, U attributeValue, String ... fields) throws NoSuchFieldException, NoResultException {
		TypedQuery<T> q = createQueryBy(attributeName, attributeValue, fields);
		T found = q.getSingleResult();
		detach(found);
		return found;
	}
	
	@Override
	public <U> Iterable<T> getAllBy(String attributeName, U attributeValue) throws NoSuchFieldException {
		TypedQuery<T> q = createQueryBy(attributeName, attributeValue);
		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}
	
	@Override
	public <U> Iterable<T> getAllBy(String attributeName, U attributeValue, String ... fields) throws NoSuchFieldException {
		TypedQuery<T> q = createQueryBy(attributeName, attributeValue, fields);
		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}
	
	@Override
	public <U> Iterable<T> getSomeBy(String attributeName, U attributeValue, int from, int to) throws NoSuchFieldException {
		TypedQuery<T> q = createQueryBy(attributeName, attributeValue);
		q.setFirstResult(from);
		q.setMaxResults(to);
		
		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}
	
	@Override
	public <U> Iterable<T> getSomeBy(String attributeName, U attributeValue, int from, int to, String ... fields) throws NoSuchFieldException {
		TypedQuery<T> q = createQueryBy(attributeName, attributeValue, fields);
		q.setFirstResult(from);
		q.setMaxResults(to);
		
		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}

	@Override
	public T create(Object ... values) {
		Constructor<?> constructor = getValidConstructor(values);
		if (constructor == null) {
			throw new InvalidParameterException();
		}

		try {
			T created = TYPE.cast(constructor.newInstance(values));
			entityManager.persist(created);
			entityManager.flush();
			return created ;
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean update(T managedObject) {
		try {
			entityManager.merge(managedObject);
		}catch (IllegalArgumentException | TransactionRequiredException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean delete(T managedObject) {
		try {
			entityManager.remove(managedObject);
		} catch (IllegalArgumentException | TransactionRequiredException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public Iterable<T> getSome(int from, int to) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(TYPE);
		
		cq.from(TYPE);		
		
		Query q = entityManager.createQuery(cq);
		q.setFirstResult(from);
		q.setMaxResults(to);

		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}
	
	@Override
	public Iterable<T> getSome(int from, int to, String ... fields) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(TYPE);
		
		Root<T> root = cq.from(TYPE);		
		setFetchCriteria(root, fields);
		
		Query q = entityManager.createQuery(cq);
		q.setFirstResult(from);
		q.setMaxResults(to);

		Iterable<T> founds = asIterable(q.getResultList());
		detach(founds);
		return founds;
	}

	protected Constructor<?> getValidConstructor(Object ... values) {
		Class<?>[] types = new Class<?>[values.length];

		for (int i = 0; i < values.length ; ++i) {
			types[i] = values[i].getClass();
		}
		
		Constructor<?>[] constructors = TYPE.getConstructors();
		
		for (Constructor<?> c : constructors) {
			boolean isCurrentValid = true;
			Class<?>[] parameters = c.getParameterTypes();
			
			if (parameters.length == values.length) {
				for (int i = 0 ; i < parameters.length ; ++i) {
					if (!parameters[i].isAssignableFrom(types[i])) {
						isCurrentValid = false;
						break;
					}
				}
			} else{
				isCurrentValid = false;	
			}	
			if (isCurrentValid) {
				return c;
			}
		}

		return null;
	}
	
	protected <U> CriteriaQuery<T> createCriteriaQueryBy(String attributeName, U attributeValue) throws NoSuchFieldException, SecurityException {
		Field attribute = TYPE.getDeclaredField(attributeName);
		Class<?> requiredType = attribute.getType();
		Class<?> givenType = attributeValue.getClass();

		if (!requiredType.isAssignableFrom(givenType)) {
			throw new IllegalArgumentException(
					"attributeValue doit être un type " +
							requiredType +
							", or, c'est un type " +
							givenType
					);
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(TYPE);

		Root<T> root = cq.from(TYPE);

		cq.where(
				cb.equal(
						root.get(attributeName), 
						attributeValue
						)
				);
		
		return cq;
	}
	
	protected <U> TypedQuery<T> createQueryBy(String attributeName, U attributeValue) throws NoSuchFieldException, SecurityException {
		CriteriaQuery<T> cq = createCriteriaQueryBy(attributeName, attributeValue);
		return entityManager.createQuery(cq);
	}
	
	protected <U> TypedQuery<T> createQueryBy(String attributeName, U attributeValue, String ... fields) throws NoSuchFieldException, SecurityException {
		CriteriaQuery<T> cq = createCriteriaQueryBy(attributeName, attributeValue);
		Root<T> root = (Root<T>) cq.getRoots().iterator().next();
		setFetchCriteria(root, fields);
		return entityManager.createQuery(cq);
	}
	
	protected void setFetchCriteria(Root<T> root, String ... fields) {
		if (fields != null) {
			for (String f : fields) {
				root.fetch(f, JoinType.LEFT);
			}
		}
	}
	
	protected void detach(T entity) {
		if (entity != null) {
			entityManager.detach(entity);
		}
	}
	
	protected void detach(Iterable<T> entities) {
		for (T e : entities) {
			detach(e);
		}
	}
	
	protected Iterable<T> asIterable(Iterable<T> iterable) {
		return iterable == null ? Collections.EMPTY_LIST : iterable;
	}
}
