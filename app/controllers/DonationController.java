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
        	
        	List<Donation> donations = Donation.findAll();
        	
        	render (user);
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
    
 

}
