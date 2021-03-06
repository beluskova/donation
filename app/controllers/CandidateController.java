/**
 * A new class created for Story09, report for Candidate moved from DonationController to create a new (separate) view
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Candidate;
import models.Donation;
import models.User;
import play.*;
import play.mvc.*;

public class CandidateController extends Controller
{
  /**
   * A method to display the report page for the chosen candidate only. The
   * report iterates through the list of all donations, but only the donations
   * made for chosen candidate (separated by the if condition) are displayed.
   * The chosen candidate is identified by his Email, which is set as an input
   * value in the initial dropdown (filter) menu on the initial Report page. The
   * resulting Report page is then displayed, including a progress bar.
   */

  public static void filterCandidates(String candidateEmail)
  {
    String userId = session.get("logged_in_userid");             //to prevent from the non-logged-in users to be able see the Report
    if (userId != null)
    {
      List<Candidate> candidates = Candidate.findAll();
      List<User> users = User.findAll();
      List<Donation> donations = new ArrayList();
      List<Donation> allDonations = Donation.findAll();
      Candidate candidate = Candidate.findByEmail(candidateEmail);
      for (Donation donation : allDonations)
      {
        if (donation.candidate == candidate)
        {
          donations.add(donation);
        }
      }
      String donationprogress = getPercentTargetAchieved(candidateEmail);
      Logger.info("Displaying list of donations for candidate " + candidate.candidateFirstName + " "
          + candidate.candidateLastName);
      render("CandidateController/index.html", users, donations, candidates, candidate, donationprogress);
    }
    else
    {
      Logger.info("Can't display the donation for candidates - user not logged in");
      Accounts.login();
    }
  }

  /**
   * This method calculates the progress percent of the total amount to be
   * achieved. Initially it iterates through the list of all donations and adds
   * up the donated amount (using a for loop) and then it calculates the
   * percentage of the target that is to be achieved. The result is then
   * returned as a String.
   * 
   */
  public static String getPercentTargetAchieved(String candidateEmail)
  {
    Candidate candidate = Candidate.findByEmail(candidateEmail);
    List<Donation> allDonations = Donation.findAll();
    long total = 0;
    for (Donation donation : allDonations)
    {
      if (donation.candidate == candidate)
      {
        total += donation.amountDonated;
      }
    }
    long target = getDonationTarget(candidateEmail);                     //story12
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    Logger.info(progress + " percent achieved for candidate: " + candidate.candidateFirstName + " "
        + candidate.candidateLastName);
    return progress;
  }

  /**
   * A helping method to set the target amount
   */
  
  //Story12: candidateEmail is added to identify the candidate for whom the target amount is set
  private static long getDonationTarget(String candidateEmail)         
  {
    Candidate candidate = Candidate.findByEmail(candidateEmail);         //story12
    return candidate.target;                                             //story12
  }   

}