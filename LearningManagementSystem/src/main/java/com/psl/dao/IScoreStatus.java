package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.psl.entities.ScoreStatus;

public interface IScoreStatus extends CrudRepository<ScoreStatus,Integer> {
	@Query(value="select l.name as name,c.coursename as coursename,co.Percentage as percentage,co.status as status from learner l,courseoffering co,TeacherCourseMapping tc,course c where c.courseid = tc.courseid and co.learnerid=l.learnerid and tc.tcid = co.tcid",nativeQuery=true)
	public List<ScoreStatus> scoreAndStatus();
}
