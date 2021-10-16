package com.nothing.allargsconstructor

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.ConstructorNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.ast.ClassNode.EMPTY_ARRAY
import static org.codehaus.groovy.ast.tools.GeneralUtils.*
import static org.codehaus.groovy.control.CompilePhase.CANONICALIZATION

@GroovyASTTransformation(phase = CANONICALIZATION)
class AllArgsConstructorASTTransformation extends AbstractASTTransformation {
    void visit(ASTNode[] nodes, SourceUnit source) {
        def cNode = nodes[1] as ClassNode

        def paramsList = getInstanceNonPropertyFields(cNode)
                .findAll { it.type.name != 'groovy.lang.MetaClass' }
                .collect { new Parameter(it.type, it.name) }

        final BlockStatement body = paramsList.inject(new BlockStatement()) { bS, param ->
            bS.addStatement(assignS(propX(varX('this'), param.name), varX(param))); bS
        }

        cNode.addConstructor new ConstructorNode(ACC_PUBLIC, paramsList as Parameter[], EMPTY_ARRAY, body)
    }
}
