package pac.cglib;

public class Test {

	public static void main(String[] args) {
		PeopleTalk peopleTalk = (PeopleTalk) new CglibProxy().getInstance(new PeopleTalk());
		peopleTalk.talk("业务方法");
	}
}
