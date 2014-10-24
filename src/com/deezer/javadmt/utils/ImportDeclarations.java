package com.deezer.javadmt.utils;

import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.expr.NameExpr;

/**
 * @author Deezer
 */
public final class ImportDeclarations {

    /**
     * @return a predicate to match ImportDeclarations based on the name of the given declaration
     */
    public static Finder.Predicate<ImportDeclaration> namePredicate(final ImportDeclaration decl) {
        return new Finder.Predicate<ImportDeclaration>() {
            NameExpr mDeclName = decl.getName();

            @Override
            public boolean matches(ImportDeclaration item) {
                NameExpr itemName;
                itemName = item.getName();

                if (mDeclName == null) {
                    return itemName == null;
                } else {
                    return mDeclName.equals(itemName);
                }

            }
        };
    }


}
