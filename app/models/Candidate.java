// a new model created for story06
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Candidate extends Model
{
  public String candidateFirstName;
  public String candidateLastName;
  public String candidateEmail;
  @OneToMany
  public List<User> users;
  @OneToMany
  public List<Donation> donations;

  public Candidate(String candidateFirstName, String candidateLastName, String candidateEmail)
  {
    this.candidateFirstName = candidateFirstName;
    this.candidateLastName = candidateLastName;
    this.candidateEmail = candidateEmail;
  }

  public static Candidate findByEmail(String candidateEmail)
  {
    return find("candidateEmail", candidateEmail).first();
  }
}