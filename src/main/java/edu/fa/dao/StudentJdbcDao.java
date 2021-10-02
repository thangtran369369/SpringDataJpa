package edu.fa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.fa.model.Student;

@Component
public class StudentJdbcDao {
	private String jdbcUrl = "jdbc:derby://localhost:1527/education;create=true";
	private Connection connection = null;
	private Statement statement = null;

	public void insertRestaurants(Student student) {
		createConnection();
		try {
			statement = connection.createStatement();
			statement.execute("insert into student values (" + student.getId() + ",'" + student.getName() + 
									"','" + student.getLocation() + "')");
			statement.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	private void createConnection() {
		if (connection == null) {
			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
				// Get a connection
				connection = DriverManager.getConnection(jdbcUrl);
			} catch (Exception except) {
				except.printStackTrace();
			}
		}
	}

	private void shutdown() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				DriverManager.getConnection(jdbcUrl + ";shutdown=true");
				connection.close();
			}
		} catch (SQLException sqlExcept) {

		}

	}
	
	
	public List<Student> getAllStudent()
    {
		createConnection();
		List<Student> students = new ArrayList<Student>();
        try
        {
        	statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from student");
            ResultSetMetaData rsmd = results.getMetaData();        
            while(results.next())
            {
                int id = results.getInt(1);
                String name = results.getString(2);
                String location = results.getString(3);
                students.add(new Student(id, name, location));
            }
            results.close();
            statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return students;
    }
}
