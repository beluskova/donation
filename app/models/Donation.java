package models;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import play.db.jpa.Model;
import play.db.jpa.Blob;

@Entity
public class Donation extends Model
{
	public long amountDonated;
	public String methodDonated;
	@ManyToOne
    public User from;

public Donation (User from, long amountDonated, String methodDonated)
{
	this.amountDonated = amountDonated;
	this.methodDonated = methodDonated;
	this.from = from;
}
}
