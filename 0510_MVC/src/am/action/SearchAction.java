package am.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.dao.MemberDAO;
import mybatis.vo.MemberVO;

public class SearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 검색할 이름을 파라미터로 받는다.
		String name = request.getParameter("name");
		
		// 파라미터로 받은 name을 searchName에 넣는다.
		MemberVO[] ar = MemberDAO.searchName(name);
		
		// 뷰페이지에 표현할 자원인 ar을 request에 저장하자
		request.setAttribute("ar", ar);
		
		return "/jsp/search_list.jsp";
	}

}
