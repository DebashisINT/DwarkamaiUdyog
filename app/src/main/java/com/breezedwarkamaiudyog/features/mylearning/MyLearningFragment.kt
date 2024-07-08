package com.breezedwarkamaiudyog.features.mylearning
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.breezedwarkamaiudyog.R
import com.breezedwarkamaiudyog.app.types.FragType
import com.breezedwarkamaiudyog.app.utils.Toaster
import com.breezedwarkamaiudyog.base.presentation.BaseFragment
import com.breezedwarkamaiudyog.features.contacts.ViewCrmOpptFrag
import com.breezedwarkamaiudyog.features.dashboard.presentation.DashboardActivity
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class MyLearningFragment : BaseFragment(),OnClickListener {
    private lateinit var mContext:Context
    private lateinit var bottomNavigation:MeowBottomNavigation
    private lateinit var cv_lms_learner_space:CardView
    private lateinit var cv_lms_leaderboard:CardView
    private lateinit var ll_knowledgeHub:LinearLayout
    private lateinit var ll_myLearning:LinearLayout
    private lateinit var cv_frag_search_mylearning_root:CardView

    private lateinit var ll_lms_performance:LinearLayout
    private lateinit var iv_lms_performance:ImageView
    private lateinit var tv_lms_performance:TextView

    private lateinit var ll_lms_mylearning:LinearLayout
    private lateinit var iv_lms_mylearning:ImageView
    private lateinit var tv_lms_mylearning:TextView

    private lateinit var ll_lms_leaderboard:LinearLayout
    private lateinit var iv_lms_leaderboard:ImageView
    private lateinit var tv_lms_leaderboard:TextView

    private lateinit var ll_lms_knowledgehub:LinearLayout
    private lateinit var iv_lms_knowledgehub:ImageView
    private lateinit var tv_lms_knowledgehub:TextView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_my_learning, container, false)
        initView(view)
        requireActivity().getOnBackPressedDispatcher().addCallback(object: OnBackPressedCallback(true) {
            @Override
            override fun handleOnBackPressed() {

            }
        });


        return view
    }

    private fun initView(view: View) {
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

        cv_lms_learner_space=view.findViewById(R.id.cv_lms_learner_space)
        ll_knowledgeHub=view.findViewById(R.id.ll_frag_search_knowledge_hub_root)
        ll_myLearning=view.findViewById(R.id.ll_frag_search_mylearning_root)
        cv_frag_search_mylearning_root=view.findViewById(R.id.cv_frag_search_mylearning_root)
        cv_lms_leaderboard=view.findViewById(R.id.cv_lms_leaderboard)

        cv_lms_learner_space.setOnClickListener {
            (mContext as DashboardActivity).loadFragment(FragType.SearchLmsFrag, true, "")
        }

        ll_knowledgeHub.setOnClickListener(this)
        cv_lms_leaderboard.setOnClickListener(this)
        ll_lms_performance.setOnClickListener(this)
        ll_lms_mylearning.setOnClickListener(this)
        ll_lms_leaderboard.setOnClickListener(this)
        ll_lms_knowledgehub.setOnClickListener(this)
        ll_myLearning.setOnClickListener(this)
        cv_frag_search_mylearning_root.setOnClickListener(this)

    }


    fun getURLForResource(resourceId: Int): String {
        return Uri.parse("android.resource://" + com.breezedwarkamaiudyog.R::class.java.getPackage().name + "/" + resourceId)
            .toString()
    }

    companion object {
        fun getInstance(objects: Any): MyLearningFragment {
            val fragment = MyLearningFragment()
            return fragment
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            ll_knowledgeHub.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsKnowledgeFrag, true, "")
            }

            ll_myLearning.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsLearningFrag, true, "")
            }

            cv_lms_leaderboard.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.LeaderboardLmsFrag, true, "")
            }

            ll_lms_mylearning.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsLearningFrag, true, "")
            }

            ll_lms_leaderboard.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.LeaderboardLmsFrag, true, "")
            }

            ll_lms_knowledgehub.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsKnowledgeFrag, true, "")
            }
            ll_lms_performance.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.MyPerformanceFrag, true, "")

            }
            cv_frag_search_mylearning_root.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.MyPerformanceFrag, true, "")
            }
        }
    }



}