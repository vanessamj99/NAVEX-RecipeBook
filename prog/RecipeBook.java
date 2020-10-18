import java.util.*;
import java.io.*;
public class RecipeBook {
    static ArrayList<RecipesDetails> recipes = new ArrayList<RecipesDetails>();
    static Recipes recipeList = new Recipes();
    public static void main(String[] args) throws FileNotFoundException{
        try{
            //first it tries to deserialize the file and it it exists, we know this is not the first time the program is ran
            //if this is the first time this is being run, an exception will be thrown and caught by the catch block where serialization and file reading occur.
            FileInputStream savedFiles = new FileInputStream("MyListOfRecipes.ser");
            ObjectInputStream ReadSavedFiles = new ObjectInputStream(savedFiles);
            // RecipeBook info = new RecipeBook();
            RecipeBook.recipes = (ArrayList<RecipesDetails>) ReadSavedFiles.readObject();
            
            ReadSavedFiles.close();
            savedFiles.close();

        }
        catch(Exception e) {
            //reads the .csv file and catches the exception if this is the first time the program is ran because the .ser would not exist yet.
            File info = new File("MyListOfRecipes.csv");
            Scanner reading = new Scanner(info);
            String s = reading.nextLine();


            String[] arr;

            //reading the file
            while(reading.hasNextLine()) {
                s = reading.nextLine();
                arr = s.split(",");

                RecipesDetails recipeInfo = new RecipesDetails(arr[0],arr[1],arr[2], arr[3], arr[4]);
                recipes.add(recipeInfo);
            }
            reading.close();
        }
        //boolean for the while loop that lets the user continously add or search for recipes
        boolean notFinished = true;
        //while loop for add, searching, or browsing the recipes, asks again if another option is entered.
        boolean actionbyUser = true;

        //boolean for if the user wants to continue or exit
        boolean continueOrExitQuestion = true;

        //getting user input for it they want to continue or exit
        Scanner continueOrExit = new Scanner(System.in);
        String conOrExit;

        System.out.println("Welcome to NAVEX's Recipe Book!");
        while(notFinished){
            System.out.println("Would you like to add (a/A), search (s/S), or browse recipes (b/B)? ");
            System.out.println("For the help menu type help(h/H)");

            Scanner firstAnswer = new Scanner(System.in);
            String userAction = firstAnswer.nextLine();

            // call the create recipe function
            while(actionbyUser){
                if (userAction.equalsIgnoreCase("add") || userAction.compareToIgnoreCase("a") == 0 ) {
                    // Recipes recipeList = new Recipes();
                    recipeList.create();
                    break;
                } else if(userAction.equalsIgnoreCase("search") || userAction.compareToIgnoreCase("s") == 0 ){
                    // // start searching for recipes that have already been created
                    Scanner search = new Scanner(System.in);
                    System.out.println("Please enter recipe title or search words: ");
                    
                    String searchWords = search.nextLine();
                    // // check for naive string match with recipe name.
                    // for(int j = 0; j < RecipeBook.recipes.size(); j++){
                    //     // if recipe match is found, print out the details of the recipe
                    //     if (RecipeBook.recipes.get(j).title.equals(searchWords)){
                    //         System.out.println("Recipe Found!" + "\n");
                    //         // Xintong, this is where we could call the explore function with the following details of the recipe
                    //         recipeList.explore(RecipeBook.recipes.get(j));
                            
                    //         System.out.println(RecipeBook.recipes.get(j).title + " " + RecipeBook.recipes.get(j).ingredients + " " + RecipeBook.recipes.get(j).steps);
                    //         break;
                    //     }
                    // }
                    recipeList.retrieve(searchWords);
                    break;
                    
                } else if(userAction.equalsIgnoreCase("browse") || userAction.compareToIgnoreCase("b") == 0 ){
                    System.out.println("Displying all recipes! ");
                    for(int i = 0; i < RecipeBook.recipes.size(); i++){
                        System.out.println(RecipeBook.recipes.get(i).title + ": " + RecipeBook.recipes.get(i).description);
                    }
                    System.out.println("Please enter the recipe you would like to look at: ");
                    Scanner key = new Scanner(System.in);
                    String findWo = key.nextLine();
                    recipeList.retrieve(findWo);
                    break;
                    
                } else if(userAction.equalsIgnoreCase("help") || userAction.compareToIgnoreCase("h") == 0 ) { 
                    System.out.println("To add a recipe: add, a, or A\n");
                    System.out.println("To browse all recipes: browse, b, or B\n");
                    System.out.println("To search for a recipe: search, s, or S\n");
                    break;
                } else {
                    System.out.println("invalid command, type help (h/H) for help menu");
            }
        }

    //if the user wants to continue or exit
    while(continueOrExitQuestion){
        System.out.println("Would you like to continue (c/C) or exit (e/E)? ");
        conOrExit = continueOrExit.nextLine();
        if(conOrExit.equalsIgnoreCase("exit") || conOrExit.compareToIgnoreCase("e") == 0 ){
            continueOrExitQuestion = false;
        }
        else if(conOrExit.equalsIgnoreCase("continue") || conOrExit.compareToIgnoreCase("c") == 0 ){
            break;
        }
        else{
            System.out.println("Invalid Input! ");
            continue;
        }
    }
    if(continueOrExitQuestion == false){
        break;
    }

    }
    }
}
