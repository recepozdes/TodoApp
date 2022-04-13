package tr.com.jandarma.ToDoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.jandarma.ToDoApp.entity.User;


//entitylerde oluşturulan her varlık için bir repository oluşturulmalı
//burada da kullanıcıları bulmak ve almak için bu bölümü kullanıyoruz
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	User findByUsernameAndPassword(String username,String password);
	//burada username ve şifre ile kullanıcı buluyoruz
	
	User findTopByUsername(String username);
	//burada veritabanından bir kullanıcıyı buluyoruz. bu yöntem kişi başına bir kullanıcı adını kısıtladığımızda kullanışlı olacaktır
	
	User findTopByPassword(String password);
	//burada da veritabanında olup olmadığını kontrol etmek için kullanıcı şifresine ihtiyaç duyuyoruz
}
