package com.deezer.javadmt.utils;

import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.QualifiedNameExpr;

import java.util.Comparator;

/**
 * A Comparator used to sort import declarations alphabetically
 *
 * @author Deezer
 */
public final class ImportDeclarations {

    /**
     * @return a predicate to match ImportDeclarations based on the name of the given declaration
     */
    public static CollectionsFinder.Predicate<ImportDeclaration> namePredicate(final ImportDeclaration decl) {
        return new CollectionsFinder.Predicate<ImportDeclaration>() {
            NameExpr mDeclName = decl.getName();
            NameExpr mDeclQName = (mDeclName instanceof QualifiedNameExpr) ? ((QualifiedNameExpr) mDeclName).getQualifier() : null;
            Class mDeclNameClass = mDeclName.getClass();

            @Override
            public boolean matches(ImportDeclaration item) {
                NameExpr itemName, itemQName;
                itemName = item.getName();

                // Check if this is a Qualified Name
                if (itemName instanceof QualifiedNameExpr) {
                    if (mDeclQName == null) {
                        return false;
                    }

                    itemQName = ((QualifiedNameExpr) itemName).getQualifier();

                    return itemName.getName().equals(mDeclName.getName()) && itemQName.getName().equals(mDeclQName.getName());

                } else {
                    return itemName.getName().equals(mDeclName.getName());
                }
            }
        };
    }


}
