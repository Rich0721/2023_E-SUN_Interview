package com.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.demo.demo.employee;
import com.demo.demo.seatInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;


@RestController
@EnableAutoConfiguration
public class DemoApplication {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "123456";

	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/getAllInfo")
	List<employee> getAllInfo() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		// List<JSONObject> entities = new ArrayList<JSONObject>();
		List<employee> contacts = new ArrayList<employee>();

		Class.forName(JDBC_DRIVER);

		// connect mysql
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT b.FLOOR_SEAT_SEQ, b.FLOOR_NO, b.SEAT_NO, a.EMP_ID, a.NAME, a.EMAIL ";
		sql = sql + "FROM demo.seatingchart as b  LEFT JOIN ( ";
		sql = sql + "SELECT *  FROM demo.employee ) a on a.FLOOR_SEAT_SEAQ = b.FLOOR_SEAT_SEQ ";
		ResultSet rs = stmt.executeQuery(sql);
	
		// 展开结果集数据库
		while (rs.next()) {

			String employId = rs.getString("EMP_ID");
			String name = rs.getString("NAME");
			String email = rs.getString("EMAIL");
			String seat_id = rs.getString("FLOOR_SEAT_SEQ");
			int floor_no = rs.getInt("FLOOR_NO");
			int seat_no = rs.getInt("SEAT_NO");
			contacts.add(new employee(employId, name, email, seat_id, floor_no, seat_no));
		}

		rs.close();
		stmt.close();
		conn.close();
		return contacts;// new ResponseEntity<Object>(entities, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/getSeatInfo")
	List<seatInfo> getSeatInfo() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		// List<JSONObject> entities = new ArrayList<JSONObject>();
		List<seatInfo> contacts = new ArrayList<seatInfo>();

		Class.forName(JDBC_DRIVER);

		// connect mysql
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM seatingchart";
		ResultSet rs = stmt.executeQuery(sql);
		
		// 展开结果集数据库
		while (rs.next()) {

			String seat_id = rs.getString("FLOOR_SEAT_SEQ");
			int floor_no = rs.getInt("FLOOR_NO");
			int seat_no = rs.getInt("SEAT_NO");
			contacts.add(new seatInfo(seat_id, floor_no, seat_no));
		}

		rs.close();
		stmt.close();
		conn.close();
		return contacts;// new ResponseEntity<Object>(entities, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/updateSeat/{id}/{seat}")
	String updateSeat(@PathVariable String id, @PathVariable String seat) throws ClassNotFoundException, SQLException{
		Connection conn = null;
		Statement stmt = null;
		Class.forName(JDBC_DRIVER);

		// connect mysql
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		sql = "UPDATE `demo`.`employee` SET `FLOOR_SEAT_SEAQ` = '" + seat + "'WHERE `EMP_ID`='"+ id + "'";
		stmt.executeUpdate(sql);
		return "OK";
	}

	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/insertInfo/{id}/{name}/{email}/{seat}")
	String updateSeat(@PathVariable String id, @PathVariable String name, @PathVariable String email, @PathVariable String seat) throws ClassNotFoundException, SQLException{
		Connection conn = null;
		Statement stmt = null;
		Class.forName(JDBC_DRIVER);

		// connect mysql
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql;
		sql = "INSERT INTO `demo`.`employee` (`EMP_ID`, `NAME`, `EMAIL`, `FLOOR_SEAT_SEAQ`) VALUES ( ";
		sql = sql + "'" + id + "','"  + "','" + email + "','" + seat + "') "; 
		stmt.executeUpdate(sql);
		return "OK";
	}

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
