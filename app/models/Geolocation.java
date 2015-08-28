// a new model created for story13
package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;
import utils.LatLng;

@Entity
public class Geolocation extends Model
{
  public double latitude;
  public double longitude;
  @OneToOne
  public User user;
  
  public Geolocation (User user, double latitude, double longitude)
  {
    this.user = user;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}

