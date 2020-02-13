package com.ashhasib.android_boilerplate.retrofit

import android.content.Context
import com.ashhasib.android_boilerplate.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitReadWrite(val context: Context) {

    fun read() {
        val retrofit = RetrofitClientInstance.getRetrofitInstance()
        val client = retrofit.create(ApiClientInterface::class.java)

        /**
         * Call and Retrofit will implement what needs to be implemented
         */
        val call = client.posts

        call.enqueue(object : Callback<List<Post>> {

            /**
             * Failed due to network error
             */
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                /**
                 * Authentication data is valid 200
                 */
                if (response.isSuccessful) { //authentication successful - 200
                    /**
                     * RESPONSE IS SUCCESSFUL
                     */
                    //response.body()
                }


                /**
                 * Authentication data is Invalid 400
                 */
                else {
                    /**
                     * BAD REQUEST
                     */
                }
            }
        })
    }
}