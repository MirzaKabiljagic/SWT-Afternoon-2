package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Hotel;
import com.example.demo.model.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerInput {
  private enum Sort {BY_PRICE, BY_DISTANCE_FROM_CENTER, BY_RATING}

  @Autowired
  private HotelService hotelService;
  private Sort default_sort;

  //user input parameters
  private String min_price_per_night = null;
  private String max_price_per_night = null;
  private String min_customer_rating = null;
  private String max_customer_rating = null;
  private String customer_stars = null;
  private String[] activities = null;
  private String[] locations = null;
  private Boolean[] other_filters = null;

  //reset all parameters
  public CustomerInput(HotelService hotelService)
  {
    default_sort = Sort.BY_PRICE;
    this.hotelService = hotelService;
    this.min_price_per_night = null;
    this.max_price_per_night = null;
    this.min_customer_rating = null;
    this.max_customer_rating = null;
    this.customer_stars = null;
    this.activities = null;
    this.locations = null;
    this.other_filters = null;
  }

  public CustomerInput(String min_price_per_night_, String max_price_per_night_,
                       String min_customer_rating_, String max_customer_rating_, String customer_stars_,
                       String[] activities_, String[] locations_, Boolean[] other_filters_)
  {
    this.min_price_per_night = min_price_per_night_;
    this.max_price_per_night = max_price_per_night_;
    this.min_customer_rating = min_customer_rating_;
    this.max_customer_rating = max_customer_rating_;
    this.customer_stars = customer_stars_;
    this.activities = activities_;
    this.locations = locations_;
    this.other_filters = other_filters_;
  }

  //-----Filtering----//
  private void determineFilters(String[] filters)
  {
    List<Filters> applied_filters = new ArrayList<Filters>();

    for(String filter_iterator: filters)
    {
      switch(filter_iterator) {
        case "parking":
          System.out.println("parking");
          break;
        case "restaurant":
          System.out.println("restaurant");
          break;
        case "non_smoking_rooms":
          System.out.println("non_smoking_rooms");
          break;
        case "pets_allowed":
          System.out.println("pets_allowed");
          break;
        case "swimming_pool":
          System.out.println("swimming_pool");
          break;
        case "beach_front":
          System.out.println("beach_front");
          break;
        case "air_conditioning":
          System.out.println("air_conditioning");
          break;
        case "free_wifi":
          System.out.println("free_wifi");
          break;
        case "sauna":
          System.out.println("sauna");
          break;
        case "fitness":
          System.out.println("fitness");
          break;
        default:
          System.out.println("There is no such filter");
      }
    }

  }



  public Iterable<Hotel> filter_getHotelWithinPriceRange(String price)
  {

    List<Category> categories = new ArrayList<Category>();

    List<Hotel> hotels_within_price_range = new ArrayList<Hotel>();
    for(Hotel hotel: hotelService.getHotels())
    {

      if(hotel.getPrice() <= Integer.parseInt(price))
      {
        hotels_within_price_range.add(hotel);
        System.out.println("hotel name is: " + hotel.getName() + " and price is " + hotel.getPrice());
      }
    }

    Collections.sort(hotels_within_price_range);
    //    for(Hotel hotel_sorted: hotels_within_price_range)
    //      System.out.println("hotel is: " + hotel_sorted.getName() + " and price is " + hotel_sorted.getPrice());

    Iterable<Hotel> hotels__ = hotels_within_price_range;
    for(Hotel it: hotels__)
      System.out.println(it.getName() + " " + it.getPrice());

    return hotels__;
  }

  public Iterable<Categories> applyAllFilters()
  {
    List<Categories> categories = new ArrayList<Categories>();
    List<Hotel> all_hotels = hotelService.getHotels();
    Hotels hotels_1 = new Hotels(all_hotels);
    categories.add(new Categories(hotels_1));

    List<Hotel> hotels_within_price_range = new ArrayList<Hotel>();
    Iterable<Hotel> found_hotels = hotels_within_price_range;

    return categories;
  }

}