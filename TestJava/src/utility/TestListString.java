package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListString {
	/*public static void main(String[] args) {
		String str = "a";
		List<String> list = Arrays.asList(str.split(","));
		System.out.println("list : "+list);
		List<String> list1 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		System.out.println("list1 : "+list1);
		
		for(int i =0; i <list.size() ; i++){
			System.out.println("list element "+i+" : "+list.get(i));
		}
		for(int i =0; i <list1.size() ; i++){
			System.out.println(" list2 element "+i+" : "+list1.get(i));
		}
	}*/
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("d");
		list.add("c");
		System.out.println(list);
		System.out.println(list.get(1));
		list.remove(1);
		list.add(1, "b");
		System.out.println(list);
	}
}
