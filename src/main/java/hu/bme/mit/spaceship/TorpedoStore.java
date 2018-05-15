package hu.bme.mit.spaceship;

import java.util.Random;

/**
* Class storing and managing the torpedoes of a ship
*/
public interface TorpedoStore {

  public boolean fire(int numberOfTorpedos);

  public boolean isEmpty();

  public int getTorpedoCount();
}
