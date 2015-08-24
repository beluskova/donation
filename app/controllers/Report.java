/**
 * A new class created for Story09, report page moved from DonationController to a separate class/view
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Candidate;
import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class Report extends Controller
{
  /**
   * This method displays the report page for the logged-in user only. When the
   * page is displayed, the method goes through the list of all donations and
   * displays them all. For the non-logged-in user the login page comes up.
   */
  public static void index()
  {
    String userId = session.get("logged_in_userid");            //to prevent from the non-logged-in users to be able see the Report
    if (userId != null)
    {
      List<Donation> donations = Donation.findAll();
      List<Candidate> candidates = Candidate.findAll();
      List<User> users = User.findAll();
      Logger.info("Displaying all donations");
      render("Report/index.html", users, donations, candidates);
    }
    else
    {
      Logger.info("Can't display the donations - user not logged in");
      Accounts.login();
    }
  }

  /**
   * A method to display the report page for the chosen Donor (user) only. The
   * report iterates through the list of all donations, but only the donations
   * made from chosen Donor (separated by the if condition) are displayed. The
   * chosen Donor is identified by his Email, which is set as an input value in
   * the initial dropdown (filter) menu on the initial Report page. The
   * resulting Report page for the chosen Donor (user) is displayed.
   */
  public static void filterDonors(String email)
  {
    String userId = session.get("logged_in_userid");            //to prevent from the non-logged-in users to be able see the Report
    if (userId != null)
    {
      List<Candidate> candidates = Candidate.findAll();
      List<User> users = User.findAll();
      List<Donation> donations = new ArrayList();
      List<Donation> allDonations = Donation.findAll();
      User user = User.findByEmail(email);
      for (Donation donation : allDonations)
      {
        if (donation.from.email == user.email)
        {
          donations.add(donation);
        }
      }
      Logger.info("Displaying list of all donations from  " + user.firstName + " " + user.lastName);
      render("Report/index.html", users, donations, candidates);
    }
    else
    {
      Logger.info("Can't display the donation from donors - user not logged in");
      Accounts.login();
    }
  }

  /**
   * A method to display the report page for the chosen State only. The report
   * iterates through the list of all donations, but only the donations made
   * from chosen State (separated by the if condition) are displayed. The chosen
   * State is identified by user.state, which is set as an input value in the
   * initial dropdown (filter) menu on the initial Report page. The resulting
   * Report page of all donations from the chosen State is displayed.
   */
  public static void filterStates(String state)
  {
    String userId = session.get("logged_in_userid");            //to prevent from the non-logged-in users to be able see the Report
    if (userId != null)
    {
      List<Candidate> candidates = Candidate.findAll();
      List<User> users = User.findAll();
      List<Donation> donations = new ArrayList();
      List<Donation> allDonations = Donation.findAll();
      User user = User.findByState(state);
      for (Donation donation : allDonations)
      {
        if (donation.from.state == user.state)
        {
          donations.add(donation);
        }
      }
      Logger.info("Displaying list of all donations from:  " + user.state);
      render("Report/index.html", users, donations, candidates);
    }
    else
    {
      Logger.info("Can't display the donation from state - user not logged in");
      Accounts.login();
    }
  }
}