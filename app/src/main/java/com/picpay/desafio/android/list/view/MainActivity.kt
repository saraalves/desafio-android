package com.picpay.desafio.android.list.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.repository.PicPayService
import com.picpay.desafio.android.R
import com.picpay.desafio.android.list.adapter.UserListAdapter
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.PicPayRepository
import com.picpay.desafio.android.viewmodel.PicPayViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private lateinit var _viewModel: PicPayViewModel

    private var _listUsers = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter(_listUsers)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModelProvider()

        progressBar.visibility = View.VISIBLE
        getListUsers()
    }

    private fun getListUsers() {
        _viewModel.getList().observe(this) {
            if(it.isNullOrEmpty()) {
                val message = getString(R.string.error)
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            } else {
                exibirLista(it)
            }
        }
    }

    private fun viewModelProvider() {
        _viewModel = ViewModelProvider(
            this,
            PicPayViewModel.PicPayViewModelFactory(PicPayRepository())
        ).get(PicPayViewModel::class.java)
    }

    private fun exibirLista(lista: List<User>) {
        lista.let {
            progressBar.visibility = View.GONE
            _listUsers.addAll(lista)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()

        getListUsers()
    }

//    override fun onResume() {
//        super.onResume()

//        recyclerView = findViewById(R.id.recyclerView)
//        progressBar = findViewById(R.id.user_list_progress_bar)

//        adapter = UserListAdapter()
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)

//        progressBar.visibility = View.VISIBLE
//        service.getUsers()
//            .enqueue(object : Callback<List<User>> {
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    val message = getString(R.string.error)
//
//                    progressBar.visibility = View.GONE
//                    recyclerView.visibility = View.GONE
//
//                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    progressBar.visibility = View.GONE
//
//                    adapter.users = response.body()!!
//                }
//            })
//    }
}
