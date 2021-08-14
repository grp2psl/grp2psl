import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo,
    faEye
  } from "@fortawesome/free-solid-svg-icons";
import {DATABASE_URL, MANAGER_URL, TCMAPPING_URL} from '../constants';

const buttonStyle={
    position: 'absolute',
    top: '105px',
    right: '35%'
};

class UpdateScoreIndividual extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.updateScores = this.updateScores.bind(this);
        this.findLearners = this.findLearners.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        learners:[],
        tcMappings:[],
        learnerid: 0,
        tcid: 0,
        percentage: 0,
        msg:""
      };

	async componentDidMount() {
        this.setState({
			msg:"Processing.. Please Wait"
		});
		try {
			// let learnersList = await axios.get("http://localhost:8080/LearningManagementSystem/learners/")
            // this.state.learners = learnersList.data;
            let tcMappings = await axios.get(DATABASE_URL+TCMAPPING_URL+"/trainer-course-names")
			this.state.tcMappings = tcMappings.data;
		} catch(error) {
			alert(error);
        }
        this.setState({
            msg: ""
        });
	}
    
	async findLearners(id) {
        this.setState({
			msg:"Processing.. Please Wait"
		});
		try {
			let learnersList = await axios.get(DATABASE_URL+MANAGER_URL+"/findLearnersByTcId/"+id)
			this.state.learners = learnersList.data;
            
		} catch(error) {
			alert(error);
        }
        this.setState({
            msg: ""
        });
	}

    resetForm = () => {
        this.setState({
            learnerid: 0,
            tcid: 0,
            learners:[],
            percentage: 0,
            msg:""
		});
    };

    async updateScores(event){
		event.preventDefault();
        
        this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
            const response = await axios.put(DATABASE_URL+MANAGER_URL+"/update-test-score?tcId="+this.state.tcid+"&learnerId="+this.state.learnerid+"&percentage="+this.state.percentage);
			
            if(response.data != null){
	        	alert("Score updated successfully");
	            console.log(response.data);
        	}	
		} catch(error) {
			alert(error.response.data);
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
                <Card.Header>Update Score</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.updateScores} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                                <Form.Label>Trainer-Course</Form.Label>
                                <Form.Select 
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
                            <Col>
                                <Button 
                                    variant="primary" 
                                    style={buttonStyle}
                                    onClick={() => this.findLearners(this.state.tcid)}>
                                    <FontAwesomeIcon icon={faEye} />{" "}
                                    Show Learners
                                </Button>
                            </Col>
                        </Row>
                        
                        <Row>
                            <Col>
                                <Form.Label>Learner</Form.Label>
                                <Form.Select
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
                            <Form.Group className="mb-3" controlId="percentage">
                                <Form.Label>Percentage</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="number" 
                                    value={this.state.percentage}
                                    onChange={this.formChange}
                                    name="percentage"
                                    placeholder="Enter percentage" />
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

export default UpdateScoreIndividual;