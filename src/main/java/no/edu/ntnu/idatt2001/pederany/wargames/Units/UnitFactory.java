package no.edu.ntnu.idatt2001.pederany.wargames.Units;

import no.edu.ntnu.idatt2001.pederany.wargames.Units.*;

import java.util.ArrayList;

public class UnitFactory {

    /**
     * Creates a single unit based on unit type, name and health
     * @param unitType Type of unit to create
     * @param name Name of the unit
     * @param health Health of the unit
     * @return A unit based of the registered parameters
     */
    public static Unit createUnit(String unitType, String name, int health) {
        Unit unit = null;
        switch (unitType) {
            case "InfantryUnit" -> unit = new InfantryUnit(name, health);
            case "RangedUnit" -> unit = new RangedUnit(name, health);
            case "CavalryUnit" -> unit = new CavalryUnit(name, health);
            case "CommanderUnit" -> unit = new CommanderUnit(name, health);
        }
        return unit;
    }

    /**
     * Creates a list of units based on various parameters
     * @param unitType Type of units
     * @param name Name of the units
     * @param health Health of the units
     * @param n Amount of units
     * @return A list of units based on various parameters
     */
    public static ArrayList<Unit> createListOfUnits(String unitType, String name, int health, int n) {
        ArrayList<Unit> listOfUnits = new ArrayList<Unit>();

        switch (unitType) {
            case "InfantryUnit":
                for(int i = 0; i < n; i++) {
                    listOfUnits.add(new InfantryUnit(name, health));
                }
                break;
            case "RangedUnit":
                for(int i = 0; i < n; i++) {
                    listOfUnits.add(new RangedUnit(name, health));
                }
                break;
            case "CavalryUnit":
                for(int i = 0; i < n; i++) {
                    listOfUnits.add(new CavalryUnit(name, health));
                }
                break;
            case "CommanderUnit":
                for(int i = 0; i < n; i++) {
                    listOfUnits.add(new CommanderUnit(name, health));
                }
                break;
        }
        return listOfUnits;
    }
}