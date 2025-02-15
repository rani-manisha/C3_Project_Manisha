import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    @BeforeEach
    public void addRestaurant(){
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant mockrestaurant= Mockito.spy(new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime));

        Mockito.when(mockrestaurant.getCurrentTime()).thenReturn(openingTime.plusHours(1));
        assertTrue(mockrestaurant.isRestaurantOpen());

        Mockito.when(mockrestaurant.getCurrentTime()).thenReturn(closingTime.minusHours(1));
        assertTrue(mockrestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant mockrestaurant= Mockito.spy(new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime));
        Mockito.when(mockrestaurant.getCurrentTime()).thenReturn(openingTime.minusHours(1));
        assertFalse(mockrestaurant.isRestaurantOpen());

        Mockito.when(mockrestaurant.getCurrentTime()).thenReturn(closingTime.plusHours(1));
        assertFalse(mockrestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>ORDERVALUE<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void for_no_item_selected_order_value_should_give_value_as_0(){
        List<String> menuItems= new ArrayList<String>();
        assertEquals(0,restaurant.orderValue(menuItems));
    }

    @Test
    public void summing_order_value_should_give_correct_order_value(){
        restaurant.addToMenu("Chicken wings",199);
        restaurant.addToMenu("Salad", 149);
        restaurant.addToMenu("Ice-cream", 99);
        String menuItem1= restaurant.getMenu().get(1).getName();
        String menuItem2= restaurant.getMenu().get(3).getName();
        String menuItem3= restaurant.getMenu().get(4).getName();
        List<String> menuItems= new ArrayList<String>();
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        menuItems.add(menuItem3);
        assertEquals(517,restaurant.orderValue(menuItems));
    }



    //<<<<<<<<<<<<<<<<<<<<<<<ORDERVALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}