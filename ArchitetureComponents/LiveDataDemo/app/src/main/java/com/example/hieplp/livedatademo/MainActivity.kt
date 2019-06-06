package com.example.hieplp.livedatademo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.hieplp.livedatademo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java!!.getSimpleName()
    private lateinit var model:MyViewModel
    private var observer:Observer<String> = Observer { s -> updateUI(s)}
//    private var observerUserList:Observer<ArrayList<User>> = Observer { list -> updateList(list)}

//    private fun updateList(list: ArrayList<User>?) {
//        binding.userRecyclerView.adapter?.notifyDataSetChanged()
//    }

    private lateinit var adapter : UserAdapter
    private lateinit var binding: ActivityMainBinding

    private fun updateUI(s:String?) {
        status_view.text = s
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        model = ViewModelProviders.of(this).get(MyViewModel::class.java)
        model.name.observe(this, observer)


        var data = ArrayList<User>()
        data.add(User("test1"))
        data.add(User("test2"))
        data.add(User("test3"))

        Log.d(TAG, "data size ${data.size}")
        adapter = UserAdapter(data, this)
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.adapter = adapter

        update_button.setOnClickListener {
//            val newName = input_text.text.toString()
//            Log.d(TAG, newName)
//            model.name.value = newName
            //model.doSomething(input_text.text.toString())
//            adapter.userList.add(User(input_text.text.toString()))
            adapter.addUser(User(input_text.text.toString()))
        }
    }
}
