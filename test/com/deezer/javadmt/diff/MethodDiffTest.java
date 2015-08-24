package com.deezer.javadmt.diff;

import com.deezer.javadmt.diff.method.AddedMethod;
import com.deezer.javadmt.diff.method.MethodDiff;
import com.deezer.javadmt.diff.pckg.AddedPackage;
import com.deezer.javadmt.diff.pckg.MovedPackage;
import com.deezer.javadmt.diff.pckg.PackageDiff;
import com.deezer.javadmt.diff.pckg.RemovedPackage;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.Node;
import japa.parser.ast.PackageDeclaration;
import org.junit.runners.Parameterized;

import java.util.*;

/**
 * @author Deezer
 */
public class MethodDiffTest extends ADiffTest {


    @Parameterized.Parameters(name = "{index} : {3}")
    public static Collection generateTestParameters() {
        List<Object[]> testSets = new ArrayList<Object[]>();


        testSets.add(new Object[]{
                "./test_data/methods/base.java",
                "./test_data/methods/base.java",
                Collections.emptyList(),
                "Identity"
        });

        testSets.add(new Object[]{
                "./test_data/methods/base.java",
                "./test_data/methods/added.java",
                Arrays.asList(new AddedMethod()),
                "Method added "
        });


        return testSets;
    }

    @Override
    protected Node extractNode(CompilationUnit unit) {
        return unit.getTypes().get(0);
    }

    /**
     * Tests the output while comparing two files
     *
     * @param firstPath     path to the first file
     * @param secondPath    path to the second file
     * @param expectedDiffs the expected diffs
     * @param testName      the (user friendly) name for the test
     */
    public MethodDiffTest(String firstPath, String secondPath, List<ADiffInfo> expectedDiffs, String testName) {
        super(firstPath, secondPath, expectedDiffs, testName);
    }


    @Override
    protected ADiff diffInstance() {
        return new MethodDiff();
    }
}
