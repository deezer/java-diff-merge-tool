package com.deezer.javadmt.diff.pckg;

import com.deezer.javadmt.diff.ADiffInfo;

/**
 * @author Deezer
 */
public class PackageMovedDiffInfo extends ADiffInfo {

    private final String mOldPackage, mNewPackage;

    public PackageMovedDiffInfo(String oldPackage, String newPackage) {
        if ((oldPackage == null) || (newPackage == null)) {
            throw new NullPointerException();
        }

        mOldPackage = oldPackage;
        mNewPackage = newPackage;
    }

    public String getOldPackage() {
        return mOldPackage;
    }

    public String getNewPackage() {
        return mNewPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageMovedDiffInfo that = (PackageMovedDiffInfo) o;

        if (!mNewPackage.equals(that.mNewPackage)) return false;
        if (!mOldPackage.equals(that.mOldPackage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mOldPackage.hashCode();
        result = 31 * result + mNewPackage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PackageMovedDiffInfo{" +
                "'" + mOldPackage + '\'' +
                " => '" + mNewPackage + '\'' +
                '}';
    }
}
