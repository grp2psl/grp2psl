import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";

class UpdateScoreIndividual extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.updateScores = this.updateScores.bind(this);
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
			let learnersList = await axios.get("http://localhost:8080/LearningManagementSystem/learners/")
            this.state.learners = learnersList.data;
            let tcMappings = await axios.get("http://localhost:8080/LearningManagementSystem/teacherCourseMapping/trainer-course-names")
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
            learnerid: 0,
            tcid: 0,
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
            //const params = JSON.stringify({ tcId: this.state.tcid,  learnerId: this.state.learnerid, percentage: this.state.percentage});
            const response = await axios.put("http://localhost:8080/LearningManagementSystem/managers/update-test-score?tcId="+this.state.tcid+"&learnerId="+this.state.learnerid+"&percentage="+this.state.percentage);
			// const response = await axios({
            //     method: 'PUT',
            //     url: 'http://localhost:8080/LearningManagementSystem/managers/update-test-score',
            //     data: {
            //         tcId: this.state.tcid,
            //         learnerId: this.state.learnerid,
            //         percentage: this.state.percentage
            //     }
            //   })
            if(response.data != null){
	        	alert("Score updated successfully");
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
        // if(this.state.learners.length > 0){
        //     const response = axios({
        //         method: 'GET',
        //         url: 'http://localhost:8080/LearningManagementSystem/managers/getCourseOffering',
        //         data: {
        //             learnerId: this.state.learnerid
        //         }
        //     })
        //     this.setState({
        //         tcMappings: response.data
        //     })
        // }
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
                                    return <option key={learner.learnerid} value={learner.learnerid}>{learner.name} - {learner.email}</option>  
                                })}
                                </Form.Select>
                           	</Col>
                        </Row>
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
                        </Row>
                        <Row>
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