package com.deezer.javadmt.diff;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.Node;

import java.util.Collection;

/**
 * An ADiff class computes the logical difference in between two Java files on a specific aspect, and returns a list
 * of diff info
 *
 * @author Deezer
 */
public abstract class ADiff {

    /**
     * Analyze the differences between two AST nodes
     *
     * @param left  the left hand
     * @param right the right hand
     * @return the differences between the two compilation units
     */
    public abstract Collection<ADiffInfo> analyze(Node left, Node right);
}
