import java.io;

public class rec8{

	//question 1
	public static void main(String... args) {
		final int number = 4;

		Predicate<Integer> compute =  t -> t > 5;
		Predicate<Integer> process = p -> (p%3 == 0);

		System.out.println((compute.test(number) && process.test(number)) ? "TRUE": "FALSE");

	}

	//question 2
	public static int addAll(Collection<integer> integers) {
		return integers.stream().reduce(0, Integer::sum);
	}

	//question 3
	public static String shortestString(List<String> l) {
		return l.stream().reduce(l.get(0), (a,b) -> a.length() < b.length() ? a: b).orElse("");
	}

	//question 4
	public static List<Integer> sqrts(List<Integer> l) {
		return l.stream().filter(x -> x>0 && ((x & (x-1)) == 0));
	}

	//question 5
	public static Set<String> getWords(String path) {
		Stream<String> stream = null;
		try {
			Files.lines(Paths.get(path)
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream.flatmap( String -> Stream.of(String.split(" "))).collect(Collectors.toSet());
	}

}
