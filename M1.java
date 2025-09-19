package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class M1 {
	
	public static void main(String[] args) {
		List<Integer> num= Arrays.asList(23,1,12,20);
		
		System.out.println("----mapToInt()---");
		int sum=num.stream().mapToInt(Integer::intValue).sum();
		System.out.println(sum);
		
		
		System.out.println("----Comparator.naturalOrder() to find max element---");
		int max=num.stream().max(Comparator.naturalOrder()).get();
		System.out.println(max);
		
		List<String> fruits= Arrays.asList("banana","apple","avacado","orange");
		
		System.out.println("----filter().count()---");
		long count=fruits.stream().filter(s->s.startsWith("a")).count();
		System.out.println(count);
		
		System.out.println("----generic map()---");
		fruits.stream().map(String::toUpperCase).forEach(System.out::println);
		
		num.stream().filter(e->e%2==1).forEach(System.out::println);
		
	
		List<Double> flow=Arrays.asList(1.1,1.2,1.3,1.46);
		
		System.out.println("----mapToDouble()---");
		double avg=flow.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
		System.out.println(avg);
		
		System.out.println("---concatenationof string  using stream--");
		String concatenatedFruits=fruits.stream().collect(Collectors.joining("::"));//:: is deliminater can be anything
		System.out.println(concatenatedFruits);
		
		List<Integer> in= Arrays.asList(23,1,12,20,23,1,5,26,13,0,20);
		Collections.sort(in);
		System.out.println("----distinct() to remove duplicate---");
		in.stream().distinct().forEach(System.out::println);
		
		List<People> Person=Arrays.asList(
		new People("Ram",20),
		new People("Sita",19),
		new People("lakshman",19),
		new People("Bharat",18),
		new People("Shatru",11));
		
		System.out.println("----Comparator.comparing()---");
		Collections.sort(Person,Comparator.comparing(People::getAge));
		System.out.println(Person);
		
		System.out.println("----Stream.sorted()---");
		Person.stream().sorted(Comparator.comparing(People::getName)).forEach(System.out::println);
		
		List<Integer> in2= Arrays.asList(23,1,12,20,23,1,5,26,13,0,20);
		System.out.println("-----AllMatch in stream");
		boolean isEven=in2.stream().allMatch(e->e%2==0);
		System.out.println(isEven);
		
		System.out.println("-----AnyMatch in stream");
		boolean isAnyEven=in2.stream().anyMatch(e->e%2==0);
		System.out.println(isAnyEven);
		
	}

}
