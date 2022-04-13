package tr.com.jandarma.ToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.jandarma.ToDoApp.Dao.LoginDao;
import tr.com.jandarma.ToDoApp.entity.User;
import tr.com.jandarma.ToDoApp.service.UserService;

@RestController
@CrossOrigin(origins= {"*"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	private ResponseEntity<User> getCurrentUser(@RequestBody User user)
	{
		System.out.println("get user by username and password");
		User user1=userService.getUser(user);
		if(user1 != null) {
			return ResponseEntity.ok(user1);
		}
		return ResponseEntity.badRequest().build();
	}
	
//	@PostMapping("/login")
//	private boolean findUserByUsername(@RequestBody LoginDao loginDao)
//	{
//		System.out.println("get user by username and password");
//		return userService.getUserByUsername(loginDao.getUsername(),loginDao.getPassword());
//	}
	
	@PostMapping("/createUser")
	private boolean addUser(@RequestBody User user)
	{
		boolean user_exist=userService.findUserByUsername(user.getUsername());
		if(user_exist)
		{
			System.out.println("Can't Create User");
			return false;
		}
		userService.saveUser(user);
		return true;
	}

}
