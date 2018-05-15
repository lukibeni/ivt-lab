package hu.bme.mit.spaceship;

import java.util.*;

/**
* A simple spaceship with two proton torpedo stores and four lasers
*/
public class GT4500 implements SpaceShip {

  private List<TorpedoStore> torpedoStoreList = new ArrayList<>();

  public GT4500() {
	torpedoStoreList.add(new TorpedoStore(10));
	torpedoStoreList.add(new TorpedoStore(10));
  }

  public boolean fireLaser(FiringMode firingMode) {
    return false;
  }

  /**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty, the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
  */
  @Override
  public boolean fireTorpedo(FiringMode firingMode) {

    boolean firingSuccess = false;

    if (firingMode == FiringMode.SINGLE) {
      try {
		torpedoStoreList.get(0).fire(1);
		Collections.swap(torpedoStoreList, 0, 1);
		firingSuccess = true;
	  } catch (IllegalArgumentException e1) {
		try {
		  torpedoStoreList.get(1).fire(1);
		  Collections.swap(torpedoStoreList, 0, 1);
		  firingSuccess = true;
		} catch (IllegalArgumentException e2) {
		  // fire failure
		}
	  }
	} else { 
      // try to fire both of the torpedo stores, edit from branch-B
	  firingSuccess = true;
	  for(TorpedoStore ts : torpedoStoreList) {
	    try {
	      ts.fire(1);
	    } catch (IllegalArgumentException e) {
	      // fire failure
	      firingSuccess = false;
	    }
	  }
	}

    return firingSuccess;
  }

}
