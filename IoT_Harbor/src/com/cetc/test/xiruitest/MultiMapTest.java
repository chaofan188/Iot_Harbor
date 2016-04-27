package com.cetc.test.xiruitest;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MultiMapTest {
	public static void main(String[] args){
		Multimap<String,String> testMap = ArrayListMultimap.create();
		testMap.put("a","hello");
		testMap.put("b", "hi");
		testMap.put("a", "111");
		testMap.put("a", "22");
		
		System.out.println(testMap.size());
		
		
		if (testMap.containsEntry("a","22")){
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
		/*Collection<String> result = testMap.get("c");
		if (result.isEmpty()){
			System.out.println("��");
		}
		System.out.println(result.toString());
		
		Iterator<String> it = result.iterator();
		System.out.println("*******");
		while (it.hasNext()){
			String temp = it.next();
			if (temp.equals("hello")){
				it.remove();
			}
			System.out.println(temp);
		}
		System.out.println("*******");
		for (String value:result){
			System.out.println(value);
		}
		System.out.println("#########");
		System.out.println(testMap.remove("a", "hello"));
		System.out.println(testMap.remove("a","hello"));
		
		for (String value:testMap.values()){
			System.out.println(value);
		}*/
	}
}
