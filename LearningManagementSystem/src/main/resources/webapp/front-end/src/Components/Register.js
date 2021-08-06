import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";

class Register extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.registerTrainer = this.registerTrainer.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        name: "",
        department: "",
        phonenumber: "",
        email: ""
      };

    resetForm = () => {
        this.setState(() => this.initialState);
    };

    registerTrainer(event){
        event.preventDefault();
        const trainer = {
            name: this.state.name,
            department: this.state.department,
            phonenumber: this.state.phonenumber,
            email: this.state.email
        }
        axios.post("http://localhost:8080/LearningManagementSystem/trainers/register", trainer)
        .then(response =>{
            if(response.data != null){
                alert("Trainer registered successfully");
                console.log(response.data);
            }
        })
        .catch(error => {
            alert(error);
        });
        this.setState(this.initialState);
    }

    formChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Register Trainer</Card.Header>
                <Form onSubmit={this.registerTrainer} onReset={this.resetForm} id="registerId" >
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
                                    className={"bg-dark text-white"}
                                    name="name"
                                    placeholder="Enter name" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="department">
                                <Form.Label>Department</Form.Label>
                                <Form.Control 
                                    type="text" autoComplete="off" 
                                    value={this.state.department}
                                    onChange={this.formChange}
                                    className={"bg-dark text-white"}
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
                                    className={"bg-dark text-white"}
                                    name="phonenumber"
                                    placeholder="Enter phone number" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="email">
                                <Form.Label>Email</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="email" 
                                    value={this.state.email}
                                    onChange={this.formChange}
                                    className={"bg-dark text-white"}
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
        );
    }
}

export default Register;