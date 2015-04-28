package com.deezer.javadmt.diff.pckg;

import com.deezer.javadmt.diff.ADiffInfo;
import japa.parser.ast.PackageDeclaration;

/**
 * @author Deezer
 */
public class AddedPackage extends ADiffInfo {
    private final PackageDeclaration mPackage;

    public AddedPackage(PackageDeclaration packageDeclaration) {
        mPackage = packageDeclaration;
    }

    public PackageDeclaration getPackage() {
        return mPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddedPackage that = (AddedPackage) o;

        return mPackage.equals(that.mPackage);

    }

    @Override
    public int hashCode() {
        return mPackage.hashCode();
    }

    @Override
    public String toString() {
        return "AddedPackage{" + mPackage.toString().trim() + '}';
    }
}
