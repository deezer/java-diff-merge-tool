package com.deezer.javadmt.diff.pckg;

import com.deezer.javadmt.diff.ADiffInfo;
import japa.parser.ast.PackageDeclaration;

/**
 * @author Deezer
 */
public class RemovedPackage extends ADiffInfo {
    private final PackageDeclaration mPackage;

    public RemovedPackage(PackageDeclaration packageDeclaration) {
        mPackage = packageDeclaration;
    }

    public PackageDeclaration getPackage() {
        return mPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemovedPackage that = (RemovedPackage) o;

        return mPackage.equals(that.mPackage);

    }

    @Override
    public int hashCode() {
        return mPackage.hashCode();
    }


    @Override
    public String toString() {
        return "RemovedPackage{" + mPackage.toString().trim() + '}';
    }
}
