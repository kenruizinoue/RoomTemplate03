package com.kenruizinoue.roomtemplate03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // db setup
        val db = Room.databaseBuilder(this, UserDatabase::class.java, "db").build()
        userDao = db.userDao()

        // listeners setup
        addButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO)  {
                val user = User(name = "pepito", age = 123)
                userDao.insertUser(user)
            }
        }

        getButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val users = userDao.getUsers()
                // ui update
                withContext(Dispatchers.Main) {
                    textView.text = users.size.toString()
                    Toast.makeText(applicationContext, "Datos obtenidos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}