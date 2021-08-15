import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";
import { DATABASE_URL, LEARNER_URL, MANAGER_URL, TCMAPPING_URL, ADMIN_USERNAME, ADMIN_PASSWORD } from '../constants';

class EnrollLearner extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.enrollLearner = this.enrollLearner.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        learners:[],
        tcMappings:[],
        learnerid: 0,
        tcid: 0,
        startdate: "",
        enddate: "",
        msg:""
      };
      
	componentWillMount(){
        if(localStorage.getItem('user') != 'manager' || localStorage.getItem('loggedin') === false){
            alert("User not logged in!");
            return this.props.history.push("/");
        }
   }
	async componentDidMount() {
        this.setState({
			msg:"Processing.. Please Wait"
		});
		try {
			let learnersList = await axios.get(DATABASE_URL+LEARNER_URL+"/", {
                auth: {
                  username: ADMIN_USERNAME,
                  password: ADMIN_PASSWORD
                }
              })
            this.state.learners = learnersList.data;
            let tcMappings = await axios.get(DATABASE_URL+TCMAPPING_URL+"/trainer-course-names", {
                auth: {
                  username: ADMIN_USERNAME,
                  password: ADMIN_PASSWORD
                }
              })
			this.state.tcMappings = tcMappings.data;
		} catch(error) {
			alert(error);
        }
        this.setState({
            msg: ""
        });
	}

    resetForm = () => {
        this.setState({
			learnerid:"",
			tcid:"",
			startdate:"",
			enddate:"",
			msg:""
		});
    };

    async enrollLearner(event){
		event.preventDefault();
		const courseOffering = {
            learnerId: this.state.learnerid,
            tcId: this.state.tcid,
            startDate: this.state.startdate,
            endDate: this.state.enddate
        }
        this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
			const response = await axios.post(DATABASE_URL+MANAGER_URL+"/enroll-learner", courseOffering, {
                auth: {
                  username: ADMIN_USERNAME,
                  password: ADMIN_PASSWORD
                }
              });
			if(response.data != null){
	        	alert("Learner enrolled successfully");
	            console.log(response.data);
        	}	
		} catch(error) {
			alert(error);
		}
        this.resetForm();
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
                <Card.Header>Enroll a Learner</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.enrollLearner} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                                <Form.Label>Learner</Form.Label>
                                <Form.Select
                                isInvalid={this.state.learnerid === 0}
                                value={this.state.learnerid}
                                onChange={(e)=>{
                                    if(e.isInvalid) {
                                        alert("Select a learner");
                                    }
                                    this.setState({
                                        learnerid: e.target.value
                                    })
                                }}>
                                <option>Select a learner</option>
                                {this.state.learners.map((learner) => {
                                    return <option key={learner.learnerId} value={learner.learnerId}>{learner.name} - {learner.email}</option>  
                                })}
                                </Form.Select>
                           	</Col>
                            <Col>
                                <Form.Label>Trainer-Course</Form.Label>
                                <Form.Select 
                                isInvalid={this.state.tcid === 0}
                                value={this.state.tcid}
                                onChange={(e)=>{
                                    if(e.isInvalid) {
                                        alert("Select a trainer-course mapping");
                                    }
                                    this.setState({
                                        tcid: e.target.value
                                    })
                                }}>
                                <option>Select Trainer and Course</option>
                                {this.state.tcMappings.map((tcMapping) => {
                                    return <option key={tcMapping.tcid} value={tcMapping.tcid}>Trainer - {tcMapping.trainerName}, Course - {tcMapping.courseName}</option>  
                                })}
                                </Form.Select>
                           	</Col>
                        </Row>
                        <Row>
                            <Col>
                                <Form.Group className="mb-3 mt-3" controlId="startdate">
                                    <Form.Label>Course's Start Date</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="date" 
                                        onChange={(e)=>{
                                            this.setState({
                                                startdate: e.target.value
                                            })
                                        }}
                                        name="startdate"
                                        value={this.state.startdate}
                                        placeholder="Select the course's start date" />
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group className="mb-3 mt-3" controlId="enddate">
                                    <Form.Label>Course's End Date</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="date" 
                                        timeFormat="yyyy-mm-dd"
                                        onChange={(e)=>{
                                            this.setState({
                                                enddate: e.target.value
                                            })
                                        }}
                                        name="enddate"
                                        value={this.state.enddate}
                                        placeholder="Select the course's end date" />
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

export default EnrollLearner;