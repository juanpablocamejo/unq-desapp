package model.outings;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class OutingPackTest {

    @Test
    public void testGetPriceReturnsZeroOnAEmptyOutingPack() {
        OutingPack packOfOutings = new OutingPack();
        assertEquals(packOfOutings.getPrice(), 0.0, 0);
    }

    @Test
    public void testGetPriceCalculateCorrectlyTheTotalPriceOfThePack() {

        Outing outing1 = Mockito.mock(Outing.class);
        Outing outing2 = Mockito.mock(Outing.class);
        Outing outing3 = Mockito.mock(Outing.class);
        when(outing1.getPrice()).thenReturn((double) 5);
        when(outing2.getPrice()).thenReturn((double) 6);
        when(outing3.getPrice()).thenReturn((double) 8);

        OutingPack packOfOutings = new OutingPack();
        packOfOutings.getOutings().add(outing1);
        packOfOutings.getOutings().add(outing2);
        packOfOutings.getOutings().add(outing3);

        assertEquals(packOfOutings.getPrice(), 19.0, 0);
    }

    @Test
    public void testAddAnExistingOutingToTheListOfOutingsDoesntAddIt() {
        Outing outing1 = Mockito.mock(Outing.class);
        Outing outing2 = Mockito.mock(Outing.class);
        when(outing1.getId()).thenReturn(1);
        when(outing2.getId()).thenReturn(2);

        OutingPack packOfOutings = new OutingPack();
        packOfOutings.addOuting(outing1);
        packOfOutings.addOuting(outing1);
        packOfOutings.addOuting(outing2);

        assertEquals(packOfOutings.getOutings().size(), 2);
    }

    @Test
    public void testRemoveAnExistingOutingIsOK() {
        Outing outing1 = Mockito.mock(Outing.class);
        Outing outing2 = Mockito.mock(Outing.class);
        when(outing1.getId()).thenReturn(1);
        when(outing2.getId()).thenReturn(2);

        OutingPack packOfOutings = new OutingPack();
        packOfOutings.addOuting(outing1);
        packOfOutings.addOuting(outing2);
        packOfOutings.removeOuting(outing2);

        assertFalse(packOfOutings.getOutings().contains(outing2));
        assertTrue(packOfOutings.getOutings().contains(outing1));


    }

}
