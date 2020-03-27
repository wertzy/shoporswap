public class Clothing {
    String name;
    String description;
    double price;
    String[] tags;

    Clothing(String name, String description, double price, String[] tags){
        //if(priceChecker(price)){
            this.name = name;
            this.description = description;
            this.price = price;
            this.tags = tags;
        //}else{
        //}
    }

    /*
    boolean priceChecker(double price){
        String deciCheck = Double.toString(Math.abs(price));
        int deciSpot = deciCheck.indexOf('.');
        int deciPlaces = deciCheck.length() - deciSpot - 1;
        if((price > 0) && (deciPlaces <= 2)) {
            return true;
        }else{
            return false;
        }
    }
     */

    String getName(){
        return name;
    }
    String getDescription(){
        return description;
    }
    double getPrice(){
        return price;
    }
    String[] getTags(){
        return tags;
    }
}
