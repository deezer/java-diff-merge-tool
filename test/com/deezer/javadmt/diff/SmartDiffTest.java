package com.deezer.javadmt.diff;

import com.deezer.javadmt.diff.imprt.AddedImport;
import com.deezer.javadmt.diff.imprt.RemovedImport;
import com.deezer.javadmt.diff.imprt.ReorderedImport;
import com.deezer.javadmt.diff.pckg.PackageMovedDiffInfo;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.QualifiedNameExpr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Deezer
 */
@RunWith(Parameterized.class)
public class SmartDiffTest {

    private final String mFirstPath, mSecondPath;
    private final List<ADiffInfo> mExpectedDiffs;
    private final String mTestName;
    private SmartDiff mSmartDiff;

    /**
     * Tests the SmartDiff output while comparing two files
     *
     * @param firstPath     path to the first file
     * @param secondPath    path to the second file
     * @param expectedDiffs the expected diffs
     * @param testName      the (user friendly) name for the test
     */
    public SmartDiffTest(String firstPath, String secondPath, List<ADiffInfo> expectedDiffs, String testName) {
        mFirstPath = firstPath;
        mSecondPath = secondPath;
        mExpectedDiffs = expectedDiffs;
        mTestName = testName;
    }

    @Before
    public void setUp() throws ParseException {
        CompilationUnit firstCU, secondCU;

        firstCU = JavaParser.parse(new File(mFirstPath));
        secondCU = JavaParser.parse(new File(mSecondPath));

        mSmartDiff = new SmartDiff(firstCU, secondCU);
    }

    @Test
    public void shouldDetectDiffs() {
        mSmartDiff.analyze();

        assertThat(mSmartDiff.getDifferences())
                .isNotNull()
                .containsOnlyElementsOf(mExpectedDiffs)
                .containsAll(mExpectedDiffs);
    }

    @After
    public void tearDown() {
        mSmartDiff = null;
    }

    @Parameterized.Parameters(name = "{index} : {3}")
    public static Collection generateTestParameters() {
        List<Object[]> testSets = new ArrayList<Object[]>();

        // PACKAGE
        testSets.add(new Object[]{
                "./test_data/packages/base.java",
                "./test_data/packages/moved.java",
                Arrays.asList(new PackageMovedDiffInfo("com.sample.packages", "com.other.packages")),
                "Package moved"
        });

        // IMPORTS
        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/reordered.java",
                Arrays.asList(
                        new ReorderedImport(new ImportDeclaration(qname("java.lang.Runnable"), false, false), 0, 3),
                        new ReorderedImport(new ImportDeclaration(qname("java.util"), false, true), 3, 0)),
                "Imports reordering"
        });
        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/added.java",
                Arrays.asList(new AddedImport(new ImportDeclaration(qname("java.lang.Comparable"), false, false)),
                        new AddedImport(new ImportDeclaration(qname("java.lang"), false, true)),
                        new AddedImport(new ImportDeclaration(qname("java.lang.StringBuilder"), true, false))),
                "Import added"
        });
        testSets.add(new Object[]{
                "./test_data/imports/none.java",
                "./test_data/imports/base.java",
                Arrays.asList(new AddedImport(new ImportDeclaration(qname("java.lang.Runnable"), false, false)),
                        new AddedImport(new ImportDeclaration(qname("java.lang.Exception"), false, false)),
                        new AddedImport(new ImportDeclaration(qname("java.lang.Cloneable"), false, false)),
                        new AddedImport(new ImportDeclaration(qname("java.util"), false, true))),
                "Import added all (null case)"
        });
        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/removed.java",
                Arrays.asList(new RemovedImport(new ImportDeclaration(qname("java.lang.Cloneable"), false, false)),
                        new RemovedImport(new ImportDeclaration(qname("java.util"), false, true))),
                "Import removed"
        });
        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/none.java",
                Arrays.asList(new RemovedImport(new ImportDeclaration(qname("java.lang.Runnable"), false, false)),
                        new RemovedImport(new ImportDeclaration(qname("java.lang.Exception"), false, false)),
                        new RemovedImport(new ImportDeclaration(qname("java.lang.Cloneable"), false, false)),
                        new RemovedImport(new ImportDeclaration(qname("java.util"), false, true))),
                "Import removed all (null case)"
        });

        // TODO add tests around static/asterisk imports


        return testSets;
    }

    private static NameExpr qname(String name) {

        String[] tokens = name.split("\\.");


        return qname(tokens, tokens.length - 1);
    }

    private static NameExpr qname(String[] tokens, int index) {
        if (index == 0) {
            return new NameExpr(0, 0, tokens[0]);
        }

        return new QualifiedNameExpr(0, 0, qname(tokens, index - 1), tokens[index]);
    }
}