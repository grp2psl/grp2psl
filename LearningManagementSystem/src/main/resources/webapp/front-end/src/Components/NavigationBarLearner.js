import React from 'react';
import {Link} from 'react-router-dom';
import {Navbar, Nav, Container} from 'react-bootstrap';
import {LEARNER_URL} from '../constants'

export default class NavigationBarLearner extends React.Component{
	render(){
		return (<Navbar bg="light" variant="light">
    <Container>
    <Link className = "navbar-brand" to = {LEARNER_URL+"/"}> Learning System </Link>
    <Nav className="me-auto">
    	<Link className = "nav-link" to ={LEARNER_URL+"/CourseOfferingListing"}>Course Offerings</Link>
      	<Link className = "nav-link" to = {LEARNER_URL+"/Feedback"}>Submit Feedback</Link>
      	<Link className = "nav-link" to = {LEARNER_URL+"/Credentials"}>Change Credentials</Link>      	
    </Nav>
    </Container>
  </Navbar>);
		
	}
}