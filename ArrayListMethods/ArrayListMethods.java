import java.util.*;

class ArrayListMethods {

    // Time complexity: O(n log n)

    public static <T extends Comparable<T>> boolean unique(List<T> list) {
        Collections.sort(list);

        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i) == list.get(i + 1))
                return false;
        }

        return true;
    }

    // Time complexity: O(n)

    public static List<Integer> allMultiples(List<Integer> list, int num) {
        List<Integer> multiples = new ArrayList<Integer>();

        for (int x : list) {
            if (x % num == 0)
                multiples.add(x);
        }

        return multiples;
    }

    // Time complexity: O(n)

    public static List<String> allStringsofSize(List<String> list, int len) {
        List<String> strings = new ArrayList<String>();

        for (String s : list) {
            if (s.length() == len)
                strings.add(s);
        }

        return strings;
    }

    // Time complexity: O(n log n)

    public static <T extends Comparable<T>> boolean isPermutation(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size())
            return false;

        Collections.sort(list1);
        Collections.sort(list2);

        for (int i = 0; i < list1.size(); ++i) {
            if (list1.get(i) != list2.get(i))
                return false;
        }

        return true;
    }

    // Time complexity: O(n)

    public static <T> List<String> tokenize(String sentence) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(sentence);

        while (st.hasMoreTokens()) {
            list.add(st.nextToken().replaceAll("[^a-zA-Z0-9\\s]", ""));
        }

        return list;
    }

    // Time complexity: O(n)

    public static <T> List<T> removeAll(List<T> list, T item) {
        List<T> newList = new ArrayList<T>();

        for (T x : list) {
            if (x != item)
                newList.add(x);
        }

        return newList;
    }
}