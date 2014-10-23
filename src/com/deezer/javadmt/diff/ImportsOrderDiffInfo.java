package com.deezer.javadmt.diff;

import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.QualifiedNameExpr;

/**
 * Denotes a difference in the imports order
 *
 * @author Deezer
 */
public class ImportsOrderDiffInfo extends ADiffInfo {

    private NameExpr mExpr;
    private int mPreviousOrder, mNewOrder;


    public ImportsOrderDiffInfo(NameExpr expr, int previousOrder, int newOrder) {
        mExpr = expr;
        mPreviousOrder = previousOrder;
        mNewOrder = newOrder;
    }

    public NameExpr getExpr() {
        return mExpr;
    }

    public int getPreviousOrder() {
        return mPreviousOrder;
    }

    public int getNewOrder() {
        return mNewOrder;
    }


}
