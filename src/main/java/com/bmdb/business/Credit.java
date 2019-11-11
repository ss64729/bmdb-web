package com.bmdb.business;

import javax.persistence.*;

@Entity
public class Credit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	private int movieid;
	@ManyToOne
	@JoinColumn(name="MovieID")
	private Movie movie;
	@ManyToOne
	@JoinColumn(name="ActorID")
	private Actor actor;
//	private int actorid;
	private String role;

	public Credit() {
		super();
	}

	
	public Credit(int id, Movie movie, Actor actor, String role) {
//	public Credit(int id, int movieid, int actorid, String role) {
		super();
		this.id = id;
	//	this.movieid = movieid;
		this.movie =movie;
		this.actor = actor;
	//	this.actorid = actorid;
		this.role = role;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	//public int getMovieid() {
	//	return movieid;
//	}

//	public void setMovieid(int movieid) {
	//	this.movieid = movieid;
//	}

//	public int getActorid() {
	//	return actorid;
//	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	//public void setActorid(int actorid) {
	//	this.actorid = actorid;
	//}


	@Override
	public String toString() {
	//	return "Credit [id=" + id + ", movieid=" + movieid + ", actorid=" + actorid + ", role=" + role + "]";
	return "Credit [id=" + id + ", movie=" + movie + ", actor=" + actor + ", role=" + role + "]";
	}

}
