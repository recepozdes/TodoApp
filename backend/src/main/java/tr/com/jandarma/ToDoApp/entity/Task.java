package tr.com.jandarma.ToDoApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				//lombok sayesinde data annotation ile getter ve setterları oluşturmamıza gerek kalmadı
@AllArgsConstructor //allargsconstructor ile bütün argümanların olduğu bir constructor oluşturduk 
@NoArgsConstructor  //noargsconstructor ile argümansız bir constructor oluşturduk
@Entity //entity oluşturacağımızı söyledik
@Table(name="task_table") //isim ile database tablosu oluşturacağımızı belirttik
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private String description;
	private String status;
	
	@Column(name="username") //Tablo içinde bir sütun oluşturacağımızı belirttik
	private String username;

}
