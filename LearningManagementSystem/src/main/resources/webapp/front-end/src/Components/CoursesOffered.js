import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import {DATABASE_URL, MANAGER_URL} from '../constants';

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
    
	componentWillMount(){
        if(localStorage.getItem('user') !== 'manager' || localStorage.getItem('loggedin') === false){
            alert("User not logged in!");
            return this.props.history.push("/");
        }
   }
    async showData(){
		
		this.setState({
			msg:"Processing.. Please Wait"
		});
		const id = this.props.location.state.id;
		try{
			const response = await axios.get(DATABASE_URL+MANAGER_URL+"/trainer/"+id, {
                auth: {
                    username: localStorage.getItem("username"),
                    password: localStorage.getItem("password")
                }
              });
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
                                    <tr key={course.courseId}>
                                    <td>{course.courseId}</td>
                                    <td>{course.courseName}</td>
                                    <td>{this.state.offerings[course.courseId].length}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={()=>{
                                                this.props.history.push({
                                                    pathname: MANAGER_URL+"/feedback",
                                                    state: {
                                                        courseId: course.courseId,
                                                        trainerId: this.state.trainerDetails.trainerId
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