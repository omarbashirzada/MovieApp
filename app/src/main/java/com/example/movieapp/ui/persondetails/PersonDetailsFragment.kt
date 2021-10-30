package com.example.movieapp.ui.persondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.model.ActorModel
import com.example.movieapp.data.model.Cast
import com.example.movieapp.data.other.Constants.IMAGE_URL
import com.example.movieapp.databinding.FragmentPersonDetailsBinding
import com.example.movieapp.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PersonDetailsFragment : Fragment() {
    private lateinit var personDetailsBinding: FragmentPersonDetailsBinding
    private var castModel: Cast? = null
    private val movieViewModel: HomeViewModel by viewModels()
    private val args: PersonDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        personDetailsBinding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        return personDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        castModel = args.castModel

        castModel?.let {
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                it.id?.let { personId ->
                    movieViewModel.getPersonData(personId)
                    movieViewModel.actorFlow.collect { event ->
                        when (event) {
                            is HomeViewModel.Event.ActorSuccess -> {
                                event.actor?.let { it1 -> setPersonData(it1) }
                            }
                            is HomeViewModel.Event.Failure -> {
                                Toast.makeText(
                                    requireContext(),
                                    event.errorText,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            is HomeViewModel.Event.Loading -> {
                                //
                            }
                        }
                    }
                }
            }
        }
        personDetailsBinding.apply {
            personToolbar.setNavigationIcon(R.drawable.back_btn)
            personToolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun setPersonData(actor: ActorModel) {
        personDetailsBinding.apply {
            iwPersonPhoto.load(IMAGE_URL + actor.profile_path)
            tvTitleActorName.text = actor.name
            tvActorName.text = actor.name
            when {
                actor.deathday != null -> {
                    tvPersonBirthday.text = "(${actor.birthday} - ${actor.deathday})"
                }
                actor.birthday != null -> {
                    tvPersonBirthday.text = "(${actor.birthday})"
                }
                else -> {
                    tvPersonBirthday.text = " "
                }
            }
            tvBirthPlace.text = actor.place_of_birth
            tvPersonDepartment.text = actor.known_for_department
            tvBio.text = actor.biography
        }
    }

}