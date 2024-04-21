package org.ms.sd.lld;

/*
https://www.youtube.com/watch?v=Yn7C0x5ozx4&t=3s

import lombok.*;

//model=========================================================
@Getter
public class Cab {
    String id;
    String driverName;

    @Setter
    Trip currentTrip;
    @Setter
    Location currentLocation;
    @Setter Boolean isAvailable;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", driverName='" + driverName + '\'' +
                ", currentLocation=" + currentLocation +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

@ToString
@Getter
@AllArgsConstructor
public class Location {
    private Double x;
    private Double y;

    public Double distance(Location location2) {
        return sqrt( pow(this.x - location2.x, 2) + pow(this.y - location2.y, 2) );
    }
}

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PostResponse {

    public static final String OK_RESPONSE = "ok";
    public static final String ERROR_RESPONSE = "error";

    @JsonProperty private String status;
    @JsonProperty private String message;

    public static PostResponse ok() {
        return new PostResponse(OK_RESPONSE, null);
    }

    public static PostResponse error(final String message) {
        return new PostResponse(ERROR_RESPONSE, message);
    }
}

@Getter
@ToString
@AllArgsConstructor
public class Rider {
    String id;
    String name;
}

enum TripStatus {
    IN_PROGRESS,
    FINISHED
}

@ToString
public class Trip {
    private Rider rider;
    private Cab cab;
    private TripStatus status;
    private Double price;
    private Location fromPoint;
    private Location toPoint;

    public Trip(
            @NonNull final Rider rider,
            @NonNull final Cab cab,
            @NonNull final Double price,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint) {
        this.rider = rider;
        this.cab = cab;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.status = TripStatus.IN_PROGRESS;
    }

    public void endTrip() {
        this.status = TripStatus.FINISHED;
    }
}

//controler==============================================================

@RestController
public class CabsController {
    private CabsManager cabsManager;
    private TripsManager tripsManager;

    public CabsController(CabsManager cabsManager, TripsManager tripsManager) {
        this.cabsManager = cabsManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value = "/register/cab", method = RequestMethod.POST)
    public ResponseEntity regiserCab(final String cabId, final String driverName) {
        cabsManager.createCab(new Cab(cabId, driverName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/location", method = RequestMethod.POST)
    public ResponseEntity updateCabLocation(
            final String cabId, final Double newX, final Double newY) {

        cabsManager.updateCabLocation(cabId, new Location(newX, newY));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/availability", method = RequestMethod.POST)
    public ResponseEntity updateCabAvailability(final String cabId, final Boolean newAvailability) {
        cabsManager.updateCabAvailability(cabId, newAvailability);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/end/trip", method = RequestMethod.POST)
    public ResponseEntity endTrip(final String cabId) {
        tripsManager.endTrip(cabsManager.getCab(cabId));
        return ResponseEntity.ok("");
    }
}

@RestController
public class RidersController {
    private RidersManager ridersManager;
    private TripsManager tripsManager;

    public RidersController(RidersManager ridersManager, TripsManager tripsManager) {
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value = "/register/rider", method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId, final String riderName) {
        ridersManager.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity book(
            final String riderId,
            final Double sourceX,
            final Double sourceY,
            final Double destX,
            final Double destY) {

        tripsManager.createTrip(
                ridersManager.getRider(riderId),
                new Location(sourceX, sourceY),
                new Location(destX, destY));

        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity fetchHistory(final String riderId) {
        List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
        return ResponseEntity.ok(trips);
    }
}

//database=====================================================================

public class CabsManager {

  Map<String, Cab> cabs = new HashMap<>();

  public void createCab(@NonNull final Cab newCab) {
    if (cabs.containsKey(newCab.getId())) {
      throw new CabAlreadyExistsException();
    }

    cabs.put(newCab.getId(), newCab);
  }

  public Cab getCab(@NonNull final String cabId) {
    if (!cabs.containsKey(cabId)) {
      throw new CabNotFoundException();
    }
    return cabs.get(cabId);
  }

  public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation) {
    if (!cabs.containsKey(cabId)) {
      throw new CabNotFoundException();
    }
    cabs.get(cabId).setCurrentLocation(newLocation);
  }

  public void updateCabAvailability(
      @NonNull final String cabId, @NonNull final Boolean newAvailability) {
    if (!cabs.containsKey(cabId)) {
      throw new CabNotFoundException();
    }
    cabs.get(cabId).setIsAvailable(newAvailability);
  }

  public List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final Double distance) {
    List<Cab> result = new ArrayList<>();
    for (Cab cab : cabs.values()) {
      // TODO: Use epsilon comparison because of double
      if (cab.getIsAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
        result.add(cab);
      }
    }
    return result;
  }
}

public class RidersManager {
  Map<String, Rider> riders = new HashMap<>();

  public void createRider(@NonNull final Rider newRider) {
    if (riders.containsKey(newRider.getId())) {
      throw new RiderAlreadyExistsException();
    }

    riders.put(newRider.getId(), newRider);
  }

  public Rider getRider(@NonNull final String riderId) {
    if (!riders.containsKey(riderId)) {
      throw new RiderNotFoundException();
    }
    return riders.get(riderId);
  }
}

public class TripsManager {

  public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
  private Map<String, List<Trip>> trips = new HashMap<>();

  private CabsManager cabsManager;
  private RidersManager ridersManager;
  private CabMatchingStrategy cabMatchingStrategy;
  private PricingStrategy pricingStrategy;

  public TripsManager(
      CabsManager cabsManager,
      RidersManager ridersManager,
      CabMatchingStrategy cabMatchingStrategy,
      PricingStrategy pricingStrategy) {
    this.cabsManager = cabsManager;
    this.ridersManager = ridersManager;
    this.cabMatchingStrategy = cabMatchingStrategy;
    this.pricingStrategy = pricingStrategy;
  }

  public void createTrip(
      @NonNull final Rider rider,
      @NonNull final Location fromPoint,
      @NonNull final Location toPoint) {
    final List<Cab> closeByCabs =
        cabsManager.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
    final List<Cab> closeByAvailableCabs =
        closeByCabs.stream()
            .filter(cab -> cab.getCurrentTrip() == null)
            .collect(Collectors.toList());

    final Cab selectedCab =
        cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, fromPoint, toPoint);
    if (selectedCab == null) {
      throw new NoCabsAvailableException();
    }

    final Double price = pricingStrategy.findPrice(fromPoint, toPoint);
    final Trip newTrip = new Trip(rider, selectedCab, price, fromPoint, toPoint);
    if (!trips.containsKey(rider.getId())) {
      trips.put(rider.getId(), new ArrayList<>());
    }
    trips.get(rider.getId()).add(newTrip);
    selectedCab.setCurrentTrip(newTrip);
  }

  public List<Trip> tripHistory(@NonNull final Rider rider) {
    return trips.get(rider.getId());
  }

  public void endTrip(@NonNull final Cab cab) {
    if (cab.getCurrentTrip() == null) {
      throw new TripNotFoundException();
    }

    cab.getCurrentTrip().endTrip();
    cab.setCurrentTrip(null);
  }
}



//strategy============================================================

public interface CabMatchingStrategy {

  Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

  @Override
  public Cab matchCabToRider(
      @NonNull final Rider rider,
      @NonNull final List<Cab> candidateCabs,
      @NonNull final Location fromPoint,
      @NonNull final Location toPoint) {
    if (candidateCabs.isEmpty()) {
      return null;
    }
    return candidateCabs.get(0);
  }
}

public class DefaultPricingStrategy implements PricingStrategy {

  public static final Double PER_KM_RATE = 10.0;

  @Override
  public Double findPrice(Location fromPoint, Location toPoint) {
    return fromPoint.distance(toPoint) * PER_KM_RATE;
  }
}

public interface PricingStrategy {
  Double findPrice(Location fromPoint, Location toPoint);
}


//exception=============================

public class CabAlreadyExistsException extends RuntimeException {}
public class CabNotFoundException extends RuntimeException {}
public class NoCabsAvailableException extends RuntimeException {}
public class RiderAlreadyExistsException extends RuntimeException {}
public class RiderNotFoundException extends RuntimeException {}
public class TripNotFoundException extends RuntimeException {}


//client
public class RunnerTest {
  CabsController cabsController;
  RidersController ridersController;

  @BeforeEach
  void setUp() {
    CabsManager cabsManager = new CabsManager();
    RidersManager ridersManager = new RidersManager();

    CabMatchingStrategy cabMatchingStrategy = new DefaultCabMatchingStrategy();
    PricingStrategy pricingStrategy = new DefaultPricingStrategy();

    TripsManager tripsManager = new TripsManager(cabsManager, ridersManager, cabMatchingStrategy, pricingStrategy);

    cabsController = new CabsController(cabsManager, tripsManager);
    ridersController = new RidersController(ridersManager, tripsManager);
  }

  @Test
  void testCabBookingFlow() {

    String r1 = "r1";
    ridersController.registerRider(r1, "ud");
    String r2 = "r2";
    ridersController.registerRider(r2, "du");
    String r3 = "r3";
    ridersController.registerRider(r3, "rider3");
    String r4 = "r4";
    ridersController.registerRider(r4, "rider4");

    String c1 = "c1";
    cabsController.regiserCab(c1, "driver1");
    String c2 = "c2";
    cabsController.regiserCab(c2, "driver2");
    String c3 = "c3";
    cabsController.regiserCab(c3, "driver3");
    String c4 = "c4";
    cabsController.regiserCab(c4, "driver4");
    String c5 = "c5";
    cabsController.regiserCab(c5, "driver5");

    cabsController.updateCabLocation(c1, 1.0, 1.0);
    cabsController.updateCabLocation(c2, 2.0, 2.0); //na
    cabsController.updateCabLocation(c3, 100.0, 100.0);
    cabsController.updateCabLocation(c4, 110.0, 110.0); //na
    cabsController.updateCabLocation(c5, 4.0, 4.0);

    cabsController.updateCabAvailability(c2, false);
    cabsController.updateCabAvailability(c4, false);

    ridersController.book(r1, 0.0, 0.0, 500.0, 500.0);
    ridersController.book(r2, 0.0, 0.0, 500.0, 500.0);

    System.out.println("\n### Printing current trips for r1 and r2");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());

    cabsController.updateCabLocation(c5, 50.0, 50.0);

    System.out.println("\n### Printing current trips for r1 and r2");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());

    cabsController.endTrip(c5);

    System.out.println("\n### Printing current trips for r1 and r2");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());


    assertThrows(NoCabsAvailableException.class, () -> {
      ridersController.book(r3, 0.0, 0.0, 500.0, 500.0);
    });

    ridersController.book(r4, 48.0, 48.0, 500.0, 500.0);
    System.out.println("\n### Printing current trips for r1, r2 and r4");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());
    System.out.println(ridersController.fetchHistory(r4).getBody());

    assertThrows(RiderNotFoundException.class, () -> {
      ridersController.book("abcd", 0.0, 0.0, 500.0, 500.0);
    });

    assertThrows(RiderAlreadyExistsException.class, () -> {
      ridersController.registerRider("r1", "shjgf");
    });

    assertThrows(CabAlreadyExistsException.class, () -> {
      cabsController.regiserCab("c1", "skjhsfkj");
    });

    assertThrows(CabNotFoundException.class, () -> {
      cabsController.updateCabLocation("shss", 110.0, 110.0);
    });

    assertThrows(CabNotFoundException.class, () -> {
      cabsController.updateCabAvailability("shss", false);
    });
  }
}

*/
public class CabBookingLLD_Udit {
}
