package edu.fa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fa.dao.StudentHibernateDao;
import edu.fa.dao.StudentJdbcDao;
import edu.fa.dao.StudentJdbcDaoSupport;
import edu.fa.dao.StudentJdbcTemplateDao;
import edu.fa.model.Student;
import edu.fa.service.StudentService;

public class StudentManagement {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//		StudentHibernateDao dao = context.getBean("studentHibernateDao", StudentHibernateDao.class);
//		dao.save(new Student(1, "Thang", "Viet Nam"));
		
		StudentService service =  context.getBean("studentService", StudentService.class);
		service.save(new Student("Thang", "Viet Nam"));
		service.test();
		
		
//		dao.deleteStudent();
//		dao.insertStudent(new Student(1, "Thang", "Viet Nam"));
//		System.out.println(dao.getAllStudent());
//		System.out.println(dao.getStudentById(1));
//		System.out.println(dao.countStudent());
//		dao.deleteStudent();
		
	}

}
