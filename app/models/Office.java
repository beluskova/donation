// a new model created for story11
package models;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Office extends Model
{
  public String title;
  public String description;
  public String phone;
 /* @OneToMany
  public List<Candidate> candidates;*/

  public Office(String title, String description, String phone)
  {
    this.title = title;
    this.description = description; 
    this.phone = phone;
  }

  public static Office findByTitle(String title)
  {
    return find("title", title).first();
  }
}