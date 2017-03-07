package am.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.dao.MemberDAO;

public class AddAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// 받은 파라미터를 DAO에게 전달하여 저장되도록 한다.
		boolean ch = MemberDAO.addMember(id, name, pwd, email);
		
		return "MyControl?type=all"; 
	}

}
