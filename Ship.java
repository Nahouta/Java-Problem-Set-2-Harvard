//@@ 39 out of 40 points

//@@     o Your classes should include zero-arg constructors, that reference a multiple-arg constructor via this(...)

//Ship.java

/**
* This class will create a ship object, identified by name, year and engine type.
* The class will also have appropriate accessors and mutators for each of these fields, and a toString() method describing it.
* The class will also have subclasses with additional specialized fields and corresponding accessors, mutators, and toString()
* @author Tresor Habib Nahouta
* @version Last modified, Feb 26 2024
**/
public abstract class Ship {
    public enum MainEngine {
        STEAM_ENGINE,
        STEAM_TURBINE,
        GAS_TURBINE,
        DIESEL,
        ELECTRIC,
        WIND,
        HUMAN_FORCE
    }

    protected String name;
    protected int year;
    protected MainEngine engine;

    /** implicit constructor */
    public Ship() {
        this.name = "shipname";
        this.year = 2000;
        this.engine = MainEngine.STEAM_ENGINE;
    }

    /**
     * This Constructor will receive the parameters name, year and engine and create a ship object based off those.
     * @param shipname  the name of the ship
     * @param shipyear  the year the ship was built
     * @param shipengine    the engine type of the ship
     */
    public Ship(String shipname, int shipyear, MainEngine shipengine) {
        this.name = shipname;
        this.year = shipyear;
        this.engine = shipengine;
    }

    /*Mutators for the fields of the ship object */
    public void setShipName(String shipname) {
        this.name = shipname;
    }

    public void setShipYear(int shipyear) {
        this.year = shipyear;
    }

    public void setShipMainEngine(MainEngine shipEngine) {
        this.engine = shipEngine;
    }

    /*Accessors for the fields of the Ship object */
    public String getShipName () {
        return this.name;
    }

    public int getShipYear() {
        return this.year;
    }

    public MainEngine getShipMainEngine () {
        return this.engine;
    }


    /**
     * This method will print and return a description of the ship: Name, Engine and Year.
     * Since it is overriding the default toString() method of every class, it also need to return a String
     *
     * @return a String representing the description of the Ship
     */

    public String toString() {
        String string = new String("Name: "+this.name + "\nEngine Type: " + this.engine.toString() + "\nYear: " + this.year + "\n");
        System.out.println(string);
        return string;
    }

    public static void main (String [] args) {
        System.out.println("This Program will create an array of six ships, two Cruise ships, two cargo ships, and two Reefer ships,\nand iterate over each element an display some information about them using their toString() methods\n");

        /*Creating an array of ships, CargoShips, CruiseShips and the additionally created Reefer child class, and printing their descriptions using toString()*/
        Ship [] arrayOfShips = new Ship [7];
        arrayOfShips[0] = new CruiseShip("Queen Victoria", 2013, MainEngine.GAS_TURBINE, 2000, true);
        arrayOfShips[1] = new CruiseShip("CORSA", 2008, MainEngine.STEAM_TURBINE, 1500, false);
        arrayOfShips[2] = new CargoShip("MAERSK", 1995, MainEngine.DIESEL, 35000);
        arrayOfShips[3] = new CargoShip("EXXONMOBIL", 2003, MainEngine.STEAM_TURBINE, 100000);
        arrayOfShips[4] = new Reefer("Del Monte", 1999, MainEngine.GAS_TURBINE, 30000, -30, 200);
        arrayOfShips[5] = new Reefer("sea Trade Cruise Services", 2009, MainEngine.DIESEL, 40000, -45, 300);
        arrayOfShips[6] = new CruiseShip();

        for (Ship myShip : arrayOfShips) {
            myShip.toString();
        }
    }

}

/**
 * This class will create a CruiseShip template, extending the Ship class.
 * It has two additional specialized fields, maximum number of passengers and whether or not the Norovirus is spreading onboard
 */
class CruiseShip extends Ship {
    protected int maxNumPass;
    protected boolean isNorovirus;

    /*This constructor will create a CruiseShip object, with the additional parameters*/
    public CruiseShip (String shipname, int shipyear, MainEngine shipengine, int shipmaxnumpass, boolean shipisNorovirus) {
        /*Construct with the superclass fields using the superclass constructors */
        super (shipname, shipyear, shipengine);

        /*Assign the two additional parameters to the additional fields values */
        this.maxNumPass = shipmaxnumpass;
        this.isNorovirus = shipisNorovirus;
    }

    /* Classes referencing zero argument constructors */
    public CruiseShip () {
         this ("Shipname", 2000, MainEngine.GAS_TURBINE, 10000, false);
    }

    /*Mutators */
    public void setMaxPass (int shipmaxnumpass) {
        this.maxNumPass = shipmaxnumpass;
    }

    public void setNorovirus (boolean shipisNorovirus) {
        this.isNorovirus = shipisNorovirus;
    }

    /*Accessors */
    public int getMaxPass () {
        return this.maxNumPass;
    }

    public boolean getNorovirus () {
        return this.isNorovirus;
    }

    /*toString() method overriding the superclass one */
    public String toString() {
        String string = new String("Name: "+this.name + "\nMaximum Number of Passengers: " + this.maxNumPass + "\n");
        System.out.println(string);
        return string;
    }

}


/**
 * This class will create a CargoShip template, extending the Ship class.
 * It has one additional specialized field, capacity in tons
 */

class CargoShip extends Ship {
    protected double capacity;

    /*This constructor will create a CruiseShip object, with the additional parameter*/
    public CargoShip(String shipname, int shipyear, MainEngine shipengine, double shipcapacity) {
        /*Construct with the superclass fields using the superclass constructors */
        super (shipname, shipyear, shipengine);

        /*Assign the two additional parameters to the additional fields values */
        this.capacity = shipcapacity;
    }

    /*Mutators */
    public void setCapacity (double shipcapacity) {
        this.capacity = shipcapacity;
    }

    /*Accessors */
    public double getCapacity () {
        return this.capacity;
    }

    /*toString() method overriding the superclass one */
    public String toString() {
        String string = new String("Name: "+this.name + "\nCargo Capacity: " + this.capacity + "\n");
        System.out.println(string);
        return string;
    }
}

/**
 * This class will create a Reffer template, extending the CargoShip class.
 * It has two additional specialized fields, the lowest operable temperature and the maximum number of plugs available for
 * refrigerated containers
 */

class Reefer extends CargoShip {
    private int lowestTemp;
    private int maxNumPlugs;

    /*This constructor will create a CruiseShip object, with the additional parameter*/
    public Reefer (String shipname, int shipyear, MainEngine shipengine, double shipcapacity, int shiplowestTemp, int shipmaxNumPlugs) {
        /*Construct with the superclass fields using the superclass constructors */
        super (shipname, shipyear, shipengine, shipcapacity);

        /*Assign the two additional parameters to the additional fields values */
        this.lowestTemp = shiplowestTemp;
        this.maxNumPlugs = shipmaxNumPlugs;
    }

    /*Mutators */
    public void setLowestTemp (int shiplowestTemp) {

    }

    public void setMaxNumPlugs (int shipmaxNumPlugs) {

    }

    /*Accessors */
    public int getLowestTemp () {
        return this.lowestTemp;
    }

    public int getMaxNumPlugs () {
        return this.maxNumPlugs;
    }

}