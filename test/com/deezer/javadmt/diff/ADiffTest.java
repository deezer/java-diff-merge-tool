package com.deezer.javadmt.diff;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.QualifiedNameExpr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Deezer
 */
@RunWith(Parameterized.class)
public abstract class ADiffTest {

    private final String mFirstPath, mSecondPath;
    private final List<ADiffInfo> mExpectedDiffs;
    private final String mTestName;
    private ADiff mDiff;

    /**
     * Tests the output while comparing two files
     *
     * @param firstPath     path to the first file
     * @param secondPath    path to the second file
     * @param expectedDiffs the expected diffs
     * @param testName      the (user friendly) name for the test
     */
    public ADiffTest(String firstPath, String secondPath, List<ADiffInfo> expectedDiffs, String testName) {
        mFirstPath = firstPath;
        mSecondPath = secondPath;
        mExpectedDiffs = expectedDiffs;
        mTestName = testName;
    }

    @Before
    public void setUp() {
        mDiff = diffInstance();
    }

    @Test
    public void shouldDetectDiffs() throws ParseException {
        CompilationUnit firstCU, secondCU;

        firstCU = JavaParser.parse(new File(mFirstPath));
        secondCU = JavaParser.parse(new File(mSecondPath));

        Collection<ADiffInfo> differences = mDiff.analyze(firstCU, secondCU);

        assertThat(differences)
                .isNotNull()
                .containsOnlyElementsOf(mExpectedDiffs)
                .containsAll(mExpectedDiffs);
    }

    @After
    public void tearDown() {
        mDiff = null;
    }


    protected abstract ADiff diffInstance();


    /**
     * Generates a qualified name based on the canonical class/package name
     *
     * @param name a class or package name (eg : com.example.Foo)
     * @return the Qualified name as it would be set by the parser (minus the line/column values)
     */
    protected static NameExpr qname(String name) {
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
