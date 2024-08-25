package ganesh.com.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ganesh.com.entity.SatCourse;
import ganesh.com.entity.SatStatus;
import ganesh.com.repository.SatCourseRepo;
import ganesh.com.repository.SatStatusRepo;

@Component
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	private SatStatusRepo status;
	
	@Autowired
	private SatCourseRepo course;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		course.deleteAll();
		
		SatCourse c = new SatCourse();
		c.setCourseName("java");
		
		SatCourse c1 = new SatCourse();
		c1.setCourseName("python");
		
		SatCourse c2 = new SatCourse();
		c2.setCourseName("dotNet");
		
		course.saveAll(Arrays.asList(c,c1,c2));
		
		status.deleteAll();
		
		SatStatus s = new SatStatus();
		s.setStatusName("New");
		
		SatStatus s1 = new SatStatus();
		s1.setStatusName("Enrolled");
		
		SatStatus s2 = new SatStatus();
		s2.setStatusName("Lost");
		
		status.saveAll(Arrays.asList(s,s1,s2));
	}

}
