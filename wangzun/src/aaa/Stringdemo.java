package aaa;

public class Stringdemo {
	public static void main(String[] args) {
		String[] ss={"a","b","d","c","e"};
		for (int i=0;i<ss.length;i++) {
			if(ss[i].equals("c")){
				ss[i]="#";
			}
			System.out.println(ss[i]);
		}
		
	}

}
