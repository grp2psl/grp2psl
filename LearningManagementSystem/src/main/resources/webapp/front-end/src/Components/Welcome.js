import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import {MANAGER_URL} from '../constants';

const cardStyle={
	height: '150px',
	marginTop: '20px'
};

const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};

class Welcome extends React.Component{
	componentWillMount(){
         if(localStorage.getItem('user') !== 'manager' || localStorage.getItem('loggedin') === false){
			 alert("User not logged in!");
			 return this.props.history.push("/");
		 }
    }
	render(){
        return(
			<div className="mt-5">
                <Container>
                		<Row xs={1} md={2} className="g-4 mb-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Register Course</Card.Title>
						          <Card.Text>
						            Register a course   
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/register-course");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>View Courses</Card.Title>
						          <Card.Text>
						            View all the courses
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/courses");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                        <Row xs={1} md={3} className="g-4 mb-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Register Learner</Card.Title>
						          <Card.Text>
						            Register learner(s)
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/register-learners");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
							<Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>View Learners</Card.Title>
						          <Card.Text>
						            View learners and their course details
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/learners");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Enroll Learner</Card.Title>
						          <Card.Text>
						            Enroll learner(s) to a course
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/enroll-learners");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                        <Row xs={1} md={3} className="g-4 mb-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Register Trainer</Card.Title>
						          <Card.Text>
						            Register trainer(s)
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/register-trainers");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>View Trainers</Card.Title>
						          <Card.Text>
						            View trainers and their course details
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/trainers");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>

						          <Card.Title>Assign Trainers</Card.Title>
						          <Card.Text>
						            Register a trainer to course   
						          </Card.Text>
						           <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/register-teacher-course-mappings");
									}}>Go</Button>

						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                		<Row xs={1} md={2} className="g-4 mb-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Update Score</Card.Title>
						          <Card.Text>
						            Update test scores of learners for courses attended  
						          </Card.Text>
								  <Button variant="primary" style={buttonStyle} onClick={()=>{
									  this.props.history.push(MANAGER_URL+"/update-scores");
								  }}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>View Course Offerings</Card.Title>
						          <Card.Text>
						            View all the course offerings
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
										this.props.history.push(MANAGER_URL+"/course-offerings");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>					
						
						
                </Container>
            </div>);
    }
}

export default Welcome;