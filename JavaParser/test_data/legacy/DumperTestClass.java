package japa.parser.ast.test.classes;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Deprecated
public class DumperTestClass<T extends List<int[]>, X> extends Base implements Serializable {

    static Class clz1 = String.class;

    Class clz2 = (String.class);

    Class clz3 = int.class;

    Class clz4 = (int.class);

    int[] arr = new int[10];

    ;

    static class Ugly {

        int x = 0;

        public static void main(String[] args) {
        }
    }

    ;

    @Deprecated
    int[][][][] arr2 = new int[10][2][1][0];

    float fff = 0x1.fffeP+127f;

    char cc = 'a';

    int[][] arr3 = { { 1, 2 }, { 3, 4 } };

    static int[] arr4[] = {};

    public static DumperTestClass t;

    static {
        arr4 = new int[][] { { 2 }, { 1 } };
    }

    {
        arr3 = new int[][] { { 2 }, { 1 } };
    }

    public enum Teste {

        asc, def
    }

    public static enum Sexo {

        m, @Deprecated
        f;

        public static enum Sexo_ implements Serializable {
        }

        private Sexo() {
        }
    }

    public static enum Enum {

        m(1) {

            void mm() {
            }
        }
        , f(2) {

            void mm() {
            }
        }
        ;

        int x;

        private Enum(int x) {
            this.x = x;
        }

        abstract void mm();
    }

    public <T, E> DumperTestClass(int x) {
        this.arr[0] = x;
        T val1 = null;
        E val2 = null;
        super.<T, E>check2(val1, val2);
        boolean b = true, y = false;
        abstract class X {

            public <D> X() {
            }

            public void m() {
            }
        }
        @Deprecated
        final class Y extends X {

            public Y() {
                super();
                Y.super.m();
            }

            public Y(int y) {
                t.<Object>super();
            }

            public Y(long x) {
                this();
            }
        }
    }

    public <T> DumperTestClass(String str) {
    }

    private class QWE extends DumperTestClass<List<int[]>, String> {

        @Deprecated
        final int z = 0;

        int i = (int) -1;

        public QWE(String... x) {
            <String>super(x[0]);
        }

        public QWE(int... x) {
            super(x[0]);
            i = x[0];
            assert true;
            assert 1 == 1 : 2;
            {
                int iii = 3;
                iii += 3;
            }
            label: {
                int iii = 1;
            }
            ;
            ;
            switch(i) {
            }
            ll: switch(i) {
                case 1:
                    System.out.println(1);
                    break ll;
                default:
                    {
                        System.out.println("default");
                        break;
                    }
                case 2:
                    System.out.println(1);
                    i++;
                    ++i;
            }
        }

        private synchronized int doSomething()[] {
            List<? extends Number> x = new ArrayList<Integer>();
            return new int[] { 1 };
        }
    }

    public static void main(String[] args) throws ParseException, IOException {
        int x = 2;
        CompilationUnit cu = parse(new File("src/japa/parser/javacc/Parser.java"));
        System.out.println(cu);
        DumperTestClass teste = new DumperTestClass(2);
        DumperTestClass.QWE qwe = teste.new QWE(1);
        if (1 + 1 == 2) {
            teste = null;
            teste = new DumperTestClass(1);
        } else {
            x = 3;
            teste = new DumperTestClass(1);
            x = 2;
        }
        if (true) x = 1; else x = 3;
        while (true) {
            while (x == 3) continue;
            break;
        }
        do {
            x++;
        } while (x < 100);
        do x++; while (x < 100);
        for (@Deprecated int i : arr4[0]) {
            x--;
        }
        for (@Deprecated final int i = 0, j = 1; i < 10; x++) {
            break;
        }
        int i, j;
        for (i = 0, j = 1; i < 10 && j < 2; i++, j--) {
            break;
        }
    }

    @AnnotationTest(value = "x")
    public static CompilationUnit parse(@Deprecated File file) throws ParseException, IOException {
        String a = ((String) "qwe");
        String x = ((String) clz1.getName());
        int y = ((Integer) (Object) x).intValue();
        synchronized (file) {
            file = null;
            file = new File("");
        }
        try {
            if (file == null) {
                throw new NullPointerException("blah");
            }
        } catch (final NullPointerException e) {
            System.out.println("catch");
        } catch (RuntimeException e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
        try {
            if (file == null) {
                throw new NullPointerException("blah");
            }
        } finally {
            System.out.println("finally");
        }
        try {
            if (file == null) {
                throw new NullPointerException("blah");
            }
        } catch (RuntimeException e) {
            System.out.println("catch");
        }
        return JavaParser.parse(file);
    }

    class A<T> {

        public <ABC> A(Integer integer, ABC string) {
        }
    }

    private <Y> void x(Map<? extends X, ? super T> x) {
        @Deprecated Comparator c = new Comparator() {

            public int compare(Object o1, Object o2) {
                A<Integer> a = new <String>A<Integer>(new Integer(11), "foo") {
                };
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        };
    }

    public @interface AnnotationTest {

        String value() default "asd";

        @Deprecated
        int[] valueI() default { 1, 2 };

        AnnotationTest valueA1() default @AnnotationTest;

        AnnotationTest valueA2() default @AnnotationTest("qwe");

        AnnotationTest valueA3() default @AnnotationTest(value = "qwe", valueI = { 1 });
    }

    ;
}

class Base {

    public <A, B> void check2(A val1, B val2) {
    }
}