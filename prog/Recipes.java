import java.util.*;
import java.io.*;
public class Recipes {
    Scanner title = new Scanner(System.in);
    Scanner description = new Scanner(System.in);
    Scanner ingredients = new Scanner(System.in);
    Scanner numIngredients = new Scanner(System.in);
    Scanner steps = new Scanner(System.in);
    Scanner numSteps = new Scanner(System.in);
    Scanner tags = new Scanner(System.in);
    Scanner yesOrNo = new Scanner(System.in);
    String recipeTitle;
    String recipeDescription;
    String recipeIngredients;
    String recipeSteps;
    String recipeTags;
    String answer;
    public void create(){
        System.out.println("What is the recipe title?: ");
        recipeTitle = title.nextLine();
        System.out.println("What is the description of this recipe? ");
        recipeDescription = description.nextLine();
        System.out.println("Number of ingredients? ");
        int numIngs = numIngredients.nextInt();
        System.out.println("What are the ingredients? ");
        for(int i=0; i < numIngs; i++){
            int j = i + 1;
            System.out.println(j + ": ");
            recipeIngredients = recipeIngredients + ingredients.nextLine();
            
        }
        // recipeIngredients = ingredients.nextLine();
        System.out.println("Number of steps?");
        int numSts = numSteps.nextInt();
        System.out.println("What are the steps?");
        for(int i=0; i < numSts; i++){
            int j = i + 1;
            System.out.println(j + ": ");

            recipeSteps = recipeSteps + steps.nextLine();
            

        }
        System.out.println("Are there any tags you would like to add (yes or no)?");
        answer = yesOrNo.nextLine();
        if (answer.equals("yes")){
            System.out.println("What are they? Separate them using commas: ");
            recipeTags = tags.nextLine();
        }
        //creates the recipe object
        RecipesDetails newRecipe = new RecipesDetails(recipeTitle,recipeDescription,recipeIngredients,recipeSteps,recipeTags);
        RecipeBook.recipes.add(newRecipe);
        try {
            FileOutputStream savedFiles = new FileOutputStream("MyListOfRecipes.ser");
            ObjectOutputStream PutSavedInfo = new ObjectOutputStream(savedFiles);
            PutSavedInfo.writeObject(RecipeBook.recipes);
            PutSavedInfo.close();
            savedFiles.close();
        }
        catch(Exception e9) {
            e9.printStackTrace();
            System.out.println("Did not save on exit");
        }
    }


    public void retrieve(String word){
        // start searching for recipes that have already been created
        
        // Scanner search = new Scanner(System.in);
        // System.out.println("Please enter recipe title or search words: ");
        // String searchWords = search.nextLine();
        
        System.out.println("Searching for: " + word + "." + "\n");

        // check for naive string match with recipe name.
        for(int j = 0; j < RecipeBook.recipes.size(); j++){
            // if recipe match is found, print out the details of the recipe
            if (RecipeBook.recipes.get(j).title.equals(word)){
                System.out.println("Recipe Found!" + "\n");
                // Xintong, this is where we could call the explore function with the following details of the recipe
                explore(RecipeBook.recipes.get(j));
                System.out.println(RecipeBook.recipes.get(j).title + " " + RecipeBook.recipes.get(j).ingredients + " " + RecipeBook.recipes.get(j).steps);
                break;
            } 
        }
    }




    public void explore(RecipesDetails recipe) {
        // let user choose the way
        Scanner opt_scan = new Scanner(System.in);
        System.out.println("Do you want to go through the whole recipe (W) or check instructions step by step (S)? [W/S]");
        char option = opt_scan.nextLine().charAt(0);
        // split steps
        String[] steps = recipe.steps.split(";"); // previously specified
        // go through
        if(option == 'W') {
            System.out.println("Here we go...");
            System.out.println("* Name: " + recipe.title);
            System.out.println("* Description: " + recipe.description);
            System.out.println("* Ingredients: " + recipe.ingredients);
            System.out.println("* Steps: ");
            for(int s = 0; s < steps.length; s++) {
                System.out.println(s + ". " + steps[s]);
            }
        }
        else if(option == 'S') {
            Scanner stop = new Scanner(System.in); // use it to allow user to go step by step
            for(int s = 0; s < steps.length; s++) {
                System.out.print(steps[s]);
                String dummy = stop.nextLine(); // use it to allow user to go step by step
            }
            System.out.println("That's it! You've done :)");
        }
    }

       
}

