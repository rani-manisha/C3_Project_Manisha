import java.time.LocalTime;
import java.util.*;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();


    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        for(Restaurant r:restaurants){
            if(r.getName().equals(restaurantName))
                return r;
        }
        throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        Map<Integer, Integer> map= new HashMap<>();
        map.keySet();
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

}
