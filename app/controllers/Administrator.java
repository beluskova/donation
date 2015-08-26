/**
 * A new class created for Story10: Administrator's account
 */
package controllers;

import java.util.List;
import org.json.simple.JSONObject;
import play.*;
import play.mvc.*;
import models.*;

public class Administrator extends Controller
{
  /**
   * A method index in this class is called when the Administrator logges in
   * with the correct details. Once the login details are successfully verified,
   * the index() method displays the Administrator's Home page along with the
   * details of all Donors and all Candidates in two separate tables.
   */
  public static void index()
  {
    String adminId = session.get("logged_in_adminid"); // to display page to logged-in administrator only
    if (adminId != null)
    {
      List<Donation> donations = Donation.findAll();
      List<Candidate> candidates = Candidate.findAll();
      List<User> users = User.findAll();
      List<Office> offices = Office.findAll();                                          //story11
      render("Administrator/index.html", users, donations, candidates, offices);
    }
    else
    {
      Logger.info("Admin authentication failed");
      login();
    }
  }

  /**
   * A method loads the Administrator login page whenever the login button is
   * clicked-on.
   */
  public static void login()
  {
    Logger.info("Administrator log-in page");
    render();
  }

  /**
   * A method logout will log out the logged-in Administrator. The session
   * established when the Administrator had logged in is closed (cleared) and
   * the user is redirected to the home page.
   */
  public static void logout()
  {
    session.clear();
    Logger.info("Administrator logged out");
    Welcome.index();
  }

  /**
   * A method authenticate is called when the Administrator wants to login with
   * the exact email address (==admin) and a matching password (==secret). The
   * amidn name is sought with a helping method (findByEmail), which is
   * introduced in an Admin model. Also a helping method checkPassword is
   * introduced in an Admin model. Once both helping methods return correct
   * respond, a new Amin's session is established and a logged-in Administrator
   * is redirected to his Adminitrator-Home page. If the Admin name doesn't
   * match the exact Administrator's details, error message will come up (with a
   * help of a JavaScript method), if the password field is not empty
   * (JavaScript validation), but the password is not correct, the person is
   * redirected to the empty Administrator log-in page.
   * 
   * 
   * @param adminEmail
   *          String saved in a database for the Administrator
   * @param adminPassword
   *          String saved in a database for the Administrator
   */
  public static void authenticate(String adminEmail, String adminPassword)
  {
    Logger.info("Attempting to authenticate with " + adminEmail + " : " + adminPassword);
    Admin admin = Admin.findByEmail(adminEmail); // a helping method introduced in an Admin model to look for admin's details in the database
    if ((adminEmail != null) && (admin.checkPassword(adminPassword) == true))
    {
      Logger.info("Successfull authentication of Administrator");
      session.put("logged_in_adminid", admin.id);
      Administrator.index();
    }
    else
    {
      Logger.info("Authentication of Administrator failed");
      login();
    }
  }

  /**
   * A method to load the page where new Candidate is created
   */
  public static void candidate()
  {
    String adminId = session.get("logged_in_adminid");                  // to display page to logged-in administrator only
    if (adminId != null)
    {
      List<Office> offices = Office.findAll();
      Logger.info("Administrator: Register a new Candidate");
      render("Administrator/registerCandidate.html", offices);
    }
    else
    {
      Logger.info("Admin authentication failed");
      login();
    }
  }

  /**
   * A method to register new Candidate along with his Office. The method checks
   * first whether Candidate is already registered, considering the email
   * address is a primary key.
   * 
   * @param candidate
   *          Candidate object to be registered
   * @param title
   *          Office object to be added to Candidate, identified by it's title
   */
  public static void registerCandidate(Candidate candidate, String title)
  {
    String adminId = session.get("logged_in_adminid");                 // to display page to logged-in administrator only
    if (adminId != null)
    {
      Candidate candidate1 = Candidate.findByEmail(candidate.candidateEmail);
      if (candidate1 != null)                                         //checking whether Candidate already exists
      {
        Logger.info("Candidate " + candidate.candidateFirstName + " " + candidate.candidateLastName
                   + " is already registered");
        index();
      }
      else
      {
        Office office = Office.findByTitle(title);
        candidate.addOffice(office);
        Logger.info("Succesful registration of new Candidate: " + candidate.candidateFirstName + " "
                     + candidate.candidateLastName);
        candidate.save();
        index();
      }
    }
    else
    {
      Logger.info("Admin authentication failed");
      login();
    }
  }
}