import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";

class CourseAttended extends React.Component{
	
    constructor(props){
		
        super(props);
        this.state={
            courses: [],
            id : 0,
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
					courses: response.data
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

    render(){
        return(
	
            <Card className={"border border-dark bg-dark text-white mt-5"}>
                <Card.Header>Course Attended</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                            	<th>Learner ID</th>
                                <th>Learner Name</th>
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>Trainer Name</th>
                                <th>Percentage</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.courses.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Courses Attended.</td>
                                </tr>
                                ) : (
                                this.state.courses.map((course) => (
                                    <tr key={course.learnerid}>
                                    <td>{course.learnerid}</td>
                                    <td>{course.name}</td>
                                    <td>{course.courseid}</td>
                                    <td>{course.coursename}</td>
                                    <td>{course.trainername}</td>
                                    <td>{course.percentage}</td>
                                    <td>{course.status}</td>
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