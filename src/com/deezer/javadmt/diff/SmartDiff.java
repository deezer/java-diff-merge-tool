package com.deezer.javadmt.diff;

import com.deezer.javadmt.utils.CollectionsFinder;
import com.deezer.javadmt.utils.ImportDeclarations;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The SmartDiff class computes the logical difference between two Java files.
 * <p/>
 * Instead of performing a "character" based diff, this class attempts to understand the logical meaning in the
 * modification between two class files.
 * <p/>
 * For instance, it can detect if imports have been rearranged, if a method has been renamed, if a set of instructions
 * have been encapsulated in a try-catch block, etc...
 *
 * @author Deezer
 */
public class SmartDiff {

    /**
     * the  compilation units being compared
     */
    private final CompilationUnit mFirst, mSecond;

    /**
     * the list of differences found in our analysis
     */
    private final List<ADiffInfo> mDifferences = new ArrayList<ADiffInfo>();


    public SmartDiff(CompilationUnit first, CompilationUnit second) {
        mFirst = first;
        mSecond = second;
    }

    public List<ADiffInfo> getDifferences() {
        return mDifferences;
    }

    public void analyze() {

        computeImportsDifferences();
    }


    private void computeImportsDifferences() {
        // get imports
        List<ImportDeclaration> firstIDs = mFirst.getImports();
        List<ImportDeclaration> secondIDs = mSecond.getImports();

        // find reordered / missing decl
        for (int f = 0; f < firstIDs.size(); ++f) {
            final ImportDeclaration fID = firstIDs.get(f);

            // find import with same name in second list
            int s = CollectionsFinder.findIndex(secondIDs, ImportDeclarations.namePredicate(fID));

            if (s == f) {
                // same order, nothing to do
            } else if (s >= 0) {
                // reordered imports
                mDifferences.add(new ImportsOrderDiffInfo(fID.getName(), f, s));
            } else {
                // TODO add missing info
            }

        }

    }

    private boolean areLocationIdentical(Node first, Node second) {
        return false;
    }
}
