package com.deezer.javadmt.diff;

import com.deezer.javadmt.diff.ADiffInfo;
import com.deezer.javadmt.diff.ImportsOrderDiffInfo;
import com.deezer.javadmt.diff.SmartDiff;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.QualifiedNameExpr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.StringBufferInputStream;
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

        assertThat(mSmartDiff.getDifferences()).isNotNull().containsAll(mExpectedDiffs);

    }

    @After
    public void tearDown() {
        mSmartDiff = null;
    }

    @Parameterized.Parameters(name = "{index} : {3}")
    public static Collection generateTestParameters() {
        List<Object[]> testSets = new ArrayList<Object[]>();

        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/reordered.java",
                Arrays.asList(new ImportsOrderDiffInfo("java.lang.Runnable", 0, 1), new ImportsOrderDiffInfo("java.lang.Exception", 1, 0)),
                "Imports reordering"
        });

        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/added.java",
                Arrays.asList(new NewImportDiffInfo("java.lang.Comparable")),
                "Import added"
        });

        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/removed.java",
                Arrays.asList(new MissingImportDiffInfo("java.lang.Cloneable")),
                "Import added"
        });

        return testSets;
    }


}
