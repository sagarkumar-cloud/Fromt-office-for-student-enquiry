package ganesh.com.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="sit_enquriry_status")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SatStatus {

	 @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusId;
	 
	
	private String statusName;

	
}
