package the_hangout_society;



public class HangoutActivities {
    
    private int code;
    private String name;
    private int averagePricelow;
    private int averagePricehigh;
    private int numberOfPeoplelow;    
    private int numberOfPeoplehigh;

    
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAveragePricelow() {
        return averagePricelow;
    }

    public void setAveragePricelow(int averagePricelow) {
        this.averagePricelow = averagePricelow;
    }

    public int getAveragePricehigh() {
        return averagePricehigh;
    }

    public void setAveragePricehigh(int averagePricehigh) {
        this.averagePricehigh = averagePricehigh;
    }

    public int getNumberOfPeoplelow() {
        return numberOfPeoplelow;
    }

    public void setNumberOfPeoplelow(int numberOfPeoplelow) {
        this.numberOfPeoplelow = numberOfPeoplelow;
    }

    public int getNumberOfPeoplehigh() {
        return numberOfPeoplehigh;
    }

    public void setNumberOfPeoplehigh(int numberOfPeoplehigh) {
        this.numberOfPeoplehigh = numberOfPeoplehigh;
    }

    public HangoutActivities(int code, String name, int averagePricelow, int averagePricehigh, int numberOfPeoplelow, int numberOfPeoplehigh) {
        this.code = code;
        this.name = name;
        this.averagePricelow = averagePricelow;
        this.averagePricehigh = averagePricehigh;
        this.numberOfPeoplelow = numberOfPeoplelow;
        this.numberOfPeoplehigh = numberOfPeoplehigh;
    }

   
    

    @Override
    public String toString() {
        return  code + "   " + name + " Average Price = " + averagePricelow + "-" + averagePricehigh + "" + "   Number Of People = " + numberOfPeoplelow +
                "-"+numberOfPeoplehigh+"\n" ;
    }
    
    
    

    
    
    
    
}
