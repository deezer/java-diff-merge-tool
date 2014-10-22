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
package japa.parser.ast.body;

import japa.parser.ast.TypeParameter;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public final class ClassOrInterfaceDeclaration extends TypeDeclaration {

    private final List<AnnotationExpr> annotations;

    private final boolean isInterface;

    private final List<TypeParameter> typeParameters;

    private final List<ClassOrInterfaceType> extendsList;

    private final List<ClassOrInterfaceType> implementsList;

    public ClassOrInterfaceDeclaration(int line, int column, JavadocComment javaDoc, int modifiers, List<AnnotationExpr> annotations, boolean isInterface, String name, List<TypeParameter> typeParameters, List<ClassOrInterfaceType> extendsList, List<ClassOrInterfaceType> implementsList, List<BodyDeclaration> members) {
        super(line, column, javaDoc, name, modifiers, members);
        this.annotations = annotations;
        this.isInterface = isInterface;
        this.typeParameters = typeParameters;
        this.extendsList = extendsList;
        this.implementsList = implementsList;
    }

    public List<AnnotationExpr> getAnnotations() {
        return annotations;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public List<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public List<ClassOrInterfaceType> getExtends() {
        return extendsList;
    }

    public List<ClassOrInterfaceType> getImplements() {
        return implementsList;
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
