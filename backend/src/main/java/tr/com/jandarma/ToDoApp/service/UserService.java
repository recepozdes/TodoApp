package tr.com.jandarma.ToDoApp.service;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.jandarma.ToDoApp.entity.User;
import tr.com.jandarma.ToDoApp.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser(User user) {
		System.out.println("Service Get");
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	public boolean getUserByUsername(String username, String password) //veritabanında kullanıcı adı ve parola varmı kontrol ediyoruz
	{
		boolean username_present;
		boolean password_present;
		try
		{
			username_present=userRepository.findTopByUsername(username)!= null ? true:false;
			System.out.println("Username present :"+ username_present);
			password_present=userRepository.findTopByPassword(password)!=null ? true:false;
			System.out.println("Password present :"+password_present);
		}
		catch(NonUniqueResultException nre)
		{
			return true;
		}
		return username_present && password_present;
	}
	
	public boolean findUserByUsername(String username) // username daha önceden alınmışmı onu kontrol ediyoruz
	{
		boolean username_present;
		try
		{
			username_present=userRepository.findTopByUsername(username)!=null?true:false;
			System.out.println("Username present (U) :"+username_present);
		}
		catch (NonUniqueResultException nre)
		{
			return true;
		}
		return username_present;
	}
	
	public User saveUser(User user) //veritabanına kullanıcı ekliyoruz
	{
		user.setRole("user");
		user.setStatus("pending");
		return userRepository.save(user);
	}

}
