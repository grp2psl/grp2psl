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
import ViewParticularCourse from './Components/ViewParticularCourse';
import ViewCoursesTrainer from './Components/ViewCoursesTrainer';
import UpdateScore from './Components/UpdateScore';
import UpdateScoreIndividual from './Components/UpdateScoreIndividual';
import UpdateScoreMultiple from './Components/UpdateScoreMultiple';
import ViewCourseOfferings from './Components/ViewCourseOfferings';
import ScoreAndStatus from './Components/ScoreAndStatus';
import { Col, Container, Row } from 'react-bootstrap';
import MultipleTeacherCourseMappingRegister from './Components/MultipleTeacherCourseMappingRegister';
import TeacherCourseMappingsRegister from './Components/TeacherCourseMappingsRegister';
import Login from './Components/Login';
import AdminLogin from './Components/AdminLogin';
import LearnerLogin from './Components/LearnerLogin';
import TrainerLogin from './Components/TrainerLogin';
import AdminDetails from './Components/AdminDetails';
import TrainerDetails from './Components/TrainerDetails';
import TrainerFeedbackview from './Components/TrainerFeedbackview';
import LearnerDetails from './Components/LearnerDetails';
import {MANAGER_URL, LEARNER_URL, TRAINER_URL} from './constants';
import CourseOfferingListing from './Components/CourseOfferingListing';
import Feedback from './Components/Feedback'
import WelcomeLearner from './Components/WelcomeLearner'
import Credentials from './Components/Credentials'
import CourseDetails from './Components/CourseDetails'

import Home from './Components/Home'
import ViewDetails from './Components/ViewDetails'

function App() {

  return (
    <div className="App">
    <Router basename="/learning-manager">
      <NavigationBar/>
      <Container>
        <Row>
          <Col lg={12}>
          <Switch>
              <Route path={"/"} exact component={Login}/>
              <Route path={"/home"} exact component={Home}/>
              <Route path={"/admin-login"} exact component={AdminLogin}/>
              <Route path={"/trainer-login"} exact component={TrainerLogin}/>
              <Route path={"/learner-login"} exact component={LearnerLogin}/>
              <Route path={"/view-details"} exact component={ViewDetails}/>
              <Route path={MANAGER_URL+"/"} exact component={Welcome}/>
              <Route path={MANAGER_URL+"/register-trainer"} exact component={Register}/>
              <Route path={MANAGER_URL+"/trainers"} exact component={ShowTrainers}/>
              <Route path={MANAGER_URL+"/courses"} exact component={ViewCourses}/>
              <Route path={MANAGER_URL+"/register-course"} exact component={CourseRegister}/>
              <Route path={MANAGER_URL+"/register-teacher-course-mapping"} exact component={TeacherCourseMappingRegister}/>
              <Route path={MANAGER_URL+"/attended-courses"} exact component={CourseAttended}/>
              <Route path={MANAGER_URL+"/register-learner"} exact component={RegisterLearner}/>
              <Route path={MANAGER_URL+"/learners"} exact component={ShowLearners}/>
              <Route path={MANAGER_URL+"/register-trainers"} exact component={RegisterTrainers}/>
              <Route path={MANAGER_URL+"/register-multiple-trainers"} exact component={RegisterMultipleTrainers}/>
              <Route path={MANAGER_URL+"/register-learners"} exact component={RegisterLearners}/>
              <Route path={MANAGER_URL+"/register-multiple-learners"} exact component={RegisterMultipleLearners}/>
              <Route path={MANAGER_URL+"/edit-trainer-details"} exact component={EditTrainerDetails}/>
              <Route path={MANAGER_URL+"/edit-learner-details"} exact component={EditLearnerDetails}/>
              <Route path={MANAGER_URL+"/edit-course-details"} exact component={EditCourseDetails}/>
              <Route path={MANAGER_URL+"/enroll-learners"} exact component={EnrollLearners}/>
              <Route path={MANAGER_URL+"/enroll-learner"} exact component={EnrollLearner}/>
              <Route path={MANAGER_URL+"/enroll-multiple-learners"} exact component={EnrollMultipleLearners}/>
              <Route path={MANAGER_URL+"/courses-offered"} exact component={CoursesOffered} />
              <Route path={MANAGER_URL+"/feedback"} exact component={ViewFeedback} />
              <Route path={MANAGER_URL+"/register-multiple-teacher-course-mappings"} exact component={MultipleTeacherCourseMappingRegister}/>
              <Route path={MANAGER_URL+"/register-teacher-course-mappings"} exact component={TeacherCourseMappingsRegister}/>
		          <Route path={MANAGER_URL+"/update-scores"} exact component={UpdateScore}/>
              <Route path={MANAGER_URL+"/update-score"} exact component={UpdateScoreIndividual}/>
              <Route path={MANAGER_URL+"/update-multiple-scores"} exact component={UpdateScoreMultiple}/>
              <Route path={MANAGER_URL+"/course-offerings"} exact component={ViewCourseOfferings}/>
              <Route path={MANAGER_URL+"/score-and-status"} exact component={ScoreAndStatus}/>
              <Route path={MANAGER_URL+"/view-details"} exact component={AdminDetails}/>
              <Route path={TRAINER_URL+"/"} exact component={ViewCoursesTrainer}/>
              <Route path={TRAINER_URL+"/course"} exact component={ViewParticularCourse}/>
              <Route path={TRAINER_URL+"/feedback"} exact component={TrainerFeedbackview}/>
              <Route path={TRAINER_URL+"/view-details"} exact component={TrainerDetails}/>
              <Route path={LEARNER_URL+"/view-details"} exact component={LearnerDetails}/>
              <Route path={LEARNER_URL+"/course-offerings"} component = {CourseOfferingListing}/>
              <Route path={LEARNER_URL+"/feedback"} component = {Feedback}/>
              <Route path={LEARNER_URL+"/credentials"} exact component = {Credentials}/>
              <Route path={LEARNER_URL+"/course-details"} exact component = {CourseDetails}/>
              <Route path={LEARNER_URL+"/"} exact component = {WelcomeLearner}/>
          </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    </div>
  );
}

export default App;
