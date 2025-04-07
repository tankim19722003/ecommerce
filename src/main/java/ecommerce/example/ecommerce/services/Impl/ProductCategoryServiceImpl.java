package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductCategoryGroupRepo;
import ecommerce.example.ecommerce.Repo.ProductCategoryRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.Repo.SubProductCategoryRepo;
import ecommerce.example.ecommerce.dtos.*;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.*;
import ecommerce.example.ecommerce.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ProductCategoryGroupRepo productCategoryGroupRepo;

    @Autowired
    private SubProductCategoryRepo subProductCategoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<ProductCategoryOneLevelResponse> addMultipleProductCategoryOneLevel(
            Long productId,
            ProductCategoryGroupDTO productCategoryGroupDTO,
            List<MultipartFile> files
    ) {

        Product product = productRepo.findById(productId).orElseThrow(
                () -> new RuntimeException("Product does not found")
        );

        // check image did images add into product category
        int count = productCategoryGroupDTO.getProductCategoryDTOs().size();
        if (files.size() != count) throw new RuntimeException("Please add image to your product categories!");

        List<Map<String, String>> images = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                images.add(cloudinaryService.uploadImage(file));
            } catch (IOException e) {
                throw new RuntimeException("Can't save image");
            }
        }

        List<ProductCategoryOneLevelResponse> productCategoryResponseList = new ArrayList<>();

        int index = 0;

        // save first level


        List<ProductCategoryGroup> savedProductCategoryGroups = new ArrayList<>();

        // save product category group
        ProductCategoryGroup productCategoryGroup = new ProductCategoryGroup();
        productCategoryGroup.setName(productCategoryGroupDTO.getName());
        productCategoryGroup.setProduct(product);
        productCategoryGroupRepo.save(productCategoryGroup);
        savedProductCategoryGroups.add(productCategoryGroup);


        for (ProductCategoryDTO productCategoryDTO : productCategoryGroupDTO.getProductCategoryDTOs()) {

            // save product category
            ProductCategory productCategory = new ProductCategory();

            // map first and second category
            modelMapper.map(productCategoryDTO, productCategory);

            productCategory.setImageUrl(images.get(index).get("publicId"));
            productCategory.setPublicId(images.get(index).get("imageUrl"));
            productCategory.setProductCategoryGroup(productCategoryGroup);
            productCategory.setPrice(productCategoryDTO.getPrice());
            productCategory.setValue(productCategoryDTO.getValue());
            // save product category
            productCategoryRepo.save(productCategory);
            index++;

            // add product category to response
            productCategoryResponseList.add(modelMapper.map(productCategory, ProductCategoryOneLevelResponse.class));
        }

        return productCategoryResponseList;

    }

    @Override
    public MultipleProductCategoryResponse addMultipleProductCategoryTwoLevel(
            Long productId,
            Long shopId,
            MultipleProductCategoryDTO multipleProductCategoryDTO,
            List<MultipartFile> files
    ) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product does not found"));


        // save images
        List<Map<String, String>> images = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                images.add(cloudinaryService.uploadImage(file));
            } catch (IOException e) {
                throw new RuntimeException("Can't save image");
            }
        }


        // is product owned by shop
        if (product.getShop().getId() != shopId)
            throw new RuntimeException("Invalid Shop");

        // save product category group
        ProductCategoryGroup productCategoryGroup = new ProductCategoryGroup();
        productCategoryGroup.setProduct(product);
        productCategoryGroup.setName(multipleProductCategoryDTO.getProductCategoryGroup());

        productCategoryGroupRepo.save(productCategoryGroup);

        ProductCategoryGroup subProductCategoryGroup = new ProductCategoryGroup();
        subProductCategoryGroup.setProduct(product);
        subProductCategoryGroup.setName(multipleProductCategoryDTO.getSubProductCategoryGroup());

        productCategoryGroupRepo.save(subProductCategoryGroup);

        //--------create group response------------
        MultipleProductCategoryResponse multipleProductCategoryResponse = new MultipleProductCategoryResponse();
        ProductCategoryGroupResponse productCategoryGroupResponse = new ProductCategoryGroupResponse();
        productCategoryGroupResponse.setId(productCategoryGroup.getId());
        productCategoryGroupResponse.setProductCategoryGroupName(productCategoryGroup.getName());
        multipleProductCategoryResponse.setProductCategoryGroup(productCategoryGroupResponse);

        ProductCategoryGroupResponse subProductCategoryGroupResponse = new ProductCategoryGroupResponse();
        subProductCategoryGroupResponse.setId(subProductCategoryGroup.getId());
        subProductCategoryGroupResponse.setProductCategoryGroupName(subProductCategoryGroup.getName());
        multipleProductCategoryResponse.setSubProductCategoryGroup(subProductCategoryGroupResponse);


        // image index
        int index = 0;

        List<ProductCategoryTwoLevelResponse> productCategoryTwoLevelResponses = new ArrayList<>();
        for (ProductCategoryTwoLevelDTO productCategoryTwoLevelDTO : multipleProductCategoryDTO.getProductCategoryTwoLevelResponse()) {

            ProductCategoryTwoLevelResponse productCategoryTwoLevelResponse = new ProductCategoryTwoLevelResponse();
            // save parent product category
            ProductCategory parentProductCategory = new ProductCategory();
            parentProductCategory.setValue(productCategoryTwoLevelDTO.getParentProductCategory());
            parentProductCategory.setPublicId(images.get(index).get("publicId"));
            parentProductCategory.setImageUrl(images.get(index).get("imageUrl"));
            index++;

            parentProductCategory.setProductCategoryGroup(productCategoryGroup);
            productCategoryRepo.save(parentProductCategory);

            // ----------- product category response -------
            ParentProductCategoryResponse parentProductCategoryResponse = new ParentProductCategoryResponse();
            parentProductCategoryResponse.setId(parentProductCategory.getId());
            parentProductCategoryResponse.setName(parentProductCategory.getValue());
            parentProductCategoryResponse.setPublicId(parentProductCategory.getPublicId());
            parentProductCategoryResponse.setImageUrl(parentProductCategory.getImageUrl());

            productCategoryTwoLevelResponse.setProductCategoryResponse(parentProductCategoryResponse);


            // save child category
            List<ChildProductCategoryResponse> childProductCategoryResponses = new ArrayList<>();
            for (ChildProductCategory childProductCategoryItem : productCategoryTwoLevelDTO.getChildProductCategories()) {

                SubProductCategory subProductCategory = new SubProductCategory();
                subProductCategory.setProductCategoryGroup(subProductCategoryGroup);
                subProductCategory.setProductCategory(parentProductCategory);
                subProductCategory.setQuantity(childProductCategoryItem.getQuantity());
                subProductCategory.setName(childProductCategoryItem.getName());
                subProductCategory.setPrice(childProductCategoryItem.getPrice());
                subProductCategoryRepo.save(subProductCategory);

                ChildProductCategoryResponse childProductCategoryResponse = new ChildProductCategoryResponse();
                childProductCategoryResponse.setId(subProductCategory.getId());
                childProductCategoryResponse.setName(childProductCategoryItem.getName());
                childProductCategoryResponse.setQuantity(subProductCategory.getQuantity());
                childProductCategoryResponse.setPrice(subProductCategory.getPrice());
                childProductCategoryResponses.add(childProductCategoryResponse);
            }
            productCategoryTwoLevelResponse.setChildProductCategoryResponses(childProductCategoryResponses);
            productCategoryTwoLevelResponses.add(productCategoryTwoLevelResponse);
        }

        multipleProductCategoryResponse.setProductCategoryTwoLevelResponse(productCategoryTwoLevelResponses);
        return multipleProductCategoryResponse;

    }

    @Override
    public ProductCategoryResponse getProductCategories(Long productId) {

        boolean isProductExiting = productRepo.existsById(productId);
        if (!isProductExiting)
            throw new RuntimeException("Product does not found");

        List<ProductCategory> productCategories = productCategoryRepo.getProductCategoriesByProductId(productId);

        if (!productCategories.isEmpty()) {

            // check is product has 2 level product category
            if (!productCategories.getFirst().getSubProductCategories().isEmpty()) {

                MultipleProductCategoryResponse multipleProductCategoryResponse = new MultipleProductCategoryResponse();

                // set product category group
                ProductCategoryGroupResponse productCategoryGroupResponse = new ProductCategoryGroupResponse();
                productCategoryGroupResponse.setId(productCategories.getFirst().getProductCategoryGroup().getId());
                productCategoryGroupResponse.setProductCategoryGroupName(productCategories.getFirst().getProductCategoryGroup().getName());
                multipleProductCategoryResponse.setProductCategoryGroup(productCategoryGroupResponse);

                // set sub product category group
                ProductCategoryGroupResponse subCategoryGroupResponse = new ProductCategoryGroupResponse();
                subCategoryGroupResponse.setId(productCategories
                        .getFirst()
                        .getSubProductCategories()
                        .getFirst().getProductCategoryGroup().getId());

                subCategoryGroupResponse.setProductCategoryGroupName(productCategories
                        .getFirst()
                        .getSubProductCategories()
                        .getFirst().getProductCategoryGroup().getName());
                multipleProductCategoryResponse.setSubProductCategoryGroup(subCategoryGroupResponse);
                // set product category 2 level
                List<ProductCategoryTwoLevelResponse> productCategoryTwoLevelResponses = new ArrayList<>();
                for (ProductCategory productCategoryItem : productCategories) {

                    ProductCategoryTwoLevelResponse productCategoryTwoLevelResponse = new ProductCategoryTwoLevelResponse();
                    // save parent product category
                    ParentProductCategoryResponse parentProductCategory = new ParentProductCategoryResponse();
                    parentProductCategory.setName(productCategoryItem.getValue());
                    parentProductCategory.setPublicId(productCategoryItem.getPublicId());
                    parentProductCategory.setImageUrl(productCategoryItem.getImageUrl());
                    parentProductCategory.setId(productCategoryItem.getId());

                    productCategoryTwoLevelResponse.setProductCategoryResponse(parentProductCategory);


                    // save child category
                    List<ChildProductCategoryResponse> childProductCategoryResponses = new ArrayList<>();
                    for (SubProductCategory childProductCategoryItem : productCategoryItem.getSubProductCategories()) {

                        ChildProductCategoryResponse childProductCategoryResponse = new ChildProductCategoryResponse();
                        childProductCategoryResponse.setId(childProductCategoryItem.getId());
                        childProductCategoryResponse.setName(childProductCategoryItem.getName());
                        childProductCategoryResponse.setQuantity(childProductCategoryItem.getQuantity());
                        childProductCategoryResponse.setPrice(childProductCategoryItem.getPrice());
                        childProductCategoryResponses.add(childProductCategoryResponse);
                    }
                    productCategoryTwoLevelResponse.setChildProductCategoryResponses(childProductCategoryResponses);
                    productCategoryTwoLevelResponses.add(productCategoryTwoLevelResponse);
                }

                multipleProductCategoryResponse.setProductCategoryTwoLevelResponse(productCategoryTwoLevelResponses);

                return multipleProductCategoryResponse;

            } else {

                // return product category 1 level
                ProductCategoryOneLevelList productCategoryOneLevelResponses = new ProductCategoryOneLevelList();

                for (ProductCategory productCategory :  productCategories) {

                    ProductCategoryOneLevelResponse productCategoryOneLevelResponse = modelMapper.map(productCategory, ProductCategoryOneLevelResponse.class);
                    productCategoryOneLevelResponses.addProductCategoryOneLevelResponse(productCategoryOneLevelResponse);

                }

                return productCategoryOneLevelResponses;

            }
        }

        return null;

    }


}
