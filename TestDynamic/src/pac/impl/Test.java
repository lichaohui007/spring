package pac.impl;

import pac.ITalk;

public class Test {

	public static void main(String[] args) {
		ITalk iTalk = (ITalk) new DynamicProxy().bind(new PeopleTalk("dyapo", "15"));
		iTalk.talk("业务说明");
	}
}
