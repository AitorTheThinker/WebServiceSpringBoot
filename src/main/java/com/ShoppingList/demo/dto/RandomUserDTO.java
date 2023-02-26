package com.ShoppingList.demo.dto;

import java.util.ArrayList;
import java.util.Date;

public class RandomUserDTO {

	public class Coordinates{
	    public String latitude;
	    public String longitude;
	}

	public class Dob{
	    public Date date;
	    public int age;
	}

	public class Id{
	    public String name;
	    public String value;
	}

	public class Info{
	    public String seed;
	    public int results;
	    public int page;
	    public String version;
	}

	public class Location{
	    public Street street;
	    public String city;
	    public String state;
	    public String country;
	    public Object postcode;
	    public Coordinates coordinates;
	    public Timezone timezone;
	}

	public class Login{
	    public String uuid;
	    public String username;
	    public String password;
	    public String salt;
	    public String md5;
	    public String sha1;
	    public String sha256;
	}

	public class Name{
	    public String title;
	    public String first;
	    public String last;
	}

	public class Picture{
	    public String large;
	    public String medium;
	    public String thumbnail;
	}

	public class Registered{
	    public Date date;
	    public int age;
	}

	public class Result{
	    public String gender;
	    public Name name;
	    public Location location;
	    public String email;
	    public Login login;
	    public Dob dob;
	    public Registered registered;
	    public String phone;
	    public String cell;
	    public Id id;
	    public Picture picture;
	    public String nat;
	}

	public class Root{
	    public ArrayList<Result> results;
	    public Info info;
	}

	public class Street{
	    public int number;
	    public String name;
	}

	public class Timezone{
	    public String offset;
	    public String description;
	}

	
}
