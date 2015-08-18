package models;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Donation extends Model
{
  public long amountDonated;
  public String methodDonated;
  @ManyToOne
  public User from;
  @ManyToOne
  public Candidate candidate;            //story07

  
  public Donation(User from, long amountDonated, String methodDonated, Candidate candidate)
  {
    this.amountDonated = amountDonated;
    this.methodDonated = methodDonated;
    this.from = from;
    this.candidate = candidate;          //story07
  }
}
