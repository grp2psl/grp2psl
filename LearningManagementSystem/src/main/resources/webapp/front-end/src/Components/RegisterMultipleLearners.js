import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faDownload,
    faUpload
  } from "@fortawesome/free-solid-svg-icons";
import { DATABASE_URL, LEARNER_URL, ADMIN_USERNAME, ADMIN_PASSWORD } from '../constants';

const cardStyle={
	height: '250px',
	marginTop: '30px',
    position:'relative'
};
const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};
class RegisterMultipleLearners extends React.Component{	
    constructor(props){
        super(props);
        this.state={
            msg: " ",
            file: null,
            showUpload: false
        };
        this.download = this.download.bind(this);
        this.registerLearners = this.registerLearners.bind(this);
    }
    
	componentWillMount(){
        if(localStorage.getItem('user') != 'manager' || localStorage.getItem('loggedin') === false){
            alert("User not logged in!");
            return this.props.history.push("/");
        }
    }
    async download(){
		this.setState({
			msg:"Downloading..Please Wait"
		});
		try{
			const response = await axios.get(DATABASE_URL+LEARNER_URL+"/generate-excel", {
                auth: {
                  username: ADMIN_USERNAME,
                  password: ADMIN_PASSWORD
                }
              });
			if(response.data != null) {
				this.setState({
                    msg: "Downloading completed. Check your Downloads folder.."
				});	
			}	
		} catch(error) {
			alert(error);
            this.setState({
                msg: "Downloading failed.."
            });	
		}
    }

    onFileChange = event => {
        this.setState({ file: event.target.files[0] });
      
    };

    async registerLearners(){
        this.setState({
            showUpload: true
		});
        if(this.state.file != null){
            this.setState({
                msg:"Processing..\nPlease Wait"
            });
            const formData = new FormData();
        
            formData.append(
                "file",
                this.state.file
            );
        
            console.log(this.state.file);
            
            const basicAuth = 'Basic ' + btoa(ADMIN_USERNAME + ':' + ADMIN_PASSWORD);

            try{
                const response = await axios.post(DATABASE_URL+LEARNER_URL+"/register-multiple", formData, {
                    auth: {
                    username: ADMIN_USERNAME,
                    password: ADMIN_PASSWORD
                  },
                  'content-type': 'multipart/form-data'
                });
                console.log(response)
                if(response.data != null){
                    alert("Learners registered successfully");
                    console.log(response.data);
                }	
            } catch(error) {
                alert(error.response.data);     
            }
            this.setState({
                msg: "",
                file: null,
                showUpload: false
            });
        }
    }

    render(){
        return(
			<div className="mt-5">
                <Container>
                        <h1 className="text-white">{this.state.msg}</h1>
                        <Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}> 
						        <Card.Body>
						          <Card.Title>Download File</Card.Title>
						          <Card.Text>
						            Click on button to download the format of excel file
						          </Card.Text>                                  
                                    <Button 
                                    size="lg"
                                    variant="outline-success" 
                                    style={buttonStyle}
                                    onClick={this.download}><FontAwesomeIcon icon={faDownload} />{" "}Download</Button>
                                  
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Upload File</Card.Title>
						          <Card.Text>
						            Click on button to upload the excel file.
                                    If you don't know the format of file to be uploaded, download first.
						          </Card.Text>
                                  {this.state.showUpload && (
                                      <input type="file" onChange={this.onFileChange} />
                                  )}
                                  
                                    <Button 
                                    size="lg"
                                    variant="outline-primary" 
                                    style={buttonStyle}
                                    onClick={this.registerLearners}><FontAwesomeIcon icon={faUpload} />{" "}Upload</Button>
                                 
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                </Container>
            </div>);
    }
}

export default RegisterMultipleLearners;