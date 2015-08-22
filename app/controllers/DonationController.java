package controllers;

import play.*;
import play.db.jpa.GenericModel;
import play.mvc.*;

import java.util.*;

import models.*;

public class DonationController extends Controller
{
  /**
   * A method calls the donation page only when the user is logged in. Otherwise
   * is the user redirected to the login page. This method also calls a helping
   * method getPercentTargetAchieved() so the actual (up-to-date) state of a
   * progress bar is displayed.
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
      List<Candidate> candidates = Candidate.findAll(); // story09
      // Logger.info("Donation controller: user is " + user.email); //displaying twice
      // String donationprogress = getPercentTargetAchieved();
      // render(user, donationprogress); // the actual progress is displayed with a help of a progress bar
      render(user, candidates);
    }
  }

  /**
   * This method registers the actual amount the logged in user wishes to
   * donate. The method is called when user clicks the Donate button.
   */
  // story07: donation for candidate only added here
  public static void donate(long amountDonated, String methodDonated, String candidateEmail)
  {
    String userId = session.get("logged_in_userid");
    if (userId != null)
    {
      User user = User.findById(Long.parseLong(userId));
      Candidate candidate = Candidate.findByEmail(candidateEmail); // story09
      //Candidate candidate = user.candidate;                      //story07 
      addDonation(user, amountDonated, methodDonated, candidate); // story07
      Logger.info("amount donated: " + amountDonated + " " + "method donated: " + methodDonated + "\n for candidate: "
          + candidate.candidateFirstName + " " + candidate.candidateLastName);
      // getPercentTargetAchieved(); // calling a helping method to display the progress bar correctly
      index();
    }
    else
    {
      Logger.info("Donation class: Unable to find User");
      Accounts.login();
    }
  }

  /**
   * A helping method to register a single donation. Parameters are user, the
   * amount they wish to donate and a method used to donate.
   */
  // story07: candidate added here
  private static void addDonation(User user, long amountDonated, String methodDonated, Candidate candidate)
  {
    Donation bal = new Donation(user, amountDonated, methodDonated, candidate);
    bal.save();
  }

  // story09: obsolete methods:
  /*  *//**
   * A helping method to set the target amount.
   */
  /*
   * private static long getDonationTarget() { return 20000; }
   */
  /**
   * This method calculates the progress percent of the total amount to be
   * achieved. Initially it iterates through the list of all donations and adds
   * up the donated amount (using a for loop) and then it calculates the
   * percentage of the target that is to be achieved. The result is then
   * returned as a String.
   * 
   */
  /*
   * //story07: report for candidate only added here public static String
   * getPercentTargetAchieved(String candidateEmail) { String userId =
   * session.get("logged_in_userid"); User user =
   * User.findById(Long.parseLong(userId)); Candidate candidate =
   * user.candidate; //story07 List<Donation> allDonations = Donation.findAll();
   * long total = 0; for (Donation donation : allDonations) { if
   * (donation.candidate == candidate) //story07 { total +=
   * donation.amountDonated; } } long target = getDonationTarget(); long
   * percentachieved = (total * 100 / target); String progress =
   * String.valueOf(percentachieved); Logger.info(progress +
   * " percent achieved for candidate: " + candidate.candidateFirstName + " " +
   * candidate.candidateLastName); return progress; }
   *//**
   * This method displays the report page for the logged-in user only. When
   * the page is displayed, the method goes through the list of all donations
   * and displays them all. For the non-logged-in user the login page comes up.
   */
  /*
   * //story07: report for candidate only added here public static void
   * renderReport() { String userId = session.get("logged_in_userid"); if
   * (userId != null) { User user = User.findById(Long.parseLong(userId));
   * List<Donation> donations = Donation.findAll(); List<Candidate> candidates =
   * Candidate.findAll(); List<User> users = User.findAll(); // following added
   * for story07: Candidate candidate = user.candidate; for (Donation donation :
   * allDonations) { if (donation.candidate == candidate) {
   * donations.add(donation); } }
   * Logger.info("Displaying all donors for candidate " +
   * candidate.candidateFirstName + " " + candidate.candidateLastName);
   * render("DonationController/renderReport.html", users, donations,
   * candidates); } else { Logger.info("User not logged in"); Accounts.login();
   * } }
   */
}
