package com.deezer.javadmt.diff.imprt;


import com.deezer.javadmt.diff.ADiff;
import com.deezer.javadmt.diff.ADiffInfo;
import com.deezer.javadmt.utils.Finder;
import com.deezer.javadmt.utils.ImportDeclarations;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The ImportDiff class computes the logical difference in imports between two Java files.
 *
 * @author Deezer
 */
public class ImportDiff extends ADiff {


    @Override
    public Collection<ADiffInfo> analyze(Node left, Node right) {
        if (!(left instanceof CompilationUnit)) {
            throw new IllegalArgumentException("Expecting nodes of type CompilationUnit, got " + left.getClass().getSimpleName());
        }

        if (!(right instanceof CompilationUnit)) {
            throw new IllegalArgumentException("Expecting nodes of type CompilationUnit, got " + left.getClass().getSimpleName());
        }

        return analyzeImports((CompilationUnit) left, (CompilationUnit) right);
    }

    /**
     * @param left  the left hand
     * @param right the right hand
     * @return the differences between the two compilation units
     */
    Collection<ADiffInfo> analyzeImports(CompilationUnit left, CompilationUnit right) {

        List<ADiffInfo> differences = new ArrayList<ADiffInfo>();

        // list all imports
        List<ImportDeclaration> leftImports = left.getImports();
        List<ImportDeclaration> rightImports = right.getImports();

        // Check for null cases
        if ((leftImports == null) && (rightImports == null)) {
            // ignore : do nothing
        } else if (leftImports == null) {
            for (ImportDeclaration sID : rightImports) {
                differences.add(new AddedImport(sID));
            }
        } else if (rightImports == null) {
            for (ImportDeclaration fID : leftImports) {
                differences.add(new RemovedImport(fID));
            }
        } else {

            // find reordered / missing decl
            for (int f = 0; f < leftImports.size(); ++f) {
                final ImportDeclaration fID = leftImports.get(f);

                // find import with same name in second list
                int s = Finder.findIndex(rightImports, ImportDeclarations.namePredicate(fID));

                if (s == f) {
                    // same order, nothing to do
                } else if (s >= 0) {
                    // reordered imports
                    differences.add(new ReorderedImport(fID, f, s));
                } else {
                    differences.add(new RemovedImport(fID));
                }
            }

            // find new decl
            for (int s = 0; s < rightImports.size(); ++s) {
                final ImportDeclaration sID = rightImports.get(s);

                // find import with same name in second list
                int f = Finder.findIndex(leftImports, ImportDeclarations.namePredicate(sID));

                // we only care for imports not in the first CU
                if (f < 0) {
                    differences.add(new AddedImport(sID));
                }
            }
        }

        return differences;
    }
}
