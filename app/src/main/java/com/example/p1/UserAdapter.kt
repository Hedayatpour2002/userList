package com.example.p1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val users: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFirstName: TextView = itemView.findViewById(R.id.tvFirstName)
        private val tvLastName : TextView = itemView.findViewById(R.id.tvLastName)
        private val tvAge : TextView = itemView.findViewById(R.id.tvAge)
        private val cbIsSelected : CheckBox = itemView.findViewById(R.id.cbIsSelected)


        fun bind(user: User) {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvAge.text = user.age.toString()


            cbIsSelected.setOnCheckedChangeListener(null)
            cbIsSelected.isChecked = user.isSelected

            cbIsSelected.setOnCheckedChangeListener { _, isChecked ->
                user.isSelected = isChecked
            }

        }
    }
}