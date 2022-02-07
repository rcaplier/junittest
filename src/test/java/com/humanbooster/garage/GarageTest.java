package com.humanbooster.garage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GarageTest {

    private Garage garage;

    @BeforeEach
    void setUp() throws Exception {
        garage = new Garage(10);

        for (long i = 0; i < 5; i++) {
            Vehicule vehicule = new Vehicule(i);
            garage.enterVehicule(vehicule);
        }
    }

    @Test
    void testAddVehicule() throws NoMoreRoomException {
        Vehicule vehicule = new Vehicule(5L);
        int nbVehicules = garage.getVehicules().size();

        garage.enterVehicule(vehicule);

        Assertions.assertTrue(garage.getVehicules().contains(vehicule));
        Assertions.assertEquals(nbVehicules + 1, garage.getVehicules().size());
    }

    @Test
    void testRemoveVehicule() {
        Vehicule vehicule = new Vehicule(0L);
        int nbVehicules = garage.getVehicules().size();

        garage.vehiculeLeaves(vehicule);

        Assertions.assertFalse(garage.getVehicules().contains(vehicule));
        Assertions.assertEquals(nbVehicules - 1, garage.getVehicules().size());
    }

    @Test
    void testHasRoomTrue() {
        Boolean hasRoom = garage.hasRoom();

        Assertions.assertTrue(hasRoom);
    }

    @Test
    void testHasRoomFalse() throws NoMoreRoomException {
        for (long i = 5; i < 10; i++) {
            Vehicule vehicule = new Vehicule(i);
            garage.enterVehicule(vehicule);
        }

        Boolean hasRoom = garage.hasRoom();

        Assertions.assertFalse(hasRoom);
    }

    @Test
    void testNbPlacesLeft() {
        Long nbPlacesLeft = garage.getNbPlacesLeft();

        Assertions.assertEquals(5L, nbPlacesLeft);
    }

    @Test
    void testNbPlacesLeftWithAddedVehicule() throws NoMoreRoomException {
        Long nbPlacesLeft = garage.getNbPlacesLeft();

        Vehicule vehicule = new Vehicule(12L);
        garage.enterVehicule(vehicule);

        Long nbPlacesLeftAfterEnter = garage.getNbPlacesLeft();

        Assertions.assertEquals(nbPlacesLeft - 1, nbPlacesLeftAfterEnter);
    }

    @Test
    void testNbPlacesLeftWithRemovedVehicule() {
        Long nbPlacesLeft = garage.getNbPlacesLeft();

        Vehicule vehicule = new Vehicule(3L);
        garage.vehiculeLeaves(vehicule);

        Long nbPlacesLeftAfterRemove = garage.getNbPlacesLeft();

        Assertions.assertEquals(nbPlacesLeft + 1, nbPlacesLeftAfterRemove);
    }

    @Test
    void testNoMoreRoomException() throws NoMoreRoomException {
        for (long i = 5; i < 10; i++) {
            Vehicule vehicule = new Vehicule(i);
            garage.enterVehicule(vehicule);
        }

        Vehicule vehicule = new Vehicule(15L);

        Assertions.assertThrows(NoMoreRoomException.class, () -> {
            garage.enterVehicule(vehicule);
        });
    }

}