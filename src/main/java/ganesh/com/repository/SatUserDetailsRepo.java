package ganesh.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ganesh.com.entity.SatUserDetails;
import java.util.List;


public interface SatUserDetailsRepo extends JpaRepository<SatUserDetails, Integer>{

	public SatUserDetails findByEmail(String email);
	
	public SatUserDetails findByEmailAndPassword(String email,String pass);
}
