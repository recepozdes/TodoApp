package tr.com.jandarma.ToDoApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.jandarma.ToDoApp.entity.Task;
import tr.com.jandarma.ToDoApp.service.TaskService;

@CrossOrigin(origins= {"*"}) //bilgisayarımıza bağlantı noktası üzerinde çalışan ön uç kısımlardan istek alacağımızı söylüyoruz
@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	private final Logger logger=LoggerFactory.getLogger(TaskController.class);
	
	
	@PostMapping("/addTask")
	public Task addTask(@RequestBody Task task)
	{
		logger.info("Task object {}",task.toString());
		return taskService.saveTask(task);
	}
	
	@PostMapping("/addTasks")
	public List<Task> addTasks(@RequestBody List<Task> tasks)
	{
		return taskService.saveTasks(tasks);
	}
	
	
	//Getmapping ile alma isteklerimizi belirtiyoruz
	@GetMapping("/tasks")
	public List<Task> getAllTasks()
	{
		return taskService.getTasks();
	}
	@GetMapping("/taskById/{id}")
	public Task findTaskById(@PathVariable int id)
	{
		return taskService.getTaskById(id);
	}
	@GetMapping("/taskByName/{name}")
	public List<Task> findTaskByName(@PathVariable String name) 
	{
		return taskService.getTaskByName(name);
	}
	@GetMapping("/listTaskByUsername/{username}")
	public List<Task> findTasksByUsername(@PathVariable String username)
	{
		return taskService.getTasksForUser(username);
	}
	
	
	@PutMapping("/update")
	public Task updateTask(@RequestBody Task task)
	{
		System.out.println("UPDATED");
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTask(@PathVariable int id)
	{
		System.out.println("DELETED");
		return taskService.deleteTask(id);
	}
	
	@PutMapping("/status")
	public Task updateStatusTask(@RequestBody Task task)
	{
		return taskService.setTaskStatus(task.getId());
	}
	

}
