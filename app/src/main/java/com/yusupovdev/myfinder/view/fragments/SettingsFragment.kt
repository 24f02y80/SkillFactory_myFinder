package com.yusupovdev.myfinder.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.yusupovdev.myfinder.R
import com.yusupovdev.myfinder.databinding.SettingsFragmentBinding
import com.yusupovdev.myfinder.utils.AnimationHelper
import com.yusupovdev.myfinder.viewmodel.SettingsFragmentViewModel
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment() {

    private lateinit var binding: SettingsFragmentBinding

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(SettingsFragmentViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = SettingsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // подключаем анимацию и передаем номер позиции у кнопки в нижнем меню
        AnimationHelper.performFragmentCircularRevealAnimation(settings_fragment_root, requireActivity(), 5)

        // слушаем, какой у нас сейчас выбран вариант в настройках
        viewModel.categoryPropertyLifeData.observe(viewLifecycleOwner, Observer<String> {
            when (it) {
                POPULAR_CATEGORY -> binding.radioGroup.check(R.id.radio_popular)
                TOP_RATED_CATEGORY -> binding.radioGroup.check(R.id.radio_top_rated)
                UPCOMING_CATEGORY -> binding.radioGroup.check(R.id.radio_upcoming)
                NOW_PLAING_CATEGORY -> binding.radioGroup.check(R.id.radio_playing)
            }
        })

        // слушатель ля отправки нового состояния настройки
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> when(checkedId) {
            R.id.radio_popular -> viewModel.putCategoryProperty(POPULAR_CATEGORY)
            R.id.radio_top_rated -> viewModel.putCategoryProperty(TOP_RATED_CATEGORY)
            R.id.radio_upcoming -> viewModel.putCategoryProperty(UPCOMING_CATEGORY)
            R.id.radio_playing -> viewModel.putCategoryProperty(NOW_PLAING_CATEGORY)
        }
    }
}

    companion object {
        private const val POPULAR_CATEGORY = "popular"
        private const val TOP_RATED_CATEGORY = "top_rated"
        private const val UPCOMING_CATEGORY = "upcoming"
        private const val NOW_PLAING_CATEGORY = "now_playing"
    }

}