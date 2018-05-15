package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private TorpedoStore mockPrimaryTorpedoStore;
  private TorpedoStore mockSecondaryTorpedoStore;
  private GT4500 ship;

  @Before
  public void init() {
	mockPrimaryTorpedoStore = mock(TorpedoStore.class);
    mockSecondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimaryTorpedoStore, mockSecondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success_All_Good(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
	when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

	verify(mockPrimaryTorpedoStore, times(1)).fire(1);
	verify(mockSecondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Failure(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenThrow(IllegalArgumentException.class);
	when(mockSecondaryTorpedoStore.fire(1)).thenThrow(IllegalArgumentException.class);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);

	verify(mockPrimaryTorpedoStore, times(1)).fire(1);
	verify(mockSecondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_First_Good_Second_Wrong(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
	when(mockSecondaryTorpedoStore.fire(1)).thenThrow(IllegalArgumentException.class);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

	verify(mockPrimaryTorpedoStore, times(1)).fire(1);
	verify(mockSecondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_First_Wrong_Second_Good(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenThrow(IllegalArgumentException.class);
	when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

	verify(mockPrimaryTorpedoStore, times(1)).fire(1);
	verify(mockSecondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
	when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

	verify(mockPrimaryTorpedoStore, times(1)).fire(1);
	verify(mockSecondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failure_Second_Wrong(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
	when(mockSecondaryTorpedoStore.fire(1)).thenThrow(IllegalArgumentException.class);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);

	verify(mockPrimaryTorpedoStore, times(1)).fire(1);
	verify(mockSecondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failure_All_Wrong(){
    // Arrange
	when(mockPrimaryTorpedoStore.fire(1)).thenThrow(IllegalArgumentException.class);
	when(mockSecondaryTorpedoStore.fire(1)).thenThrow(IllegalArgumentException.class);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);

	verify(mockPrimaryTorpedoStore, times(1)).fire(1);
	verify(mockSecondaryTorpedoStore, times(1)).fire(1);
  }
}
