import java.util.*;
import java.io.*;
public class RecipeBook {
    static ArrayList<RecipesDetails> recipes = new ArrayList<RecipesDetails>();
    public static void main(String[] args) throws FileNotFoundException{
        try{
            //first it tries to deserialize the file and it it exists, we know this is not the first time the program is ran
            //if this is the first time this is being run, an exception will be thrown and caught by the catch block where serialization and file reading occur.
            FileInputStream savedFiles = new FileInputStream("MyListOfRecipes.ser");
            ObjectInputStream ReadSavedFiles = new ObjectInputStream(savedFiles);
            RecipeBook info = new RecipeBook();
            info.recipes = (ArrayList<RecipesDetails>) ReadSavedFiles.readObject();
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

        }
        Recipes recipeList = new Recipes();
        recipeList.create();

        //checking the contents of the recipe list
        // for(int j = 0; j < RecipeBook.recipes.size(); j++){
        //     System.out.println(RecipeBook.recipes.get(j).title + " " + RecipeBook.recipes.get(j).ingredients);
        // }
    }
}