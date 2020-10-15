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
            recipeIngredients = ingredients.nextLine();
        }
        // recipeIngredients = ingredients.nextLine();
        System.out.println("Number of steps?");
        int numSts = numSteps.nextInt();
        System.out.println("What are the steps?");
        for(int i=0; i < numSts; i++){
            int j = i + 1;
            System.out.println(j + ": ");
            recipeSteps = steps.nextLine();
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
}
