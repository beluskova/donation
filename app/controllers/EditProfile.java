/**
 * a new Java class created for Story03: edit profile
 */
package controllers;

import play.*;
import play.mvc.*;
import models.*;

public class EditProfile extends Controller
{
  /**
   * A method to redirect the logged-in user to the page where he can change
   * some of his details. The helping method render(user) will bring up the
   * logged-in user's details that are already saved in a database.
   * If the user is not logged in, the redirection to login page will follow.
   */
  public static void index()
  {
    String userId = session.get("logged_in_userid");
    if (userId == null)
    {
      Logger.info("Donation class: Unable to find User");
      Accounts.login();
    }
    else
    {
      User user = User.findById(Long.parseLong(userId));
      render(user);
    }
  }
  
  /**
   * A method to edit user's details once the user changes their information in
   * the form provided on views/EditProfile/index.html Even though the method
   * constructor takes a User model as a whole, only the four of the logged-in
   * user's details are changed (as in a form), following the JavaScript script
   * (edit.js). Thus the details are only changed when something else (other
   * than already saved details) is typed-in. Once the new details are saved,
   * the user is redirected to the Donation page.
   */
  //story01: age and state added here
  //story05: addrLine1, addrLine2, city, zipcode added here
  public static void edit(boolean usCitizen, String firstName, String lastName, String age, String email,
                          String password, String state, String addrLine1, String addrLine2, String city, String zipcode)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    user.firstName = firstName;
    user.lastName = lastName;
    user.age = age;                  //story01
    user.state = state;              //story01 
    user.addrLine1 = addrLine1;      //story05
    user.addrLine2 = addrLine2;      //story05
    user.city = city;                //story05
    user.zipcode = zipcode;          //story05
    user.save();
    Logger.info("Editing successful");
    DonationController.index();
  }
}