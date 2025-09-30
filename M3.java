package test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class M3 {

	public static void main(String[] args) {
		FilterEvenNumber();
        System.out.println("-------------------------");
        convertToUpperCase();
        System.out.println("-------------------------");
        findFirstElementGreaterThan10();
        System.out.println("-------------------------");
        sortByLengthOfString();
        System.out.println("-------------------------");
        countWordStartingWitha();
        System.out.println("-------------------------");
        groupByStringLength();
        System.out.println("-------------------------");
        joinStringWithComma();
        System.out.println("-------------------------");
        findMaxElement();
        System.out.println("-------------------------");
        flatMapForNestedList();
        System.out.println("-------------------------");
        
        ////////////////////////////
        
        FindEmployeesWithDuplicateNames();
        System.out.println("--------------------------------");
        findTopKFrequentElementsInAlist();
        System.out.println("--------------------------------");
        findCommonElementsInTwoLists();
        System.out.println("--------------------------------");
        checkIfTwoStringsAreAnagrams();
        System.out.println("--------------------------------");
        findMajorityElement();
        System.out.println("--------------------------------");
        findTheFirstRepeatedCharacterInAString();
        System.out.println("--------------------------------");
        findTheLongestWordInASentence();
        System.out.println("--------------------------------");
        findAllNumbersThatAppearExactlyTwice();
        System.out.println("--------------------------------");
        findTheKthLargestElement();
        System.out.println("--------------------------------");
        findDuplicateCharactersInAString();
	}

	private static void FilterEvenNumber() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //traditional way
        List<Integer> evens = new ArrayList<>();
        for (Integer n : numbers) {
            if (n % 2 == 0) {
                evens.add(n);
            }
        }
        evens.stream().forEach(System.out::println);

        //using stream
        List<Integer> result = numbers.stream().filter(n->n%2==0).collect(Collectors.toList());
        result.stream().forEach(System.out::println);

    }
	

    public static void convertToUpperCase(){
        List<String> names = Arrays.asList("john", "doe", "alice");

        //traditional way
        List<String> upper = new ArrayList<>();
        for (String name : names) {
            upper.add(name.toUpperCase());
        }
        System.out.println(upper);

        //using stream api
        names.stream().map(s->s.toUpperCase()).forEach(System.out::println);
    }
    
    public static void findFirstElementGreaterThan10(){
        List<Integer> nums = Arrays.asList(5, 9, 1, 3, 5);

        //traditional way
        Integer result = null;
        for (Integer n : nums) {
            if (n > 10) {
                result = n;
                break;
            }
        }
        System.out.println(result);

        //using stream api
        Optional<Integer> optional = nums.stream().filter(n->n>10).findFirst();
        optional.ifPresent(System.out::println);
        optional.ifPresentOrElse(System.out::println,()->System.out.println("No value are greater than 10"));
    }
		
    public static void sortByLengthOfString(){
        List<String> list = Arrays.asList("banana", "apple", "kiwi");

        //traditional way
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        System.out.println(list);

        //using stream api
        list.stream().sorted(Comparator.comparingInt(s->s.length())).forEach(System.out::println);

        //natural ordering
        list.stream().sorted().forEach(System.out::println);
    }
    public static void countWordStartingWitha(){
        List<String> words = Arrays.asList("apple", "banana", "apricot", "grape");

        //traditional way
        int count = 0;
        for (String word : words) {
            if (word.startsWith("a")) {
                count++;
            }
        }
        System.out.println(count);
        //using stream api
        System.out.println("words starting with 'a' "+words.stream().filter(s->s.startsWith("a")).count());
    }
    
    public static void groupByStringLength(){
        List<String> items = Arrays.asList("a", "bb", "ccc", "dd", "eee");

        //traditional way
        Map<Integer, List<String>> grouped = new HashMap<>();
        for (String item : items) {
            int len = item.length();
            if (!grouped.containsKey(len)) {
                grouped.put(len, new ArrayList<>());
            }
            grouped.get(len).add(item);
        }
        System.out.println(grouped);

        //using stream api
        Map<Integer, List<String>> grouped1 = items.stream().collect(Collectors.groupingBy(s->s.length()));
        System.out.println(grouped1);
    }
    
    public static void joinStringWithComma(){
        List<String> list = Arrays.asList("apple", "banana", "cherry");

        //traditional way
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println("Result: "+sb.toString());

        //using stream api
        String result = list.stream().collect(Collectors.joining(", "));
        System.out.println("Result: "+result);
    }
    
    public static void findMaxElement(){
        List<Integer> nums = Arrays.asList(5, 9, 15, 3, 15);

        //traditional way
        int max = Integer.MIN_VALUE;
        for (Integer n : nums) {
            if (n > max) {
                max = n;
            }
        }
        System.out.println(max);

        //using stream api
        OptionalInt optional = nums.stream().mapToInt(n->n).max();
        optional.ifPresentOrElse(System.out::println,()-> System.out.println("No Max value present"));
    }
    
    public static void flatMapForNestedList(){
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"),
                Arrays.asList("e", "f")
        );

        //traditional way
        List<String> flat = new ArrayList<>();
        for (List<String> sublist : list) {
            for (String s : sublist) {
                flat.add(s);
            }
        }
        System.out.println("Flatting map: "+flat);

        //using stream api
        List<String> flat1 =list.stream().flatMap(s->s.stream()).collect(Collectors.toList());
        System.out.println("Flatting map: "+flat1);

    }
    public static void FindEmployeesWithDuplicateNames(){
        List<String> names = Arrays.asList("Ajay", "Vijay", "Sanjay", "Ajay", "Deepak", "Sanjay");

        List<String> duplicates = names.stream().collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .filter(e->e.getValue()>1)
                        .map(Map.Entry::getKey)
                        .toList();
        System.out.println(duplicates);
        //Output: [Ajay, Sanjay]
    }

    public static void findTopKFrequentElementsInAlist(){
        List<Integer> nums = Arrays.asList(1,1,1,2,2,3,3,3,3,4,5);
        int k = 2;

        List<Integer> list = nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer,Long>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(list);
        //output: [3, 2]
    }

    public static void findCommonElementsInTwoLists(){
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        List<Integer> commons = list1.stream().filter(list2::contains)
                .toList();

        System.out.println(commons);
        //Output: [4, 5]
    }

    public static void checkIfTwoStringsAreAnagrams(){
        String s1 = "listen", s2 = "silent";

        boolean isAnagrams = s1.chars().sorted().boxed().collect(Collectors.toList())
                .equals(s2.chars().sorted().boxed().collect(Collectors.toList()));

        System.out.println("Is Anagram: "+isAnagrams);
        //Output: true
    }

    //Find majority element (> n/2 times)
    //the element which presence greater than half the size of array
    //2 present 4 times and array half size is 7/2 = 3
    public static void findMajorityElement(){
        List<Integer> nums = Arrays.asList(2,2,1,1,1,2,2);

        Optional<Integer> optional = nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()>nums.size()/2)
                .map(Map.Entry::getKey)
                .findFirst();
        optional.ifPresentOrElse(System.out::println,()->System.out.println("No majority element found"));
        //output: 2
    }

    public static void findTheFirstRepeatedCharacterInAString(){
        String str = "programming";

        Character character = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()>1)
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);

        System.out.println(character);
        //Output: r;
    }

    public static void findTheLongestWordInASentence(){
        String sentence = "Java Stream API makes coding concise and powerful";

        String longestWord = Arrays.stream(sentence.split("\\s"))
                .max(Comparator.comparingInt(String::length))
                        .orElse("");

        System.out.println(longestWord);
        //output: powerful
    }

    public static void findAllNumbersThatAppearExactlyTwice(){
        List<Integer> nums = Arrays.asList(4, 5, 6, 5, 6, 7, 8, 8);

        List<Integer> twiceNumberList = nums.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()==2)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println(twiceNumberList);
        //output: [5, 6, 8]
    }

    public static void findTheKthLargestElement(){
        List<Integer> nums = Arrays.asList(3, 2, 1, 5, 6, 4);
        int k = 2;

        Integer element = nums.stream().sorted(Comparator.reverseOrder())
                .skip(k-1)
                .findFirst().orElse(-1);

        System.out.println(element);
        //Output: 5
    }

    public static void findDuplicateCharactersInAString(){
        String str = "banana";

        Set<Character> duplicates = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println(duplicates);
        //Output: [a, n]
    }
	}
	
	
