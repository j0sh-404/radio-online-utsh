package com.example.ultima.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ultima.R
import com.example.ultima.ui.Model
import com.example.ultima.ui.ModelB
import com.example.ultima.ui.tools.ViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SendFragment : Fragment() {
    var mReciclerView: RecyclerView? = null
    var mFirebaseDatabase: FirebaseDatabase? = null
    var mRef: DatabaseReference? = null
    private var sendViewModel: SendViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sendViewModel = ViewModelProviders.of(this).get(SendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)

        mReciclerView = root.findViewById(R.id.recycleView)
        mReciclerView!!.setHasFixedSize(true)
        mReciclerView!!.setLayoutManager(LinearLayoutManager( this@SendFragment.requireActivity()))
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mRef = mFirebaseDatabase!!.getReference("Data")
        return root
    }

    override fun onStart() {
        super.onStart()
        val firebaseRecyclerAdapter: FirebaseRecyclerAdapter<ModelB, ViewHolder> = object : FirebaseRecyclerAdapter<ModelB, ViewHolder>(
                ModelB::class.java,
                R.layout.programs,
                ViewHolder::class.java,
                mRef
        ) {
            override fun populateViewHolder(viewHolder: ViewHolder, model: ModelB, i: Int) {
                viewHolder.setDetails(getContext(), model.title, model.description, model.image)
            }
        }
        mReciclerView!!.adapter = firebaseRecyclerAdapter
    }
}