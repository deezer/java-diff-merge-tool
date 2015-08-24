package com.deezer.javadmt.diff.method;

import com.deezer.javadmt.diff.ADiff;
import com.deezer.javadmt.diff.ADiffInfo;
import com.deezer.javadmt.utils.Pair;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.Node;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.lang.reflect.Method;
import java.util.*;

/**
 * The MethodDiff class computes the logical difference in methods signature between two Java files.
 *
 * @author Deezer
 */
public class MethodDiff extends ADiff {


    @Override
    public Collection<ADiffInfo> analyze(Node left, Node right) {
        if (!(left instanceof TypeDeclaration)) {
            throw new IllegalArgumentException("Expecting nodes of type TypeDeclaration, got " + left.getClass().getSimpleName());
        }

        if (!(right instanceof TypeDeclaration)) {
            throw new IllegalArgumentException("Expecting nodes of type TypeDeclaration, got " + left.getClass().getSimpleName());
        }

        return analyzeMethods((TypeDeclaration) left, (TypeDeclaration) right);
    }

    /**
     * @param left  the left hand
     * @param right the right hand
     * @return the differences between the two compilation units
     */
    Collection<ADiffInfo> analyzeMethods(TypeDeclaration left, TypeDeclaration right) {

        // extract methods
        List<MethodDeclaration> leftMethods = exctractMethods(left);
        List<MethodDeclaration> rightMethods = exctractMethods(right);

        // match methods
        List<Pair<MethodDeclaration>> matched = new LinkedList<>();


        return Collections.emptyList();
    }

    /**
     * A fast match, comparing only method name and signature (the signature will be in the form
     *
     * @param leftMethods
     * @param rightMethods
     * @param matched
     */
    private void fastMatchMethods(List<MethodDeclaration> leftMethods,
                                  List<MethodDeclaration> rightMethods,
                                  List<Pair<MethodDeclaration>> matched) {

        for (MethodDeclaration method : leftMethods) {
            for (MethodDeclaration candidate : rightMethods) {

            }
        }
    }

    /**
     * Return if both methods match, using a fast test (usually only the name and signature are checked
     *
     * @param left
     * @param right
     * @return
     */
    private boolean fastMatch(MethodDeclaration left, MethodDeclaration right) {
        return false;
    }

    private List<MethodDeclaration> exctractMethods(TypeDeclaration typeDeclaration) {
        List<MethodDeclaration> methods = new ArrayList<>();

        for (BodyDeclaration bodyDeclaration : typeDeclaration.getMembers()) {
            if (bodyDeclaration instanceof MethodDeclaration) {
                methods.add((MethodDeclaration) bodyDeclaration);
            }
        }

        return methods;
    }
}
