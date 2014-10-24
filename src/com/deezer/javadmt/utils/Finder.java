package com.deezer.javadmt.utils;

import java.util.Collection;
import java.util.List;

/**
 * A utility used to look around for things in collection
 */
public class Finder {

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
     * Describes a predicate to match items in a collection
     *
     * @param <T>
     */
    public static interface FuzzyPredicate<T> {

        /**
         * @param item the item to test
         * @return the accuracy of the match. 0 means it's not a match at all, 1.0 means it's a perfect match
         */
        float match(T item);
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
            if (predicate.matches(list.get(i))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Finds the best match in a collection according to a predicate
     *
     * @param collection the collection to look in
     * @param predicate  the predicate to use
     * @param minimum    the minimum level of accuracy to accept for a match (value between 0.0 and 1.0)
     * @param <T>        the type of elements in the collections
     * @return the best matching item, or null if no match is found
     */
    public static <T> T findFuzzy(Collection<T> collection, FuzzyPredicate<T> predicate, float minimum) {
        float bestMatchValue = -1.0f;
        T bestMatch = null;

        for (T item : collection) {
            // Matchmaker matchmaker make me a match...
            float value = predicate.match(item);

            // its a nice find, a good match, right ? Of course right ...
            if ((value > minimum) && (value > bestMatchValue)) {

                bestMatchValue = value;
                bestMatch = item;
            }
        }

        return bestMatch;
    }
}
