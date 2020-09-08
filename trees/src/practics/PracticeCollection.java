package practics;

public class PracticeCollection {

	public static void main(String[] args) {

//		int[] array = new int[10];
//
//		for (int i = 0; i < 10; i++) {
//			array[i] = i;
//		}
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println(i + "th index value " + array[i]);
//		}
//
//		List<Integer> list = new ArrayList<>();
//
//		for (int i = 0; i < 12; i++) {
//			list.add(i);
//		}
//		List<Integer> list2 = new ArrayList<>();
//		for (int i = 12; i < 20; i++) {
//			list.add(i);
//		}
//		list.addAll(list2);
//		list.set(19, 20);
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//
//		System.out.println(list.contains(20));
//		System.out.println(list.containsAll(list2));
//		System.out.println(list.indexOf(19));
//
//		Set<String> set = new HashSet<>();
//		set.add("kikke");
//		set.add("Kikke");
//
//		for (String string : set) {
//			System.out.println(string);
//		}
//
//		Map<String, String> map = new HashMap<>();
//		map.put("Kikke", "22");
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}
//		System.out.println(map.containsKey("Kikke"));
//		System.out.println(map.containsKey("Kikke1"));
//
//		Student s1 = new Student(3, "kkkk");
//		Student s2 = new Student(2, "bbb");
//		Student s3 = new Student(1, "jj");
//
//		List<Student> list = new ArrayList<>();
//		list.add(s1);
//		list.add(s2);
//		list.add(s3);
//		list.sort(new StudentComparator());
//		for (Student student : list) {
//			System.out.println(student.getRollNo() + " " + student.getName());
//		}

//		Map<String, Boolean> map1 = new HashMap<>();
//		Map<String, Boolean> map2 = new HashMap<>();
//		map1.put("Kikke", true);
//		map1.put("Kikke1", true);
//		map1.put("Kikke2", true);
//		map1.put("Kikke3", true);
//		
//		map2.put("Kikke", true);
//		map2.put("Kikke1", true);
//		map1.keySet().retainAll(map2.keySet());
//		
//		for (Map.Entry<String, Boolean> entry : map1.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}

		String x = "kikke";
		String y = " and";
		String z = " vani";
		String a = x + y + z;
		System.out.println(a);
		System.out.println(x.hashCode());
		System.out.println(y.hashCode());
		System.out.println(z.hashCode());
		System.out.println(a.hashCode());
		System.out.println("*************************");

		StringBuilder builder = new StringBuilder();
		System.out.println(builder.hashCode());
		builder.append("kikke");
		System.out.println(builder.hashCode());
		builder.append(" and");
		System.out.println(builder.hashCode());
		builder.append(" vani");
		System.out.println(builder.hashCode());
		System.out.println(builder.toString());
	}
}
