package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entities.CourseAttended;

public interface ICourseAttended extends CrudRepository<CourseAttended, Integer>{

	@Query(value="select  l.name, c.courseid, c.coursename,l.learnerid from learner l, course c, TeacherCourseMapping tc, courseoffering co where c.courseid = tc.courseid and co.learnerid=l.learnerid and tc.tcid = co.tcid ", nativeQuery=true)
	public List<CourseAttended> courseAttended();
}
