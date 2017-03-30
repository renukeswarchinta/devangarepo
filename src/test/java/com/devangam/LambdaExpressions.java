package com.devangam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaExpressions {
	
	private static List<Employee> empList = new ArrayList<Employee>(); 
	public static void constructEmployee(){
		empList.add(new Employee("Renu",100));
		empList.add(new Employee("Renu",200));
		empList.add(new Employee("Renu",10));
		empList.add(new Employee("Eswar",1100));
	}
	public static void sortEmployee(){

		constructEmployee();
		/*empList.sort((p1,p2)->p1.getName().compareTo(p2.getName()));
		empList.sort((p1,p2)->p1.getId().compareTo(p2.getId()));
		//Collections.sort(empList, (e1,e2)-> Integer.compare(e1.getId(), e2.getId()));
		Comparator<Employee> comp = (e1,e2) -> e1.getName().compareTo(e2.getName());
		Comparator<Employee> comp2 = (e1,e2) -> e1.getId().compareTo(e2.getId());
		Collections.sort(empList,comp);
		*/
		empList.sort(Comparator.comparing(Employee::getId).thenComparing(Employee::getName));
		
		//empList.forEach(e->System.out.println(e.getName()));
		
		List<Employee> idSum = empList.stream()
				.filter(e->e.getName().equalsIgnoreCase("Renu"))
				.collect(Collectors.toList());
		idSum.forEach(e->System.out.println("Name "+e.getName()+" Id  "+e.getId()));
		
		 final Map<Integer, Employee> mapFromListJavaEight =
		            IntStream.range(0,idSum.size())
		            .mapToObj(index -> index)
		            .collect(Collectors.toMap(Function.identity(),
		            		idSum::get));
		 	 
		 Map<String,List<Employee>> map = new HashMap<String,List<Employee>>();
		 List<Employee> list = new ArrayList<Employee>();
		 for(Employee e: empList){
			 if(map.containsKey(e.getName())){
				 list.add(e);
				 map.put(e.getName(), list);
			 }else{
				 map.put(e.getName(), list);
			 }
		 }
		 Set<Entry<String, List<Employee>>> entrySet = map.entrySet();
		 entrySet.forEach(e->System.out.println("------  "+e.getValue()+"  "+e.getKey()));
/*		List<Employee> distinctElements = empList.stream().filter(distinctByKey(p -> p.getName())).collect(Collectors.toList());
		distinctElements.forEach(e->System.out.println("------  "+e.getName()));
		
		Map<String, Employee> result =
				empList.stream().collect(Collectors.toMap(Employee::getName,
			                                              Function.identity()));
		Set<Entry<String, Employee>> entrySet = result.entrySet();
		entrySet.forEach(e->System.out.println("------  "+e.getValue()+"  "+e.getKey()));
*/		/*
		Stream<String> unique = Stream.of("Renu","Renukeswar","Eswar","Abhi","manu").distinct();
		//unique.forEach(System.out::println);
		Optional<String> max = unique.max(String::compareToIgnoreCase);
		if(max.isPresent()){
			System.out.println(max.get());
		}*/
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        
        
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
	public static void countWordsinFile() throws IOException{
		String contents = new String(Files.readAllBytes(Paths.get("C:\\Renu\\IDM Workspace\\Core Java\\test.txt")), StandardCharsets.UTF_8); // Read file into string
		List<String> words = Arrays.asList(contents.split(" "));
		int count = 0;
		for (String w : words) {
			if (w.length() > 12) 
				count++;
		}
		long count1 = words.stream().filter(w->w.length()>12).count();
		System.out.println("Count value "+count1);
		Stream<String> s = Stream.empty();
	}
	public static void main(String[] args) throws IOException {
		sortEmployee();
		countWordsinFile();
		
	}
	
}


class Employee{
	
	private String name;
	private Integer id;
	
	Employee(String name,Integer id){
		this.name =name;
		this.id =id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}