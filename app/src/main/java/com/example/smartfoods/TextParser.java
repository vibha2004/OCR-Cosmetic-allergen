package com.example.smartfoods;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class TextParser  {

    public ArrayList<ArrayList<String>> allAllergens;
    public ArrayList<String> userAllergens = new ArrayList<>();
    public ArrayList<String> allPore; // the list of things a lactose intolerant person should not eat
    public ArrayList<String> allSensitive;
    public ArrayList<String> allOrganic;
    public ArrayList<String> allCruelty;

    public TextParser() {
        this.allAllergens = this.fillInAllergens();
        this.allPore = (new ArrayList(Arrays.asList("lanolin", "coconut", "cocoa", "shea", "wheat", "soybean", "isopropyl", "petrolatum", "sodium", "myristyl", "palmitate", "hexadecyl", "palm", "cetearyl"
        )));
        this.allSensitive = (new ArrayList(Arrays.asList("fragrance", "alcohol", "sls", "sles", "parabens", "benzoyl", "retinoids", "salicylic", "aha", "bha", "colorants", "lavender", "peppermint", "oxybenzone", "avobenzone", "formaldehyde", "lanolin"
        )));
        this.allOrganic = (new ArrayList(Arrays.asList("synthetic", "parabens", "phthalates", "sulfates", "fragrance", "silicone", "petroleum", "mineraloil", "triclosan", "phenoxyethanol", "peg", "polyethylene", "dyes", "preservatives", "artificial"
        )));
        this.allCruelty = (new ArrayList(Arrays.asList("animal","testing")));
    }

    public static void main(String[] args) {
        TextParser tt = new TextParser();

        tt.setUserPreferences("1111111111");
        ArrayList<String> ii = new ArrayList<String>(Arrays.asList(" hoiusdfhium,, oifdshj hif  1.Gelatingt"));
        ArrayList<ArrayList> done = tt.checkAllergens(ii);
        ArrayList<String> done2 = tt.checkPore(ii);
        ArrayList<String> done3 = tt.checkSensitive(ii);


        for (ArrayList<String> sub : done) {
            for (String str : sub) {
                System.out.println(str);
            }
        }

        for (String str : done2) {
            System.out.println(str);
        }

        for (String str : done3) {
            System.out.println(str);
        }

    }

    public int getUserBmr(int mass, int age, int height) {
        double menBmr = 66.473 + (13.7516 * mass) + (5.0033 * height) - (6.755 * age);
        double womenBmr = 655.0955 + (9.5634 * mass) + (1.8496 * height) + (4.6756 * age);
        int result = (int) (menBmr + womenBmr) / 2;
        return result;
    }

    public ArrayList checkNutrition(ArrayList<String> nutritionFactsInput, String mass, String age, String height) {
        ArrayList nutritionFacts = this.processInput(nutritionFactsInput);
        ArrayList returnList = new ArrayList();
        String returnString = new String();
        int userMass;
        int userAge;
        int userHeight;
        try {
            userMass = Integer.parseInt(mass);
            userAge = Integer.parseInt(age);
            userHeight = Integer.parseInt(height);
        } catch (Exception e) {
            returnList.add("The weight, age and/or height for the user is/are invalid");
            returnString += "The weight, age or height for the user is invalid";
            return returnList;
        }
        if (nutritionFacts.contains("calories")) {
            try {
                int calories = Integer.valueOf((nutritionFacts.indexOf("calories") + 1));
                int userBmr = getUserBmr(userMass, userAge, userHeight);
                int percent_cal = (int) ((userBmr / calories) * 100);
                if (calories > userBmr) {
                    returnList.add("The calorie count in this food is over the daily recommended minimum for you!");
                    returnString += " The calorie count in this food is over the daily recommended minimum for you!";
                } else if (calories == userBmr) {
                    returnList.add("The calorie count in this food is at the daily recommended minimum for you!");
                    returnString += " The calorie count in this food is at the daily recommended minimum for you!";
                } else {
                    returnList.add("The calorie count in this food is " + percent_cal + "percent the daily recommended minimum for you");
                    returnString += " The calorie count in this food is " + percent_cal + "percent the daily recommended minimum for you";
                }
            } catch (Exception e) {
                returnList.add("Calorie related data could not be calculated");
                returnString += " Calorie related data could not be calculated";
            }
        }
        if (nutritionFacts.contains("sodium")) {
            try {
                int sodium_mass = Integer.valueOf(nutritionFacts.indexOf("sodium") + 1);
                int percent_sodium = ((sodium_mass / 2300) * 100);
                if (percent_sodium > 100) {
                    returnList.add("The sodium content in this food is over the daily recommended limit of 2300 miligrams !");
                    returnString += " The sodium content in this food is over the daily recommended limit of 2300 miligrams !";
                } else if (percent_sodium == 100) {
                    returnList.add("The sodium content in this food is at the daily recommended limit of 2300 miligrams !");
                    returnString += " The sodium content in this food is at the daily recommended limit of 2300 miligrams !";
                } else {
                    returnList.add("The sodium content in this food is " + percent_sodium + "percent the daily recommended limit of 2300 miligrams");
                    returnString += "The sodium content in this food is " + percent_sodium + "percent the daily recommended limit of 2300 miligrams";
                }
            } catch (Exception e) {
                returnList.add("Sodium related data could not be calculated");
                returnString += " Sodium related data could not be calculated";
            }

        }
        if (returnString.length() > 0) {
            returnList.add(returnString);
        }
        return returnList;
    }

    public ArrayList fillInAllergens() {
        ArrayList returnList = new ArrayList();
        // paraben allergens
        returnList.add(new ArrayList(Arrays.asList("methylparaben", "ethylparaben", "propylparaben", "butylparaben", "isobutylparaben", "isopropylparaben", "benzylparaben","paraben")));
        // fragrance allergens
        returnList.add(new ArrayList(Arrays.asList("cinnamal", "eugenol", "isoeugenol", "cinnamyl Alcohol", "geraniol", "hydroxycitronellal", "amyl cinnamal", "linalool", "citral", "limonene", "benzyl alcohol", "coumarin", "benzyl benzoate", "farnesol", "benzyl salicylate", "hexyl cinnamal", "alpha-isomethyl ionone")));
        // additive allergens
        returnList.add(new ArrayList(Arrays.asList("tartrazine","carmine","amaranth","annatto","azodyes","cochineal","phenylenediamine"
        )));
        // sulphate allergens
        returnList.add(new ArrayList(Arrays.asList("sodium lauryl sulfate", "sodium laureth sulfate", "ammonium lauryl sulfate", "ammonium laureth sulfate", "magnesium lauryl sulfate", "magnesium laureth sulfate","sulphate")));
        // lanolin allergens
        returnList.add(new ArrayList(Arrays.asList("lanolin alcohol", "wool alcohols", "acetylated lanolin alcohol", "lanolin", "isopropyl lanolate", "ethoxylated lanolin", "peg-75 lanolin", "peg-100 lanolin", "peg-16 lanolin", "hydrogenated lanolin", "laneth-10", "laneth-15", "laneth-20", "laneth-25", "lanolin oil"
        )));
        // metal allergens
        returnList.add(new ArrayList(Arrays.asList("nickel", "cobalt", "chromium", "mercury", "gold", "silver", "lead", "zinc")));
        return returnList;
    }

    public ArrayList processInput(ArrayList<String> ingredients) {
        ArrayList<String> allIngredients = new ArrayList();
        // turn ingredient list passed in into a single array called allIngredients
        String line;
        String[] linePieces;
        for (String str : ingredients) {
            line = str.toLowerCase();
            linePieces = line.split(" ");
            for (String str2 : linePieces) {
                allIngredients.add(str2.replaceAll("[^a-zA-Z]", ""));
            }
        }
        return allIngredients;
    }

    public ArrayList checkAllergens(ArrayList<String> ingredients) {
        ArrayList returnList = new ArrayList();
        String returnString = new String();
        ArrayList<String> allIngredients = this.processInput(ingredients);
        ArrayList mapping = new ArrayList(Arrays.asList("paraben allergen(s)", "fragrance allergen(s)", "additive allergen(s)",
                "sulphate allergen(s)", "lanolin allergen(s)", "metal allergen(s)"));

        // now iterate and find any allergens
        ArrayList<String> temp;
        for (int index = 0; index < 6; index++) {
            if (this.userAllergens.get(index).equals("1")) {
                temp = new ArrayList();
                temp.add(("Possible " + mapping.get(index)));
                for (String allergen : this.allAllergens.get(index)) {
                    for (String ingredient : allIngredients) {
                        if (ingredient.contains(allergen) && temp.contains(allergen) == false) {
                            temp.add(allergen);
                        }
                    }
                }
                if (temp.size() > 1) {
                    for (String s : temp) {
                        returnString += s + " ";
                    }
                    returnList.add(temp);
                }

            }
        }
        if (returnString.length() > 0) {
            returnList.add(returnString);
        }
        return returnList;
    }

    public ArrayList checkPore(ArrayList<String> ingredients) {

        ArrayList returnList = new ArrayList();
        String returnString = new String();
        if (this.userAllergens.get(6).equals("1")) {

            returnList.add("Warning: Since you have sensitive pores you may want to avoid using this. It contains...");
            returnString += "Warning: Since you have sensitive pores you may want to avoid using this. It contains...";
            ArrayList<String> allIngredients = this.processInput(ingredients);
            for (String ingredient : allIngredients) {
                for (String item : this.allPore) {
                    if (ingredient.contains(item)) {
                        returnList.add(item);
                        returnString += " " + item;
                    }
                }
            }
            if (returnList.size() <= 1) {
                returnList = new ArrayList();
                returnString = new String();
            }
            if (returnString.length() > 0) {
                returnList.add(returnString);
            }
        }
        return returnList;
    }

    public ArrayList checkSensitive(ArrayList<String> ingredients) {
        // Return example ["The warning message here", "gluten", "pork", "beef"]
        ArrayList returnList = new ArrayList();
        String returnString = new String();
        if (this.userAllergens.get(7).equals("1")) {

            returnList.add("Warning: Since you have sensitive skin you may want to avoid using this. It contains...");
            returnString += "Warning: Since you have sensitive skin you may want to avoid using this. It contains...";
            ArrayList<String> allIngredients = this.processInput(ingredients);
            for (String ingredient : allIngredients) {
                for (String item : this.allSensitive) {
                    if (ingredient.contains(item)) {
                        returnList.add(item);
                        returnString += " " + item;
                    }
                }
            }
            if (returnList.size() <= 1) {
                returnList = new ArrayList();
                returnString = new String();
            }
        }
        if (returnString.length() > 0) {
            returnList.add(returnString);
        }
        return returnList;
    }

    public ArrayList checkOrganic(ArrayList<String> ingredients) {
        // Return example ["The warning message here", "veal", "pork", "beef"]
        ArrayList returnList = new ArrayList();
        String returnString = new String();
        if (this.userAllergens.get(8).equals("1")) {
            // Log.i("Parse", "VEGETARIAN");
            returnList.add("Warning: Since you want a 100% Organic product you might want to avoid this. It contains...");
            returnString += "Warning: Since you want a 100% Organic product you might want to avoid this. It contains...";
            ArrayList<String> allIngredients = this.processInput(ingredients);
            for (String ingredient : allIngredients) {
                for (String item : this.allOrganic) {
                    if (ingredient.contains(item)) {
                        returnList.add(item);
                        returnString += " " + item;
                    }
                }
            }
            if (returnList.size() <= 1) {
                returnList = new ArrayList();
                returnString = new String();
            }
        }
        if (returnString.length() > 0) {
            returnList.add(returnString);
        }
        return returnList;
    }

    public ArrayList checkCruelty(ArrayList<String> ingredients) {


        ArrayList returnList = new ArrayList();
        String returnString = new String();
        if (this.userAllergens.get(9).equals("1")) {
            // Log.i("Parse", "GLUTEN");
            returnList.add("Warning: Since you prefer cruelty free products you may want to avoid using this. It contains...");
            returnString += "Warning: Since you prefer cruelty free products you may want to avoid using this. It contains...";
            ArrayList<String> allIngredients = this.processInput(ingredients);
            for (String ingredient : allIngredients) {
                for (String item : this.allOrganic) {
                    if (ingredient.contains(item)) {
                        returnList.add(item);
                        returnString += " " + item;
                    }
                }
            }
            if (returnList.size() <= 1) {
                returnList = new ArrayList();
                returnString = new String();
            }
        }
        if (returnString.length() > 0) {
            returnList.add(returnString);
        }
        return returnList;
    }

    public void setUserPreferences(String input) {
        this.userAllergens.clear();
        Log.i("cake", input);
        for (int i = 0; i < 10; i++) {
            this.userAllergens.add(Character.toString(input.charAt(i)));
        }
    }
}
