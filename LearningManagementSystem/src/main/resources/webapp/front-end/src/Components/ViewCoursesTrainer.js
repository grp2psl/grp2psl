import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import ReactStars from 'react-stars';
import {TRAINER_URL} from '../constants';

class ViewCoursesTrainer extends React.Component{
    constructor(props){
        super(props);
        this.state={
            courses: [],
            msg:"",
            comment:[],
            rating:[]
        };
    }

    async showData(){
		this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
      let id = localStorage.getItem('userId');

      let url = "http://localhost:8080/LearningManagementSystem/trainers/"+ id +"/coursestaughtbytrainer";
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
			alert("Operation failed!");
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
                <Card.Header>Courses</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Course Id</th>
                                <th>Course Name</th>
                                <th>Course Rating</th>
                                <th>Course Feedback</th>

                            </tr>
                        </thead>
                        <tbody>
                            {this.state.courses.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Course Available.</td>
                                </tr>
                                ) : (
                                this.state.courses.map((course) => (
                                    <tr key={course.tcId}>
                                    <td>{course.courseId}</td>
                                    <td>{course.course.courseName}</td>
                                    <td><ReactStars
                                                      count={5}
                                                      value={course.rating}
                                                      size={20}
                                                      edit={false}
                                                      color2={'#ffd700'}
                                                  /></td>
                                                <td><ButtonGroup>
                                                <Button
                                                    size="sm"
                                                    variant="outline-primary"
                                                    onClick={()=>{
                                                        this.props.history.push({
                                                            pathname: TRAINER_URL+"/feedback",
                                                            state: {
                                                                id: localStorage.getItem('userId'),
                                                                tcid: course.tcId
                                                            },
                                                        })
                                                    }}
                                                >View Feedback</Button>
                                                </ButtonGroup></td>
                                      <td>
                                                               <ButtonGroup>
                                                               <Button
                                                                   size="sm"
                                                                   variant="outline-primary"
                                                                   onClick={()=>{
                                                                       this.props.history.push({
                                                                           pathname: TRAINER_URL+"/course",
                                                                           state: {
                                                                               courseId: course.courseId,
                                                                           },
                                                                       })
                                                                   }}
                                                               >View Details</Button>
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

export default ViewCoursesTrainer;
