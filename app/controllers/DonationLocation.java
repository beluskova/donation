/**
 * A new class created for Story15, display location of donors to Administrator
 */
package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.Candidate;
import models.Donation;
import models.Office;
import models.User;
import play.*;
import play.mvc.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DonationLocation extends Controller
{
  /**
   * A method to redirect the logged-in Administrator to the page where he can
   * see the location of all donors on the map. If the Administrator is not
   * logged in, the redirection to welcome page will follow.
   */
  public static void index()
  {
    String adminId = session.get("logged_in_adminid"); // to display page to logged-in administrator only
    if (adminId == null)
    {
      Logger.info("Donor Location: Administrator not logged-in");
      Welcome.index();
    }
    else
    {
      Logger.info("Displaying geolocations of all donors");
      render();
    }
  }

  /**
   * A method to display all Geolocations of users on the map (their first names
   * and coordinates). The method goes through list of all users, selecting only
   * their first name and coordinates to the new JSON array list.
   * 
   */
  public static void listGeolocations()
  {
    {
      JSONArray listGeolocations = new JSONArray();
      List<User> users = User.findAll();
      for (User user : users)
      {
        listGeolocations.add(Arrays.asList(user.firstName, user.latitude, user.longitude));
      }
      renderJSON(listGeolocations);
    }
  }
}
