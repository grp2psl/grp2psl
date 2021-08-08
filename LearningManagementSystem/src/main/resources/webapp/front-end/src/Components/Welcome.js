import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';

const cardStyle={
	height: '200px',
	marginTop: '30px'
};
class Welcome extends React.Component{
		registerTrainer = () => {
			this.props.history.push("/register");
		}
		
		showTrainer = () => {
			this.props.history.push("/show");
		}
		
		registerCourse = () => {
			this.props.history.push("/registerCourse");
		}
		
		viewCourse = () => {
			this.props.history.push("/viewCourse");
		}
		

		teacherCourseMappingRegister = () => {
			this.props.history.push("/TeacherCourseMappingRegister");
		}
		
		viewCourseAttended = () => {
			this.props.history.push("/ShowCourseAttended");
			}

		registerLearner = () => {
			this.props.history.push("/registerLearner");
		}
		
		showLearner = () => {
			this.props.history.push("/showLearners");
		}
	
    render(){
        return(
			<div className="mt-5">
                <Container>
                        <Row xs={1} md={4} className="g-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}> 
						        <Card.Body>
						          <Card.Title>Register Trainer</Card.Title>
						          <Card.Text>
						            Register a trainer to a course OR
						            Register multiple trainers to a course
						          </Card.Text>
						          <Button variant="primary" onClick={this.registerTrainer}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>View Trainers</Card.Title>
						          <Card.Text>
						            View all the trainers, their respective course offerings
						            and average ratings.
						          </Card.Text>
						          <Button variant="primary" onClick={this.showTrainer}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Register Learner</Card.Title>
						          <Card.Text>
						            Register a learner to a course OR
						            Register multiple learners to a course  
						          </Card.Text>
						          <Button variant="primary" onClick={this.registerLearner}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>View Learners</Card.Title>
						          <Card.Text>
						            View all the learners, their respective course offerings
						            and average ratings.
						          </Card.Text>
						          <Button variant="primary" onClick={this.showLearner}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
						
						<Row xs={1} md={4} className="g-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Register Course</Card.Title>
						          <Card.Text>
						            Register a course   
						          </Card.Text>
						          <Button variant="primary" onClick={this.registerCourse}>Go</Button>
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
						          <Button variant="primary" onClick={this.viewCourse}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
						
						<Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light">
						        <Card.Body>
						          <Card.Title>Register Trainer to course</Card.Title>
						          <Card.Text>
						            Register a trainer to course   
						          </Card.Text>
						          <Button variant="primary" onClick={this.teacherCourseMappingRegister}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    
						    <Col>
						      <Card className="bg-light">
						        <Card.Body>
						          <Card.Title>View course attended</Card.Title>
						          <Card.Text>
						            View course attended  
						          </Card.Text>
						          <Button variant="primary" onClick={this.viewCourseAttended}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    
						</Row>
						
                </Container>
            </div>);
    }
}

export default Welcome;