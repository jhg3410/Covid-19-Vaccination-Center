package org.jik.retrofit_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), TextWatcher {
    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        recyclerview =findViewById(R.id.recyclerView)

        val editText = findViewById<EditText>(R.id.editText)
        editText.addTextChangedListener(this)
    }


    fun setAdapter() {
        val adapter = CustomAdapter()
        // adapter.filteredList= dataList as MutableList<DataX>
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
                .baseUrl(CoronaOpenApi.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val coronaService = retrofit.create(CoronaService::class.java)
        coronaService.datas(1,270,Config.API_KEY).enqueue(object :Callback<Data> {

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    setAdapter()
                    dum.dum.addAll(body?.data!!)
                } else {
                    Toast.makeText(baseContext, "서버연결은 됬으나 데이터를 가져올 수 없습니다.", Toast.LENGTH_LONG)
                            .show()
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(baseContext, "서버연결 실패.", Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        (recyclerview.adapter as CustomAdapter).filter.filter(p0)
//        CustomAdapter().filter.filter(p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}