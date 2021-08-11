import './App.css';
import NavigationBar from './components/NavigationBar';
import CourseOfferingListing from './components/CourseOfferingListing';
import Feedback from './components/Feedback'
import Welcome from './components/Welcome'
import Credentials from './components/Credentials'
import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router,Switch, Route} from 'react-router-dom';
import Footer from './components/Footer';

function App() {
  return (
    <Router>
    	<NavigationBar/>
    	<Container>
    		<Row>
    			<Col lg={12}>
    			<Switch>
    				<Route path = "/CourseOfferingListing/:learnerid" component = {CourseOfferingListing}/>
    				<Route path = "/Feedback" exact component = {Feedback}/>
    				<Route path = "/Credentials" exact component = {Credentials}/>
    				<Route path = "/" exact component = {Welcome}/>
    			</Switch>
    			</Col>
    		</Row>
    	</Container>
    		<Footer/>
    </Router>
  );
}

export default App;
