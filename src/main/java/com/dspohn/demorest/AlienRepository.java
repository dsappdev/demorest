package com.dspohn.demorest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
	
	List<Alien> aliens;
	Connection con = null;
	
	/**
	 * Constructor sets up the connection to a MySQL database
	 */
	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
//			if(con != null) {
//				System.out.println("Database successfully connected!");
//			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Returns all the aliens 
	 * 
	 * @return a list of aliens as an Array List
	 */
	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien";
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while(rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				aliens.add(a);
			}
		
		} catch(Exception e) {
			System.out.println(e);
		}
		return aliens;
	}
	
	/**
	 * Return a single alien
	 * 
	 * @param id the alien's id as type int
	 * @return an alien matching the id
	 */
	public Alien getAlien(int id) {		
		Alien alien = new Alien();
		String sql = "select * from alien where id=" + id;
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			if(rs.next()) {				
				alien.setId(rs.getInt(1));
				alien.setName(rs.getString(2));
				alien.setPoints(rs.getInt(3));

			}
		
		} catch(Exception e) {
			System.out.println(e);
		}
		return alien;
	}
	
	/**
	 * Adds a new alien 
	 * 
	 * @param a1 
	 */
	public void create(Alien a1) {
		String sql = "insert into alien values(?,?,?)";
		
		try {
			PreparedStatement smt = con.prepareStatement(sql);					
			smt.setInt(1, a1.getId());
			smt.setString(2, a1.getName());
			smt.setInt(3, a1.getPoints());
			smt.executeUpdate();

		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Updates the record of an existing alien. If the
	 * alien does not exist then it will create a new 
	 * record.
	 * 
	 * 
	 * @param a1
	 */
	public void update(Alien a1) {
		String sql = "update alien set name=?, points=? where id=?";
		
		try {
			PreparedStatement smt = con.prepareStatement(sql);
			
			smt.setString(1, a1.getName());
			smt.setInt(2, a1.getPoints());
			smt.setInt(3, a1.getId());
						
			smt.executeUpdate();

		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletes an alien
	 * 
	 * @param id
	 */
	public void delete(int id) {
		String sql = "delete from alien where id=?";
		
		try {
			PreparedStatement smt = con.prepareStatement(sql);						
			smt.setInt(1, id);						
			smt.executeUpdate();

		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
}
