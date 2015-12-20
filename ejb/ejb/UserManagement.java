package ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import managers.interfaces.UserManager;
import security.MD5PasswordPolicy;
import security.PasswordPolicy;
import beans.User;

/**
 * Session Bean implementation class UserManagement
 */
@Stateless
@LocalBean
public class UserManagement {
	
	@EJB
	UserManager userManager;
	
	PasswordPolicy passwordPolicy;

	/**
     * Default constructor
     */
    public UserManagement() {
        this.passwordPolicy = new MD5PasswordPolicy();
    }

    
    public PasswordPolicy getPasswordPolicy() {
		return passwordPolicy;
	}


	public void setPasswordPolicy(PasswordPolicy passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}


	/**
     * Sign up an user without administrator's rights
     * @return The created user
     */
    public User signUp(String login, String password, String firstName, String lastName, String email) {
    	String digest = passwordPolicy.hash(password);
    	return userManager.create(login, digest, firstName, lastName, email, false);
    }
    
    public User get(int id, String ... fields) {
    	return userManager.get(id, fields);
	}
    
    public User getByLogin(String login, String ... fields) {
    	User user = null;
		try {
			user = userManager.getSingleBy("login", login, fields);
		} catch (Exception e) {
			if (!e.getCause().getClass().equals(NoResultException.class)) {
				e.printStackTrace();
			}
		}
		
		return user;
    }
    
    public User login(String login, String password) {
    	User user = getByLogin(login);
    	
    	if (user != null && passwordPolicy.match(password, user.getPassword())) {
    		return user;
    	}
    	else {
    		return null;
    	}
    }
    
    /**
     * Update an user information
     * @return true if update is successful, false otherwise
     */
    public boolean update(User userToUpdate, String pwd) {
    	if (!pwd.equals("")){
        	String digest = passwordPolicy.hash(pwd);
        	userToUpdate.setPassword(digest);
    	}
    	return userManager.update(userToUpdate);
    }

    public boolean deleteUser(User userToDelete) {
    	return userManager.delete(userToDelete);
    }
    
    
	public User createUser(String login, String password, String firstName,
			String lastName, String email, Boolean administrator) {
		return userManager.create(login, password, firstName, lastName, email, administrator);
	}
	
	public Iterable<User> getSome(int from, int to, String ... fields) {
		return userManager.getSome(from, to, fields);
	}


	public int getUserCount() {
		return (int) userManager.getRowCount();
	}


	public Iterable<User> getAll() {
		// TODO Auto-generated method stub
		return userManager.getAll();
	}
}
