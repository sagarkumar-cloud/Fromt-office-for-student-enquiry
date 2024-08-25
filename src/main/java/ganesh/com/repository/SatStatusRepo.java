package ganesh.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ganesh.com.entity.SatStatus;

public interface SatStatusRepo extends JpaRepository<SatStatus, Integer>{

}
