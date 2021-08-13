import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";

const MANAGER_URL = process.env.REACT_APP_MANAGER_URL;

class CourseAttended extends React.Component{
	
    constructor(props){
		
        super(props);
        this.state={
            courseOffering: [],
            msg:""
        };
    }
    
    async showData(){
		
		this.setState({
			msg:"Processing.. Please Wait"
			
		});
		const id = this.props.location.state.detail;
		try{
			const response = await axios.get("http://localhost:8080/LearningManagementSystem/managers/course-attended/"+id);
			if(response.data != null) {
				console.log(response.data)
				this.setState({
					
					courseOffering: response.data 
				});	
				console.log(this.state.courseOffering)
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

    render(){
        return(
	
            <Card className={"border border-dark bg-dark text-white mt-5"}>
                <Card.Header>Course Attended</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                            	
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>Syllabus</th>
                                <th>Trainer Name</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.courseOffering.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Courses Attended.</td>
                                </tr>
                                ) : (
                                this.state.courseOffering.map((course) => (
                                    <tr key={course.offerings.courseOfferingId}>
                                
                                    <td>{course.courses.courseId}</td>
                                    <td>{course.courses.courseName}</td>
                                  

                                    <td>{<a target='_blank' href={course.courses.syllabus} className="text-white">{course.courses.courseName}</a>}</td>
                                    <td>{course.trainers.name}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={()=>{
                                                this.props.history.push({
                                                    pathname: MANAGER_URL+"/viewScoreAndStatus",
                                                    state: {
                                                        courseId: course.courses.courseId,
                                                        learnerId: course.learners.learnerId
                                                    },
                                                })
                                            }}
                                        >View Score and Status</Button>
                                        </ButtonGroup>
                                    </td>
                                    </tr>
                                ))
                                )}

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default CourseAttended;