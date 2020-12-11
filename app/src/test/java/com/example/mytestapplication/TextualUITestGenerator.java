package com.example.mytestapplication;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.google.common.collect.Lists;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public class TextualUITestGenerator {

    private static final String FILE_PATH = "src/androidTest/java/com/example/mytestapplication/SimpleTest.java";
    
    @Test
    public void textualUITestGenerator() throws FileNotFoundException {
        // Parser configuration:
        TypeSolver typeSolver = new CombinedTypeSolver();
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);
        // Parsing:
        CompilationUnit cu= StaticJavaParser.parse(new File(FILE_PATH));
        Optional<ClassOrInterfaceDeclaration> simpleTest=cu.getClassByName("SimpleTest");
        // We print the class to check that the class is correcly parsed
        System.out.println(simpleTest);
        // We  visit each methos and print its name:
        VoidVisitor<?> methodNameVisitor = new MethodNamePrinter();
        methodNameVisitor.visit(cu, null);
        cu.findAll(MethodDeclaration.class).forEach(md -> {exploreMethods(md);});

    }

    public void exploreMethods(MethodDeclaration md){
        List<String> methodsToAvoid= Lists.newArrayList("matchesSafely","childAtPosition","describeInfo");
        if(!methodsToAvoid.contains(md.getName())){
            processMethod(md);
        }
    }

    private void processMethod(MethodDeclaration md) {
        // Procesamos las declaraciones de interacciones:
        md.findAll(VariableDeclarationExpr.class).forEach(vde -> {processVariableDesclaration(vde);});
        // Procesmoas las acciones sobre las interacciones:
        md.findAll(MethodCallExpr.class).forEach(cn ->{
                processAction(cn);
        });
    }

    private void processVariableDesclaration(VariableDeclarationExpr vde) {
        // Imprimimos el nombre de l
        System.out.println("Variable name:" + vde.getVariables().get(0).getName());
        // Imprimimos el tipo de selector usado y sus parámetros:
        vde.findAll(MethodCallExpr.class).forEach(mc->{findeSelector(mc);});
    }

    private void processAction(MethodCallExpr cn) {
        cn.findAll(MethodCallExpr.class).forEach(mc -> {
            if(mc.getName().toString().equals("perform"))
                System.out.println("Perform "+ mc.getArgument(0).toString()+" on "+cn.getScope().toString());
        });
    }

    private void findeSelector(MethodCallExpr mc) {
        List<String> supportedSelectors=Lists.newArrayList("withId","withText");
        switch (mc.getName().toString()){
            case "withId":
                System.out.println("BY ID:"+mc.getArguments().get(0).toString());
                break;
            case "withText":
                System.out.println("BY TEXT:"+mc.getArguments().get(0).toString());
                break;
           // default:
           //    System.out.println("Unrecognized selector:" + mc.getName().toString()) ;
        }
    }

    private static class MethodNamePrinter extends VoidVisitorAdapter<Void> {
        @Override
         public void visit(MethodDeclaration md, Void arg) {
                 super.visit(md, arg);
                 System.out.println("Method Name Printed: " + md.getName());
             }
    }
}
