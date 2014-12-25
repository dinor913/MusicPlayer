package adrian.example.musicplayer.dao.user;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.model.User.UserInformation;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TransactionConfiguration
@Transactional
public class UserDaoImplTest {

	@Autowired
	UserDao userDaoImpl;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void saveUser() throws Exception {
		User userToSave = new User();
		userToSave.setLogin("User2");
		userToSave.setActive_cod(54321);
		userToSave.setAddress("AddressSave");
		userToSave.setEmail("SavedEmail@Test.com");
		userToSave.setEnabled(false);
		userToSave.setFirstName("SavedName");
		userToSave.setPassword("password12345@");
		
		this.userDaoImpl.saveUser(userToSave);
        
		User loadedUser = (User) this.userDaoImpl.findUserByLogin("User2");
		
		assertEquals("Tested User id", 2, loadedUser.getUser_id());
		assertEquals("Tested User Login", "User2", loadedUser.getLogin());
		assertEquals("Tested User Password", 
				"password12345@",
				loadedUser.getPassword());
		assertEquals("Tested User FirstName", "SavedName", loadedUser.getFirstName());
		assertEquals("Tested User Address", "AddressSave", loadedUser.getAddress());
		assertEquals("Tested User ActiveCode", 54321, loadedUser.getActive_cod());
	} 
	
	@Test
	public void getUser_id() throws Exception {
		int user_id = userDaoImpl.getUser_id("TestLogin");
		
		assertEquals("Tested User id" ,1 , user_id);
	} 
	
	@Test
	public void findLogin() throws Exception {
		User user = userDaoImpl.findUserByLogin("TestLogin");
		
		assertEquals("Tested User id", 1, user.getUser_id());
		assertEquals("Tested User Login", "TestLogin", user.getLogin());
		assertEquals("Tested User Password", 
				"$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK",
				user.getPassword());
		assertEquals("Tested User FirstName", "TestFirstName", user.getFirstName());
		assertEquals("Tested User Address", "TestAddress", user.getAddress());
		assertEquals("Tested User ActiveCode", 12345, user.getActive_cod());
	} 
	
	
	@Test
	public void findUserById() throws Exception {
		User user = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("Tested User id", 1, user.getUser_id());
		assertEquals("Tested User Login", "TestLogin", user.getLogin());
		assertEquals("Tested User Password", 
				"$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK",
				user.getPassword());
		assertEquals("Tested User FirstName", "TestFirstName", user.getFirstName());
		assertEquals("Tested User Address", "TestAddress", user.getAddress());
		assertEquals("Tested User ActiveCode", 12345, user.getActive_cod());
		
	} 
	
	@Test
	public void getUserInformationById() throws Exception {
		UserInformation userInformation = userDaoImpl.getUserInformationById(1);
		
		assertEquals("Tested UserInformation id", 1, userInformation.getUser_information_id());
		assertEquals("Tested UserInformation Age", 23, userInformation.getAge());
		assertEquals("Tested UserInformation Interest", "Football, Video Game, Travel", 
				userInformation.getInterest());
		assertEquals("Tested UserInformation Sex" ,"yes", userInformation.getSex());
		assertEquals("Tested UserInformation Programming Skill", 
				 "JAVA, JAVAEE", userInformation.getProgrammingSkill());
	} 
	
	@Test
	public void updateUserInformation() throws Exception {
		
		User userBeforeUpdate = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("Test user Address Before Update", "TestAddress",
				userBeforeUpdate.getAddress());
		assertEquals("Test FirstName Before Update", "TestFirstName", 
				userBeforeUpdate.getFirstName());
		
		userDaoImpl.updateUserInformation(1, "Wisla", "Aniki");
		
		User userAfterUpdate = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("Tested Address", "Wisla", userAfterUpdate.getAddress());
		assertEquals("Tested FirstName", "Aniki", userAfterUpdate.getFirstName());
	} 
	
