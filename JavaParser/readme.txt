+-------------------------------------------------------------------------------+
|  Java 1.5 parser and Abstract Syntax Tree.                                    |
+-------------------------------------------------------------------------------+
|  Copyright (C) 2007 Júlio Vilmar Gesser                                       |
|  jgesser@gmail.com                                                            |
|  http://code.google.com/p/javaparser/                                         |
+-------------------------------------------------------------------------------+
|  This program is free software: you can redistribute it and/or modify         |
|  it under the terms of the GNU Lesser General Public License as published by  |
|  the Free Software Foundation, either version 3 of the License, or            |
|  (at your option) any later version.                                          |
|                                                                               |
|  This program is distributed in the hope that it will be useful,              |
|  but WITHOUT ANY WARRANTY; without even the implied warranty of               |
|  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                |
|  GNU Lesser General Public License for more details.                          |
|                                                                               |
|  You should have received a copy of the GNU Lesser General Public License     |
|  along with this program.  If not, see <http://www.gnu.org/licenses/>.        |
+-------------------------------------------------------------------------------+    

This package contains a Java 1.5 Parser with AST generation and visitor support. 
The AST records the source code structure, javadoc and comments. Soon will be 
possible change the AST nodes or create new ones to modify source code like refactoring.
This parser is based on Sreenivasa Viswanadha Java 1.5 parser.

Visit the project site, there you can get help, view some sample codes, report 
bugs and feature enhacement and download the latest version:
 	http://code.google.com/p/javaparser/


People who helped to improve this parser
(Thank you all, sorry if I miss someone)
------------------------------------------

Dmitry Kichinsky
John Li
Leon Poyyayil
Keffin Barnaby
Marc-Philippe Huget
Samuel Stanojevic
Sreenivasa Viswanadha
Stephan Heiss
Vadim TSES'KO


Version history
---------------

2008-05-28
- This project now is published at Google Code:
	- http://code.google.com/p/javaparser/

2008-05-25
- Added support for comments and javadoc to the tree. 
	- Javadocs are stored directly to members (BodyDeclaration and all deriveds (classes, methods, fields, etc.)), accessible by the method getJavadoc().
	- All comments are stored in the CompilationUnit, accessible by the method getComments().

2008-04-01
- Changed all nodes public attributes to be private and created getters to access them
- Changed the methods of the Node getLine e getColumn to getBeginLine and getBeginColumn
- Added the methods getEndLine and getEndColumn to the Node class (works only in the BlockNode)

2007-12-22
- Corrected ConditionalExpression bug

2007-10-21
- Added LGPL License

2007-10-21
- Bugs corrected:  
  - Created PackageDeclaration member of CompilationUnit to add suport for annotations in the package declaration
  - Parameterized anonymous constructor invocation
  - Explicit constructor invotation Type Arguments
  - ctrl+z ("\u001A") ar end of compilation unit

2007-10-09
- EnumConstantDeclaration annotation support corrected
- Parssing Java Unicode escape characters suport added

2007-10-03
- Bug corrected: "MotifComboPopup.this.super()" statement was generating parser error
	                    
2007-10-01
- Bug corrected: Casting signed primitive values
	double d = (double) -1;
	                    ^

2007-08-06
- Bug with the ingle line comments in the final of the unit corrected

2007-07-31
- Fixed the bug with the following expression:
	Class c = (int.class);

2007-06-26
- Bug fixes from Leon Poyyayil work
	- suport for hex floating point
	- unicode digits in indentifier 
	- MemberValueArrayInitializer

2007-03-09
- Long and Integer literal MIN_VALUE bug	

2007-02-24
- '\0' bug fixed	

2007-02-01
- Many bug fixes
- Added line/column to nodes