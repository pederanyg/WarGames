package no.edu.ntnu.idatt2001.pederany.wargames.Battle;

import no.edu.ntnu.idatt2001.pederany.wargames.Units.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Army {

    public String name;
    public ArrayList<Unit> units = new ArrayList<Unit>();
    public Random r = new Random();

    /**
     * a constructor for an army with a name
     * @param name  the name of the army
     */
    public Army(String name){
        this.name = name;
    }

    /**
     * a constructor for an army with a name and a list of units
     * @param name      the name of the army
     * @param units     a list of the army's units
     */
    public Army(String name, ArrayList<Unit> units){
        this.name = name;
        this.units = units;
    }

    public Army(Army army) {
        this.name = army.getName();
        for (Unit unit : army.getAllUnits()) {
            this.units.add(UnitFactory.createUnit(unit.getClass().getSimpleName(), unit.getName(), unit.getHealth()));
        }
    }

    /**
     * a get method for the army's name
     * @return      the army's name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * a method for adding a unit to an army
     * @param unit  the unit that is added to the army
     */
    public void add(Unit unit) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException("This Unit does not exist.");
        }
        units.add(unit);
    }

    /**
     * a method for adding a list of units to an army
     * @param units a list of units that are added
     */
    public void addAll(ArrayList<Unit> units){
        this.units.addAll(units);
    }

    /**
     * a method for removing a unit from an army
     * @param unit  the unit that is removed from the army
     */
    public void remove(Unit unit){
        units.remove(unit);
    }

    /**
     * a boolean method to check if an army has units in it
     * @return  true if the army has units in it, false if not
     */
    public boolean hasUnits(){
        return !units.isEmpty();
    }

    /**
     * a get method for a list of units
     * @return  a list of units
     */
    public ArrayList<Unit> getAllUnits(){
        return units;
    }

    /**
     * a get method for getting a randomly selected unit
     * @return  a randomly selected unit
     */
    public Unit getRandom(){
        return this.units.get(r.nextInt(units.size()));
    }

    /**
     * a to-string method that gets the name of the army
     * @return  the name of the army
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * a boolean method for comparing an object to an army (?)
     * @param o     the object that is compared (?)
     * @return      true if its equal, false if it's not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     * a hashcode method for systemizing an army
     * @return  an integer value that represents an army
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }

    public List<Unit> getInfantryUnits(){
        return units.stream().filter(i -> i instanceof InfantryUnit).collect(Collectors.toList());
    }

    public List<Unit> getRangedUnits(){
        return units.stream().filter(r -> r instanceof RangedUnit).collect(Collectors.toList());
    }

    public List<Unit> getCavalryUnits(){
        return units.stream().filter(c -> c instanceof CavalryUnit && c.getAttack() == 20).collect(Collectors.toList());
    }

    public List<Unit> getCommanderUnits(){
        return units.stream().filter(com -> com instanceof CommanderUnit).collect(Collectors.toList());
    }

    public int getTotalArmyHealth() {
        return units.stream().mapToInt(Unit :: getHealth).sum();
    }
}
