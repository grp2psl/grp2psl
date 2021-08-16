import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";
import { DATABASE_URL, MANAGER_URL, ADMIN_USERNAME, ADMIN_PASSWORD } from '../constants';

class AdminDetails extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.update = this.update.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        id: localStorage.getItem('userId'),
        name: '',
        phonenumber: '',
        email: '',
        msg:""
    };
    
    async showData(){
		this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
			const response = await axios.get(DATABASE_URL+MANAGER_URL+"/"+this.state.id, {
                auth: {
                  username: ADMIN_USERNAME,
                  password: ADMIN_PASSWORD
                }
              });
			if(response.data != null) {
				this.setState({
                    name: response.data.name,
                    phonenumber: response.data.phoneNumber,
                    email: response.data.email
				});	
			}	
		} catch(error) {
			alert(error);
		}
        this.setState({
			msg: ""
		})
    }
    componentDidMount(){
        this.showData();        
    }
	componentWillMount(){
        if(localStorage.getItem('user') != 'manager' || localStorage.getItem('loggedin') === false){
            alert("User not logged in!");
            return this.props.history.push("/");
        }
   }

    resetForm = () => {
        this.setState(() => this.initialState);
    };
    
    validateForm(phoneNumber) {
        if(phoneNumber.length > 11 || phoneNumber.length < 10){
            alert("Enter valid phoneNumber");
            return false;
        }
        return true;
    }

    async update(event){
		event.preventDefault();
		const manager = {
            managerId: this.state.id,
            name: this.state.name,
            phoneNumber: this.state.phonenumber,
            email: this.state.email
        }
        if(this.validateForm(manager.phoneNumber) === true){
            this.setState({
                msg:"Processing..\nPlease Wait"
            });
            try{
                const response = await axios.put(DATABASE_URL+MANAGER_URL+"/update", manager, {
                    auth: {
                      username: ADMIN_USERNAME,
                      password: ADMIN_PASSWORD
                    }
                  });
                if(response.data != null){
                    alert("Admin updated successfully");
                    console.log(response.data);
                }	
            } catch(error) {
                alert(error);
            }
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
                <Card.Header>Admin Details</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.update} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                            <Form.Group className="mb-3" controlId="name">
                                <Form.Label>ID</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test" 
                                    value={this.state.id}
                                    disabled={true}
                                    name="id" />
                            </Form.Group>
                            </Col>
                            <Col>
                            <Form.Group className="mb-3" controlId="name">
                                <Form.Label>Name</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test" 
                                    value={this.state.name}
                                    disabled={true}
                                    name="name"
                                    placeholder="Enter name" />
                            </Form.Group>
                            </Col>
                        </Row>
                        <Row>
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
                            </Col>
                            <Col>

                            <Form.Group className="mb-3" controlId="email">
                                <Form.Label>Email</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="email" 
                                    value={this.state.email}
                                    disabled={true}
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

export default AdminDetails;