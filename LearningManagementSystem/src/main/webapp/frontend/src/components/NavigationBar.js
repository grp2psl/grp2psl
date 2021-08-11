import React from 'react';
import {Link} from 'react-router-dom';
import {Navbar, Nav, Container} from 'react-bootstrap';

export default class NavigationBar extends React.Component{
	render(){
		return (<Navbar bg="light" variant="light">
    <Container>
    <Link className = "navbar-brand" to = {"/"}> Learning System </Link>
    <Nav className="me-auto">
    	<Link className = "nav-link" to ={"CourseOfferingListing"}>Course Offerings</Link>
      	<Link className = "nav-link" to = {"Feedback"}>Submit Feedback</Link>
      	<Link className = "nav-link" to = {"Credentials"}>Change Credentials</Link>      	
    </Nav>
    </Container>
  </Navbar>);
		
	}
}