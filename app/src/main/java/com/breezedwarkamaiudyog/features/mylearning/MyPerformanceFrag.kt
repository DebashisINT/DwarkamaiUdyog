package com.breezedwarkamaiudyog.features.mylearning

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.breezedwarkamaiudyog.R
import com.breezedwarkamaiudyog.app.types.FragType
import com.breezedwarkamaiudyog.base.presentation.BaseFragment
import com.breezedwarkamaiudyog.features.dashboard.presentation.DashboardActivity

class MyPerformanceFrag : BaseFragment(), View.OnClickListener {
    private lateinit var ll_lms_performance: LinearLayout
    private lateinit var iv_lms_performance: ImageView
    private lateinit var tv_lms_performance: TextView

    private lateinit var ll_lms_mylearning: LinearLayout
    private lateinit var iv_lms_mylearning: ImageView
    private lateinit var tv_lms_mylearning: TextView

    private lateinit var ll_lms_leaderboard: LinearLayout
    private lateinit var iv_lms_leaderboard: ImageView
    private lateinit var tv_lms_leaderboard: TextView

    private lateinit var ll_lms_knowledgehub: LinearLayout
    private lateinit var iv_lms_knowledgehub: ImageView
    private lateinit var tv_lms_knowledgehub: TextView

    private lateinit var mContext:Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_my_performance, container, false)
        initview(view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    private fun initview(view: View) {

        //performance
        ll_lms_performance=view.findViewById(R.id.ll_lms_performance)
        iv_lms_performance=view.findViewById(R.id.iv_lms_performance)
        tv_lms_performance=view.findViewById(R.id.tv_lms_performance)

        //mylearning
        ll_lms_mylearning=view.findViewById(R.id.ll_lms_mylearning)
        iv_lms_mylearning=view.findViewById(R.id.iv_lms_mylearning)
        tv_lms_mylearning=view.findViewById(R.id.tv_lms_mylearning)

        //leaderboard
        ll_lms_leaderboard=view.findViewById(R.id.ll_lms_leaderboard)
        iv_lms_leaderboard=view.findViewById(R.id.iv_lms_leaderboard)
        tv_lms_leaderboard=view.findViewById(R.id.tv_lms_leaderboard)

        //knowledgehub
        ll_lms_knowledgehub=view.findViewById(R.id.ll_lms_knowledgehub)
        iv_lms_knowledgehub=view.findViewById(R.id.iv_lms_knowledgehub)
        tv_lms_knowledgehub=view.findViewById(R.id.tv_lms_knowledgehub)

        iv_lms_performance.setImageResource(R.drawable.my_performance_filled_clr)

        ll_lms_performance.setOnClickListener(this)
        ll_lms_mylearning.setOnClickListener(this)
        ll_lms_leaderboard.setOnClickListener(this)
        ll_lms_knowledgehub.setOnClickListener(this)

    }

    companion object {

        fun getInstance(objects: Any): MyPerformanceFrag {
            val fragment = MyPerformanceFrag()
            return fragment
        }
    }


    override fun onClick(p0: View?) {
        when(p0?.id) {
            ll_lms_mylearning.id -> {
                (mContext as DashboardActivity).loadFragment(
                    FragType.SearchLmsLearningFrag,
                    true,
                    ""
                )
            }

            ll_lms_leaderboard.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.LeaderboardLmsFrag, true, "")
            }

            ll_lms_knowledgehub.id -> {
                (mContext as DashboardActivity).loadFragment(
                    FragType.SearchLmsKnowledgeFrag,
                    true,
                    ""
                )
            }

            ll_lms_performance.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.MyPerformanceFrag, true, "")

            }
        }
    }
}