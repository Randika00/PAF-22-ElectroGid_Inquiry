package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inquiry {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget_clientside", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readInquiry() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Inquiry Name</th>" + "<th>Inquiry Acc</th><th>Inquiry Reason</th>"
					+ "<th>Inquiry Date</th>" + "<th>Inquiry pNo</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from inquiry";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String inquiryID = Integer.toString(rs.getInt("inquiryID"));
				String inquiryName = rs.getString("inquiryName");
				String inquiryAcc = rs.getString("inquiryAcc");
				String inquiryReason = rs.getString("inquiryReason");
				String inquiryDate = rs.getString("inquiryDate");
				String inquirypNo = Integer.toString(rs.getInt("inquirypNo"));

				// Add into the html table

				output += "<tr><td><input id='hidinquiryIDUpdate' name='hidinquiryIDUpdate' type='hidden' value='"
						+ inquiryID + "'>" + inquiryName + "</td>";

				output += "<td>" + inquiryAcc + "</td>";
				output += "<td>" + inquiryReason + "</td>";
				output += "<td>" + inquiryDate + "</td>";
				output += "<td>" + inquirypNo + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerID='"
						+ inquiryID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Inquiry Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert Inquiry
	public String insertInquiry(String inquiryName, String inquiryAcc, String inquiryReason,
			String inquiryDate , String inquirypNo ) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into Inquiry (`inquiryID`,`inquiryName`,`inquiryAcc`,`inquiryReason`,`inquiryDate`,`inquirypNo`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, inquiryName);
			preparedStmt.setString(3, inquiryAcc);
			preparedStmt.setString(4, inquiryReason);
			preparedStmt.setString(5, inquiryDate);
			preparedStmt.setString(5, inquirypNo);

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newInquiry = readInquiry();
			output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Inquiry.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update Inquiry
	public String updateInquiry(String inquiryID, String inquiryName, String inquiryAcc, String inquiryReason,
			String inquiryDate, String inquirypNo) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE inquiry SET inquiryName=?,inquiryAcc=?,inquiryReason=?,inquiryDate=? WHERE inquiryID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, inquiryName);
			preparedStmt.setString(2, inquiryAcc);
			preparedStmt.setString(3, inquiryReason);
			preparedStmt.setString(3, inquiryDate);
			preparedStmt.setInt(4, Integer.parseInt(inquirypNo));
			preparedStmt.setInt(5, Integer.parseInt(inquiryID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newInquiry = readInquiry();
			output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Inquiry Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteInquiry(String inquiryID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM inquiry WHERE inquiryID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(inquiryID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newInquiry = readInquiry();
			output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Inquiry.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}

}
