package javacc;

public class Main {

	public static void main(String[] args) throws ParseException {
		javacc.Language lan = new javacc.Language(System.in);
		lan.words();
	}
}
