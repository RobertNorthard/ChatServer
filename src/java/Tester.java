/**
*	A chat server tester class
*/
public class Tester{

	public static void main(String[] args) throws Exception{
		new ChatServer(9000).start();
	}

}