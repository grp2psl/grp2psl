import React, { Component } from 'react'
import Select from 'react-select'
import axios from 'axios'

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
      <form onSubmit={this.teacherCourseMapping}>
        <label>
          Select Trainer :
          <div>
        <Select options={this.state.trainerList} onChange={this.handleChangeTrainer.bind(this)} />
    <p>You have selected <strong>{this.state.trainerName}</strong> whose id is <strong>{this.state.trainerId}</strong></p>
      </div>
        </label>
        
        <label>
          Select Course :
          <div>
        <Select options={this.state.courseList} onChange={this.handleChangeCourse.bind(this)} />
    <p>You have selected <strong>{this.state.courseName}</strong> whose id is <strong>{this.state.courseId}</strong></p>
      </div>
        </label>
        <input type="submit" value="Submit" />
      </form>
    )
  }
}
