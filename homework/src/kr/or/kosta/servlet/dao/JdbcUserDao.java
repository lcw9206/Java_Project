package kr.or.kosta.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;;

public class JdbcUserDao implements UserDAO {


	private DataSource dataSource;
	
	/**setter/getter*/
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql=	"INSERT INTO users \r\n" + 
				"            (id, \r\n" + 
				"             NAME, \r\n" + 
				"             passwd, \r\n" + 
				"             email, \r\n" + 
				"             regdate) \r\n" + 
				"VALUES      (?, \r\n" + 
				"             ?, \r\n" + 
				"             ?, \r\n" + 
				"             ?, \r\n" + 
				"             sysdate) ";
			
		
		try {
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPasswd());
		pstmt.setString(4, user.getEmail());
		pstmt.executeUpdate();
		}finally {
		
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
/*			*//**사용한 connectionpool 반납*//*
			if(con != null) UserConnectionPool.getInstance().releaseConnection(con);*/
			}
	}

	@Override
	public User read(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * \r\n" + 
					 "FROM   users \r\n" + 
					 "WHERE  id = ? ";
		
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs =pstmt.executeQuery();
		
		/*while(rs.next()) {
			String Id = rs.getString("id");
			String name = rs.getString("name");
			String passwd = rs.getString("passwd");
			String email = rs.getString("email");
			String regdate =  rs.getString("regdate");
			user = new User(Id, name,passwd, email , regdate);	
		}
		*/
		try {
		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPasswd(rs.getString("passwd"));
			user.setEmail(rs.getString("email"));
			user.setRegdate(rs.getString("regdate"));
		}
		}finally {
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(con != null)con.close();
		}
		return user;
	}

	@Override
	public void update(User user) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE users \r\n" + 
				"SET    passwd = '2222' \r\n" + 
				"WHERE  name = ?";
	
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getName());
		pstmt.executeUpdate();
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE users \r\n" + 
				"WHERE  id = ? ";
	
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.executeUpdate();

	}

	@Override
	public List<User> listAll() throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> alist = new ArrayList<User>();
		String sql = "SELECT * \r\n" + 
				"FROM   users  ";
		conn =dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPasswd(rs.getString("passwd"));
			user.setEmail(rs.getString("email"));
			user.setRegdate(rs.getString("regdate"));
			alist.add(user);
		}
		return alist;
	}

	@Override
	public User certify(String id, String passwd) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * \r\n" + 
				"FROM   users \r\n" + 
				"WHERE  id = ? \r\n" + 
				"       AND passwd = ? ";
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, passwd);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPasswd(rs.getString("passwd"));
			user.setEmail(rs.getString("email"));
			user.setRegdate(rs.getString("regdate"));
		}
		return user;
	}
	
/*	@Override
	public List<Employee> employeeList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee employee = null;
		List<Employee> alist = new ArrayList<Employee>();
		String sql="SELECT e.employee_id   employeeid, \r\n" + 
				"       e.last_name     lastname, \r\n" + 
				"       e.salary, \r\n" + 
				"       d.department_id departmentid, \r\n" + 
				"       l.city \r\n" + 
				"FROM   employees e \r\n" + 
				"       join departments d \r\n" + 
				"         ON e.department_id = d.department_id \r\n" + 
				"       join locations l \r\n" + 
				"         ON d.location_id = l.location_id ";
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		ResultSetMetaData rsm = rs.getMetaData();
		int count = rsm.getColumnCount();
		
		for(int i=1 ; i<=count; i++) {
			System.out.print(rsm.getColumnName(i) +"\t");
		}
		System.out.println();
		while(rs.next()) {
			employee = new Employee();
			employee.setEmployeeId(rs.getInt("employeeid"));
			employee.setName(rs.getString("lastname"));
			employee.setSalary(rs.getInt("salary"));
			employee.setDepartmentName(rs.getString("departmentid"));
			employee.setCity(rs.getString("city"));
			alist.add(employee);
		}
		return alist;
	}
	
	*/
	
	@Override
	public List<Map<String, String>> employeeList() throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, String>> alist = new ArrayList<Map<String,String>>();
		String sql = "SELECT e.employee_id   employeeid, \r\n" + 
				"       e.last_name     lastname, \r\n" + 
				"       e.salary, \r\n" + 
				"       d.department_id departmentid, \r\n" + 
				"       l.city \r\n" + 
				"FROM   employees e \r\n" + 
				"       left outer join departments d \r\n" + 
				"                    ON e.department_id = d.department_id \r\n" + 
				"       left outer join locations l \r\n" + 
				"                    ON d.location_id = l.location_id \r\n" + 
				"       left outer join employees e2 \r\n" + 
				"                    ON e.manager_id = e2.employee_id ";
		conn = dataSource.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		ResultSetMetaData rsm = rs.getMetaData();
		int count = rsm.getColumnCount();
		for(int i=1 ; i<=count; i++) {
			System.out.print(rsm.getColumnName(i) +"\t");
		}
		System.out.println();
		while(rs.next()) {
			Map<String, String> row = new HashMap<String,String>();
			for(int i=1; i<=count; i++) {
				String columName = rsm.getColumnLabel(i);
				String columValue = rs.getString(i);
				row.put(columName, columValue);
				alist.add(row);
			}
		}
		
		return alist;
	}

}
