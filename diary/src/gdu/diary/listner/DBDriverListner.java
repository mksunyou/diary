package gdu.diary.listner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBDriverListner implements ServletContextListener {
	//톰캐 부팅시
    public void contextInitialized(ServletContextEvent sce)  { 
    	try{
    		Class.forName("org.mariadb.jdbc.Driver");
    		System.out.println(this.getClass() + "DB Driver 로딩 성공"); //extends object가 생략 되어있기 때문에 getClass가 가능.
    	} catch(ClassNotFoundException e) {
    		System.out.println("DB Driver 로딩 실패");
    		e.printStackTrace();
    	}
    }
	
	//생성자
    public DBDriverListner() {
    }
    
    //톰캣 종료시
    public void contextDestroyed(ServletContextEvent sce)  { 
    }
   
    
	
}
