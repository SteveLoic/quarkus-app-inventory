package org.stve.product;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.stve.common.CommonResponse;

import java.util.List;

@Path("/products")
@Produces
@Consumes
@Tag(name = "Products")
public class ProductController {
    @Inject
    ProductService productService;

    @GET
    @Path("/all")
    public Response getAllProducts() {
        List<ProductResponse> productResponses = productService.getAllProducts();
        CommonResponse commonResponse = CommonResponse.builder()
                .status(200)
                .message("Retrieve all Product Successfully")
                .productResponses(productResponses)
                .build();
        return Response.ok(commonResponse).build();
    }

    @GET
    @Path("/{productId}/product")
    public Response getProductId(@PathParam("productId") Long productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .productResponse(productResponse)
                .build();
        return Response.ok(productResponse).build();
    }

    @POST
    @Path("/product/add")
    public Response addProduct(ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .message("Product successfully added")
                .productResponse(productResponse)
                .build();
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{productId}/update")
    public Response updateProduct(@PathParam("productId") Long productId, ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(productId, productRequest);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .message("Product updated successfuly")
                .productResponse(productResponse)
                .build();
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{productId}/update")
    public Response deleteProduct(@PathParam("productId") Long productId) {
        productService.deleteProduct(productId);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .message("Product has been successfully deleted")
                .build();
        return Response.ok(response).build();
    }
}
