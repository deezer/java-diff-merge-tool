package com.deezer.javadmt.diff;

import com.deezer.javadmt.diff.imprt.AddedImport;
import com.deezer.javadmt.diff.imprt.ImportDiff;
import com.deezer.javadmt.diff.imprt.RemovedImport;
import com.deezer.javadmt.diff.imprt.ReorderedImport;
import com.deezer.javadmt.diff.pckg.AddedPackage;
import com.deezer.javadmt.diff.pckg.MovedPackage;
import com.deezer.javadmt.diff.pckg.PackageDiff;
import com.deezer.javadmt.diff.pckg.RemovedPackage;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import org.junit.runners.Parameterized;

import java.util.*;

/**
 * @author Deezer
 */
public class PackageDiffTest extends ADiffTest {

    // TODO handle package level annotations ?

    @Parameterized.Parameters(name = "{index} : {3}")
    public static Collection generateTestParameters() {
        List<Object[]> testSets = new ArrayList<Object[]>();


        testSets.add(new Object[]{
                "./test_data/packages/base.java",
                "./test_data/packages/base.java",
                Collections.emptyList(),
                "Identity"
        });

        testSets.add(new Object[]{
                "./test_data/packages/base.java",
                "./test_data/packages/moved.java",
                Arrays.asList(new MovedPackage(
                        new PackageDeclaration(qname("com.sample.packages")),
                        new PackageDeclaration(qname("com.other.packages")))),
                "Package moved"
        });

        testSets.add(new Object[]{
                "./test_data/packages/base.java",
                "./test_data/packages/none.java",
                Arrays.asList(new RemovedPackage(new PackageDeclaration(qname("com.sample.packages")))),
                "Package removed"
        });

        testSets.add(new Object[]{
                "./test_data/packages/none.java",
                "./test_data/packages/base.java",
                Arrays.asList(new AddedPackage(new PackageDeclaration(qname("com.sample.packages")))),
                "Package added"
        });

        return testSets;
    }

    /**
     * Tests the output while comparing two files
     *
     * @param firstPath     path to the first file
     * @param secondPath    path to the second file
     * @param expectedDiffs the expected diffs
     * @param testName      the (user friendly) name for the test
     */
    public PackageDiffTest(String firstPath, String secondPath, List<ADiffInfo> expectedDiffs, String testName) {
        super(firstPath, secondPath, expectedDiffs, testName);
    }


    @Override
    protected ADiff diffInstance() {
        return new PackageDiff();
    }
}
