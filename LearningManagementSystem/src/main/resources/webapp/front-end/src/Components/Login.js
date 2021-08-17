import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';

const cardStyle={
	height: '200px',
	marginTop: '20px'
};

const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};

class Login extends React.Component{
	componentDidMount(){		
        localStorage.setItem('loggedin', false);
        localStorage.setItem('userId', '');
        localStorage.setItem('user', '');
	}

	render(){
        return(
			<div className="mt-5">
                <Container>
                		<Row xs={1} md={3} className="g-4 mb-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						            <Card.Title>Admin Login</Card.Title>
                            
                                    <Button variant="primary" style={buttonStyle} onClick={() => {
									this.props.history.push("/admin-login")}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Trainer Login</Card.Title>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
									this.props.history.push("/trainer-login")}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Learner Login</Card.Title>
						          <Button variant="primary" style={buttonStyle} onClick={() => {
									this.props.history.push("/learner-login")}}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>	    
						</Row>
						
                </Container>
            </div>);
    }
}

export default Login;