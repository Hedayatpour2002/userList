package com.example.p1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val userList = mutableListOf<User>()
    private lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvUsers = findViewById<RecyclerView>(R.id.rvUsers)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etAge = findViewById<EditText>(R.id.etAge)

        userAdapter = UserAdapter(userList)
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = userAdapter

        btnAdd.setOnClickListener {
            val firstName: String = etFirstName.text.toString().trim()
            val lastName: String = etLastName.text.toString().trim()
            val ageText: String = etAge.text.toString().trim()

            if (firstName.isNotEmpty() and lastName.isNotEmpty() and ageText.isNotEmpty()) {
                val age: Int = ageText.toInt()
                val newUser = User(userList.size, firstName, lastName, age, isSelected = false)
                userList.add(newUser)

                userList.sortByDescending { it.age }

                userAdapter.notifyDataSetChanged()

                etFirstName.text.clear()
                etLastName.text.clear()
                etAge.text.clear()
            }
        }

        btnDelete.setOnClickListener {
            val selectedUsers = userList.removeAll { it.isSelected }
            if (!selectedUsers) {
                if (userList.isNotEmpty()) userList.removeAt(userList.size - 1)
                else Toast.makeText(this, "user list is empty", Toast.LENGTH_SHORT).show()
            }

            userAdapter.notifyDataSetChanged()

        }
    }
}
