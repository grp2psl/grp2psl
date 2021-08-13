import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";
import {DATABASE_URL,MANAGER_URL,COURSE_URL} from '../constants'

class ViewCourses extends React.Component{
    constructor(props){
        super(props);
        this.state={
            courses: [],
            msg:""
        };
    }
    
    async showData(){
		this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
			const response = await axios.get(DATABASE_URL+COURSE_URL+"/allcourses/");
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
    
     async deleteData(id){
		this.setState({
			msg:"Processing.. Please Wait",
            show: true
		});
		try{
            const response = await axios.delete(DATABASE_URL+COURSE_URL+"/course/delete/"+id);
            console.log(response);
		} catch(error) {
			alert(error);
		}
        this.setState({
			msg: ""
		})
        this.showData();
    }
    
     editDetails(course){
        this.props.history.push({
            pathname: MANAGER_URL+'/editCourseDetails',
            state: { course: course }
        });
     }

    componentDidMount(){
        this.showData();
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white mt-5"}>
                <Card.Header>Courses</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Course Id</th>
                                <th>Course Name</th>
                                <th>Prerequisite</th>
                                <th>Syllabus</th>
                                <th>Duration</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.courses.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Course Available.</td>
                                </tr>
                                ) : (
                                this.state.courses.map((course) => (
                                    <tr key={course.courseId}>
                                    <td>{course.courseId}</td>
                                    <td>{course.courseName}</td>
                                    <td>{course.prerequisite}</td>
                                    <td>{<a target='_blank' href={course.syllabus}>Click here to see syllabus</a>}</td>
                                    <td>{course.duration}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={() => this.editDetails(course)}
                                        >
                                            <FontAwesomeIcon icon={faEdit} />
                                        </Button>{" "}
                                        <Button
                                            size="sm"
                                            variant="outline-danger"
                                            onClick={() => this.deleteData(course.courseId)}
                                        >
                                            <FontAwesomeIcon icon={faTrash} />
                                        </Button>
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

export default ViewCourses;