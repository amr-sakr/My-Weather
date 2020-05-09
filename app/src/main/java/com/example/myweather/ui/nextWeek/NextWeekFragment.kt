package com.example.myweather.ui.nextWeek

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.R

class NextWeekFragment : Fragment() {

    companion object {
        fun newInstance() = NextWeekFragment()
    }

    private lateinit var viewModel: NextWeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.next_week_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NextWeekViewModel::class.java)
        // TODO: Use the ViewModel
    }

}