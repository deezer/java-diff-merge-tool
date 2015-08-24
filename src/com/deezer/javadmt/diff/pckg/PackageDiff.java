package com.deezer.javadmt.diff.pckg;

import com.deezer.javadmt.diff.ADiff;
import com.deezer.javadmt.diff.ADiffInfo;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.Node;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The PackageDiff class computes the logical difference in packages between two Java files.
 *
 * @author Deezer
 */
public class PackageDiff extends ADiff {

    @Override
    public Collection<ADiffInfo> analyze(Node left, Node right) {
        if (!(left instanceof CompilationUnit)) {
            throw new IllegalArgumentException("Expecting nodes of type CompilationUnit, got " + left.getClass().getSimpleName());
        }

        if (!(right instanceof CompilationUnit)) {
            throw new IllegalArgumentException("Expecting nodes of type CompilationUnit, got " + left.getClass().getSimpleName());
        }

        return analyzePackages((CompilationUnit) left, (CompilationUnit) right);
    }

    Collection<ADiffInfo> analyzePackages(CompilationUnit left, CompilationUnit right) {
        List<ADiffInfo> differences = new ArrayList<ADiffInfo>();

        // get packages
        PackageDeclaration leftPackage = left.getPakage();
        PackageDeclaration rightPakage = right.getPakage();

        // check for renamed package
        if ((leftPackage == null) && (rightPakage == null)) {
            // nothing to report, both have default package
        } else if (leftPackage == null) {
            differences.add(new AddedPackage(rightPakage));
        } else if (rightPakage == null) {
            differences.add(new RemovedPackage(leftPackage));
        } else if (!leftPackage.getName().equals(rightPakage.getName())) {
            differences.add(new MovedPackage(leftPackage, rightPakage));
        }

        return differences;
    }
}
