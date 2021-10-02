package edu.fa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import edu.fa.model.Student;

@Component
public class StudentJdbcTemplateDao {

	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insertStudent(Student student) {
		String query = "insert into student values (" + student.getId() + ",'" + student.getName() + 
				"','" + student.getLocation() + "')";
		jdbcTemplate.execute(query);
	}
	
	
	public Student getStudentById(int id) {
		String query = "select * from student where id = ?";
		return jdbcTemplate.queryForObject(query, new Object[] {id}, new StudentMapper());
		
	}
	
	public void deleteStudent() {
		String query = "delete from student";
		jdbcTemplate.execute(query);
	}
	
	
	public int countStudent() {
		String query = "select count (*) from student";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	
	
	public List<Student> getAllStudent()
    {
		String query = "select * from student";
		return jdbcTemplate.query(query, new StudentMapper());
    }
	
	
	private static final class StudentMapper implements RowMapper<Student>{

		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
		}
		
			
	}
}
