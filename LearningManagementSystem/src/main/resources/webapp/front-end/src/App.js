// import './App.css';

// import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

// // import NavigationBar from './Components_Managers/NavigationBar';
// // import Welcome from './Components_Managers/Welcome';
// // import EnrollLearner from './Components_Managers/EnrollLearner';
// // import Register from './Components_Managers/RegisterTrainer';
// // import RegisterTrainers from './Components_Managers/RegisterTrainers';
// // import RegisterMultipleTrainers from './Components_Managers/RegisterMultipleTrainers';
// // import ShowTrainers from './Components_Managers/ShowTrainers';
// // import ViewCourses from './Components_Managers/ViewCourses';
// // import CourseRegister from './Components_Managers/CourseRegister';
// // import TeacherCourseMappingRegister from './Components_Managers/TeacherCourseMappingRegister';
// // import CourseAttended from './Components_Managers/CourseAttended';
// // import RegisterLearner from './Components_Managers/RegisterLearner';
// // import RegisterLearners from './Components_Managers/RegisterLearners';
// // import RegisterMultipleLearners from './Components_Managers/RegisterMultipleLearners';
// // import ShowLearners from './Components_Managers/ShowLearners';

// import NavigationBar from './Components_Learners/NavigationBar';

// import CourseOfferingListing from './Components_Learners/CourseOfferingListing';
// import Feedback from './Components_Learners/Feedback';
// import Welcome from './Components_Learners/Welcome';
// import Credentials from './Components_Learners/Credentials';
// import Footer from './Components_Learners/Footer';

// import { Col, Container, Row } from 'react-bootstrap';

// function App() {
//   return (
//     <div className="App">
//     <Router>
//       <NavigationBar/>
//       <Container>
//         <Row>
//           <Col lg={12}>
//           <Switch>
//               {/* <Route path="/" exact component={Welcome}/>
//               <Route path="/enroll" exact component={EnrollLearner}/>
//               <Route path="/registerTrainer" exact component={Register}/>
//               <Route path="/showTrainers" exact component={ShowTrainers}/>
//               <Route path="/viewCourse" exact component={ViewCourses}/>
//               <Route path="/registerCourse" exact component={CourseRegister}/>
//               <Route path="/TeacherCourseMappingRegister" exact component={TeacherCourseMappingRegister}/>
//               <Route path="/ShowCourseAttended" exact component={CourseAttended}/>
//               <Route path="/registerLearner" exact component={RegisterLearner}/>
//               <Route path="/showLearners" exact component={ShowLearners}/>
//               <Route path="/registerTrainers" exact component={RegisterTrainers}/>
//               <Route path="/registerMultipleTrainers" exact component={RegisterMultipleTrainers}/>
//               <Route path="/registerLearners" exact component={RegisterLearners}/>
//               <Route path="/registerMultipleLearners" exact component={RegisterMultipleLearners}/> */}
//               <Route path = "/CourseOfferingListing/:learnerid" component = {CourseOfferingListing}/>
//               <Route path = "/Feedback/:courseofferingid" component = {Feedback}/>
//               <Route path = "/Credentials/:learnerid" exact component = {Credentials}/>
//               <Route path = "/" exact component = {Welcome}/>
//           </Switch>
//           </Col>
//         </Row>
//       </Container>
//     </Router>
//     </div>
//   );
// }

// export default App;

import './App.css';
import NavigationBar from './Components_Learners/NavigationBar';
import CourseOfferingListing from './Components_Learners/CourseOfferingListing';
import Feedback from './Components_Learners/Feedback'
import Welcome from './Components_Learners/Welcome'
import Credentials from './Components_Learners/Credentials'
import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router,Switch, Route} from 'react-router-dom';
import Footer from './Components_Learners/Footer';

export default function App() {
  return (
    <Router>
    	<NavigationBar/>
    	<Container>
    		<Row>
    			<Col lg={12}>
    			<Switch>
    				<Route path = "/CourseOfferingListing/:learnerid" component = {CourseOfferingListing}/>
    				<Route path = "/Feedback/:courseofferingid" component = {Feedback}/>
    				<Route path = "/Credentials/:learnerid" exact component = {Credentials}/>
    				<Route path = "/" exact component = {Welcome}/>
    			</Switch>
    			</Col>
    		</Row>
    	</Container>
    		<Footer/>
    </Router>
  );
}
