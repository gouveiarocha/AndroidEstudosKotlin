package com.gouveia.ytpde_certificacaoandroid.gdc

import android.os.Bundle
import android.view.View
import com.gouveia.ytpde_certificacaoandroid.R
import com.gouveia.ytpde_certificacaoandroid.databinding.FragmentMainBinding

class MainFragment : androidx.fragment.app.Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
    }

}