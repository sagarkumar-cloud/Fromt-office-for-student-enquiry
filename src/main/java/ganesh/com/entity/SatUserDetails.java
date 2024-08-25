package ganesh.com.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="sit_user_dtls")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SatUserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer userId;
	
	private String name;
	
	private String email;
	
	private String phno;
	
	private String password;
	
	
	private String accountStatus;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL,fetch =FetchType.EAGER)
	private List<StudentAddEnquiry> enquiries;


}
