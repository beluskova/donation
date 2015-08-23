package controllers;

import java.util.List;

import play.*;
import play.mvc.*;
import models.*;

public class Accounts extends Controller
{
  /**
   * A method to call the signup page whenever a signup button is clicked-on.
   */
  public static void signup()
  {
/*    story09: obsolete code
 * List<Candidate> candidates = Candidate.findAll();     //story06
    render(candidates);                                   //story06  
*/  
    render();  
  }

  /**
   * A method to register a new member who wants to register on the signup page
   * (view). All parameters are saved in the database for a new user, a user's id is
   * automatically added to the user's details in the database. A new
   * session is established and a new (just registered) user is redirected to
   * the Donation page.
   * 
   * @param usCitizen
   *          boolean true when the user is a usCitizen
   * @param firstName
   *          any String, mandatory, must be filled in
   * @param lastName
   *          any String
   * @param email
   *          any String, mandatory, must be filled in
   * @param password
   *          any String, mandatory, must be filled in, must be at least 6
   *          characters long
   */
  //story01: age and state added here
  //story05: the register form has changed
  //story06: candidate added
  public static void register (User user,String candidateEmail)
      {
       /*  story05: obsolete code 
    * Logger.info("New user: " + usCitizen + " " + firstName + " " + lastName +  " " + age + " " + email + " " + password + " "         
                + state);
    User user = new User(usCitizen, firstName, lastName, age, email, password, state);*/
   /* story09: obsolete code
    * Candidate candidate = Candidate.findByEmail(candidateEmail);
    user.addCandidate(candidate);                //story06
    */   
    if (user.state == null)                      //to search for users who are not registered in any US State
    {
      user.state = "Not US Citizen";
    }
    user.save();                                 //story06
    Logger.info("Registration successful");
    session.put("logged_in_userid", user.id);
    DonationController.index();
  }
  
  /**
   * A method loads the login page whenever the login button is clicked-on.
   */
  public static void login()
  {
    render();
  }

  /**
   * A method logout will log out the logged-in user. The session established
   * when the user had logged in is closed (cleared) and the user is redirected
   * to the home page.
   */
  public static void logout()
  {
    session.clear();
    Logger.info("User logged out");
    Welcome.index();
  }

  /**
   * A method authenticate is called when a user wants to login with a
   * registered email address and a matching password (= login credentials). 
   * The email address is sought with a helping method (findByEmail), which is
   * introduced in a User model. Also a helping method checkPassword is
   * introduced in a User model. Once both helping methods return correct
   * respond, this means that the user exists and his typed-in login credentials are
   * stored in a database. A new user's session is established and a logged-in
   * user is redirected to the Donation page. If the login credentials don't
   * match the details in the database, the non-logged-in user is redirected to
   * the home (welcoming) page.
   * 
   * @param email
   *          String saved in a database
   * @param password
   *          String saved in a database
   */
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);
    User user = User.findByEmail(email); // a helping method introduced in a
                                         // User model
    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Successfull authentication of  " + user.firstName + " " + user.lastName);
      session.put("logged_in_userid", user.id);
      DonationController.index();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }
}