package model.outings;

import model.outings.Outing;
import model.outings.OutingPack;
import org.junit.Test;

import static org.junit.Assert.*;
import static model.builders.OutingBuilder.anyOuting;

public class OutingPackTest {

    @Test
    public void testGetPriceReturnsZeroOnAEmptyOutingPack() {
        OutingPack packOfOutings = new OutingPack();
        assertEquals(packOfOutings.getPrice(), 0.0, 0);
    }

    @Test
    public void testGetPriceCalculateCorrectlyTheTotalPriceOfThePack() {

        Outing outing1 = anyOuting().withPrice(5).build();
        Outing outing2 = anyOuting().withPrice(6).build();
        Outing outing3 = anyOuting().withPrice(8).build();

        OutingPack packOfOutings = new OutingPack();
        packOfOutings.getOutings().add(outing1);
        packOfOutings.getOutings().add(outing2);
        packOfOutings.getOutings().add(outing3);

        assertEquals(packOfOutings.getPrice(), 19.0, 0);
    }

    @Test
    public void testAddAnExistingOutingToTheListOfOutingsDoesntAddIt() {
        Outing outing1 = anyOuting().build();
        Outing outing2 = anyOuting().build();

        OutingPack packOfOutings = new OutingPack();
        packOfOutings.addOuting(outing1);
        packOfOutings.addOuting(outing1);
        packOfOutings.addOuting(outing2);

        assertEquals(packOfOutings.getOutings().size(), 2);
    }

    @Test
    public void testRemoveAnExistingOutingIsOK() {
        Outing outing1 = anyOuting().build();
        Outing outing2 = anyOuting().build();

        OutingPack packOfOutings = new OutingPack();
        packOfOutings.addOuting(outing1);
        packOfOutings.addOuting(outing2);
        packOfOutings.removeOuting(outing2);

        assertFalse(packOfOutings.getOutings().contains(outing2));
        assertTrue(packOfOutings.getOutings().contains(outing1));


    }

}
