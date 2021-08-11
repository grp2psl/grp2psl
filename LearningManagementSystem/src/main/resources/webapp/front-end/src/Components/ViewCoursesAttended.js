import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';

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
            const response = await axios.get("http://localhost:8080/LearningManagementSystem/managers/courses-attended/"+id);
			if(response.data != null) {
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
                                    <td colSpan="10">No Courses Attended.</td>
                                </tr>
                                ) : (
                                this.state.courses.map((course) => (
                                    <tr key={course.offering.courseofferingid}>
                                    <td>{course.learner.learnerid}</td>
                                    <td>{course.learner.name}</td>
                                    <td>{course.course.courseid}</td>
                                    <td><a href={course.course.syllabus} className="text-white"  style={{ textDecoration: 'none' }}>{course.course.coursename}</a></td>
                                    <td>{course.trainer.name}</td>
                                    <td>{course.offering.percentage}</td>
                                    <td>{course.offering.status}</td>
                                    <td></td>
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