package il.ac.hit.controller.service;

import il.ac.hit.domains.WorkOuts;

import java.util.Comparator;

public class CaloriesComperator implements Comparator<WorkOuts> {
    @Override
    public int compare(WorkOuts o1, WorkOuts o2) {
        return (int) (o1.getCalories() - o2.getCalories());
    }
}
