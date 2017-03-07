package mybatis.dao;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.vo.MemberVO;

public class MemberDAO {
	
	private static SqlSessionFactory factory;
	
	static{
		try {
			Reader r = Resources.getResourceAsReader("mybatis/config/config.xml");
			factory = new SqlSessionFactoryBuilder().build(r);
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//비지니스 로직
	public static MemberVO[] getAll(){
		SqlSession ss = factory.openSession();
		
		List<MemberVO> list =
			ss.selectList("member.all");
		
		//List에 있는 요소들을 배열로 변환해야 한다.
		MemberVO[] ar = null;
		if(list != null && list.size() > 0){
			//배열을 List의 크기만큼 생성한다.
			ar = new MemberVO[list.size()];
			
			//List에 요소들을 배열에 복사한다.
			list.toArray(ar);
		}
		ss.close();
		return ar;
	}
	
	// 이름검색 기능
	public static MemberVO[] searchName(String n){
		SqlSession ss = factory.openSession();
		
		List<MemberVO> list = ss.selectList("member.searchName", n);
		MemberVO[] ar = null;
		if(list != null && list.size() > 0){
			//배열을 List의 크기만큼 생성한다.
			ar = new MemberVO[list.size()];
			
			//List에 요소들을 배열에 복사한다.
			list.toArray(ar);
		}
		ss.close();
		return ar;
	}
	
	// 멤버추가 기능
	public static boolean addMember(String id, String name, String pwd, String email){
		
		boolean ch = false;
		SqlSession ss = factory.openSession();
		
		Map<String, String > map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("name", name);
		map.put("pwd", pwd);
		map.put("email", email);
		
		int cnt = ss.insert("member.add",map);
		if(cnt > 0){
			ch = true;
			ss.commit();
		}else{
			ss.rollback();
		}
		
		ss.close();
		return ch;
	}
		
}