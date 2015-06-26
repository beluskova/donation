package controllers;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class DonationController extends Controller 
{

    public static void index() 
    {
    	String userId = session.get("logged_in_userid");
    	User user = User.findById(Long.parseLong(userId));
        if (user != null)
        {
        	Logger.info("Donation controller: user is " + user.email);     	
        	String donationprogress = getPercentTargetAchieved();
        	render(user, donationprogress);
        }
        else
        {
        	Logger.info ("Donation class: Unable to find User");
        	Accounts.login();
        }   
    }
    
    public static void donate (long amountDonated, String methodDonated)
    {
    	String userId = session.get("logged_in_userid");
    	User user = User.findById(Long.parseLong(userId));
        if (user != null)
        {
        	addDonation (user, amountDonated, methodDonated);
        	Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
        	getPercentTargetAchieved();
        	index();
        }
        else
        {
        	Logger.info("Donation class: Unable to find User");
        	Accounts.login();
        }    
    }
    
    private static void addDonation (User user, long amountDonated, String methodDonated)
    {
    	Donation bal = new Donation (user, amountDonated, methodDonated);
    	bal.save();
    }
    
    private static long getDonationTarget()
    {
    	return 20000;
    }
    
    public static String getPercentTargetAchieved()
    {
    	List<Donation> allDonations = Donation.findAll();
    	long total = 0;
    	for (Donation donation : allDonations)
    	{
    		total += donation.amountDonated;
    	}
    	long target = getDonationTarget();
    	long percentachieved = (total*100/target);
    	String progress = String.valueOf(percentachieved);
    	Logger.info( progress + " percent achieved ");
    	return progress;
    }
    
    public static void renderReport()
    {
    	String userId = session.get("logged_in_userid");
    	User user = User.findById(Long.parseLong(userId));
        if (user != null)
        {
    	List <Donation> donations = Donation.findAll();
    	render(donations);
    	Logger.info("Displaying all donors");
        }
    	else
        {
        	Logger.info("User not logged in");
        	Accounts.login();
        } 
    }
  }

