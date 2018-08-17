package com.projet.estm.projetm2glar.sample;

import com.projet.estm.projetm2glar.model.Produit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataProvider {
    public static List<Produit> produitList;
    public static Map<String, Produit> produitMap;

    static {
        produitList = new ArrayList<>();
        produitMap = new HashMap<>();

        addItem(new Produit( "Quinoa Salmon Salad", "Salads",
                "Our quinoa salad is served with quinoa, tomatoes, cucumber, scallions, and smoked salmon. Served with your choice of dressing.",
                true, 10, "quinoa_salad.jpg"));

        addItem(new Produit( "Chef's Salad", "Salads",
                "The chef’s salad has cucumber, tomatoes, red onions, mushrooms, hard-boiled eggs, cheese, and hot grilled chicken on a bed of romaine lettuce. Served with croutons and your choice of dressing.",
                true, 9, "chef_salad.jpg"));

        addItem(new Produit( "House Salad", "Salads",
                "Our house salad is made with romaine lettuce and spinach, topped with tomatoes, cucumbers, red onions and carrots. Served with a dressing of your choice.",
                true, 7, "house_salad.jpg"));

        addItem(new Produit( "Garden Buffet", "Salads",
                "Choose from our fresh local, organically grown ingredients to make a custom salad.",
                false, 10, "french_onion_soup.jpg"));

        addItem(new Produit( "Mini Cheeseburgers", "Starters",
                "These mini cheeseburgers are served on a fresh baked pretzel bun with lettuce, tomato, avocado, and your choice of cheese.",
                true, 8, "mini_cheeseburgers.jpg"));

        addItem(new Produit( "Panko Stuffed Mushrooms", "Starters",
                "Large mushroom caps are filled a savory cream cheese, bacon and panko breadcrumb stuffing, topped with cheddar cheese.",
                true, 7, "stuffed_mushrooms.jpg"));

        addItem(new Produit( "French Onion Soup", "Starters",
                "Caramelized onions slow cooked in a savory broth, topped with sourdough and a provolone cheese blend. Served with sourdough bread.",
                true, 7, "french_onion_soup.jpg"));

        addItem(new Produit( "Artichokes with Garlic Aeoli", "Starters",
                "Our artichokes are brushed with an olive oil and rosemary blend and then broiled to perfection. Served with a side of creamy garlic aioli.",
                false, 9, "artichokes.jpg"));

        addItem(new Produit( "Parmesan Deviled Eggs", "Starters",
                "SOME SAY OUR EGGS ARE DEVILISHLY GOOD. I HAVE TO AGREE.\n" +
                        "These delectable little bites are made with organic eggs, fresh Parmesan, and chopped pine nuts.\"",
                false, 8, "deviled_eggs.jpg"));

        addItem(new Produit( "Classic Burger", "Entrees",
                "Our classic burger is made with 100% pure angus beef, served with lettuce, tomatoes, onions, pickles, and cheese of your choice. Veggie burger available upon request. Served with French fries, fresh fruit, or a side salad.",
                true, 10, "classic_burger.jpg"));

        addItem(new Produit( "Tomato Bruschetta Tortellini", "Entrees",
                "This classic cheese tortellini is cooked in a sundried tomato sauce. Served with bruschetta topped with a tomato and basil marinara.",
                true, 15, "tortellini.jpg"));

        addItem(new Produit( "Handcrafted Pizza", "Entrees",
                "Our thin crust pizzas are made fresh daily and topped with your choices of fresh meats, veggies, cheese, and sauce.  Price includes two toppings. Add $1 for each additional topping.",
                true, 10, "pizza.jpg"));

        addItem(new Produit( "Barbecued Tofu Skewers", "Entrees",
                "Our barbecued skewers include tofu, cherry tomatoes, bell peppers, and zucchini marinated in a ginger sesame sauce and charbroiled. Served with steamed rice.",
                false, 10, "tofu_skewers.jpg"));

        addItem(new Produit( "Fiesta Family Platter", "Entrees",
                "This platter is perfect for sharing! Enjoy our spicy buffalo wings, traditional nachos, and cheese quesadillas served with freshly made guacamole dip.",
                false, 16, "fiesta_platter.jpg"));

        addItem(new Produit( "Crème Brûlée", "Desserts",
                "Elegantly crafted creamy vanilla custard with a caramelized crunchy layer on top. Served with seasonal fruit.",
                true, 9, "creme_brulee.jpg"));

        addItem(new Produit( "Cheesecake", "Desserts",
                "Our New York Style Cheesecake is rich, smooth, and creamy. Available in various flavors, and with seasonal fruit toppings.",
                true, 9, "cheesecake.jpg"));

        addItem(new Produit( "Chocolate Chip Brownie ", "Desserts",
                "A warm chocolate chip brownie served with chocolate or vanilla ice cream and rich chocolate sauce.",
                true, 6, "brownie.jpg"));

        addItem(new Produit( "Apple Pie", "Desserts",
                "Made with local granny smith apples to bring you the freshest classic apple pie available.",
                false, 56, "apple_pie.jpg"));

        addItem(new Produit( "Mixed Berry Tart", "Desserts",
                "Raspberries, blueberries, and strawberries on top of a creamy filling served in a crispy tart.",
                false, 7, "berry_tart.jpg"));

        addItem(new Produit( "Tropical Blue Smoothie", "Drinks",
                "This blueberry mint-based smoothie is refreshing and perfect for any celebration.",
                true, 6, "smoothie.jpg"));

        addItem(new Produit( "Pomegranate Iced Tea", "Drinks",
                "Our unique blend of pomegranate juice, black Rubio, and mint tea creates this light fusion of flavors.  ",
                true, 36, "iced_tea.jpg"));

        addItem(new Produit( "Café Latte", "Drinks",
                "Our house blend of espresso and foamed milk. Can be served with flavored syrups and over ice.  Non-dairy substitutions available upon request.",
                true, 6, "cafe_latte.jpg"));

    }

    private static void addItem(Produit item) {
        produitList.add(item);
        produitMap.put(Integer.toString(item.getItemId()), item);
    }

}
