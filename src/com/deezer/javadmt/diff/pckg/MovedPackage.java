package com.deezer.javadmt.diff.pckg;

import com.deezer.javadmt.diff.ADiffInfo;
import japa.parser.ast.PackageDeclaration;

/**
 * @author Deezer
 */
public class MovedPackage extends ADiffInfo {

    private final PackageDeclaration mOldPackage, mNewPackage;

    public MovedPackage(PackageDeclaration oldPackage, PackageDeclaration newPackage) {
        if ((oldPackage == null) || (newPackage == null)) {
            throw new NullPointerException();
        }

        mOldPackage = oldPackage;
        mNewPackage = newPackage;
    }

    public PackageDeclaration getOldPackage() {
        return mOldPackage;
    }

    public PackageDeclaration getNewPackage() {
        return mNewPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovedPackage that = (MovedPackage) o;

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
        return "MovedPackage{" +
                "'" + mOldPackage.getName() + '\'' +
                " => '" + mNewPackage.getName() + '\'' +
                '}';
    }
}
