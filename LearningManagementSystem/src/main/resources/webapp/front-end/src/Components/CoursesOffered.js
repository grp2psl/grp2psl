import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';

class CoursesOffered extends React.Component{
	
    constructor(props){
		
        super(props);
        this.state={
            trainerDetails: [],
            courses: [],
            offerings: [],
            msg:""
        };
    }
    
    async showData(){
		
		this.setState({
			msg:"Processing.. Please Wait"
		});
		const id = this.props.location.state.id;
		try{
			const response = await axios.get("http://localhost:8080/LearningManagementSystem/managers/trainer/"+id);
			if(response.data != null) {
				this.setState({
                    trainerDetails: response.data.trainerDetails,
                    courses: response.data.courses,
                    offerings: response.data.offerings                    
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
                <Card.Header>Courses Offered by {this.state.trainerDetails.name}</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>Number of Learners Enrolled</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.courses.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Courses Offered.</td>
                                </tr>
                                ) : (
                                this.state.courses.map((course) => (
                                    <tr key={course.courseid}>
                                    <td>{course.courseid}</td>
                                    <td>{course.coursename}</td>
                                    <td>{this.state.offerings[course.courseid].length}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={()=>{
                                                this.props.history.push({
                                                    pathname: "/viewFeedback",
                                                    state: {
                                                        courseId: course.courseid,
                                                        trainerId: this.state.trainerDetails.trainerid
                                                    },
                                                })
                                            }}
                                        >View feedback and ratings</Button>
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

export default CoursesOffered;