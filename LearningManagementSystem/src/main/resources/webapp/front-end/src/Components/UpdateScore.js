import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';

const cardStyle={
	height: '200px',
	marginTop: '30px'
};

const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};

const MANAGER_URL = process.env.REACT_APP_MANAGER_URL;

class UpdateScore extends React.Component{
		updateScore = () => {
			this.props.history.push(MANAGER_URL+"/update-score");
		}
		
		updateScoreMultiple = () => {
			this.props.history.push(MANAGER_URL+"/update-score-multiple");
		}
	
    render(){
        return(
			<div className="mt-5">
                <Container>
                        <Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}> 
						        <Card.Body>
						          <Card.Title>Update test score of a learner</Card.Title>
						          <Card.Text>
                                  Update test score of a learner for a course attended by filling in a form
						          </Card.Text>
						          <Button variant="primary" onClick={this.updateScore} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Update test score of multiple learners</Card.Title>
						          <Card.Text>
						            Update test score of multiple learners by uploading an EXCEL file
						          </Card.Text>
						          <Button variant="primary" onClick={this.updateScoreMultiple} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                </Container>
            </div>);
    }
}

export default UpdateScore;