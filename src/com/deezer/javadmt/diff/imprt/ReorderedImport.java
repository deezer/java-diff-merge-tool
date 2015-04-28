package com.deezer.javadmt.diff.imprt;

import com.deezer.javadmt.diff.ADiffInfo;
import japa.parser.ast.ImportDeclaration;

/**
 * Denotes a difference in the imports order
 *
 * @author Deezer
 */
public class ReorderedImport extends ADiffInfo {

    private ImportDeclaration mImportDecl;
    private int mPreviousOrder, mNewOrder;


    public ReorderedImport(ImportDeclaration importDecl, int previousOrder, int newOrder) {

        if (importDecl == null) {
            throw new NullPointerException();
        }

        mImportDecl = importDecl;
        mPreviousOrder = previousOrder;
        mNewOrder = newOrder;
    }

    public ImportDeclaration getImportDecl() {
        return mImportDecl;
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

        ReorderedImport that = (ReorderedImport) o;

        if (mNewOrder != that.mNewOrder) return false;
        if (mPreviousOrder != that.mPreviousOrder) return false;
        if (!mImportDecl.equals(that.mImportDecl)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mImportDecl.hashCode();
        result = 31 * result + mPreviousOrder;
        result = 31 * result + mNewOrder;
        return result;
    }

    @Override
    public String toString() {
        return "ReorderedImport{" +
                "'" + mImportDecl.toString().trim() + '\'' +
                ", " + mPreviousOrder +
                "=> " + mNewOrder +
                '}';
    }
}
