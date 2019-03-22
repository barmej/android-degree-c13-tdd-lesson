package barmej.com.healthyfamily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import barmej.com.healthyfamily.OnItemClickListener
import barmej.com.healthyfamily.R
import barmej.com.healthyfamily.UsersListActivity
import barmej.com.healthyfamily.model.Gender
import barmej.com.healthyfamily.model.User

class UsersAdapter internal constructor(var context: Context) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var usersList: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        val user = usersList[position]

        viewHolder.nameTextView.text = user.name
        viewHolder.userImageView.setImageDrawable(when (user.gender) {
            Gender.MALE -> context.getDrawable(R.drawable.ic_male)
            Gender.FEMALE -> context.getDrawable(R.drawable.ic_female)
        })

        viewHolder.view.setOnClickListener { (context as UsersListActivity).onItemClick(position) }

    }

    fun setUsersList(usersList: List<User>) {
        this.usersList = usersList

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = usersList.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val userImageView: ImageView = itemView.findViewById(R.id.imageView)

    }
}