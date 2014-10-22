/*
 * Created on 22/11/2006
 */
package japa.parser.ast.legacy;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import junit.framework.TestCase;

import java.io.*;

/**
 * @author Julio Vilmar Gesser
 */
public class TestDumper extends TestCase {

    private String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        try {
            StringBuilder ret = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                ret.append(line);
                ret.append("\n");
            }
            return ret.toString();
        } finally {
            reader.close();
        }
    }

    public void testDumpVisitor() throws Exception {
        String source = readFile("./JavaParser/test_data/legacy/DumperTestClass.java");
        CompilationUnit cu = JavaParser.parse(new StringBufferInputStream(source));
        assertEquals(source, cu.toString());
    }

    public void testJavadoc() throws Exception {
        String source = readFile("./JavaParser/test_data/legacy/JavadocTestClass.java");
        CompilationUnit cu = JavaParser.parse(new StringBufferInputStream(source));
        assertEquals(source, cu.toString());
        assertEquals(19, cu.getComments().size());
    }

    public void testComments() throws Exception {
        final String source_with_comment = //
        "package japa.parser.javacc;\n" + //
                "public class Teste {\n" + //
                "//line comment\n" + //
                "int a = 0;" + //
                "//line comment\r\n" + //
                "int b = 0;" + //
                "//line comment\r" + //
                "int c = 0;" + //
                "/* multi-line\n comment\n*/" + //
                "int d = 0;" + //
                "/** multi-line\r\n javadoc\n*/" + //
                "int e = 0;" + //
                "}\n" + //
                "//final comment" + //
                "";
        final String source_without_comment = //
        "package japa.parser.javacc;\n" + //
                "\n" + //
                "public class Teste {\n" + //
                "\n" + //
                "    int a = 0;\n" + //
                "\n" + //
                "    int b = 0;\n" + //
                "\n" + //
                "    int c = 0;\n" + //
                "\n" + //
                "    int d = 0;\n" + //
                "\n" + //
                "    /** multi-line\r\n javadoc\n*/\n" + //
                "    int e = 0;\n" + //
                "}\n" + //
                "";

        CompilationUnit cu = JavaParser.parse(new StringBufferInputStream(source_with_comment));
        assertEquals(source_without_comment, cu.toString());
        assertEquals(6, cu.getComments().size());
    }
}
