package com.deezer.javadmt.diff;

import com.deezer.javadmt.diff.imprt.AddedImport;
import com.deezer.javadmt.diff.imprt.RemovedImport;
import com.deezer.javadmt.diff.imprt.ReorderedImport;
import com.deezer.javadmt.diff.pckg.PackageMovedDiffInfo;
import com.deezer.javadmt.utils.Finder;
import com.deezer.javadmt.utils.ImportDeclarations;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;

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
        computePacakgeDifferences();
        computeImportsDifferences();
    }


    /**
     * Compute differences in the package declaration
     */
    private void computePacakgeDifferences() {

        // get packages
        PackageDeclaration firstPD = mFirst.getPakage();
        PackageDeclaration secondPD = mSecond.getPakage();

        // check for renamed package
        if (!firstPD.getName().equals(secondPD.getName())) {
            mDifferences.add(new PackageMovedDiffInfo(firstPD.getName().getFullName(), secondPD.getName().getFullName()));
        }

    }

    /**
     * Compute all differences in import declarations.
     */
    private void computeImportsDifferences() {
        // get imports
        List<ImportDeclaration> firstIDs = mFirst.getImports();
        List<ImportDeclaration> secondIDs = mSecond.getImports();

        // Check for null cases
        if ((firstIDs == null) && (secondIDs == null)) {
            return;
        } else if (firstIDs == null) {
            for (ImportDeclaration sID : secondIDs) {
                mDifferences.add(new AddedImport(sID));
            }
            return;
        } else if (secondIDs == null) {
            for (ImportDeclaration fID : firstIDs) {
                mDifferences.add(new RemovedImport(fID));
            }
            return;
        }

        // find reordered / missing decl
        for (int f = 0; f < firstIDs.size(); ++f) {
            final ImportDeclaration fID = firstIDs.get(f);

            // find import with same name in second list
            int s = Finder.findIndex(secondIDs, ImportDeclarations.namePredicate(fID));

            if (s == f) {
                // same order, nothing to do
            } else if (s >= 0) {
                // reordered imports
                mDifferences.add(new ReorderedImport(fID, f, s));
            } else {
                mDifferences.add(new RemovedImport(fID));
            }
        }

        // find new decl
        for (int s = 0; s < secondIDs.size(); ++s) {
            final ImportDeclaration sID = secondIDs.get(s);

            // find import with same name in second list
            int f = Finder.findIndex(firstIDs, ImportDeclarations.namePredicate(sID));

            // we only care for imports not in the first CU
            if (f < 0) {
                mDifferences.add(new AddedImport(sID));
            }
        }

    }

}
