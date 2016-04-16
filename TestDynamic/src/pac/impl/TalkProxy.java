package pac.impl;

import pac.ITalk;

public class TalkProxy  implements ITalk{

	private ITalk talker;
	
	public TalkProxy(ITalk talker) {
		// TODO Auto-generated constructor stub
		this.talker = talker;
	}
	@Override
	public void talk(String msg) {
		// TODO Auto-generated method stub
		talker.talk(msg);
	}
	
	public void talk(String msg, String singname){
		talker.talk(msg);
		sing(singname);
	}

	public void sing(String singname){
		System.out.println("唱歌：" + singname);
	}
}
