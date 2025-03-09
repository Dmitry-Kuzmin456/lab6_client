package util;

import model.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ask {
    private static boolean isScript = false;

    public static void setIsScript(boolean isScript) {
        Ask.isScript = isScript;
    }

    public static Government askGovernment(Scanner sc) {
        if (isScript) {MyConsole.setBlock(true);}

        while (true){
            MyConsole.print("government(" + Government.valuesList() + "): ");
            String s = sc.nextLine();
            try{
                return Government.fromDescription(s);
            } catch (IllegalArgumentException e) {
                MyConsole.println(e.getMessage());
            }
        }

    }

    public static City ask(Scanner sc) throws NoSuchElementException {
        if (isScript) {MyConsole.setBlock(true);}
        City.CityBuilder builder = new City.CityBuilder();
        String s;
        while (true){
            MyConsole.print("Name: ");
            s = sc.nextLine();
            if (s == null || s.isEmpty()) {
                MyConsole.println("name cannot be empty");
                continue;
            }
            break;
        }
        builder.setName(s);

        while (true){
            MyConsole.print("coordinate x: ");
            s = sc.nextLine();
            if (s == null || s.isEmpty() || s.split(",").length != 1) {continue;}
            try {
                Double.parseDouble(s);
            } catch (NumberFormatException e) {
                MyConsole.println("this value must be a number");
                continue;
            }
            break;
        }
        Double x = Double.parseDouble(s);

        while (true){
            MyConsole.print("coordinate y: ");
            s = sc.nextLine();
            if (s == null || s.isEmpty() || s.split(",").length != 1) {continue;}
            try {
                Float.parseFloat(s);
            } catch (NumberFormatException e) {
                MyConsole.println("this value must be a number");
                continue;
            }
            break;
        }
        builder.setCoordinates(new Coordinates(x, Float.parseFloat(s)));

        while (true){
            MyConsole.print("area: ");
            s = sc.nextLine();
            if (s == null || s.isEmpty() || s.split(",").length != 1) {continue;}
            try {
                if (Long.parseLong(s) <= 0){
                    MyConsole.println("this value must be a positive number");
                    continue;
                }
            } catch (Exception e) {
                MyConsole.println("this value must be a positive number");
                continue;
            }
            break;
        }
        builder.setArea(Long.parseLong(s));

        while (true){
            MyConsole.print("population: ");
            s = sc.nextLine();
            if (s == null || s.isEmpty() || s.split(",").length != 1) {continue;}
            try {
                if (Integer.parseInt(s) <= 0){
                    MyConsole.println("this value must be a positive number");
                    continue;
                }
            } catch (Exception e) {
                MyConsole.println("this value must be a positive number");
                continue;
            }
            break;
        }
        builder.setPopulation(Integer.parseInt(s));

        while (true){
            MyConsole.print("meters above sea level: ");
            s = sc.nextLine();
            if (s == null || s.isEmpty() || s.split(",").length != 1) {continue;}
            try {
                Float.parseFloat(s);
            } catch (NumberFormatException e) {
                MyConsole.println("this value must be a number");
                continue;
            }
            break;
        }
        builder.setMetersAboveSeaLevel(Float.parseFloat(s));

        while (true){
            MyConsole.print("climate(" + Climate.valuesList() + "): ");
            s = sc.nextLine();
            try{
                Climate.fromDescription(s);
            } catch (IllegalArgumentException e) {
                MyConsole.println(e.getMessage());
                continue;
            }
            break;
        }
        builder.setClimate(Climate.fromDescription(s));

        while (true){
            MyConsole.print("government(" + Government.valuesList() + "): ");
            s = sc.nextLine();
            try{
                Government.fromDescription(s);
            } catch (IllegalArgumentException e) {
                MyConsole.println(e.getMessage());
                continue;
            }
            break;
        }
        builder.setGovernment(Government.fromDescription(s));

        while (true){
            MyConsole.print("standard of living(" + StandardOfLiving.valuesList() + "): ");
            s = sc.nextLine();
            try{
                StandardOfLiving.fromDescription(s);
            } catch (IllegalArgumentException e) {
                MyConsole.println(e.getMessage());
                continue;
            }
            break;
        }
        builder.setStandardOfLiving(StandardOfLiving.fromDescription(s));

        while (true){
            MyConsole.print("governors age: ");
            s = sc.nextLine();
            if (s == null || s.isEmpty() || s.split(",").length != 1) {continue;}
            try {
                if (Integer.parseInt(s) <= 0){
                    MyConsole.println("this value must be a positive number");
                    continue;
                }
            } catch (Exception e) {
                MyConsole.println("this value must be a positive number");
                continue;
            }
            break;
        }
        builder.setGovernor(new Human(Long.parseLong(s)));
        MyConsole.setBlock(false);

        return builder.build();
    }
}
