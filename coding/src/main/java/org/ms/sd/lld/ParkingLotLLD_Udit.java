package org.ms.sd.lld;

/*
I own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention.

When a car enters my parking lot, I want to have a ticket issued to the driver. The ticket issuing process includes us documenting the registration number (number plate) and the colour of the car and allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them). The customer should be allocated a parking slot which is nearest to the entry. At the exit the customer returns the ticket which then marks the slot they were using as being available.

Due to government regulation, the system should provide me with the ability to find out:

Registration numbers of all cars of a particular colour.
Slot number in which a car with a given registration number is parked.
Slot numbers of all slots where a car of a particular colour is parked.
We interact with the system via a simple set of commands which produce a specific output. Please take a look at the example below, which includes all the commands you need to support - they're self explanatory. The system should allow input in two ways. Just to clarify, the same codebase should support both modes of input - we don't want two distinct submissions.

It should provide us with an interactive command prompt based shell where commands can be typed in
It should accept a filename as a parameter at the command prompt and read the commands from that file
Example: File
Input (contents of file):
create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red
park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
status
park KA-01-P-333 White
park DL-12-AA-9999 White
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KA-01-HH-3141
slot_number_for_registration_number MH-04-AY-1111

Output (to STDOUT)
Created a parking lot with 6 slots
Allocated slot number: 1
Allocated slot number: 2
Allocated slot number: 3
Allocated slot number: 4
Allocated slot number: 5
Allocated slot number: 6
Slot number 4 is free
Slot No. Registration No Colour
1 KA-01-HH-1234 White
2 KA-01-HH-9999 White
3 KA-01-BB-0001 Black
5 KA-01-HH-2701 Blue
6 KA-01-HH-3141 Black
Allocated slot number: 4
Sorry, parking lot is full
KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333
1, 2, 4
6
Not found

Example: Interactive
Assuming a parking lot with 6 slots, the following commands should be run in sequence by typing them in at a prompt and should produce output as described below the command. Note that exit terminates the process and returns control to the shell.

$ create_parking_lot 6
Created a parking lot with 6 slots

$ park KA-01-HH-1234 White
Allocated slot number: 1

$ park KA-01-HH-9999 White
Allocated slot number: 2

$ park KA-01-BB-0001 Black
Allocated slot number: 3

$ park KA-01-HH-7777 Red
Allocated slot number: 4

$ park KA-01-HH-2701 Blue
Allocated slot number: 5

$ park KA-01-HH-3141 Black
Allocated slot number: 6

$ leave 4
Slot number 4 is free

$ status
Slot No. Registration No Colour
1 KA-01-HH-1234 White
2 KA-01-HH-9999 White
3 KA-01-BB-0001 Black
5 KA-01-HH-2701 Blue
6 KA-01-HH-3141 Black

$ park KA-01-P-333 White
Allocated slot number: 4

$ park DL-12-AA-9999 White
Sorry, parking lot is full

$ registration_numbers_for_cars_with_colour White
KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333

$ slot_numbers_for_cars_with_colour White
1, 2, 4

$ slot_number_for_registration_number KA-01-HH-3141
6

$ slot_number_for_registration_number MH-04-AY-1111
Not found

$ exit
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//model=============================================
class Car {
    private String registrationNumber;
    private String color;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public Car(final String registrationNumber, final String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
}

class Command {

    private static final String SPACE = " ";
    private String commandName;
    private List<String> params;

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }


    public Command(final String inputLine) {
        final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        if (tokensList.size() == 0) {
            throw new InvalidCommandException();
        }

        commandName = tokensList.get(0).toLowerCase();
        tokensList.remove(0);
        params = tokensList;
    }

}

class ParkingLot {
    private static int MAX_CAPACITY = 100000;
    private int capacity;
    private Map<Integer, Slot> slots;

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(final int capacity) {
        if (capacity > MAX_CAPACITY || capacity <= 0) {
            throw new ParkingLotException("Invalid capacity given for parking lot.");
        }
        this.capacity = capacity;
        this.slots = new HashMap<>();
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }


    private Slot getSlot(final Integer slotNumber) {
        if (slotNumber > getCapacity() || slotNumber <= 0) {
            throw new InvalidSlotException();
        }
        final Map<Integer, Slot> allSlots = getSlots();
        if (!allSlots.containsKey(slotNumber)) {
            allSlots.put(slotNumber, new Slot(slotNumber));
        }
        return allSlots.get(slotNumber);
    }


    public Slot park(final Car car, final Integer slotNumber) {
        final Slot slot = getSlot(slotNumber);
        if (!slot.isSlotFree()) {
            throw new SlotAlreadyOccupiedException();
        }
        slot.assignCar(car);
        return slot;
    }


    public Slot makeSlotFree(final Integer slotNumber) {
        final Slot slot = getSlot(slotNumber);
        slot.unassignCar();
        return slot;
    }
}

class Slot {
    private Car parkedCar;
    private Integer slotNumber;

    public Slot(final Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public boolean isSlotFree() {
        return parkedCar == null;
    }

    public void assignCar(Car car) {
        this.parkedCar = car;
    }

    public void unassignCar() {
        this.parkedCar = null;
    }
}

//model/parking/strategy/===================================================================
interface ParkingStrategy {
    public void addSlot(Integer slotNumber);
    public void removeSlot(Integer slotNumber);
    public Integer getNextSlot();
}

class NaturalOrderingParkingStrategy implements ParkingStrategy {
    TreeSet<Integer> slotTreeSet;
    public NaturalOrderingParkingStrategy() {
        this.slotTreeSet = new TreeSet<>();
    }

    @Override
    public void addSlot(Integer slotNumber) {
        this.slotTreeSet.add(slotNumber);
    }

    @Override
    public void removeSlot(Integer slotNumber) {
        this.slotTreeSet.remove(slotNumber);
    }

    @Override
    public Integer getNextSlot() {
        if (slotTreeSet.isEmpty()) {
            throw new NoFreeSlotAvailableException();
        }
        return this.slotTreeSet.first();
    }
}

//mode=====================================================
abstract class Mode {

    private CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    public Mode(
            final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }


    protected void processCommand(final Command command) {
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    public abstract void process() throws IOException;
}

class InteractiveMode extends Mode {

    public InteractiveMode(
            final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
        super(commandExecutorFactory, outputPrinter);
    }


    @Override
    public void process() throws IOException {
        outputPrinter.welcome();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            final String input = reader.readLine();
            final Command command = new Command(input);
            processCommand(command);
            if (command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)) {
                break;
            }
        }
    }
}

class FileMode extends Mode {
    private String fileName;

    public FileMode(
            final CommandExecutorFactory commandExecutorFactory,
            final OutputPrinter outputPrinter,
            final String fileName) {
        super(commandExecutorFactory, outputPrinter);
        this.fileName = fileName;
    }

    @Override
    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            outputPrinter.invalidFile();
            return;
        }

        String input = reader.readLine();
        while (input != null) {
            final Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }
    }
}

//commands======================================================================

abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;
    protected OutputPrinter outputPrinter;

    public CommandExecutor(final ParkingLotService parkingLotService,
                           final OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
    }


    public abstract boolean validate(Command command);


    public abstract void execute(Command command);
}

class StatusCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "status";

    public StatusCommandExecutor(final ParkingLotService parkingLotService,
                                 final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(final Command command) {
        return command.getParams().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Command command) {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();

        if (occupiedSlots.isEmpty()) {
            outputPrinter.parkingLotEmpty();
            return;
        }

        outputPrinter.statusHeader();
        for (Slot slot : occupiedSlots) {
            final Car parkedCar = slot.getParkedCar();
            final String slotNumber = slot.getSlotNumber().toString();

            outputPrinter.printWithNewLine(padString(slotNumber, 12)
                    + padString(parkedCar.getRegistrationNumber(), 19) + parkedCar.getColor());
        }
    }

    private static String padString(final String word, final int length) {
        String newWord = word;
        for(int count = word.length(); count < length; count++) {
            newWord = newWord + " ";
        }
        return newWord;
    }
}

class CommandExecutorFactory {
    private Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(final ParkingLotService parkingLotService) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        commands.put(
                CreateParkingLotCommandExecutor.COMMAND_NAME,
                new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                ParkCommandExecutor.COMMAND_NAME,
                new ParkCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                LeaveCommandExecutor.COMMAND_NAME,
                new LeaveCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                StatusCommandExecutor.COMMAND_NAME,
                new StatusCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                ColorToRegNumberCommandExecutor.COMMAND_NAME,
                new ColorToRegNumberCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                ColorToSlotNumberCommandExecutor.COMMAND_NAME,
                new ColorToSlotNumberCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                SlotForRegNumberCommandExecutor.COMMAND_NAME,
                new SlotForRegNumberCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                ExitCommandExecutor.COMMAND_NAME,
                new ExitCommandExecutor(parkingLotService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(final Command command) {
        final CommandExecutor commandExecutor = commands.get(command.getCommandName());
        if (commandExecutor == null) {
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }
}

class ColorToRegNumberCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "registration_numbers_for_cars_with_colour";

    public ColorToRegNumberCommandExecutor(
            final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        return command.getParams().size() == 1;
    }


    @Override
    public void execute(final Command command) {
        final List<Slot> slotsForColor = parkingLotService.getSlotsForColor(command.getParams().get(0));
        if (slotsForColor.isEmpty()) {
            outputPrinter.notFound();
        } else {
            final String result =
                    slotsForColor.stream()
                            .map(slot -> slot.getParkedCar().getRegistrationNumber())
                            .collect(Collectors.joining(", "));
            outputPrinter.printWithNewLine(result);
        }
    }
}

class CreateParkingLotCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(
            final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        final List<String> params = command.getParams();
        if (params.size() != 1) {
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }


    @Override
    public void execute(final Command command) {
        final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
        final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);
        parkingLotService.createParkingLot(parkingLot, new NaturalOrderingParkingStrategy());
        outputPrinter.printWithNewLine(
                "Created a parking lot with " + parkingLot.getCapacity() + " slots");
    }
}

class ColorToSlotNumberCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

    public ColorToSlotNumberCommandExecutor(
            final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        return command.getParams().size() == 1;
    }


    @Override
    public void execute(final Command command) {
        final List<Slot> slotsForColor = parkingLotService.getSlotsForColor(command.getParams().get(0));
        if (slotsForColor.isEmpty()) {
            outputPrinter.notFound();
        } else {
            final String result =
                    slotsForColor.stream()
                            .map(slot -> slot.getSlotNumber().toString())
                            .collect(Collectors.joining(", "));
            outputPrinter.printWithNewLine(result);
        }
    }
}

class ExitCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "exit";

    public ExitCommandExecutor(
            final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        return command.getParams().isEmpty();
    }


    @Override
    public void execute(final Command command) {
        outputPrinter.end();
    }
}

class LeaveCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "leave";

    public LeaveCommandExecutor(final ParkingLotService parkingLotService,
                                final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        final List<String> params = command.getParams();
        if (params.size() != 1) {
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }


    @Override
    public void execute(final Command command) {
        final int slotNum = Integer.parseInt(command.getParams().get(0));
        parkingLotService.makeSlotFree(slotNum);
        outputPrinter.printWithNewLine("Slot number " + slotNum + " is free");
    }
}

class ParkCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "park";

    public ParkCommandExecutor(
            final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        return command.getParams().size() == 2;
    }


    @Override
    public void execute(final Command command) {
        final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
        try {
            final Integer slot = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Allocated slot number: " + slot);
        } catch (NoFreeSlotAvailableException exception) {
            outputPrinter.parkingLotFull();
        }
    }
}

class SlotForRegNumberCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "slot_number_for_registration_number";

    public SlotForRegNumberCommandExecutor(
            final ParkingLotService parkingLotService,
            final OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }


    @Override
    public boolean validate(final Command command) {
        return command.getParams().size() == 1;
    }


    @Override
    public void execute(final Command command) {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String regNumberToFind = command.getParams().get(0);
        final Optional<Slot> foundSlot = occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getRegistrationNumber().equals(regNumberToFind))
                .findFirst();
        if (foundSlot.isPresent()) {
            outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
        } else {
            outputPrinter.notFound();
        }
    }
}
//service==============================================================
class ParkingLotService {
    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;


    public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy) {
        if (this.parkingLot != null) {
            throw new ParkingLotException("Parking lot already exists.");
        }
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }


    public Integer park(final Car car) {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }


    public void makeSlotFree(final Integer slotNumber) {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }


    public List<Slot> getOccupiedSlots() {
        validateParkingLotExists();
        final List<Slot> occupiedSlotsList = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            if (allSlots.containsKey(i)) {
                final Slot slot = allSlots.get(i);
                if (!slot.isSlotFree()) {
                    occupiedSlotsList.add(slot);
                }
            }
        }

        return occupiedSlotsList;
    }


    private void validateParkingLotExists() {
        if (parkingLot == null) {
            throw new ParkingLotException("Parking lot does not exists to park.");
        }
    }


    public List<Slot> getSlotsForColor(final String color) {
        final List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(color))
                .collect(Collectors.toList());
    }


}

//validator===================================================================

class IntegerValidator {

    public static boolean isInteger(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}

//exception==============================================================
class ParkingLotException extends RuntimeException {

    public ParkingLotException() {
    }

    public ParkingLotException(String message) {
        super(message);
    }
}
class InvalidCommandException extends RuntimeException {

}
class InvalidModeException extends RuntimeException {

}
class InvalidSlotException extends ParkingLotException {

}
class NoFreeSlotAvailableException extends ParkingLotException {

}
class SlotAlreadyOccupiedException extends ParkingLotException {
}


//misc==================================================
class OutputPrinter {

    public void welcome() {
        printWithNewLine("Welcome to Go-Jek Parking lot.");
    }

    public void end() {
        printWithNewLine("Thanks for using Go-Jek Parking lot service.");
    }

    public void notFound() {
        printWithNewLine("Not found");
    }

    public void statusHeader() {
        printWithNewLine("Slot No.    Registration No    Colour");
    }

    public void parkingLotFull() {
        printWithNewLine("Sorry, parking lot is full");
    }

    public void parkingLotEmpty() {
        printWithNewLine("Parking lot is empty");
    }

    public void invalidFile() {
        printWithNewLine("Invalid file given.");
    }

    public void printWithNewLine(final String msg) {
        System.out.println(msg);
    }
}

//client==========================================================
public class ParkingLotLLD_Udit {
    public static void main(final String[] args) throws IOException {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(parkingLotService);

        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        } else if (isFileInputMode(args)) {
            new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
        } else {
            throw new InvalidModeException();
        }
    }


    private static boolean isFileInputMode(final String[] args) {
        return args.length == 1;
    }


    private static boolean isInteractiveMode(final String[] args) {
        return args.length == 0;
    }
}

