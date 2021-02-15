package esadrcanfer.us.alumno.autotesting;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TextualUITestGenerator {

    // Name to change
    private static final String FILE = "CreateNoteTest";

    private static final String FILE_PATH = "src\\androidTest\\java\\esadrcanfer\\us\\alumno\\autotesting\\tests\\"+FILE+".java";

    WriterUtil writerUtil = new WriterUtil();

    private List<String> objectTypes = new ArrayList<>();
    private List<String> selectors = new ArrayList<>();
    private List<String> texts = new ArrayList<>();


    @Test
    public void textualUITestGenerator() throws FileNotFoundException {
        // Parser configuration:
        TypeSolver typeSolver = new CombinedTypeSolver();
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);
        // Parsing:
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
        Optional<ClassOrInterfaceDeclaration> fileParsed = cu.getClassByName(FILE);
        // We print the class to check that the class is correcly parsed
        System.out.println(fileParsed);
        // We  visit each methos and print its name:
        VoidVisitor<?> methodNameVisitor = new MethodNamePrinter();
        methodNameVisitor.visit(cu, null);
        cu.findAll(MethodDeclaration.class).forEach((md) -> {
            exploreMethods(md);
        });

        System.out.println(objectTypes);
        System.out.println(selectors);
        System.out.println(texts);
        writerUtil.write(BuildConfig.APPLICATION_ID);
        writerUtil.write("-1");
        writerUtil.write(String.valueOf(objectTypes.size()));
        for(int i = 0; i < objectTypes.size(); i++){
            writerUtil.write(objectTypes.get(i)+","+" UiSelector"+"["+selectors.get(i)+"]"+", "+texts.get(i).substring(1, texts.get(i).length() - 1));
        }
        writerUtil.write("finalState.contains(testActions[1].value)");


    }

    public void exploreMethods(MethodDeclaration md) {
        List<String> methodsToAvoid = Lists.newArrayList("matchesSafely", "childAtPosition", "describeInfo");
        if (!methodsToAvoid.contains(md.getName())) {
            processMethod(md);
        }
    }

    private void processMethod(MethodDeclaration md) {
        // Procesmoas las acciones sobre las interacciones:
        md.findAll(MethodCallExpr.class).forEach(cn -> {
            processActionAndSelectors(cn);
        });
    }

    private void processActionAndSelectors(MethodCallExpr mc) {
        for(int i = 0; i < mc.getArguments().size(); i++){
            if(!mc.getName().equals("withText") && mc.getParentNode().toString().startsWith("Optional[appCompatCheckedTextView") && mc.getParentNode().toString().contains(".atPosition")){
                selectors.add("POSITION=" + mc.getArguments().toString().substring(1, mc.getArguments().toString().length() - 1));
            }

            if((mc.getParentNode().toString().startsWith("Optional[allOf") || mc.getParentNode().toString().startsWith("Optional[Matchers.allOf"))  && mc.getParentNode().toString().contains("isDisplayed()")) {

                if (mc.getName().toString().equals("withId")) {
                    selectors.add("RESOURCE_ID=" + BuildConfig.APPLICATION_ID + ":id/" + mc.getArguments().get(i).toString().substring(5));
                }
                if (mc.getName().toString().equals("withText")) {
                    texts.add(mc.getArguments().get(i).toString());
                }
            }
            if(mc.getName().toString().equals("perform")){
                if(mc.getScope().toString().contains("appCompatCheckBox")){
                    objectTypes.add("CHECKBOX");
                }
                if(mc.getScope().toString().contains("appCompatRadioButton")){
                    objectTypes.add("RADIO_BUTTON");
                }
                if(mc.getScope().toString().contains("appCompatSpinner")){
                    objectTypes.add("SPINNER");
                    texts.add("  ");
                }
                if(mc.getScope().toString().contains("appCompatCheckedTextView")){
                    objectTypes.add("CHECKED_TEXT");
                    texts.add("  ");
                }
                if(mc.getScope().toString().contains("appCompatButton")){
                    objectTypes.add("BUTTON");
                }
            }
            if(mc.getName().toString().equals("replaceText")){
                objectTypes.add("TEXT");
                texts.add(mc.getArgument(i).toString());
            }
        }
    }

    private class MethodNamePrinter extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
        }
    }
}
