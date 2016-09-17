package model;

import model.builders.OutingBuilder;
import model.outings.Outing;
import model.planning.IPlanningResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static model.builders.OutingBuilder.anyOuting;
import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void testMatchOutingsIncludesCorrectlyTheOutingsContainedOnBothLists() {

        Outing outing1 = anyOuting().withName("Outing 1").build();
        Outing outing2 = anyOuting().withName("Outing 2").build();
        Outing outing3 = anyOuting().withName("Outing 3").build();
        Outing outing4 = anyOuting().withName("Outing 4").build();

        List<IPlanningResult> listA = new ArrayList<>();
        List<IPlanningResult> listB = new ArrayList<>();

        listA.add(outing1);
        listA.add(outing3);
        listA.add(outing4);

        listB.add(outing2);
        listB.add(outing4);
        listB.add(outing1);

        assertTrue(Application.matchOutings(listA, listB).contains(outing1));
        assertFalse(Application.matchOutings(listA, listB).contains(outing2));
        assertFalse(Application.matchOutings(listA, listB).contains(outing3));
        assertTrue(Application.matchOutings(listA, listB).contains(outing4));

    }

}