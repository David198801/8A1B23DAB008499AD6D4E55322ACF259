HttpSessionBindingListener



实体类实现接口，则该实体类对象被保存到session时触发HttpSessionBindingListener

setAttribute()时执行valueBound()

removeAttribute()或invalidate()时执行valueUnbound()

invalidate()中解绑了所有的数据



统计在线人数，session中保存User对象时，application中保存的人数+1

```javascript
package entity;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener{

	private Integer id;
	private String username;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {}
	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	private String password;
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		ServletContext servletContext = session.getServletContext();
		Integer onlineCount = (Integer)servletContext.getAttribute("onlineCount");
		if(null==onlineCount) {
			servletContext.setAttribute("onlineCount", 1);
		}else {
			servletContext.setAttribute("onlineCount", onlineCount+1);
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		ServletContext servletContext = session.getServletContext();
		Integer onlineCount = (Integer)servletContext.getAttribute("onlineCount");
		servletContext.setAttribute("onlineCount", onlineCount-1);
	}
}

```

