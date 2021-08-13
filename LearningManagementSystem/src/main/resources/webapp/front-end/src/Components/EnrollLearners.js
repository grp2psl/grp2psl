import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';

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

const MANAGER_URL = process.env.REACT_APP_MANAGER_URL;

class EnrollLearners extends React.Component{
    render(){
        return(
			<div className="mt-5">
                <Container>
                        <Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}> 
						        <Card.Body>
						          <Card.Title>Enroll a Learner</Card.Title>
						          <Card.Text>
						            Enroll a learner to a course by filling in a form
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
                                      this.props.history.push(MANAGER_URL+"/enrollLearner");
                                  }}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Enroll Multiple Learners</Card.Title>
						          <Card.Text>
						            Enroll multiple learners by uploading an EXCEL file
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
                                      this.props.history.push(MANAGER_URL+"/enrollMultipleLearners");
                                  }}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                </Container>
            </div>);
    }
}

export default EnrollLearners;