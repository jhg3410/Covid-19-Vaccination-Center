package org.jik.retrofit_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), TextWatcher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        var editText = findViewById<EditText>(R.id.editText)
        editText.addTextChangedListener(this)
    }
    fun setAdapter(dataList: List<DataX>) {
        val adapter = CustomAdapter(dataList,this)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(CoronaOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            val coronaService = retrofit.create(CoronaService::class.java)
            coronaService.datas(1,270,Configs.API_KEY).enqueue(object :Callback<Data> {

                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        setAdapter(body?.data!!)
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
        TODO("Not yet implemented")
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        TODO("Not yet implemented")
    }
}