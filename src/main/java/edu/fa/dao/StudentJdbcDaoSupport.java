package edu.fa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import edu.fa.model.Student;

@Component
public class StudentJdbcDaoSupport extends JdbcDaoSupport{


	public void insertStudent(Student student) {
		String query = "insert into student values (" + student.getId() + ",'" + student.getName() + 
				"','" + student.getLocation() + "')";
		this.getJdbcTemplate().execute(query);
	}
	
	
	public Student getStudentById(int id) {
		String query = "select * from student where id = ?";
		return this.getJdbcTemplate().queryForObject(query, new Object[] {id}, new StudentMapper());
		
	}
	
	public void deleteStudent() {
		String query = "delete from student";
		this.getJdbcTemplate().execute(query);
	}
	
	
	public int countStudent() {
		String query = "select count (*) from student";
		return this.getJdbcTemplate().queryForObject(query, Integer.class);
	}

	
	
	public List<Student> getAllStudent()
    {
		String query = "select * from student";
		return this.getJdbcTemplate().query(query, new StudentMapper());
    }
	
	
	private static final class StudentMapper implements RowMapper<Student>{

		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
		}
		
			
	}
}
