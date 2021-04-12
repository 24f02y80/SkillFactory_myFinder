package com.yusupovdev.myfinder.data
import com.yusupovdev.myfinder.R
import com.yusupovdev.myfinder.domain.Film

class MainRepository {
    val filmsDataBase = listOf(
            Film("American Ultra", R.drawable.americanultra, "This should be a description", 9f),
            Film("Divergent", R.drawable.divergent, "This should be a description", 7.8f),
            Film("Avatar", R.drawable.ktoia, "This should be a description", 6.3f),
            Film("Кто Я", R.drawable.scale_1200, "This should be a description", 3.3f),
            Film("Пираты Корибского моря", R.drawable.marsianin, "This should be a description", 8.3f),
            Film("Шпионский мост", R.drawable.tomhanks, "This should be a description", 7.2f),
            Film("Titanik", R.drawable.titanik, "This should be a description", 6.7f),
            Film("Высотка", R.drawable.visotka, "This should be a description", 5.3f)
    )
}