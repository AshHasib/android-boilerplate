package com.ashhasib.android_boilerplate.firebase;

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.ashhasib.android_boilerplate.model.UploadImage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
