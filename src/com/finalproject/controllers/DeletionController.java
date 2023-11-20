package com.finalproject.controllers;

import com.finalproject.useCases.ProductModificationService;
import com.finalproject.useCases.ProductSearcher;
import com.finalproject.useCases.UserModificationService;

public class DeletionController
{
    private ProductSearcher productSearcher;
    private ProductModificationService productModificationService;
    public DeletionController(UserModificationService userModificationService, ProductSearcher productSearcher, ProductModificationService productModificationService)
    {
        userModificationService.setOnUserDeleted(this::onUserDeleted);
        this.productSearcher = productSearcher;
        this.productModificationService = productModificationService;
    }

    private void onUserDeleted(String clientId)
    {
        productSearcher.getProductsByUniqueOwner(clientId).forEach(product -> productModificationService.deleteProduct(product.getId()));
    }
}
