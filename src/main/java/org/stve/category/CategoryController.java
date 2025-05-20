package org.stve.category;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.stve.common.CommonResponse;

import java.util.List;

@Path("/categories")
@Tag(name = "Categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    @Inject
    CategoryService categoryService;
    @GET
    @Path("/all")
    public Response getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategory();
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .categoryResponses(categories)
                .build();
        return Response.ok(response).build();
    }

    @POST
    @Path("/category/add")
    public Response addNewCategory(CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.addNewCategory(categoryRequest);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .message("Category successfully added")
                .categoryResponse(categoryResponse)
                .build();
        return Response.ok(response).build();
    }

    @GET
    @Path("/{categoryName}")
    public Response getCategoryByName(@PathParam("categoryName") String categoryName) {
        CategoryResponse categoryResponse = categoryService.getCategoryByname(categoryName);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .categoryResponse(categoryResponse)
                .build();
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{categoryName}/update")
    public Response updateCategory(@PathParam("categoryName") String categoryName, CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.updateCategory(categoryName, categoryRequest);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .message("Category successfully updated")
                .categoryResponse(categoryResponse)
                .build();
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{categoryName}/delete")
    public Response deleteCategory(@PathParam("categoryName") String categoryName) {
        categoryService.deleteCategory(categoryName);
        CommonResponse response = CommonResponse.builder()
                .status(200)
                .message("Category has been successfully deleted")
                .build();
        return Response.ok(response).build();
    }

}
