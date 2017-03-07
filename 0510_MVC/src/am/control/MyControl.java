package am.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.action.Action;

/**
 * Servlet implementation class MyControl
 */
@WebServlet(
		urlPatterns = { "/MyControl" }, 
		initParams = { 
				@WebInitParam(name = "param1", value = "/WEB-INF/props/action.properties")
		})
public class MyControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // WEBinitParam에 의해 properties파일의 경로들을 받아 
	// 생성하여 객체들을 저장할 맵구조를 선언한다.
	private Map<String, Object> commMap;
	
    public MyControl() {
        super();
        commMap = new HashMap<String, Object>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청시 항상 수행하는 영역
		
		// 요청시 한글처리
		request.setCharacterEncoding("utf-8");
		
		// type이라는 파라미터 값 받기
		String type = request.getParameter("type");
		
	
		if(type == null )
			return;
		
		Action action = (Action) commMap.get(type);
		String viewPage = action.execute(request, response);
		
		// forward를 위한 작업
		RequestDispatcher disp = request.getRequestDispatcher(viewPage);
		disp.forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		// 현재 메서드는 오직 첫 방문자에 의해 단 한번만 수행한다.
		// 현재 서블릿이 생성될때 초기화 파라미터(initParam)가 전달되는데
		// 이전 버전에서는 web.xml문서에 <init-param>태그로 정의 했었다.
		// 하지만 현재 웹모듈 3.x부터는 서블릿 전반부에 정의하도록 되어 있다.
		
		// WebInitParam값을 받는다.
		String param = getInitParameter("param1");
		
		// 위에서 받은 action.properties파일의 경로를 절대경로화 시킨다.
		ServletContext application = getServletContext();
		String path = application.getRealPath(param);
		
		// 위의 절대경로로 스트림을 만들 것이며, 그 후에는 내용을 읽어
		// 저장할 곳이 필요하다. 그것이 바로 properties이다.
		Properties props = new Properties();
		
		// 위의 절대경로와 연결되는 스트림 준비
		FileInputStream fis = null;
		try {
			// 목적파일(action.properties)의 내요을 읽어서 비어있는
			// props에 값들을 옮겨 놓는다.
			fis = new FileInputStream(path);
			props.load(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null)
					fis.close();
			} catch (Exception e2) {}
		}
		
		// props 즉, Properties객체에 있는 all=action.AllAction와 같은
		// Key-Value들을 하나씩 읽어서 객체를 생성한 후 commMap에 저장
		
		// 일괄처리를 위해 properties에 저장된 Key들만 따로 Iterator로 받아둔다.
		Iterator<Object> it = props.keySet().iterator();
		while(it.hasNext()){
			// 키 하나씩 얻어내기
			String commKey = (String)it.next(); // "all"
			
			// 키를 얻었으니 연결된 value 즉, 클래스 경로를 가져온다.
			String commValue = props.getProperty(commKey); // "action.AllAction"
			
			// 위에서 받은 클래스 경로는 문자열일 뿐 실제 생성된 객체가 아니다.
			// 그 경로로 객체를 생성한 후 맵구조(commMap) commKey와 함께 저장한다.
			try {
				Object obj = Class.forName(commValue).newInstance();
				
				// Map구조에 생성된 객체 주소를 저장
				commMap.put(commKey, obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // while문의 끝
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
