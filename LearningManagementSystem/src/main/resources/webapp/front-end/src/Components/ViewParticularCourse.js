import React from 'react';

import {Card, Table} from 'react-bootstrap';
import axios from 'axios';

class ViewParticularCourse extends React.Component{
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
      // let id = this.props.location.state.detail;
      // let id=localStorage.getItem('userId');
      let courseId = this.props.location.state.courseId;
      let url = "http://localhost:8080/LearningManagementSystem/trainers/course/"+ courseId;
      console.log(url)
			const response = await axios.get(url, {
          auth: {
            username: localStorage.getItem("username"),
            password: localStorage.getItem("password")
        }
      });
			if(response.data != null) {
				this.setState({
					courses: response.data
				});
			}
		} catch(error) {
			alert(error);
            console.log(localStorage.getItem("username"))
            console.log(localStorage.getItem("password"))
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
                <Card.Header>Course</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Course Id</th>
                                <th>Course Name</th>
                                <th>Prerequisite</th>
                                <th>Syllabus</th>
                                <th>Duration (hrs)</th>

                            </tr>
                        </thead>
                        <tbody>
                            {this.state.courses.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Course Available.</td>
                                </tr>
                                ) : (
                                    <tr>
                                    <td>{this.state.courses.courseId}</td>
                                    <td>{this.state.courses.courseName}</td>
                                    <td>{this.state.courses.prerequisite}</td>
                                    <td>{<a target='_blank' href={this.state.courses.syllabus} className="text-white">{this.state.courses.syllabus}</a>}</td>
                                    <td>{this.state.courses.duration}</td>
                                    </tr>
                                )}

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default ViewParticularCourse;

// <th>Prerequisite</th>
// <th>Syllabus</th>
// <th>Duration</th>




// <td>{course.coursename}</td>
// <td>{course.prerequisite}</td>
// <td>{<a target='_blank' href={course.syllabus}>Click here to see syllabus</a>}</td>
// <td>{course.duration}</td>
