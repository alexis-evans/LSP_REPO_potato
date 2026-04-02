package org.howard.edu.lsp.assignment5;

public class Driver {
    public static void main(String[] args) {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        System.out.println();
        System.out.println("Union: " + set1.union(set2));
        System.out.println("Intersection: " + set1.intersect(set2));
        System.out.println("Difference (set1 - set2): " + set1.diff(set2));
        System.out.println("Complement (set2 - set1): " +
                set1.complement(set2));
        System.out.println();
        System.out.println("Set1 after operations: " + set1);
        System.out.println("Set2 after operations: " + set2);
        System.out.println();
        System.out.println("Contains 2 in Set1: " + set1.contains(2));
        System.out.println("Largest in Set1: " + set1.largest());
        System.out.println("Smallest in Set1: " + set1.smallest());
        System.out.println();
        set1.remove(2);
        System.out.println("Set1 after removing 2: " + set1);
        System.out.println("Length of Set1: " + set1.length());
        System.out.println("Is Set1 empty: " + set1.isEmpty());
        System.out.println();
        set1.clear();
        System.out.println("Set1 after clear: " + set1);
    }
}