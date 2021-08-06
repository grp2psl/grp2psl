/*
 * Entity declaration of Learner 
 */

// This shows that learner is declared in com.psl.entities package
package com.psl.entities;

// Necessary imports for entity declaration
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * This entity represents an employee who can take part in various courses
 * in the Learning Management System.
 * Learner is identified by a unique and non-null integer learnerId.
 * A Learner record contains various attributes other than learnerId 
 * which are name, phoneNumber, department, email and password.
 * Getter, setter and constructors both with and without parameters are declared.
 * toString method is overridden to display instance of the class appropriately.    
 * */

//Annotation to recognize learner as an Entity
@Entity
//Name of the MySql table in which Learner Entity records are stored
@Table(name = "learner")
//Definition of Learner Entity which inherits the user class
public class Learner extends User {
	
	/*learnerId attribute is the primary key. 
	 *stored in learnerid column in the table.
	 *Compulsory for learnerId to be non-empty.
	 *Minimun value is 1 because it can not be 0 or negative.  
	*/
	@Id
	@Column(name = "learnerid")
	@NotNull(message = "LearnerId field can't be empty")
	@Min(value = 1, message = "LearnerId field should be non zerp")
	private int learnerId;
	
	
	/*
	 * Name attribute stores name of learner in a string format 
	 * with maximum limit on length of the string at 50.
	 */
	@NotNull(message = "Name field can't be empty")
	@Size(max = 50, message = "Length of name can't be more than 50 characheters!")
	private String name;
	
	/*
	 * phoneNumber attribute stores contact number of the learner in string format
	 * Attribute stored in phonenumber column of the table
	 */
	@Size(max = 15, message = "Length of phoneNumber can't be more than 15 characheters!")	
	@Column(name = "phonenumber")
	private String phoneNumber;
	
	/*
	 * department attribute stores department name of the learner in string format 
	 * Maximum permitted length of the string is 50 characters
	 */
	@Size(max = 50, message = "Length of department can't be more than 50 characheters!")
	private String department;
	
	/*
	 * Stores email id of learner 
	 * It must be unique and of 50 characters at max
	 * It is compulsory because required during login
	 */
	@Size(max = 50, message = "Length of email can't be more than 50 characheters!")
	@NotNull(message = "email field can't be empty")
	@Email
	private String email;
	
	/*
	 * Stores password of the learner in 25 characters at max
	 * It is compulsory because required during login
	 */
	@Size(max = 25, message = "Length of password can't be more than 25 characheters!")
	@NotNull(message = "password field can't be empty")
	private String password;
	
	/*
	 * Public getter and setter methods of all attributes
	 */
	public int getLearnerId() {
		return learnerId;
	}
	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Overrides toString function to change output of print function
	@Override
	public String toString() {
		return "Learner [learnerId=" + learnerId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", department="
				+ department + ", email=" + email + ", password=" + password + "]";
	}
	
	//Parameterized Constructor
	public Learner(int learnerId, String name, String phoneNumber, String department, String email, String password) {
		super();
		this.learnerId = learnerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.email = email;
		this.password = password;
	}
	
	//Default Constructor
	public Learner() {
		super();
	}
}
