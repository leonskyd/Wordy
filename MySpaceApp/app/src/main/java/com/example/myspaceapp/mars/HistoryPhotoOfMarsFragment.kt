package com.example.myspaceapp.mars

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import com.example.myspaceapp.R
import kotlinx.android.synthetic.main.fragment_history_photo_of_mars.*
import kotlinx.android.synthetic.main.fragment_mars_photo_detail.*
import kotlinx.android.synthetic.main.fragment_mars_photo_detail.imageView

class HistoryPhotoOfMarsFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryPhotoOfMarsFragment()
    }
    private var show = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history_photo_of_mars,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView.setOnClickListener {
            if(show) hideDetails()
            else showDetails()
        }
    }

    private fun showDetails() {
        show = true
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_mars_photo_detail)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(fragment_history_photo_of_mars,transition)
        constraintSet.applyTo(fragment_history_photo_of_mars)
    }

    private fun hideDetails() {
        show = false
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_history_photo_of_mars)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(fragment_history_photo_of_mars,transition)
        constraintSet.applyTo(fragment_history_photo_of_mars)
    }
}