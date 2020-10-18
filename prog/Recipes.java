import java.util.*;
import java.io.*;
import org.apache.commons.lang3.StringUtils;

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
    String recipeIngredients = "";
    String recipeSteps = "";
    String recipeTags;
    String answer;
    public void create(){
    	System.out.println();
    	System.out.println("RECIPE CREATING...");
        System.out.println(">> What is the recipe title?: ");
        recipeTitle = title.nextLine();
        if(this.checkRepeat(recipeTitle)) System.out.println("Title ALREADY EXISTED! Please change your title a little bit...:(");
        else {
        	System.out.println(">> What is the description of this recipe? ");
            recipeDescription = description.nextLine();
            System.out.println(">> Number of ingredients? ");
            int numIngs = numIngredients.nextInt();
            System.out.println(">> What are the ingredients? ");
            for(int i=0; i < numIngs; i++){
                int j = i + 1;
                System.out.println("* " + j + ": ");

                recipeIngredients += "\n" + j + ": " + ingredients.nextLine() + "\n";
                
                
            }
            // recipeIngredients = ingredients.nextLine();
            System.out.println(">> Number of steps?");
            int numSts = numSteps.nextInt();
            System.out.println(">> What are the steps?");
            for(int i=0; i < numSts; i++){
                int j = i + 1;
                System.out.println("* " + j + ": ");

                recipeSteps += "\n" + j + ": " + steps.nextLine() + "\n";
                

            }
            System.out.println(">> Are there any tags you would like to add (yes or no)?");
            answer = yesOrNo.nextLine();
            if (answer.equals("yes")){
                System.out.println(">> What are they? (!)Separate them using COMMAS: ");
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
                System.out.println("!Did not save on exit!");
            }
        }       
    }
    
    public boolean checkRepeat(String recipeTitle) {
        for(int j = 0; j < RecipeBook.recipes.size(); j++){
            // if recipe match is found, print out the details of the recipe
            if (RecipeBook.recipes.get(j).title.equals(recipeTitle)) return true;
        }
        return false;
    }


    public void retrieve(String word){
        // start searching for recipes that have already been created
        
        // Scanner search = new Scanner(System.in);
        // System.out.println("Please enter recipe title or search words: ");
        // String searchWords = search.nextLine();
        
        System.out.println("Getting " + word + "..." + "\n");

        // check for naive string match with recipe name.
        // first try to search by name
        int flag = -1;
        for(int j = 0; j < RecipeBook.recipes.size(); j++){
            // if recipe match is found, print out the details of the recipe
            if (RecipeBook.recipes.get(j).title.equals(word)){
                System.out.println("Recipe FOUND!" + "\n");
                flag = 1;
                explore(RecipeBook.recipes.get(j));
                //System.out.println(RecipeBook.recipes.get(j).title + " " + RecipeBook.recipes.get(j).ingredients + " " + RecipeBook.recipes.get(j).steps);
                break;
            } 
        }
        // try to search for tag
        if(flag == -1) {
        	for(int j = 0; j < RecipeBook.recipes.size(); j++) {
        		RecipesDetails currR = RecipeBook.recipes.get(j);
        		String[] tagArr;
        		if(currR.tags == null) continue;
        		if(currR.tags.indexOf(",") <= 0) {
        			tagArr = new String[1];
        			tagArr[0] = currR.tags;
        		}
        		else {
        			tagArr = currR.tags.split(",");
        		}
        		if(Arrays.binarySearch(tagArr, word) >= 0) {
        			System.out.println(RecipeBook.recipes.get(j).title + ": " + RecipeBook.recipes.get(j).description);
        			flag = 0;
        		}
        	}
        	if(flag == 0) {
        		System.out.println("\nAll recipes with your searching tag are found!\n");
        		System.out.println(">> Please enter the recipe you would like to look at: ");
        		Scanner key = new Scanner(System.in);
                String findWo = key.nextLine();
                flag = -1;
                for(int i = 0; i < RecipeBook.recipes.size(); i++){
                    // if recipe match is found, print out the details of the recipe
                    if (RecipeBook.recipes.get(i).title.equals(findWo)){
                        flag = 1;
                        explore(RecipeBook.recipes.get(i));
                        //System.out.println(RecipeBook.recipes.get(j).title + " " + RecipeBook.recipes.get(j).ingredients + " " + RecipeBook.recipes.get(j).steps);
                        break;
                    }
                }              
        	}        	
        }
        if(flag == -1) {
        	for(int i = 0; i < RecipeBook.recipes.size(); i++) {
//        		String str1 = "";
//        		for(int l = 0; l < word.length(); l++) {
//        			str1 += word.charAt(l);
//        		}
//        		String str2 = "";
//        		for(int l = 0; l < RecipeBook.recipes.get(i).title.length(); l++) {
//        			str2 += RecipeBook.recipes.get(l).title.charAt(l);
//        		}
        		int distance =  StringUtils.getLevenshteinDistance(word, RecipeBook.recipes.get(i).title);
        		if(distance <= 1.5) {
        			flag = 2;
        			System.out.println("\nWe did not find the one you exactly want, but we found you a very similar one!\n");
        			System.out.println(RecipeBook.recipes.get(i).title + ": " + RecipeBook.recipes.get(i).description);
        			explore(RecipeBook.recipes.get(i));
        		}
        	}
        }
        if(flag == -1) System.out.println("\nRecipe NOT FOUND! Please check your spelling a little bit more carefully...");
    }




    public void explore(RecipesDetails recipe) {
    	System.out.println();
        // let user choose the way
        Scanner opt_scan = new Scanner(System.in);
        System.out.println(">> Do you want to go through the whole recipe (w/W) or check instructions step by step (s/S)?");
        char option = opt_scan.nextLine().charAt(0);
        // split steps
        String[] steps = recipe.steps.split(";"); // previously specified
        // go through
        if(option == 'W' || option == 'w') {
            System.out.println("Here we go...");
            System.out.println("* Name: " + recipe.title);
            System.out.println("* Description: " + recipe.description);
            System.out.println("* Ingredients: " + recipe.ingredients);
            System.out.println("* Steps: ");
            for(int s = 0; s < steps.length; s++) {
                // System.out.println(s + ": " + steps[s]);
            	if(!steps[s].equals("")) System.out.println(steps[s]);
            }
        }
        else if(option == 'S' || option == 's') {
            Scanner stop = new Scanner(System.in); // use it to allow user to go step by step
            System.out.println("Going step-by-step now: ");
            for(int s = 0; s < steps.length; s++) {
                //System.out.print(steps[s]);
                String[] parseStps = steps[s].split("\n"); 
                // String dummy = stop.next(); // use it to allow user to go step by step
                for(int i = 0; i < parseStps.length; i++){
                	if(!parseStps[i].equals("")) {
                		System.out.println(parseStps[i]);
                        promptEnterKey();
                	}
                }
            }
            System.out.println("That's it! You're done :)");
        }
    }
    
    
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

       
}
