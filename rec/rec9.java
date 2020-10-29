import java.util.*;

public class rec9 {

	//Question 1.1
	public static int sum(int start, int end, Stream<Integer> ak) {
		return ak.skip(start).limit(end - start).reduce(0, (x, y) -> x + y);
	}

	//Question 1.2
	public static double dSum(int low, int high, IntToDoubleFunction f) {
		return IntStream.rangeClosed(low, high).mapToDouble(f).sum();
	}

	//Question 2.1
	public static<T> void swap(T x, T y) {
		T tmp = x;
		x = y;
		y = tmp;
	}

	public static void main(String... args) {

		//Question 2.1
		String s1 = "the first string";
		String s2 = "the second string";
		swap(s1, s2);
		//both return "the second string", "the first string"
		
		//Question 2.2
		int[] numbers = {1, 2, 3, 4, 5};
		int i = 2;
		swap(1, numbers[i]);
		//pass by reference: (1,2,2,4,5)
		//pass by value: (1,2,3,2,5)

	}
}

