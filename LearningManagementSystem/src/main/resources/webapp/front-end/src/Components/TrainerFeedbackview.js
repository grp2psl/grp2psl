import React from 'react';

import {Card, Table} from 'react-bootstrap';
import axios from 'axios';
// import  { Redirect } from 'react-router-dom';

class TrainerFeedbackview extends React.Component{
  constructor(props){
    super(props);
    this.state={
      comments: [],
      ratings: 0,
      msg:""
    };
  }

  async showData(){
    this.setState({
      msg:"Processing.. Please Wait"
    });
    try{
      let id = this.props.location.state.id;
      let tcid = this.props.location.state.tcid;
      let fburl = "http://localhost:8080/LearningManagementSystem/trainers/" + id + "/" +tcid;
      let fbres = await axios.get(fburl, {
        auth: {
          username: localStorage.getItem("username"),
          password: localStorage.getItem("password")
        }
      });
      // data[i]['feedback'] = fbres.data;
      console.log("comments 1");
      console.log(fbres.data.comments);
      // if(fbres.data.comments != null) {
        console.log("inside if");
        this.setState({
             comments: fbres.data.comments,
             ratings: fbres.data.ratings
        });
      // }
      console.log("comments 2");
      console.log(this.state.comments);
      console.log(this.state.ratings);
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
        <Card.Header>Course</Card.Header>
        <h3 className="text-white mt-2">{this.state.msg}</h3>
        <Card.Body>
          <Table striped bordered hover variant="dark">
            <thead>
              <tr>
                <th>Comments</th>
              </tr>
            </thead>
            <tbody>
              {this.state.comments.length === 0? (
                <tr align="center">
                  <td colSpan="7">No Comments Available.</td>
                </tr>
              ) : (
                this.state.comments.map((comment) => (
                  <tr>
                    <td>{comment}</td>
                  </tr>
                ))
              )
            }

          </tbody>
        </Table>
      </Card.Body>
    </Card>
  );
}
}

export default TrainerFeedbackview;

// <th>Prerequisite</th>
// <th>Syllabus</th>
// <th>Duration</th>




// <td>{course.coursename}</td>
// <td>{course.prerequisite}</td>
// <td>{<a target='_blank' href={course.syllabus}>Click here to see syllabus</a>}</td>
// <td>{course.duration}</td>
