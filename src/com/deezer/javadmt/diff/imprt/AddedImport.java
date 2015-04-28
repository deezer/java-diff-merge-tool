package com.deezer.javadmt.diff.imprt;

import com.deezer.javadmt.diff.ADiffInfo;
import japa.parser.ast.ImportDeclaration;

/**
 * @author Deezer
 */
public class AddedImport extends ADiffInfo {


    private final ImportDeclaration mImportDecl;

    public AddedImport(ImportDeclaration importDecl) {
        if (importDecl == null) {
            throw new NullPointerException();
        }

        mImportDecl = importDecl;
    }

    public ImportDeclaration getImportDecl() {
        return mImportDecl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddedImport that = (AddedImport) o;

        if (!mImportDecl.equals(that.mImportDecl)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mImportDecl.hashCode();
    }

    @Override
    public String toString() {
        return "AddedImport{"  +
                "'" + mImportDecl.toString().trim() + '\'' +
                '}';
    }
}
