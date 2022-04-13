package tr.com.jandarma.ToDoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.jandarma.ToDoApp.entity.Task;
import tr.com.jandarma.ToDoApp.repositories.JobRepository;

@Service //service olduğunu belirtmeliyiz
public class TaskService {
	
	@Autowired // ile repository i manuel oluşturmanın önüne geçiyoruz
	private JobRepository jobRepository;
	
	
	//Post ile gönderiyi yapıyoruz
	public Task saveTask(Task task) 
	{
		System.out.println(task.toString());
		return jobRepository.save(task);
	}
	
	//Optional olarak tümünü gönderebiliriz
	public List<Task> saveTasks(List<Task> tasks)
	{
		return jobRepository.saveAll(tasks);
	}
	
	//Get ile oluşturulanları getirebiliriz aşağıda nasıl getirmek istersek o şekilde yöntemler mevcut
	public List<Task> getTasks()
	{
		return jobRepository.findAll();
	}
	
	public Task getTaskById(int id)
	{
		return jobRepository.findById(id).orElse(null);
	}
	
	public List<Task> getTaskByName(String name)
	{
		return jobRepository.findByUsernameOrderByIdDesc(name);
	}
	
	public List<Task> getTasksForUser(String username)
	{
		return jobRepository.findAllByUsername(username);
	}
	
	public Task setTaskStatus(int id)
	{
		Task existingTask=jobRepository.findById(id).orElse(null);
		//Task task1 = new Task();
		//task1.setId(id);
		existingTask.setStatus("yapıldı");
		return jobRepository.save(existingTask);
	}
	
	//Put ile güncelleme yapıyoruz ve save yaparak güncellediklerimizi kaydediyoruz
	public Task updateTask(Task task) 
	{
		System.out.println("updates");
		Task existingTask=jobRepository.findById(task.getId()).orElse(null);
		existingTask.setName(task.getName());
		existingTask.setDescription(task.getDescription());
		existingTask.setStatus(task.getStatus());
		return jobRepository.save(existingTask);
	}
	
	//Delete ile var olan id yi bulup siliyoruz ve sildiğimize dair geri gönderim yapıyoruz
	public String deleteTask(int id)
	{
		jobRepository.deleteById(id);
		return id+"id -> task removed/completed";
	}

}
