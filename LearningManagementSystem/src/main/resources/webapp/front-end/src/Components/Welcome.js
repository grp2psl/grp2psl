import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';

class Welcome extends React.Component{
	register = () => {
			this.props.history.push("/register");
		}
		
		show = () => {
			this.props.history.push("/show");
		}
		
		registerCourse = () => {
			this.props.history.push("/registerCourse");
		}
		
		viewCourse = () => {
			this.props.history.push("/viewCourse");
		}
	
    render(){
        return(
			<div className="mt-5">
                <Container>
                        <Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light">
						        <Card.Body>
						          <Card.Title>Register Trainer</Card.Title>
						          <Card.Text>
						            Register a trainer to a course OR
						            Register multiple trainers to a course
						          </Card.Text>
						          <Button variant="primary" onClick={this.register}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light">
						        <Card.Body>
						          <Card.Title>View Trainers</Card.Title>
						          <Card.Text>
						            View all the trainers, their respective course offerings
						            and average ratings.
						          </Card.Text>
						          <Button variant="primary" onClick={this.show}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
						
						<Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light">
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
						      <Card className="bg-light">
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
                </Container>
            </div>);
    }
}

export default Welcome;