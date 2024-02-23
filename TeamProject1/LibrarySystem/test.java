package TeamProject1.LibrarySystem;
import java.util.*;

public class test {

	public static void main(String[] args) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> cord = new HashMap<String, String>();
		
		cord.put("x10", "y10");
		list.add(cord);
		cord.put("x8", "y10");
		list.add(cord);
		
		System.out.println(list.indexOf(1)+""+list.indexOf(0));
		
		
	}

}
