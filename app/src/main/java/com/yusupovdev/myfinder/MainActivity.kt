package com.yusupovdev.myfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val filmDataBase = listOf(
            Film("American Ultra", R.drawable.americanultra, "This should be a description"),
            Film("Divergent", R.drawable.divergent, "This should be a description"),
            Film("Avatar", R.drawable.ktoia, "This should be a description"),
            Film("Кто Я", R.drawable.scale_1200, "This should be a description"),
            Film("Марсиянин", R.drawable.marsianin, "This should be a description"),
            Film("Шпионский мост", R.drawable.tomhanks, "This should be a description"),
            Film("Titanik", R.drawable.titanik, "This should be a description"),
            Film("Высотка", R.drawable.visotka, "This should be a description")
    )
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //       initMenuButtons()
        //        button_menu.setOnClickListener{}

        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fav -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selection -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }


        /*fun onClickToast(view: View){
        Toast.makeText(this,"Меню", Toast.LENGTH_SHORT).show()
*/

/*private fun initMenuButtons() {
    button_menu.setOnClickListener {
        Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()
    }
    btn_fav.setOnClickListener {
        Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
    }
    btn_watch_later.setOnClickListener {
        Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
    }
    btn_collection.setOnClickListener {
        Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
    }
    btn_setting.setOnClickListener {
        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
    }*/
    }
}