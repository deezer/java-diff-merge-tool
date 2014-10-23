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

import japa.parser.ast.visitor.DumpVisitor;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class Node {

    private final int beginLine;

    private final int beginColumn;

    private final int endLine;

    private final int endColumn;

    /**
     * This attribute can store additional information from semantic analysis.
     */
    private Object data;

    @Deprecated
    public Node(int line, int column) {
        this.beginLine = line;
        this.beginColumn = column;
        this.endLine = line;
        this.endColumn = column;
    }

    public Node(int beginLine, int beginColumn, int endLine, int endColumn) {
        this.beginLine = beginLine;
        this.beginColumn = beginColumn;
        this.endLine = endLine;
        this.endColumn = endColumn;
    }

    /**
     * Use this to retrieve additional information associated to this node.
     */
    public Object getData() {
        return data;
    }

    /**
     * Use this to store additional information to this node.
     */
    public void setData(Object data) {
        this.data = data;
    }

    public final int getBeginLine() {
        return beginLine;
    }

    public final int getBeginColumn() {
        return beginColumn;
    }

    public final int getEndLine() {
        return endLine;
    }

    public final int getEndColumn() {
        return endColumn;
    }

    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public final String toString() {
        DumpVisitor visitor = new DumpVisitor();
        accept(visitor, null);
        return visitor.getSource();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (beginColumn != node.beginColumn) return false;
        if (beginLine != node.beginLine) return false;
        if (endColumn != node.endColumn) return false;
        if (endLine != node.endLine) return false;
        if (data != null ? !data.equals(node.data) : node.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = beginLine;
        result = 31 * result + beginColumn;
        result = 31 * result + endLine;
        result = 31 * result + endColumn;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
