package controllers;

import play.*;
import play.mvc.*;

public class Welcome extends Controller
{
  /**
   *  A method to display the landing page (initial home page)
   */
  
  public static void index()
  {
    Logger.info("Landed in Welcome class");
    render();
  }

}