package controllers;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Accounts extends Controller 
{

    public static void index() 
    {
        render();
    }
    public static void signup()
    {
    	render();
    }
    
    public static void register(boolean usCitizen, String firstName, String lastName, String email, String password)
    	  {
    	    if (email != null && email.length() == 0 || email == "")
    	    {
    	      Logger.info("No email provided, user not registered");
    	      Accounts.index();
    	    }
    	    else
    	      if (firstName != null && firstName.length() == 0 || firstName == "")
    	      {
    	        Logger.info("No first name provided, user not registered");
    	        Accounts.index();
    	      }
    	      else
    	        if (password != null && password.length() == 0 || password == "")
    	        {
    	          Logger.info("No password provided, user not registered");
    	          Accounts.index();
    	        }
    	        else
    	        {
    	          Logger.info(usCitizen+ " " + firstName + " " + lastName + " " + email + " " + password + " " );
    	          User user = new User(usCitizen, firstName, lastName, email, password);
    	          user.save();
    	          Logger.info("Registration successful");
    	          session.put("logged_in_userId", user.id);
    	          Accounts.index();
    	        }
    	  }
    
    public static void login()
    {
    	
    	render();
    }
    
    public static void logout()
    {
    	session.clear();
    	Welcome.index();
    }
    
 
    public static void authenticate(String email, String password)
      {
        Logger.info("Attempting to authenticate with " + email + ":"+ password);
        User user = User.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true))
        {
          Logger.info("Successfull authentication of  "+ user.firstName + " "+ user.lastName);
          session.put("logged_in_userId", user.id);
          Accounts.index();
        }
    else
        {
          Logger.info("Authentication failed");
          login();
        }
      }

}