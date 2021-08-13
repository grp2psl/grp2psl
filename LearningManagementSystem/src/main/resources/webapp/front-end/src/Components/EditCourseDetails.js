import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";
import { COURSE_URL, DATABASE_URL } from '../constants';

class EditCourseDetails extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.register = this.register.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        id: this.props.location.state.course.courseId,
        coursename: this.props.location.state.course.courseName,
        prerequisite: this.props.location.state.course.prerequisite,
        syllabus: this.props.location.state.course.syllabus,
        duration: this.props.location.state.course.duration,
        msg:""
      };

    resetForm = () => {
        this.setState(() => this.initialState);
    };
    
   

    async register(event){
		event.preventDefault();
		const course = {
            courseId: this.state.id,
            courseName: this.state.coursename,
            prerequisite: this.state.prerequisite,
            syllabus: this.state.syllabus,
            duration: this.state.duration
        }
     
            try{
                const response = await axios.put(DATABASE_URL+COURSE_URL+"/update", course);
                if(response.data != null){
                    alert("Course updated successfully");
                    console.log(response.data);
                }	
            } catch(error) {
                alert(error);
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
                <Card.Header>Register Course</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.register} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                            <Form.Group className="mb-3" controlId="coursename">
                                <Form.Label>Course Name</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test" 
                                    value={this.state.coursename}
                                    disabled={true}
                                    name="coursename"
                                    placeholder="Enter course name" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="prerequisite">
                                <Form.Label>Prerequisite</Form.Label>
                                <Form.Control 
                                    type="text" autoComplete="off" 
                                    value={this.state.prerequisite}
                                    onChange={this.formChange}
                                    name="prerequisite"
                                    placeholder="Enter prerequisite" />
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
                                    placeholder="Enter syllabus" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="duration">
                                <Form.Label>Duration</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" 
                                    value={this.state.duration}
                                    onChange={this.formChange}
                                    name="duration"
                                    placeholder="Enter duration" />
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

export default EditCourseDetails;