package com.ashhasib.android_boilerplate.retrofit;



import com.ashhasib.android_boilerplate.model.Comment;
import com.ashhasib.android_boilerplate.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClientInterface {


    /**
     * GET methods
     * ================================================
     */

    /**
     * Returns a list of posts
     * @return
     */
    @GET("posts/")
    Call<List<Post>>getPosts();


    /**@Path
     *  /posts/1/comments/
     * Query with parameter
     * @param id
     * @return
     */
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int id);


    /**@Query
     * /comments?postId=1
     *
     */
    @GET("comments/")
    Call<List<Comment>> getCommentsById(@Query("postId") int id);


    /**@Query
     * /comments?postId=1&_sort=id&_order=desc
     * Multiple param.
     */
    @GET("comments/")
    Call<List<Comment>> getCommentsByIdSortedOrder(
            @Query("postId") int id,
            @Query("_sort") String sort,
            @Query("_order") String order
        );

    /**@Query
     * /comments?postId=1&_sort=id&_order=desc
     * Multiple param. multiple ids
     */
    @GET("comments/")
    Call<List<Comment>> getCommentsByMultipleIdSortedOrder(
            @Query("postId") int []ids,
            @Query("_sort") String sort,
            @Query("_order") String order
    );




    /**
     * POST methods
     * ================================================
     */

    /**
     * Basic POST
     * @param post
     * @return
     */
    @POST
    Call<Post> createPost(@Body Post post);


    /**
     * In case of URL formatted data passing
     */
    @FormUrlEncoded
    @POST("posts/")
    Call<Post> createPostUrlFormatted(
            @Field("userId") Integer id,
            @Field("title") String title,
            @Field("body") String body
    );


    /**
     * PUT, PATCH, DELETE methods are similar
     * ================================================
     */

    /**
     * For DELETE, it will be Void, else the model will take place
     * @param id
     * @return
     */
    @DELETE("posts/{id}/")
    Call<Void> delete(@Path("id") int id);

}
