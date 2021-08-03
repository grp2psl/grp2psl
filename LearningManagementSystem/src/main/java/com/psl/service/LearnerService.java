package com.psl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.psl.dao.ILearnerDAO;
import com.psl.entities.Learner;

@Configurable
@Service("learnerService")
public class LearnerService {
	@Autowired
	private ILearnerDAO dao;
	
	public void addLearner(Learner l) {
		dao.save(l);
	} 
	
	public Learner getLearner(int id) {
		return dao.findById(id).get();
	}

	public Learner getLearnerid(String email) {
		System.out.println(dao.findByEmail(email).get(0));
		return dao.findByEmail(email).get(0);
		// return dao.findById(id).get();
	}

	public int getLearneridByEmail(String email) {
		return dao.findLearneridByEmail(email);
	}


	// @PersistenceContext
  	// private EntityManager entityManager;

	// public List<Learner> getLearnerIdbyEmail(String email) {
	// 	return entityManager.createQuery("SELECT learnerid FROM Learner where email = :email",
	// 	Learner.class).setParameter("email", email).getResultList();
	// }





	// public int getLearnerIdbyEmail(String string) {
	// 	dao.findAll();
	// 	@Query("SELECT max(t.id) FROM #{#entityName} t")
	// 	Integer getMaxId();
	// 	return 
	// }

	

	

	// @PersistenceContext
  	// private EntityManager entityManager;

	// public Learner getIdByEmail(String email) {
	// 	return (Learner) entityManager.createQuery(
	// 		"from learnerid where email = :email")
	// 		.setParameter("email", email)
	// 		.getSingleResult();
	// }

	// public long getIdByEmail(String email) {
	// 	return ((Number) entityManager.createQuery("select learnerid from learner where email = " + email).getSingleResult()).longValue();
	// }

	// public static void main(String[] args) {
	// 	// String email = "adams@gmail.com";
	// 	// LearnerService service = new LearnerService();
	// 	// System.out.println(service.getLearner(1));
	// 	System.out.println(getLearner(1));
	// 	// System.out.println(service.getIdByEmail(email));
	// }
	
}
