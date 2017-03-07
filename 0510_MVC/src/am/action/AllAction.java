package am.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.dao.MemberDAO;
import mybatis.vo.MemberVO;

public class AllAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
	
		// DAO를 이용하여 정보를 배열로 받는다.
		MemberVO[] ar = MemberDAO.getAll();
		
		// 받은 배열을 뷰페이지(JSP)에서 사용할 수 있도록 request에 저장
		request.setAttribute("ar", ar);
		
		return "/jsp/all.jsp";
	}

}
