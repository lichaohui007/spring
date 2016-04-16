package pac.impl;

import pac.ITalk;

public class ProxyPattern {

	public static void main(String[] args) {
		ITalk people = new PeopleTalk("aop", "18");
		people.talk("No Poxy Test");
		System.out.println("---------------------");
		
		TalkProxy talker = new TalkProxy(people);
		talker.talk("proxyTest", "代理");
	}
	
	
}
