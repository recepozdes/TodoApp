package tr.com.jandarma.ToDoApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.jandarma.ToDoApp.entity.Task;

@Repository //spring dataya bunun özel bir interface olduğunu belirttik
public interface JobRepository extends JpaRepository<Task,Integer> { //<hangi entity için, ve onun ID tipi>
	
	Task findByName(String name); //yapılacak bir iş metodu tanımladık
	
	List<Task> findAllByUsername(String username); // burada da belirli bir kişinin yapacağı tüm işlemleri kişi üzerinden aldık
	
	List<Task> findByUsernameOrderByIdDesc(String username);

}
