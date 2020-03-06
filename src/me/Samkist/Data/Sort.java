package me.Samkist.Data;

/**
 * Created by Samkist
 * https://github.com/Samkist
 */
public class Sort<T extends Comparable<T>> {
    private ArrayList<T> list;
    private String sort;


    public Sort(ArrayList<T> list, String sort) {
        this.list = (ArrayList<T>) list.clone();
        this.sort = sort;
        if(sort.equalsIgnoreCase("selection")) {
            selectionSort();
            return;
        }
        insertionSort();
    }

    private void selectionSort() {
        for(int i = 0; i < list.size(); i++) {
            int min = i;
            for(int j = i +1; j < list.size(); j++) {
                if(list.get(j).compareTo(list.get(min)) < 0)
                    min = j;
            }
            if(min != i) {
                T a = list.get(min);
                T b = list.get(i);
                list.set(min, b);
                list.set(i, a);
            }
        }
    }

    private void insertionSort() {
        for(int i = 1; i < list.size(); i++) {
            T t = list.get(i);
            int pos = i;

            while(pos > 0 && list.get(pos-1).compareTo(t) > 0) {
                list.set(pos, list.get(pos-1));
                pos--;
            }

            list.set(pos, t);
        }
    }

    public ArrayList<T> get() {
        return list;
    }
}
