package com.deezer.javadmt.utils;

import java.util.Collection;
import java.util.List;

/**
 * A utility used to look around for things in collection
 */
public class CollectionsFinder {

    /**
     * Describes a predicate to select items in a collection
     *
     * @param <T>
     */
    public static interface Predicate<T> {

        /**
         * @param item the item to test
         * @return if this item matches this predicate
         */
        boolean matches(T item);
    }

    /**
     * Finds an item in a collection matching a predicate
     *
     * @param collection the collection to look in
     * @param predicate  the predicate
     * @param <T>        the type of elements in the collection
     * @return the first item matching the predicate, or null
     */
    public static <T> T find(Collection<T> collection, Predicate<T> predicate) {
        for (T item : collection) {
            if (predicate.matches(item)) {
                return item;
            }
        }

        return null;
    }


    /**
     * Finds an item in a list matching a predicate
     *
     * @param list      the list to look in
     * @param predicate the predicate
     * @param <T>       the type of elements in the list
     * @return the index of the first item matching the predicate, or -1 if none was found
     */
    public static <T> int findIndex(List<T> list, Predicate<T> predicate) {
        for (int i = 0; i < list.size(); ++i) {
            if (predicate.matches(list.get(i))){
                return i;
            }
        }

        return -1;
    }
}
