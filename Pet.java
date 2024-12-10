//Pet.java

/**
 * This class will create a template for the Pet type with two fields,
 * the Name, year, and typical accessors and mutators
 * 
 * @author Tresor Habib Nahouta
 * @version Last modified Feb 25, 2024
 **/


public abstract class Pet {
    /*The fields for the Pet class */
    protected String name;
    protected int year;


    /**
     * This method will construct a Pet object using the parameters
     * 
     * @param name  The name of the Pet
     * @param year  The year the Pet was born
     */
    public Pet (String name, int year) {
        this.name = name;
        this.year = year;
    }

    /**
     * This method is an accessor and will return the name of the Pet object
     * 
     * @return  The name of the Pet object
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is an abstract method to be implemented by the subclasses, 
     * which returns the cry of the Pet
     * 
     * @return a String representing the cry of the Pet
     */
    public abstract String speak ();


}




