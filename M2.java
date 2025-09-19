package test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class M2 {
	
	public static void main(String[] args) {
		
		/*
		 * GroupingBy(classifier,downstream)
		 * classifier logic to group
		 * downstream to reduce basically count or collect
		 * 
		 * 
		 * */
		
		System.out.println("----frequency of each word ---");
		String sentence="one word come twice is duplicate word";
		
		Map<String,Long> wordfrequency=Arrays.stream(sentence.split("\\s")).
				collect(Collectors.groupingBy(String::toLowerCase,Collectors.counting()));
		System.out.println(wordfrequency);
		
		List<Integer> num= Arrays.asList(12,23,15,63,158,45);
		num.stream().filter(e->String.valueOf(e).startsWith("1")).forEach(System.out::println);
		
		
		String[] names= {"Alice","BOB","SMALL","APPLE"};
		
		Map<Character,Long> mapOfName=Arrays.stream(names).collect(Collectors.groupingBy(e->e.charAt(0),Collectors.counting()));
		System.out.println(mapOfName);
		
		int[] ae= {1,56,2,1,69,41,56,0,1,2};
		
		Arrays.stream(ae).boxed()//auto boxed primitive data
		.collect(Collectors.groupingBy(e->e,Collectors.counting()))//GroupingBy( number itself , count as reduction)
		.entrySet().stream()//conversion of Map to stream of k,v pair
		.filter(e->e.getValue()>1)//filter v>1
		.map(Map.Entry::getKey)//printing key as that will be duplicate
		.forEach(System.out::println);
		
		//remove duplicate
		int[] newar=Arrays.stream(ae).distinct().toArray();
		System.out.println(Arrays.toString(newar));
		
		
		//print pallindrome string
		
		List<String> s=Arrays.asList("level","tets","test","mom","toot");
		
		
		s.stream().filter(e->e.equals(new StringBuilder(e).reverse().toString()))
				.collect(Collectors.toList()).forEach(System.out::println);
		
		
		//merge 2 array into one and sort them
		int[] a1= {2,4,3,1};
		int[] a2= {6,7,9,5};
		
		int[] sortedMergedArray=IntStream.concat(Arrays.stream(a1), Arrays.stream(a2)).sorted().toArray();
		System.out.println(Arrays.toString(sortedMergedArray));
		
		//merge 2 list of string and remove duplicate
		List<String> fruit1=Arrays.asList("banana","Apple","Guava","Orange");
		List<String> fruit2=Arrays.asList("banana","Apple","Avacado","Pomagranade");
		
		List<String> mergedFruitList=Stream.concat(fruit1.stream(), fruit2.stream()).distinct().collect(Collectors.toList());
		
		System.out.println(mergedFruitList);
		
		List<Student> students=
				List.of(new Student("AJAY", 50),
						new Student("Jay", 40),
						new Student("DJay", 80),
						new Student("sanJay", 60)
						);
		
		
		Map<String,List<Student>> studentMap=students.stream().collect(Collectors.groupingBy(e->e.getGrade()>50?"Pass":"Fail"));
		
		System.out.println(studentMap);
		
		//sort the string on its length
		List<String> fruits= Arrays.asList("banana","Pomagranade","apple","kiwi","avacado","orange");
		
		fruits.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
		
	}

}
