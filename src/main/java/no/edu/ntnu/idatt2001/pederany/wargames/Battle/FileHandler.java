package no.edu.ntnu.idatt2001.pederany.wargames.Battle;

import no.edu.ntnu.idatt2001.pederany.wargames.Units.*;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static String convertToString(Army army) {
        StringBuilder sb = new StringBuilder();
        sb.append(army.getName()).append("\r\n");
        for (Unit unit : army.getAllUnits()) {
            sb.append(getUnitType(unit)).append(",");
            sb.append(unit.getName()).append(",");
            sb.append(unit.getHealth());
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public static void writeToCsv(String stringToWrite, String filePath) throws IOException {
        try (FileWriter file = new FileWriter(filePath);
             PrintWriter output = new PrintWriter(file)) {
            output.print(stringToWrite);
        } catch(Exception e) {
            e.getStackTrace();
        }
    }

    public static Army readFromCsv(String filePath) throws FileNotFoundException, NumberFormatException{
        String line;
        String splitBy = ",";
        ArrayList<Unit> returnList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Army army = new Army(br.readLine().split(splitBy)[0]);

            while ((line = br.readLine()) != null) {
                String[] unit = line.split(splitBy);
                returnList.add(makeUnitFromList(unit));
            }
            army.addAll(returnList);
            return army;
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e);
        }
        return null;
    }

    public static String getUnitType(Unit unit) {
        String type = "";
        if(unit instanceof InfantryUnit) {
            type = "Infantry Unit";
        } else if(unit instanceof RangedUnit) {
            type =  "Ranged Unit";
        } else if(unit instanceof CommanderUnit) {
            type = "Commander Unit";
        } else if(unit instanceof CavalryUnit) {
            type = "Cavalry Unit";
        }
        return type;
    }

    public static Unit makeUnitFromList(String[] unitInfo) {
        return switch (unitInfo[0]) {
            case "Infantry Unit" -> (new InfantryUnit(unitInfo[1], Integer.parseInt(unitInfo[2])));
            case "Ranged Unit" -> (new RangedUnit(unitInfo[1], Integer.parseInt(unitInfo[2])));
            case "Cavalry Unit" -> (new CavalryUnit(unitInfo[1], Integer.parseInt(unitInfo[2])));
            case "Commander Unit" -> (new CommanderUnit(unitInfo[1], Integer.parseInt(unitInfo[2])));
            default -> null;
        };
    }
}