	@Test
	public void updatePassword() throws Exception {
		User userBeforeUpdate = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("Tested Password Before Update", 
				"$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK",
				userBeforeUpdate.getPassword());
		
		userDaoImpl.updatePassword(userBeforeUpdate.getUser_id(), "qwertY1@");
		
		User userAfterUpdate = (User) this.userDaoImpl.findUserById(1);
		
		assertTrue("Tested Password After Update", bcryptEncoder.matches("qwertY1@",
				userAfterUpdate.getPassword()));
	} 
	
	@Test
	public void updateEmail() throws Exception {
		User userBeforeUpdate = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("Tested user email before update", "TestEmail@Test.com",
				userBeforeUpdate.getEmail());
		
		userDaoImpl.updateEmail(userBeforeUpdate.getUser_id(), "NowyMail@gmail.com");
		
		User userAfterUpdate = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("tested user email after update", "NowyMail@gmail.com", 
				userAfterUpdate.getEmail());
	} 
	
	@Test
	public void updateOrSaveInformation() throws Exception {
        
		User user = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("Tested user id", 1, user.getUser_id());
		assertEquals("Tested UserInformation id", 1, 
				user.getUserInformation().getUser_information_id());
		assertEquals("Tested UserInformation age", 23, user.getUserInformation().getAge());
		assertEquals("Tested UserInformation interest", "Football, Video Game, Travel", 
				user.getUserInformation().getInterest());
		assertEquals("Tested UserInformation ProgrammingSKill", "JAVA, JAVAEE", 
				user.getUserInformation().getProgrammingSkill());
		assertEquals("Tested UserInformation sex", "yes", user.getUserInformation().getSex());
		assertEquals("Tested UserInformation Surname", "TestSurname", 
				user.getUserInformation().getSurname());
		
		UserInformation userInformation2 = new UserInformation();
		userInformation2.setAge(44);
		userInformation2.setInterest("Game, Tennis");
		userInformation2.setProgrammingSkill("Hibernate, JPA");
		userInformation2.setSex("no");
		userInformation2.setSurname("Czesław");
		
		
		userDaoImpl.updateOrSaveUserInformation(user.getUser_id(), userInformation2);
		
        User userAfterUpdate = (User) this.userDaoImpl.findUserById(1);
		
		assertEquals("Tested user id", 1, userAfterUpdate.getUser_id());
		assertEquals("Tested UserInformation id", 1, 
				userAfterUpdate.getUserInformation().getUser_information_id());
		assertEquals("Tested UserInformation age", 44, userAfterUpdate.getUserInformation().getAge());
		assertEquals("Tested UserInformation interest", "Game, Tennis", 
				userAfterUpdate.getUserInformation().getInterest());
		assertEquals("Tested UserInformation ProgrammingSKill", "Hibernate, JPA", 
				userAfterUpdate.getUserInformation().getProgrammingSkill());
		assertEquals("Tested UserInformation sex", "no", userAfterUpdate.getUserInformation().getSex());
		assertEquals("Tested UserInformation Surname", "Czesław", 
				userAfterUpdate.getUserInformation().getSurname());  
	} 
  
  @Test
  public void checkPassword() throws Exception {
	  User user = (User) this.userDaoImpl.findUserById(1);
	  assertTrue("Tested Password", userDaoImpl.checkPassword(user.getUser_id(), 
			      "TestPassword12345@"));
  }
  
  @Test
  public void checkUserVerify() throws Exception {
	  User user = (User) this.userDaoImpl.findUserById(1);
	  assertTrue("Test User verify active code",
			  this.userDaoImpl.checkUserVerify(user.getUser_id(), 12345));
  }
  
  @Test
  public void setEnebledTrue() throws Exception {
	  User user = (User) this.userDaoImpl.findUserById(1);
	  assertFalse("Tested Enabled before update", user.isEnabled());
	  
	  userDaoImpl.setEnabledTrue(user.getUser_id());
	  assertTrue("Tested Enabled After Update ", user.isEnabled());
  }
}
