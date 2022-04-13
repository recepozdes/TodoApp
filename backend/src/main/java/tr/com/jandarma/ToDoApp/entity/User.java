package tr.com.jandarma.ToDoApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="username",unique=true) //oluşturulacak her usernamein eşsiz olması gerektiğini belirttik
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String status;
	private String role;

}
