import java.io.*;
public class RecipesDetails implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected String title;
    protected String description;
    protected String ingredients;
    protected String steps;
    protected String tags;

    public RecipesDetails(String intitle, String indescription, String iningredients, String insteps, String intags){
        this.title = intitle;
        this.description = indescription;
        this.ingredients = iningredients;
        this.steps = insteps;
        this.tags = intags;
    }

    public void printRecipeSteps(){
        
    }
}
