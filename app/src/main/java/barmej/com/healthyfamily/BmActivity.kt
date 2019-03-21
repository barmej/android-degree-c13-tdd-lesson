package barmej.com.healthyfamily

import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import barmej.com.healthyfamily.model.User

open class BmActivity : AppCompatActivity() {

    lateinit var user: User

    fun setUIWithUserData() {
        user = intent.getSerializableExtra(MainActivity.CURRENT_USER) as User

        val massUnit = resources.getString(R.string.kg)
        val height = when (this) {
            is BmiActivity -> "${user.heightInM}${resources.getString(R.string.m)}"
            else -> "${user.height}${resources.getString(R.string.cm)}"
        }

        findViewById<TextView>(R.id.nameTextView).text = user.name
        findViewById<TextView>(R.id.weightTextView).text = "${user.weight}$massUnit"
        findViewById<TextView>(R.id.heightTextView).text = height
    }
}
