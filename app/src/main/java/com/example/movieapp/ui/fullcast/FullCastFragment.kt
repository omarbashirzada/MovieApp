package com.example.movieapp.ui.fullcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.data.model.Cast
import com.example.movieapp.data.other.Constants.FULL_CAST_TYPE
import com.example.movieapp.databinding.FragmentFullCastBinding


class FullCastFragment : Fragment() {
    private lateinit var fullCastBinding: FragmentFullCastBinding
    private lateinit var castAdapter: FullCastAdapter
    private val args: FullCastFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fullCastBinding = FragmentFullCastBinding.inflate(inflater, container, false)
        return fullCastBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val castList = args.castList

        castAdapter = FullCastAdapter()
        castAdapter.sendTypeCast(FULL_CAST_TYPE)

        fullCastBinding.apply {
            castToolbar.setNavigationIcon(R.drawable.back_btn)

            castToolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            rvFullCast.adapter = castAdapter

            castList?.let {
                castAdapter.differ.submitList(it as List<Cast>)
            }

            castAdapter.setOnItemClickListener {
                findNavController().navigate(
                    FullCastFragmentDirections.actionFullCastFragmentToPersonDetailsFragment(it)
                )
            }
        }

    }

}