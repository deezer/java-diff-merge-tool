/*
 * Copyright (C) 2007 J�lio Vilmar Gesser.
 * 
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 05/10/2006
 */
package japa.parser.ast;

import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ImportDeclaration extends Node {

    private final NameExpr name;

    private final boolean isStatic;

    private final boolean isAsterisk;

    public ImportDeclaration(NameExpr name, boolean isStatic, boolean isAsterisk) {
        this(0, 0, 0, 0, name, isStatic, isAsterisk);
    }

    public ImportDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, NameExpr name, boolean isStatic, boolean isAsterisk) {
        super(beginLine, beginColumn, endLine, endColumn);
        this.name = name;
        this.isStatic = isStatic;
        this.isAsterisk = isAsterisk;
    }

    public NameExpr getName() {
        return name;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isAsterisk() {
        return isAsterisk;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        // if (!super.equals(o)) return false;

        ImportDeclaration that = (ImportDeclaration) o;

        if (isStatic != that.isStatic) return false;
        if (isAsterisk != that.isAsterisk) return false;

        if (name == null) {
            return that.name == null;
        }

        if (that.name == null) {
            return false;
        }

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isStatic ? 1 : 0);
        result = 31 * result + (isAsterisk ? 1 : 0);
        return result;
    }
}
