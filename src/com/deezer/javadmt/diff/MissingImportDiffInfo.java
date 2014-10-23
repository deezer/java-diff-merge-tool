package com.deezer.javadmt.diff;

/**
 * @author Deezer
 */
public class MissingImportDiffInfo extends ADiffInfo {
    private final String mImportName;

    public MissingImportDiffInfo(String importName) {
        if (importName == null) {
            throw new NullPointerException();
        }

        mImportName = importName;
    }

    public String getImportName() {
        return mImportName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissingImportDiffInfo that = (MissingImportDiffInfo) o;

        if (!mImportName.equals(that.mImportName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mImportName.hashCode();
    }
}
