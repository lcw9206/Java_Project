package kr.or.kosta.servlet.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;

public class UserDaoTester {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "hr";
	private static final String PASSWORD = "a1234";
	
	public static void main(String[] args) throws Exception {
		JdbcUserDao dao  = new JdbcUserDao();
		BasicDataSource dataSource = new BasicDataSource(); //dataSource는 sun의 규격 basicDAtaSource는 구현된 구현체 
		//DataSource dsource =  (BasicDataSource)new BasicDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(7);
		dao.setDataSource(dataSource);

	//UserDAO dao = new XXXXDao();
		User user = new User();
		user.setId("bangry");
		user.setName("김기정");
		user.setPasswd("1111");
		user.setEmail("bangry313@naver.com");
		
		User user2 = new User();
		user2.setId("bangry1");
		user2.setName("김기정1");
		user2.setPasswd("2222");
		user2.setEmail("bangry323@naver.com");
		
/*		try {
			//dao.create(user);
			dao.create(user2);
			System.out.println("회원가입 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//**sqlException 을 활용해 에러코드 출력하기*//*
			SQLException ex = (SQLException)e;
			System.out.println(ex.getErrorCode());
		}*/
		
/*		try {
			user = dao.read("bangry");
			System.out.println(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//dao.update(user);
		//System.out.println("수정완료");
		
	/*	dao.delete("bangry");
		System.out.println("삭제완료");*/
		List<User>alist = dao.listAll();
		for (User user3 : alist) {
			System.out.println(user3);
		}
		//System.out.println(dao.certify("bangry", "1111"));
		
/*		List<Map<String, String>>alist = dao.employeeList();
		for (Map<String, String> map : alist) {
			System.out.println(map);
		}*/
	}

}
