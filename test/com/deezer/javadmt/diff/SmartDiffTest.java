package com.deezer.javadmt.diff;

import com.deezer.javadmt.diff.ADiffInfo;
import com.deezer.javadmt.diff.ImportsOrderDiffInfo;
import com.deezer.javadmt.diff.SmartDiff;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringBufferInputStream;
import java.util.List;

/**
 * @author Deezer
 */
// TODO @RunWith(Parameterized.class)
public class SmartDiffTest {


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    /**
     * Imports reordering should not be seen as a logical difference
     */
    @Test
    public void shouldDetectImportsReordering() throws ParseException {
        String base = "package com.sample.imports; \n" +
                "\n" +
                "import java.lang.Runnable;\n" +
                "import java.lang.Exception;\n" +
                "\n" +
                "public class RunnableException extends Exception implements Runnable {\n" +
                "}";
        String local = "package com.sample.imports; \n" +
                "\n" +
                "import java.lang.Exception;\n" +
                "import java.lang.Runnable;\n" +
                "\n" +
                "public class RunnableException extends Exception implements Runnable {\n" +
                "}";

        // parse compilation units
        CompilationUnit baseCU, localCU;
        baseCU = JavaParser.parse(new StringBufferInputStream(base));
        localCU = JavaParser.parse(new StringBufferInputStream(local));


        // compute differences
        SmartDiff smartDiff = new SmartDiff(baseCU, localCU);
        smartDiff.analyze();

        List<ADiffInfo> diffs = smartDiff.getDifferences();




    }
}
