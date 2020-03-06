package me.Samkist.Data;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Search<T extends Comparable<T>> {
    private ArrayList<T> list;
    private ArrayList<T> results;
    private T item;
    private int checks;

    public Search(ArrayList<T> list, T item) throws NullPointerException {
        this.list = (ArrayList<T>) list.clone();
        this.item = item;
        this.results = binarySearch();
    }

    public int getChecks() {
        return checks;
    }

    public ArrayList<T> get() {
        return results;
    }

    private ArrayList<T> binarySearch() throws NullPointerException {
        ArrayList<T> results = new ArrayList<>();
        int first = 0;
        int last = list.size() - 1;
        int middle = (first + last) / 2;

        while(first <= last) {
            if(list.size() == 1) {
                first = 0;
                last = 0;
            }
            if(list.get(middle).compareTo(item) < 0) {
                first = middle + 1;
            } else if(list.get(middle).compareTo(item) == 0) {
                results.add(list.get(middle));
                list.remove(middle);
                last--;
                middle = (first + last) / 2;
                continue;
            } else {
                last = middle - 1;
            }
            checks++;
            middle = (first + last) / 2;
        }
        return results;
    }
}
