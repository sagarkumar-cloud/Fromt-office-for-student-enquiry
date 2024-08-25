package ganesh.com.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentAddEnquiry {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
     private Integer id;
     private String studentName;
     private Long phno;
	 private String classMode;
	 private String courseName;
	 private String enqStatus;
	 
	 @CreationTimestamp
	 private LocalDate dateCreated;
	 
	 @UpdateTimestamp
	 private LocalDate lastUpdate;
	 
	 @ManyToOne
	 @JoinColumn(name="userId")
	 private SatUserDetails user;



	
	 
}
