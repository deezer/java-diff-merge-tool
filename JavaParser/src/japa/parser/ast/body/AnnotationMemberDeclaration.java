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
 * Created on 21/11/2006
 */
package japa.parser.ast.body;

import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public final class AnnotationMemberDeclaration extends BodyDeclaration {

    private final int modifiers;

    private final List<AnnotationExpr> annotations;

    private final Type type;

    private final String name;

    private final Expression defaultValue;

    public AnnotationMemberDeclaration(int line, int column, JavadocComment javaDoc, int modifiers, List<AnnotationExpr> annotations, Type type, String name, Expression defaultValue) {
        super(line, column, javaDoc);
        this.modifiers = modifiers;
        this.annotations = annotations;
        this.type = type;
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public int getModifiers() {
        return modifiers;
    }

    public List<AnnotationExpr> getAnnotations() {
        return annotations;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Expression getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }
}
