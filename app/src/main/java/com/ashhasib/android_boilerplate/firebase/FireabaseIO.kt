package com.ashhasib.android_boilerplate.firebase;

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.ashhasib.android_boilerplate.model.UploadImage
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage


class FireabaseIO(val context: Context){


    /**
     * Basic write operation
     */
    fun writeData() {
        val reference = FirebaseDatabase
            .getInstance()
            .getReference("sample_data")

        reference.setValue("this is a sample read data")

        Toast.makeText(context, "Write Successful", Toast.LENGTH_LONG).show()
    }


    /**
     * Basic Read operation
     */
    fun readData(){
        val reference = FirebaseDatabase.getInstance().getReference("sample_data")
        reference.addValueEventListener(object :ValueEventListener{

            /**
             * data not received for some reason
             */
            override fun onCancelled(p0: DatabaseError) {




            }

            /**
             * data received
             */
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Toast.makeText(context, dataSnapshot.getValue(String::class.java), Toast.LENGTH_LONG).show()
            }

        })
    }




    /**
     * Update a node value in firebase
     * data - key -(title-John Doe, email-john@gmail.com,phone-0123456789)
     * Update the phone number
     */
    fun updateData() {
        val ref:DatabaseReference = FirebaseDatabase
            .getInstance()
            .getReference("data")
            .child("phone")

        ref.setValue("987654321")
    }








    /**
     * Delete a node data - (title-Hello, description-This is description 1), (title-Hello Again, description-This is description 2)
     * Delete first node
     */
    fun deleteData() {
        val ref = FirebaseDatabase.getInstance().reference
        val applesQuery: Query = ref.child("data").orderByChild("title").equalTo("Hello")

        applesQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (appleSnapshot in dataSnapshot.children) {
                    appleSnapshot.ref.removeValue()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("DELETE", "onCancelled", databaseError.toException())
            }
        })
    }








    /**
     * Basic image write operation
     */
    fun uploadImage(activity:Context,
        fileUriList:List<Uri>,
        imgRef:String,
        fileNameList:List<String>
        ) {
        val mainStorageRef = FirebaseStorage.getInstance().getReference("img")
        val databaseReference=
            FirebaseDatabase.getInstance().reference.child("img").child(imgRef)

        for(i in 0 until fileUriList.size) {

            val fUri = fileUriList[i]
            val fRef = mainStorageRef.child(imgRef).child(fileNameList[i])

            fRef.putFile(fUri)
                .addOnSuccessListener {

                    /**
                     * Setting the image links to Realtime Database
                     */
                    val upImg = UploadImage(
                        fileNameList[i],
                        it.metadata!!.reference!!.downloadUrl.toString())
                    val upId = databaseReference.push().key.toString()
                    databaseReference.child(upId).setValue(upImg)

                }
                .addOnFailureListener{
                    Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {
                    Toast.makeText(activity, "Uploading", Toast.LENGTH_LONG).show()
                }
                .addOnCompleteListener{
                    /**
                     * Upload completed
                     */
                }
        }
    }
}
