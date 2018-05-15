package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class FiringModeTest {

  private FiringMode firingMode;

  @Before
  public void init() {
    this.firingMode = FiringMode.SINGLE;
  }

  @Test
  public void testValues() {
    assertEquals(FiringMode.SINGLE, this.firingMode);
  }

  @Test
  public void testValueOf() {
	assertEquals(FiringMode.ALL, FiringMode.valueOf("ALL"));
  }
}
