import './App.css';

import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import NavigationBar from './Components/NavigationBar';
import Welcome from './Components/Welcome';
import EnrollLearner from './Components/EnrollLearner';
import EnrollLearners from './Components/EnrollLearners';
import EnrollMultipleLearners from './Components/EnrollMultipleLearners';
import Register from './Components/RegisterTrainer';
import RegisterTrainers from './Components/RegisterTrainers';
import RegisterMultipleTrainers from './Components/RegisterMultipleTrainers';
import ShowTrainers from './Components/ShowTrainers';
import ViewCourses from './Components/ViewCourses';
import CourseRegister from './Components/CourseRegister';
import TeacherCourseMappingRegister from './Components/TeacherCourseMappingRegister';
import CourseAttended from './Components/CourseAttended';
import CoursesOffered from './Components/CoursesOffered';
import ViewFeedback from './Components/ViewFeedback';
import RegisterLearner from './Components/RegisterLearner';
import RegisterLearners from './Components/RegisterLearners';
import RegisterMultipleLearners from './Components/RegisterMultipleLearners';
import ShowLearners from './Components/ShowLearners';
import EditTrainerDetails from './Components/EditTrainerDetails';
import EditLearnerDetails from './Components/EditLearnerDetails';
import EditCourseDetails from './Components/EditCourseDetails';
import UpdateScore from './Components/UpdateScore';
import UpdateScoreIndividual from './Components/UpdateScoreIndividual';
import UpdateScoreMultiple from './Components/UpdateScoreMultiple';
import { Col, Container, Row } from 'react-bootstrap';
import MultipleTeacherCourseMappingRegister from './Components/MultipleTeacherCourseMappingRegister';
import TeacherCourseMappingsRegister from './Components/TeacherCourseMappingsRegister';

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
              <Route path="/registerTrainer" exact component={Register}/>
              <Route path="/showTrainers" exact component={ShowTrainers}/>
              <Route path="/viewCourse" exact component={ViewCourses}/>
              <Route path="/registerCourse" exact component={CourseRegister}/>
              <Route path="/TeacherCourseMappingRegister" exact component={TeacherCourseMappingRegister}/>
              <Route path="/ShowCourseAttended" exact component={CourseAttended}/>
              <Route path="/registerLearner" exact component={RegisterLearner}/>
              <Route path="/showLearners" exact component={ShowLearners}/>
              <Route path="/registerTrainers" exact component={RegisterTrainers}/>
              <Route path="/registerMultipleTrainers" exact component={RegisterMultipleTrainers}/>
              <Route path="/registerLearners" exact component={RegisterLearners}/>
              <Route path="/registerMultipleLearners" exact component={RegisterMultipleLearners}/>
              <Route path="/editTrainerDetails" exact component={EditTrainerDetails}/>
              <Route path="/editLearnerDetails" exact component={EditLearnerDetails}/>
              <Route path="/editCourseDetails" exact component={EditCourseDetails}/>
              <Route path="/enrollLearners" exact component={EnrollLearners}/>
              <Route path="/enrollLearner" exact component={EnrollLearner}/>
              <Route path="/enrollMultipleLearners" exact component={EnrollMultipleLearners}/>
              <Route path="/viewCoursesOffered" exact component={CoursesOffered} />
              <Route path="/viewFeedback" exact component={ViewFeedback} />
              <Route path="/MultipleTeacherCourseMappingRegister" exact component={MultipleTeacherCourseMappingRegister}/>
              <Route path="/TeacherCourseMappingsRegister" exact component={TeacherCourseMappingsRegister}/>
		      <Route path="/update-scores" exact component={UpdateScore}/>
              <Route path="/update-score" exact component={UpdateScoreIndividual}/>
              <Route path="/update-score-multiple" exact component={UpdateScoreMultiple}/>

          </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    </div>
  );
}

export default App;
