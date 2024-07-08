package com.breezedwarkamaiudyog.features.mylearning

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.breezedwarkamaiudyog.R
import com.breezedwarkamaiudyog.app.types.FragType
import com.breezedwarkamaiudyog.base.presentation.BaseFragment
import com.breezedwarkamaiudyog.features.dashboard.presentation.DashboardActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class SearchLmsKnowledgeFrag : BaseFragment() , LmsSearchAdapter.OnItemClickListener{
    private lateinit var mContext:Context
    lateinit var gv_search: RecyclerView
    lateinit var tv_next_afterSearch_lms: LinearLayout
    lateinit var courseList: List<LmsSearchData>
    lateinit var lmsSearchAdapter: LmsSearchAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_search_lms, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        gv_search = view.findViewById(R.id.gv_search)
        tv_next_afterSearch_lms = view.findViewById(R.id.tv_next_afterSearch_lms)


        courseList = ArrayList<LmsSearchData>()
        courseList = courseList + LmsSearchData("1","Sales Skill")
        courseList = courseList + LmsSearchData("2","Scheme Knowledge")
        courseList = courseList + LmsSearchData("3","Product Knowledge")
        courseList = courseList + LmsSearchData("4","Qualifying the Sale")
        courseList = courseList + LmsSearchData("5","Mini Quizzes")
        courseList = courseList + LmsSearchData("6","Customer Knowledge")
        courseList = courseList + LmsSearchData("7","Time Management")
        courseList = courseList + LmsSearchData("8","Negotiation")
        courseList = courseList + LmsSearchData("9","Analyzing Customer Needs")

        val layoutManager = FlexboxLayoutManager(mContext)
        layoutManager.flexDirection = FlexDirection.COLUMN
        layoutManager.justifyContent = JustifyContent.FLEX_END
        gv_search.layoutManager = FlexboxLayoutManager(mContext)
        lmsSearchAdapter = LmsSearchAdapter(mContext,courseList,this)
        gv_search.adapter = lmsSearchAdapter

        tv_next_afterSearch_lms.setOnClickListener {
            if (lmsSearchAdapter.getSelectedPosition() == RecyclerView.NO_POSITION) {
                Toast.makeText(mContext, "Please select one item", Toast.LENGTH_SHORT).show()
            } else {
                val selectedItem = courseList[lmsSearchAdapter.getSelectedPosition()]
                (mContext as DashboardActivity).loadFragment(FragType.KnowledgeHubAllVideoList, true, "")
            }
        }

    }

    companion object {
        fun getInstance(objects: Any): SearchLmsKnowledgeFrag {
            val fragment = SearchLmsKnowledgeFrag()
            return fragment
        }
    }

    override fun onItemClick(item: LmsSearchData) {
        //Toast.makeText(mContext, "Clicked item: ${item.courseName} ", Toast.LENGTH_SHORT).show()
    }


}