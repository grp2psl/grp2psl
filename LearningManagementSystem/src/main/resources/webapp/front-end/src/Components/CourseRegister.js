import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";
import { COURSE_URL, DATABASE_URL } from '../constants';

class CourseRegister extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.registerCourse = this.registerCourse.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        coursename: "",
        prerequisite: "",
        syllabus: "",
        duration: "",
        msg:""
      };

    resetForm = () => {
        this.setState(() => this.initialState);
    };

    async registerCourse(event){
		event.preventDefault();
		const course = {
            courseName: this.state.coursename,
            prerequisite: this.state.prerequisite,
            syllabus: this.state.syllabus,
            duration: this.state.duration
        }
        this.setState({
			msg:"Processing..\nPlease Wait"
		});
		try{
			const response = await axios.post(DATABASE_URL+COURSE_URL+"/addcourse", course);
			if(response.data != null){
	        	alert("Course registered successfully");
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
                <Card.Header>Register Course</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.registerCourse} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                            <Form.Group className="mb-3" controlId="coursename">
                                <Form.Label>Course Name</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" 
                                    value={this.state.coursename}
                                    onChange={this.formChange}
                                    name="coursename"
                                    placeholder="Enter Course Name" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="prerequisite">
                                <Form.Label>Prerequisite</Form.Label>
                                <Form.Control 
                                    type="text" autoComplete="off" 
                                    value={this.state.prerequisite}
                                    onChange={this.formChange}
                                    name="prerequisite"
                                    placeholder="Enter prerequisite for course" />
                            </Form.Group>
                            </Col>
                            <Col>
                            <Form.Group className="mb-3" controlId="syllabus">
                                <Form.Label>Enter Syllabus URL</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="url" 
                                    value={this.state.syllabus}
                                    onChange={this.formChange}
                                    name="syllabus"
                                    placeholder="Enter syllabus of the course" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="duration">
                                <Form.Label>Duration</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" 
                                    value={this.state.duration}
                                    onChange={this.formChange}
                                    name="duration"
                                    placeholder="Enter duration of course" />
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

export default CourseRegister;