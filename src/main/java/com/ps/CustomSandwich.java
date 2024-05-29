package com.ps;

public class CustomSandwich extends Sandwich {

    public static final String BLT = "BLT";
    public static final String PHILLY_CHEESE_STEAK = "Philly Cheese Steak";


    public CustomSandwich(String name, double price, String bread, int size, String[] meats,
                          String[] cheeses, String[] otherToppings, String[] sauces, boolean toasted) {
        super(name, price);
        setBread(bread);
        setSize(size);
        setMeats(meats);
        setCheeses(cheeses);
        setOtherToppings(otherToppings);
        setSauces(sauces);
        setToasted(toasted);
        calculatePrice();
    }

    public static CustomSandwich createBLT() {
        return new CustomSandwich(
                BLT,
                0.0,
                "white",
                8,
                new String[]{"bacon"},
                new String[]{"cheddar"},
                new String[]{"lettuce", "tomato"},
                new String[]{"ranch"},
                true
        );
    }

    public static CustomSandwich createPhillyCheeseSteak() {
        return new CustomSandwich(
                PHILLY_CHEESE_STEAK,
                0.0,
                "white",
                8,
                new String[]{"steak"},
                new String[]{"american"},
                new String[]{"peppers"},
                new String[]{"mayo"},
                true
        );
    }


    @Override
    public void productSelection() {

        System.out.println("You have selected a " + name + " sandwich.");
    }
}



