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
        System.out.println("Welcome to NAVEX's Recipe Book!");
        System.out.println("Would you like to add (a/A), search (s/S), or browse recipes (b/B)? ");

        Scanner firstAnswer = new Scanner(System.in);
        String userAction = firstAnswer.nextLine();

        // call the create recipe function
        if (userAction.equalsIgnoreCase("add") || userAction.compareToIgnoreCase("a") == 0 ) {
            // Recipes recipeList = new Recipes();
            recipeList.create();
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
            
        } else if(userAction.equalsIgnoreCase("browse") || userAction.compareToIgnoreCase("b") == 0 ){
            System.out.println("browsing!");
            for(int i = 0; i < RecipeBook.recipes.size(); i++){
                System.out.println(RecipeBook.recipes.get(i).title);
            }
            System.out.println("Please enter the recipe you would like to look at: ");
            Scanner key = new Scanner(System.in);
            String findWo = key.nextLine();
            recipeList.retrieve(findWo);


            
            
        } else { //handle empty or invalid commands
            System.out.println("please enter a command...");
        }
    }
}
