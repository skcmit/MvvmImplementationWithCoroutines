package com.santosh.navi.ui.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.santosh.navi.R
import com.santosh.navi.api.user.GitHubUserProfileDetailsResponseDTO
import com.santosh.navi.databinding.LayoutUserProfileBinding
import com.santosh.navi.util.STATE
import com.santosh.navi.util.getViewModel
import com.santosh.navi.util.setOptionalText

class UserProfileDetailFragment : Fragment() {

    private lateinit var binding: LayoutUserProfileBinding
    private val viewModel: UserProfileViewModel by lazy { getViewModel(UserProfileViewModel::class.java) }
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        userId = bundle?.getString(USER_ID) ?: throw Exception()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setUpToolbar()
        fetchData()
    }

    private fun setUpToolbar() {
        binding.toolBar.toolBarTitle.text = getString(R.string.user_profile)
    }

    private fun fetchData() {
        viewModel.fetchData(userId)
    }

    private fun setupObservers() {
        viewModel.getUserProfileDetail.observe(viewLifecycleOwner, {
            when (it.state) {
                STATE.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    setUpViews(it.data!!)
                }
                STATE.ERROR -> {
                    binding.errorLayout.root.visibility = View.VISIBLE
                    binding.errorLayout.errorTitle.text = it.message
                    binding.errorLayout.retryButton.setOnClickListener {
                        fetchData()
                    }
                    binding.progress.visibility = View.GONE
                }
                STATE.INPROGRESS ->
                    binding.progress.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpViews(data: GitHubUserProfileDetailsResponseDTO) {
        binding.bio.setOptionalText( data.bio, binding.bioLabel)
        binding.name.setOptionalText(data.name)
        binding.followers.setOptionalText(data.followers.toString(), binding.followersLabel)
        binding.following.setOptionalText(data.following.toString(), binding.followingLabel)
        binding.location.setOptionalText(data.location, binding.locationLabel)
        Glide.with(binding.root)
            .load(data.imageUrl)
            .transform(CircleCrop())
            .into(binding.image)
    }

    companion object {
        private const val USER_ID = "USER_ID"
        fun getInstance(userId: String): UserProfileDetailFragment {
            val fragment = UserProfileDetailFragment()
            fragment.arguments = Bundle().also {
                it.putString(USER_ID, userId)
            }
            return fragment
        }
    }
}
