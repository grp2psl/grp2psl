import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";

class EnrollLearner extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.registerTrainer = this.registerTrainer.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
		learners:[],
        name: "",
        department: "",
        phonenumber: "",
        email: "",
        msg:""
      };

	async componentDidMount() {
		try {
			let learnersList = await axios.get("http://localhost:8090/LearningManagementSystem/learners/")
			this.state.learners = learnersList.data;
			console.log(this.state.learners);	
		} catch(error) {
			alert(error);
		}
	}

    resetForm = () => {
        this.setState({
			name:"",
			department:"",
			phonenumber:"",
			email:"",
			msg:""
		});
    };

    async registerTrainer(event){
		event.preventDefault();
		const trainer = {
            name: this.state.name,
            department: this.state.department,
            phonenumber: this.state.phonenumber,
            email: this.state.email
        }
        this.setState({
			msg:"Processing..\nPlease Wait"
		});
		try{
			const response = await axios.post("http://localhost:8080/LearningManagementSystem/trainers/register", trainer);
			if(response.data != null){
	        	alert("Trainer registered successfully");
	            console.log(response.data);
        	}	
		} catch(error) {
			alert(error);
		}
        this.setState(this.initialState);
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
                <Card.Header>Register Trainer</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.registerTrainer} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                            <Form.Select aria-label="Default select example">
							  <option>Open this select menu</option>
							</Form.Select>

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

export default EnrollLearner;