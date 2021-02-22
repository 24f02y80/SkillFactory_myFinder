package com.yusupovdev.myfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    val filmDataBase = listOf(
        Film("American Ultra", R.drawable.americanultra, "This should be a description"),
        Film("Divergent", R.drawable.divergent, "This should be a description"),
        Film("Avatar", R.drawable.ktoia, "This should be a description"),
        Film("Кто Я", R.drawable.scale_1200, "This should be a description"),
        Film("Пираты Корибского моря", R.drawable.marsianin, "This should be a description"),
        Film("Шпионский мост", R.drawable.tomhanks, "This should be a description"),
        Film("Titanik", R.drawable.titanik, "This should be a description"),
        Film("Высотка", R.drawable.visotka, "This should be a description")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // находим наш RV
        main_recycler.apply {
            // Иницилизируем наш адаптер в конструктор передаем ананимно иницилизированный интнрфейс
            //   осьавим его пока пустым, он нам понадобиться во второй части задания
            filmsAdapter =
                FilmListRecyclerAdapter(object: FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }

                })

            // Присваиваем адаптер
            adapter = filmsAdapter
            // Присвоим LayoutManager
            layoutManager = LinearLayoutManager(requireContext())
            // Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        // Кладем нашу БД в RV
        filmsAdapter.addItems(filmDataBase)
    }

}