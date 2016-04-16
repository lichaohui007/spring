package pac.impl;

import pac.ITalk;

public class PeopleTalk implements ITalk{

	private String username;
	private String age;
	
	public PeopleTalk(String username, String age) {
		this.username = username;
		this.age = age;
	}
	
	@Override
	public void talk(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg + "!你好，我是" + username + ",年龄是" + age);
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	
}
