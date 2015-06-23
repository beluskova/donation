import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;
import play.db.jpa.Blob;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException
  {

      Fixtures.deleteDatabase();
      Fixtures.loadModels("data.yml");
 //     Logger.info("Bootsrap runs");
 //     User user = new User(true, "firstName", "lastName", "email", "password");
 //     user.save();
    
  }
}