import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';

const cardStyle={
	height: '200px',
	marginTop: '30px'
};

class Welcome extends React.Component{
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
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/registerCourse");
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
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/viewCourse");
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
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/registerLearner");
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
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/enroll");
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
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/viewLearners");
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
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/register");
									}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Assign Trainer</Card.Title>
						          <Card.Text>
						            Assign trainer(s) to a course
						          </Card.Text>
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/");
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
						          <Button variant="primary" onClick={() => {
										this.props.history.push("/show");
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