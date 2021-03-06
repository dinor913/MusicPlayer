package adrian.example.musicplayer.service.AdministrationAccount;

import org.hibernate.ObjectNotFoundException;

import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.model.User.UserInformation;

public interface UserAdministrationService {
	
	/**
	 * get user_id using login
	 * 
	 * @param login the login to search
	 * @return user_id;
	 */
	int getUser_id(String login);
	
	/**
	 * Return UserInformation from database
	 * @param user_id the user_id for search UserInformation
	 * @return UserInformation if found or null if UserInformation
	 * dont exist in database
	 */
	  UserInformation getUserInformationById(int user_id);
	
	/**
	 * Retrive a <code>User</code> from data store by login.
	 * 
	 * @param login the login to search for User
	 * @return the <code>User</user> if found 
	 * null if not found
	 * 
	 */
	User findLogin(String login);
	
	/**
	 * Update <code>User</code> from Administration User Profile
	 * using Hibernate load
	 * 
	 * @param user_id the user_id to get User
	 */
	void updateUserInformation(int user_id, String address, String firstName);
	
    /**
	 * Update <code>User</code> Password 
	 * using Hibernate load
	 * 
	 * @param user_id the user_id for search User
	 * @param password the password to update
	 * @throws ObjectNotFoundException if user not found
	 */
	 void updatePassword(int user_id, String password);
	 
     /**
	  * Update <code>User</code>Email
	  * using Hibernate load
	  *  
	  * @param user_id the user_id for search User
	  * @param email the email to update
	  */
	 void updateEmail(int user_id, String email);
	 
	 /**
	  * Update or Save UserInformation using Hibernate load
	  * 
	  * @param user_id the user_id to search User
	  * @param userInformation the userInformation to save or update
	  */
	 void updateOrSaveUserInformation(int user_id, UserInformation userInformation);
	 
	 /**
	 * Check Verify User by user_id and active cod
     * using Hibernate Session load().
	 * 
	 * @param user_id the user_id to search for User
	 * @param active_code the active_code to check its match 
	 * with User
	 * 
	 * @return true if User match with @param
	 * return false if not match
	 * 
	 */
	 boolean checkUserVerify(int user_id, int active_code);
	 
	 /**
	  * Check if the User pass correctPassword
	  * 
	  * @param password the password to check
	  * @return true if user give the correct password
	  * false word not match with password in database
	  */
	 boolean checkPassword(int user_id, String passwordToMatch);
		
	 /**
	  * Update enabled account to true
	  * 
	  * @param user_id the user_id for update Access User
	  */
	  void setEnabledTrue(int user_id);
}
