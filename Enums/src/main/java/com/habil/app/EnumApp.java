package main.java.com.habil.app;

import java.util.ArrayList;
import java.util.List;

import main.java.com.habil.models.Matter;
import main.java.com.habil.models.StatesOfMatter;

public class EnumApp
{
    public static void main(String[] args)
    {
        Matter[] matterObjects = {
            new Matter(StatesOfMatter.GAS, "Oxygen"),
            new Matter(StatesOfMatter.SOLID, "Chair"),
            new Matter(StatesOfMatter.LIQUID, "Water"),
            new Matter(StatesOfMatter.LIQUID, "DiMethylMercury"),
            new Matter(StatesOfMatter.GAS, "Chlorine"),
            new Matter(StatesOfMatter.SOLID, "Thorium"),
            new Matter(StatesOfMatter.LIQUID, "Milk"),
            new Matter(StatesOfMatter.SOLID, "Spice"),
            new Matter(StatesOfMatter.GAS, "Nitrogen(II)Oxide")
        };

        List<Matter> solids = new ArrayList<>();
        List<Matter> liquids = new ArrayList<>();
        List<Matter> gases = new ArrayList<>();

        for (Matter matter : matterObjects)
        {
            switch (matter.getState()) {
                case SOLID:
                    solids.add(matter);
                    break;
                case LIQUID:
                    liquids.add(matter);
                    break;
                case GAS:
                    gases.add(matter);
                    break;
                default:
                    break;
            }
        }

        System.out.println("Solids: ");
        solids.forEach(s -> System.out.println(" - " + s.getObjectName()));

        System.out.println("Liquids: ");
        liquids.forEach(l -> System.out.println(" - " + l.getObjectName()));

        System.out.println("Gases: ");
        gases.forEach(g -> System.out.println(" - " + g.getObjectName()));
    }
}
