import './App.css';

import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import NavigationBar from './Components/NavigationBar';
import Welcome from './Components/Welcome';
import EnrollLearner from './Components/EnrollLearner';
import Register from './Components/RegisterTrainer';
import RegisterTrainers from './Components/RegisterTrainers';
import RegisterMultipleTrainers from './Components/RegisterMultipleTrainers';
import ShowTrainers from './Components/ShowTrainers';
import ViewCourses from './Components/ViewCourses';
import CourseRegister from './Components/CourseRegister';
import RegisterLearner from './Components/RegisterLearner';
import RegisterLearners from './Components/RegisterLearners';
import RegisterMultipleLearners from './Components/RegisterMultipleLearners';
import ShowLearners from './Components/ShowLearners';
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
              <Route path="/enroll" exact component={EnrollLearner}/>
              <Route path="/registerTrainer" exact component={Register}/>
              <Route path="/showTrainers" exact component={ShowTrainers}/>
              <Route path="/viewCourse" exact component={ViewCourses}/>
              <Route path="/registerCourse" exact component={CourseRegister}/>
              <Route path="/registerLearner" exact component={RegisterLearner}/>
              <Route path="/showLearners" exact component={ShowLearners}/>
              <Route path="/registerTrainers" exact component={RegisterTrainers}/>
              <Route path="/registerMultipleTrainers" exact component={RegisterMultipleTrainers}/>
              <Route path="/registerLearners" exact component={RegisterLearners}/>
              <Route path="/registerMultipleLearners" exact component={RegisterMultipleLearners}/>
          </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    </div>
  );
}

export default App;
