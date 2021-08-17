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
import { useHistory } from "react-router-dom";
import NavigationBarLearner from './Components/NavigationBarLearner';
import CourseOfferingListing from './Components/CourseOfferingListing';
import Feedback from './Components/Feedback'
import WelcomeLearner from './Components/WelcomeLearner'
import WelcomeTrainer from './Components/WelcomeTrainer'
import Credentials from './Components/Credentials'
import UpdateAdminPassword from './Components/UpdateAdminPassword'
import UpdateTrainerPassword from './Components/UpdateTrainerPassword'
import CourseDetails from './Components/CourseDetails'
import ViewCoursesTrainer from './Components/ViewCoursesTrainer'


import Home from './Components/Home'
import ViewDetails from './Components/ViewDetails'

function App() {
  const history = useHistory();

  return (
    <div className="App">
    <Router basename="/LearningManager">
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
              <Route path={MANAGER_URL+"/registerTrainer"} exact component={Register}/>
              <Route path={MANAGER_URL+"/showTrainers"} exact component={ShowTrainers}/>
              <Route path={MANAGER_URL+"/viewCourse"} exact component={ViewCourses}/>
              <Route path={MANAGER_URL+"/registerCourse"} exact component={CourseRegister}/>
              <Route path={MANAGER_URL+"/TeacherCourseMappingRegister"} exact component={TeacherCourseMappingRegister}/>
              <Route path={MANAGER_URL+"/ShowCourseAttended"} exact component={CourseAttended}/>
              <Route path={MANAGER_URL+"/registerLearner"} exact component={RegisterLearner}/>
              <Route path={TRAINER_URL+"/"} exact component={WelcomeTrainer}/>
              <Route path={TRAINER_URL+"/viewparticularcourse"} exact component={ViewParticularCourse}/>
              <Route path={TRAINER_URL+"/TrainerFeedbackview"} exact component={TrainerFeedbackview}/>
              <Route path={MANAGER_URL+"/showLearners"} exact component={ShowLearners}/>
              <Route path={MANAGER_URL+"/registerTrainers"} exact component={RegisterTrainers}/>
              <Route path={MANAGER_URL+"/registerMultipleTrainers"} exact component={RegisterMultipleTrainers}/>
              <Route path={MANAGER_URL+"/registerLearners"} exact component={RegisterLearners}/>
              <Route path={MANAGER_URL+"/registerMultipleLearners"} exact component={RegisterMultipleLearners}/>
              <Route path={MANAGER_URL+"/editTrainerDetails"} exact component={EditTrainerDetails}/>
              <Route path={MANAGER_URL+"/editLearnerDetails"} exact component={EditLearnerDetails}/>
              <Route path={MANAGER_URL+"/editCourseDetails"} exact component={EditCourseDetails}/>
              <Route path={MANAGER_URL+"/enrollLearners"} exact component={EnrollLearners}/>
              <Route path={MANAGER_URL+"/enrollLearner"} exact component={EnrollLearner}/>
              <Route path={MANAGER_URL+"/enrollMultipleLearners"} exact component={EnrollMultipleLearners}/>
              <Route path={MANAGER_URL+"/viewCoursesOffered"} exact component={CoursesOffered} />
              <Route path={MANAGER_URL+"/viewFeedback"} exact component={ViewFeedback} />
              <Route path={MANAGER_URL+"/MultipleTeacherCourseMappingRegister"} exact component={MultipleTeacherCourseMappingRegister}/>
              <Route path={MANAGER_URL+"/TeacherCourseMappingsRegister"} exact component={TeacherCourseMappingsRegister}/>
		      <Route path={MANAGER_URL+"/update-scores"} exact component={UpdateScore}/>
              <Route path={MANAGER_URL+"/update-score"} exact component={UpdateScoreIndividual}/>
              <Route path={MANAGER_URL+"/update-score-multiple"} exact component={UpdateScoreMultiple}/>
              <Route path={MANAGER_URL+"/viewCourseOfferings"} exact component={ViewCourseOfferings}/>
              <Route path={MANAGER_URL+"/viewScoreAndStatus"} exact component={ScoreAndStatus}/>
              <Route path={MANAGER_URL+"/view-details-manager"} exact component={AdminDetails}/>
              <Route path={TRAINER_URL+"/view-details-trainer"} exact component={TrainerDetails}/>
              <Route path={LEARNER_URL+"/view-details-learner"} exact component={LearnerDetails}/>
              <Route path = {LEARNER_URL+"/CourseOfferingListing"} component = {CourseOfferingListing}/>
    		  <Route path = {LEARNER_URL+"/Feedback"} component = {Feedback}/>
    		  <Route path = {LEARNER_URL+"/Credentials"} exact component = {Credentials}/>
    		  <Route path = {MANAGER_URL+"/UpdateAdminPassword"} exact component = {UpdateAdminPassword}/>
    		  <Route path = {TRAINER_URL+"/UpdateTrainerPassword"} exact component = {UpdateTrainerPassword}/>
    		  <Route path={TRAINER_URL+"/ViewCoursesTrainer"} exact component={ViewCoursesTrainer}/>
    		  <Route path = {LEARNER_URL+"/CourseDetails"} exact component = {CourseDetails}/>
    		  <Route path = {LEARNER_URL+"/"} exact component = {WelcomeLearner}/>
          </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    </div>
  );
}

export default App;
