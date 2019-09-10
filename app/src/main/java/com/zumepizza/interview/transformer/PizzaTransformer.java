package com.zumepizza.interview.transformer;

import com.zumepizza.interview.data.CategoryServerModel;
import com.zumepizza.interview.data.ClassificationServerModel;
import com.zumepizza.interview.data.PizzaServerModel;
import com.zumepizza.interview.data.ToppingServerModel;
import com.zumepizza.interview.ui.PizzaCategory;
import com.zumepizza.interview.ui.PizzaModel;
import com.zumepizza.interview.ui.Topping;
import com.zumepizza.interview.ui.ViewItemData;

import java.util.ArrayList;
import java.util.List;

public class PizzaTransformer {

    public List<ViewItemData> toPizzaList(List<CategoryServerModel> categoryServerModelList) {
        List<ViewItemData> dataList = new ArrayList<>();
        for (CategoryServerModel serverModel : categoryServerModelList) {
            if (serverModel.chefsChoice != null) {
                dataList.add(new PizzaCategory("Chef's Choice"));
                for (PizzaServerModel pizzaServerModel : serverModel.chefsChoice) {
                    dataList.add(toPizzaModel(pizzaServerModel));
                }
            }

            if (serverModel.classics != null) {
                dataList.add(new PizzaCategory("Classics"));
                for (PizzaServerModel pizzaServerModel : serverModel.classics) {
                    dataList.add(toPizzaModel(pizzaServerModel));
                }
            }

            if (serverModel.signature != null) {
                dataList.add(new PizzaCategory("Signature"));
                for (PizzaServerModel pizzaServerModel : serverModel.signature) {
                    dataList.add(toPizzaModel(pizzaServerModel));
                }
            }

            if (serverModel.vegetarian != null) {
                dataList.add(new PizzaCategory("Vegetarian"));
                for (PizzaServerModel pizzaServerModel : serverModel.vegetarian) {
                    dataList.add(toPizzaModel(pizzaServerModel));
                }
            }
        }
        return dataList;
    }

    public PizzaModel toPizzaModel(PizzaServerModel serverModel) {
        PizzaModel pizzaModel = new PizzaModel();
        pizzaModel.name = serverModel.name;
        pizzaModel.price = serverModel.price;
        pizzaModel.menuDescription = serverModel.menu_description;
        pizzaModel.assetImageURL = serverModel.assets.product_details_page.get(0).url;
        pizzaModel.mark = initMark(serverModel.classificationServerModel);
        pizzaModel.toppings = toToppings(serverModel.toppings);
        return pizzaModel;
    }

    private String initMark(ClassificationServerModel classificationServerModel) {
        if (classificationServerModel == null) return "";

        StringBuilder mark = new StringBuilder();
        if (classificationServerModel.vegetarian) {
            mark.append("vegetarian");
        }
        if (classificationServerModel.gluten_free) {
            mark.append(mark.length() != 0 ? " & " : "").append("gluten free");
        }

        return mark.toString();
    }

    private List<Topping> toToppings(List<ToppingServerModel> toppings) {
        List<Topping> toppingList = new ArrayList<>();
        toppings.forEach(toppingServerModel -> toppingList.add(new Topping(toppingServerModel.name, toppingServerModel.description, toppingServerModel.asset.url)));

        return toppingList;
    }

}
