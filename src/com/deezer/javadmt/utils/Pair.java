package com.deezer.javadmt.utils;

/**
 * An immutable pair of objects of the same type
 *
 * @author Deezer
 */
public class Pair<T> {

    private final T mLeft, mRight;

    public Pair(T left, T right) {
        mLeft = left;
        mRight = right;
    }

    public T getLeft() {
        return mLeft;
    }

    public T getRight() {
        return mRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?> pair = (Pair<?>) o;

        if (mLeft != null ? !mLeft.equals(pair.mLeft) : pair.mLeft != null) return false;
        return !(mRight != null ? !mRight.equals(pair.mRight) : pair.mRight != null);

    }

    @Override
    public int hashCode() {
        int result = mLeft != null ? mLeft.hashCode() : 0;
        result = 31 * result + (mRight != null ? mRight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" + mLeft + ", " + mRight + '}';
    }
}
