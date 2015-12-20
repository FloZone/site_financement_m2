package managers.interfaces;

import javax.ejb.Stateless;

import beans.Compensation;
import beans.Donation;

@Stateless
public interface DonationManager extends DefaultManager<Donation> {

}
