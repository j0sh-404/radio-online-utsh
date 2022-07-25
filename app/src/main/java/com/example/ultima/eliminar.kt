package com.example.ultima

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ultima.ui.Model
import com.example.ultima.ui.tools.ViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class eliminar : AppCompatActivity() {
    var mReciclerView: RecyclerView? = null
    var mFirebaseDatabase: FirebaseDatabase? = null
    var mRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)
        val actionBar = supportActionBar
        actionBar!!.setTitle("Eventos")

        mReciclerView = findViewById(R.id.recycleView)
        mReciclerView!!.setHasFixedSize(true)
        mReciclerView!!.setLayoutManager(LinearLayoutManager(this))
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mRef = mFirebaseDatabase!!.getReference("Data")
    }

    override fun onStart() {
        super.onStart()
        val firebaseRecyclerAdapter: FirebaseRecyclerAdapter<Model, ViewHolder> = object : FirebaseRecyclerAdapter<Model, ViewHolder>(
                Model::class.java,
                R.layout.row,
                ViewHolder::class.java,
                mRef
        ) {
            override fun populateViewHolder(viewHolder: ViewHolder, model: Model, i: Int) {
                viewHolder.setDetails(applicationContext, model.title, model.description, model.image)
            }
        }
        mReciclerView!!.adapter = firebaseRecyclerAdapter
    }
}