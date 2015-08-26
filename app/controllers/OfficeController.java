/**
 * A new class created for Story09, report for Candidate moved from DonationController to create a new (separate) view
 */
package controllers;

import java.util.ArrayList;
import java.util.List;





import models.Candidate;
import models.Donation;
import models.Office;
import models.User;
import play.*;
import play.mvc.*;

import org.json.simple.JSONObject;



public class OfficeController extends Controller
{
  /**
   * A method to redirect the logged-in user to the page where he can change
   * some of his details. The helping method render(user) will bring up the
   * logged-in user's details that are already saved in a database.
   * If the user is not logged in, the redirection to login page will follow.
   */
  public static void index()
  {
    String adminId = session.get("logged_in_adminid");                             // to display page to logged-in administrator only
    if (adminId == null)
    {
      Logger.info("Office creator: Administrator not logged-in");
      Welcome.index();
    }
    else
    {
      Logger.info("Administrator: Create a new office");
      render();
    }
  }
  
  /**
   * A method to register new Office. The method checks first whether Office is already registered,
   * considering the office title is a unique primary key.
   * @param office - Office object to be registered
   */
  public static void createOffice (Office office)
  {
    String adminId = session.get("logged_in_adminid");                          // to display page to logged-in administrator only
    if (adminId != null)
    {
      String title = office.title;
      Office office1 = Office.findByTitle(title);
      if (office1 != null)                                                     //checking whether Office already exists
      {
        Logger.info("Office " + office1.title + " already registered");
        index();
      }
    else
    {
      Logger.info("A new office " + office.title + " created");
      office.save();
      Administrator.index();
    }
  }
}
}
