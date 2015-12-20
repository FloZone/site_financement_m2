package managers.interfaces;

import javax.ejb.Stateless;

import beans.Compensation;
import beans.Donation;
import beans.Project;

@Stateless
public interface CompensationManager extends DefaultManager<Compensation> {
	// Méthodes spécifiques à l'entité 'Compensation' à déclarer ici :
	/**
	 * Get all compensations related to the given project id, sorted by amount
	 * @param projectId
	 * @return Returns a list of compensations related to the given project id, sorted by amount
	 */
	Iterable<Compensation> getAllProjectCompensationsOrderByAmount(int projectId);
	
	Compensation getLinkedCompensation(Donation donation, String ... fields);
}
