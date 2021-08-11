package com.psl.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.psl.entities.CourseAttended;

public interface ICourseAttended extends CrudRepository<CourseAttended, Integer>{
	@Transactional
	@Modifying
	@Query(value="select  l.learnerid,l.name, c.courseid, c.coursename, t.name as trainername,co.percentage,co.status from learner l, course c, TeacherCourseMapping tc, courseoffering co, trainer t where c.courseid = tc.courseid and co.learnerid=l.learnerid and tc.tcid = co.tcid and t.trainerid = tc.trainerid and co.learnerid=:id GROUP BY co.tcid ", nativeQuery=true)
	public List<CourseAttended> courseAttended(@Param("id") int id);
}
