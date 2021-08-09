import React, {Component } from 'react'
import {Container, Row, Col, Card, Button, Form} from 'react-bootstrap';
import Select from 'react-select'
import axios from 'axios'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";

export default class TeacherCourseMappingRegister extends Component {

  constructor(props){
    super(props)
    this.state = {
      trainerList :[],
	
      courseList : [],
      trainerId: "",
      courseId : "",
      trainerName:"",
      courseName:""
    }
    this.getOptions = this.getOptions.bind(this);
    this.teacherCourseMapping = this.teacherCourseMapping.bind(this);
  
  }

 async getOptions() {
    const trainerResponse = await axios.get("http://localhost:8080/LearningManagementSystem/trainers/")
    const trainerData = trainerResponse.data

    const tranierOptions = trainerData.map(d => ({
      "value" : d.trainerid,
      "label" : d.name

    }))

    this.setState({trainerList: tranierOptions})
    
    const courseResponse = await axios.get("http://localhost:8080/LearningManagementSystem/course/allcourses")
    const courseData = courseResponse.data

    const courseOptions = courseData.map(d => ({
      "value" : d.courseid,
      "label" : d.coursename

    }))
	
    this.setState({courseList: courseOptions})

  }

  handleChangeTrainer(e){
   this.setState({trainerId:e.value, trainerName:e.label})
  }
  
  
  handleChangeCourse(e){
   this.setState({courseId:e.value, courseName:e.label})
   
  }

 async teacherCourseMapping (event) {
	
	event.preventDefault();
	const trainerCourseMapping = {
            trainerId: parseInt(this.state.trainerId,10),
            courseId: parseInt(this.state.courseId,10)
        }
        console.log(trainerCourseMapping)
        console.log(typeof(this.state.trainerId))
	try{
			const response = await axios.post("http://localhost:8080/LearningManagementSystem/teacherCourseMapping/register", trainerCourseMapping);
			if(response.data != null){
	        	alert("Trainer assigned to course successfully");
	            console.log(response.data);
        	}	
		} catch(error) {
			alert(error);
		}
    
  }
  componentDidMount(){
      this.getOptions()
  }

  render() {
 
    return (
	  <div className="mt-5">
            <Card className={"border border-dark bg-dark"}>
                <Card.Header className={"text-white"}>Register Trainer</Card.Header>
                
      <form onSubmit={this.teacherCourseMapping}>
      <Card.Body>
            <Container>
                 <Row>
        <label className={"text-white"}>
          Select Trainer :
          <div>
        <Select className={"text-dark"} options={this.state.trainerList} onChange={this.handleChangeTrainer.bind(this)} />
    <p>You have selected <strong>{this.state.trainerName}</strong> whose id is <strong>{this.state.trainerId}</strong></p>
      </div>
        </label>
         </Row>
   		<Row>
        <label className={"text-white"}>
          Select Course :
          <div>
        <Select className={"text-dark"} options={this.state.courseList} onChange={this.handleChangeCourse.bind(this)} />
    <p>You have selected <strong>{this.state.courseName}</strong> whose id is <strong>{this.state.courseId}</strong></p>
      </div>
        </label>
        </Row>
         </Container>
                        
                </Card.Body>
        
           <Card.Footer style={{"text-align":"right"}}>
         <Button variant="success" type="submit">
                        <FontAwesomeIcon icon={faSave} />{" "}
                        Submit
                    </Button>{" "}
          </Card.Footer>
      </form>
       </Card>
      </div>
    )
  }
}

<Form.Select aria-label="Default select example">
  <option>Open this select menu</option>
  <option value="1">One</option>
  <option value="2">Two</option>
  <option value="3">Three</option>
</Form.Select>
