package the_hangout_society;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class User implements Serializable {

    private String name;
    private String City;
    private int filter1Choice;
    private int filter2Choice;
    private int choose_an_activity_or_random;
    private String name_the_activity;

    // Constructor
    public User(String name, String City, int filter1Choice,int filter2Choice , int choose_an_activity_or_random, String name_the_activity) {
        this.name = name;
        this.City = City;
        this.filter1Choice = filter1Choice;
        this.filter2Choice = filter2Choice;
        this.choose_an_activity_or_random = choose_an_activity_or_random;
        this.name_the_activity = name_the_activity;

    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return City;
    }

    public int getFilter1Choice() {
        return filter1Choice;
    }

    public int getFilter2Choice() {
        return filter2Choice;
    }

    public int getChoose_an_activity_or_random() {
        return choose_an_activity_or_random;
    }

    public String getName_the_activity() {
        return name_the_activity;
    }

   
  
}
