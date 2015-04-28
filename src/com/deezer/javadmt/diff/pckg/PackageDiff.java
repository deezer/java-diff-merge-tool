package com.deezer.javadmt.diff.pckg;

import com.deezer.javadmt.diff.ADiff;
import com.deezer.javadmt.diff.ADiffInfo;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.PackageDeclaration;

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
    public Collection<ADiffInfo> analyze(CompilationUnit left, CompilationUnit right) {
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
