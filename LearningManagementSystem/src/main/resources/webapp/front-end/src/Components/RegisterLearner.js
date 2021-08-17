import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";
import { DATABASE_URL, LEARNER_URL, MANAGER_URL } from '../constants';  

class RegisterLearner extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.register = this.register.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        name: "",
        department: "",
        phonenumber: "",
        email: "",
        msg:""
      };
      
	componentWillMount(){
        if(localStorage.getItem('user') !== 'manager' || localStorage.getItem('loggedin') === false){
            alert("User not logged in!");
            return this.props.history.push("/");
        }
   }
    resetForm = () => {
        this.setState(() => this.initialState);
    };    
    
    validateForm(phoneNumber) {
        if(phoneNumber.length > 11 || phoneNumber.length < 10 || !(/^\d+$/.test(phoneNumber))){
            alert("Enter valid phoneNumber");
            return false;
        }
        return true;
    }

    async register(event){
		event.preventDefault();
		const learner = {
            name: this.state.name,
            department: this.state.department,
            phoneNumber: this.state.phonenumber,
            email: this.state.email
        }
        if(this.validateForm(learner.phoneNumber) === true){
            this.setState({
                msg:"Processing..\nPlease Wait"
            });
            try{
                const response = await axios.post(DATABASE_URL+MANAGER_URL+LEARNER_URL+"/register", learner, {
                    auth: {
                        username: localStorage.getItem("username"),
                        password: localStorage.getItem("password")
                  }
                });
                if(response.data != null){
                    alert("Learner registered successfully");
                    console.log(response.data);
                }	
            } catch(error) {
                if(error.response != null){
                    alert(error.response.data);
                }
                alert("Operation failed!");
            }
            this.setState(this.initialState);
        }
    }

    formChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render(){
        return(
			<div className="mt-5">
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Register a Learner</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.register} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                            <Form.Group className="mb-3" controlId="name">
                                <Form.Label>Name</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test" 
                                    value={this.state.name}
                                    onChange={this.formChange}
                                    name="name"
                                    placeholder="Enter name" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="department">
                                <Form.Label>Department</Form.Label>
                                <Form.Control 
                                    type="text" autoComplete="off" 
                                    value={this.state.department}
                                    onChange={this.formChange}
                                    name="department"
                                    placeholder="Enter department" />
                            </Form.Group>
                            </Col>
                            <Col>
                            <Form.Group className="mb-3" controlId="phoneNumber">
                                <Form.Label>Phone Number</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" 
                                    value={this.state.phonenumber}
                                    onChange={this.formChange}
                                    name="phonenumber"
                                    placeholder="Enter phone number" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="email">
                                <Form.Label>Email</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="email" 
                                    value={this.state.email}
                                    onChange={this.formChange}
                                    name="email"
                                    placeholder="Enter email" />
                            </Form.Group>
                            </Col>
                        </Row>
                        
                    </Container>
                        
                </Card.Body>
                <Card.Footer style={{"text-align":"right"}}>
                    <Button variant="success" type="submit">
                        <FontAwesomeIcon icon={faSave} />{" "}
                        Submit
                    </Button>{" "}
                    <Button variant="danger" type="reset">
                        <FontAwesomeIcon icon={faUndo} />{" "}
                        Reset
                    </Button>
                </Card.Footer>
                </Form>
            </Card>
      </div>
        );
    }
}

export default RegisterLearner;