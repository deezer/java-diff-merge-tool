package com.deezer.javadmt.diff;

/**
 * Denotes a difference in the imports order
 *
 * @author Deezer
 */
public class ImportsOrderDiffInfo extends ADiffInfo {

    private String mImportName;
    private int mPreviousOrder, mNewOrder;


    public ImportsOrderDiffInfo(String importName, int previousOrder, int newOrder) {

        if (importName == null) {
            throw new NullPointerException();
        }

        mImportName = importName;
        mPreviousOrder = previousOrder;
        mNewOrder = newOrder;
    }

    public String getImportName() {
        return mImportName;
    }

    public int getPreviousOrder() {
        return mPreviousOrder;
    }

    public int getNewOrder() {
        return mNewOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImportsOrderDiffInfo that = (ImportsOrderDiffInfo) o;

        if (mNewOrder != that.mNewOrder) return false;
        if (mPreviousOrder != that.mPreviousOrder) return false;
        if (!mImportName.equals(that.mImportName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mImportName.hashCode();
        result = 31 * result + mPreviousOrder;
        result = 31 * result + mNewOrder;
        return result;
    }

    @Override
    public String toString() {
        return "ImportsOrderDiffInfo{" +
                "'" + mImportName + '\'' +
                ", " + mPreviousOrder +
                "=> " + mNewOrder +
                '}';
    }
}
