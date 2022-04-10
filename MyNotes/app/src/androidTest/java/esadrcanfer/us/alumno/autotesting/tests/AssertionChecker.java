package esadrcanfer.us.alumno.autotesting.tests;

import java.util.List;

public abstract class AssertionChecker {

    public static List<String> initialLabels;
    public static List<String> finalLabels;

    public abstract void assertionCheck();

    public static void setInitialLabels(List<String> labels){
        initialLabels = labels;
    }

    public static void setFinalLabels(List<String> labels){
        finalLabels = labels;
    }
}
