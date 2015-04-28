package com.deezer.javadmt.diff;

import com.deezer.javadmt.diff.imprt.AddedImport;
import com.deezer.javadmt.diff.imprt.ImportDiff;
import com.deezer.javadmt.diff.imprt.RemovedImport;
import com.deezer.javadmt.diff.imprt.ReorderedImport;
import japa.parser.ast.ImportDeclaration;
import org.junit.runners.Parameterized;

import java.util.*;

/**
 * @author Deezer
 */
public class ImportDiffTest extends ADiffTest {


    @Parameterized.Parameters(name = "{index} : {3}")
    public static Collection generateTestParameters() {
        List<Object[]> testSets = new ArrayList<Object[]>();

        testSets.add(new Object[]{
                "./test_data/imports/base.java",
                "./test_data/imports/base.java",
                Collections.emptyList(),
                "Identity"
        });

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
    public ImportDiffTest(String firstPath, String secondPath, List<ADiffInfo> expectedDiffs, String testName) {
        super(firstPath, secondPath, expectedDiffs, testName);
    }


    @Override
    protected ADiff diffInstance() {
        return new ImportDiff();
    }
}
