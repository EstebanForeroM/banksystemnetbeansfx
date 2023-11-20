package com.finalproject.frameworks.repositoryLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.finalproject.entities.Product;
import com.finalproject.entities.products.Account;
import com.finalproject.entities.products.AccountType;
import com.finalproject.entities.products.CDT;
import com.finalproject.entities.products.Card;
import com.finalproject.entities.products.CardType;
import com.finalproject.entities.products.ProductType;
import com.finalproject.entities.products.UninitializedProduct;

public class ProductSerializer implements Serializer<Product> {

    public String serialize(Product product) {

        StringBuilder sb = new StringBuilder();

        sb.append(serializeGeneralProduct(product));


        if (product instanceof UninitializedProduct) {
            UninitializedProduct uninitializedProduct = (UninitializedProduct) product;

            sb.append("," + uninitializedProduct.getProductType().getName());
        } else if (product instanceof CDT) {
            CDT cdt = (CDT) product;

            sb.append("," + cdt.getExpirationMonths());
        }

        return sb.toString();
    }

    public Product deserialize(String productString) {
        String[] productData = productString.split(",");
        Product product = null;

        if (productString.startsWith(ProductType.UninitializedProduct.getName())) {
            product = new UninitializedProduct(productData[1], productData[2], ProductType.getProductType(productData[5]));
        } else if (productString.startsWith(ProductType.CDT.getName())) {
            product = new CDT(productData[1], productData[2], getDateFromString(productData[3]), Integer.parseInt(productData[5]));
        } else if (productString.startsWith(ProductType.VISA_CARD.getName())) {
            product = getCardFromString(productData, CardType.VISA);
        } else if (productString.startsWith(ProductType.MASTERCARD.getName())) {
            product = getCardFromString(productData, CardType.MASTERCARD);
        } else if (productString.startsWith(ProductType.AMERICAN_EXPRESS.getName())) {
            product = getCardFromString(productData, CardType.AMERICAN_EXPRESS);
        } else if (productString.startsWith(ProductType.CHECKING_ACCOUNT.getName())) {
            product = getAccountFromString(productData, AccountType.CHECKING);
        } else if (productString.startsWith(ProductType.SAVINGS_ACCOUNT.getName())) {
            product = getAccountFromString(productData, AccountType.SAVINGS);
        } else {
            throw new IllegalArgumentException("Invalid product type");
        }

        if (product != null) {
            product.setBalance(Integer.parseInt(productData[4]));
        }

        return product;
    }


    private String serializeGeneralProduct(Product product) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String openingDate = formatter.format(product.getOpeningDate());
        return product.getProductName() + "," + product.getId() + "," + product.getOwnerId() + "," + openingDate + "," + product.getBalance();
    }

    private Date getDateFromString(String openingDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(openingDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    private Account getAccountFromString(String[] productData, AccountType accountType) {
        return new Account(productData[1], productData[2], getDateFromString(productData[3]), accountType);
    }

    private Card getCardFromString(String[] productData, CardType cardType) {
        return new Card(productData[1], productData[2], getDateFromString(productData[3]), cardType);
    }
}
