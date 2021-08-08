import './App.css';

import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import NavigationBar from './Components/NavigationBar';
import Welcome from './Components/Welcome';
import Register from './Components/Register';
import ShowTrainers from './Components/ShowTrainers';
import ViewCourses from './Components/ViewCourses';
import CourseRegister from './Components/CourseRegister';
import TeacherCourseMappingRegister from './Components/TeacherCourseMappingRegister';
import CourseAttended from './Components/CourseAttended';
import { Col, Container, Row } from 'react-bootstrap';

function App() {
  return (
    <div className="App">
    <Router>
      <NavigationBar/>
      <Container>
        <Row>
          <Col lg={12}>
          <Switch>
              <Route path="/" exact component={Welcome}/>
              <Route path="/register" exact component={Register}/>
              <Route path="/show" exact component={ShowTrainers}/>
              <Route path="/viewCourse" exact component={ViewCourses}/>
              <Route path="/registerCourse" exact component={CourseRegister}/>
              <Route path="/TeacherCourseMappingRegister" exact component={TeacherCourseMappingRegister}/>
              <Route path="/ShowCourseAttended" exact component={CourseAttended}/>
              
          </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    </div>
  );
}

export default App;
