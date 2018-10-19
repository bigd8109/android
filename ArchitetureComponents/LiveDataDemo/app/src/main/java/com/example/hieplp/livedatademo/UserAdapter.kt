package com.example.hieplp.livedatademo

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.user_list_view_row.view.*
import java.util.ArrayList

data class User(val name:String)

class UserAdapter(val  userList: ArrayList<User>, val context: Context): RecyclerView.Adapter<UserAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_view_row, parent, false)

        return DataViewHolder(view)
    }

    fun addUser(user: User) {
        userList.add(user)
        notifyDataSetChanged()
    }

    override fun getItemCount()=  userList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        }else {
            holder.textView.background = ContextCompat.getDrawable(context, R.color.material_grey_100)
        }
        holder.textView.text = userList[position].name
    }

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(itemView: View?) {
            Log.d("xxx", "Click ${itemView} at pos: ${adapterPosition}")
        }

        val textView = view.user_view_row
    }


}