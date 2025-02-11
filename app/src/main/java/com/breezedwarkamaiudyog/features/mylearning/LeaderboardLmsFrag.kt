package com.breezedwarkamaiudyog.features.mylearning

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.breezedwarkamaiudyog.R
import com.breezedwarkamaiudyog.app.NetworkConstant
import com.breezedwarkamaiudyog.app.Pref
import com.breezedwarkamaiudyog.app.types.FragType
import com.breezedwarkamaiudyog.app.types.TopBarConfig
import com.breezedwarkamaiudyog.app.utils.AppUtils
import com.breezedwarkamaiudyog.app.utils.Toaster
import com.breezedwarkamaiudyog.base.presentation.BaseActivity
import com.breezedwarkamaiudyog.base.presentation.BaseFragment
import com.breezedwarkamaiudyog.features.contacts.CustomData
import com.breezedwarkamaiudyog.features.contacts.GenericDialog
import com.breezedwarkamaiudyog.features.contacts.GenericDialogWithOutSearch
import com.breezedwarkamaiudyog.features.dashboard.presentation.DashboardActivity
import com.breezedwarkamaiudyog.features.leaderboard.AdapterSubBranchName
import com.breezedwarkamaiudyog.features.leaderboard.LeaderBoardFilterOnDurationData
import com.breezedwarkamaiudyog.features.leaderboard.api.BranchData
import com.breezedwarkamaiudyog.features.leaderboard.api.LeaderboardBranchData
import com.breezedwarkamaiudyog.features.leaderboard.api.LeaderboardRepoProvider
import com.breezedwarkamaiudyog.features.leaderboard.api.SubBranchData
import com.breezedwarkamaiudyog.widgets.AppCustomEditText
import com.breezedwarkamaiudyog.widgets.AppCustomTextView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.pnikosis.materialishprogress.ProgressWheel
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LeaderboardLmsFrag : BaseFragment(), View.OnClickListener {
    private lateinit var mContext:Context
    private lateinit var bottomNavigation: MeowBottomNavigation
    private lateinit var ll_ldr_lms_top_stick_bar: LinearLayout
    private lateinit var ll_ldr_lms_ovrown: LinearLayout
    private lateinit var tv_ldr_lms_ovr: TextView
    private lateinit var tv_ldr_lms_own: TextView
    private lateinit var iv_ldr_lms_hand_anim: ImageView
    private lateinit var ll_ldr_lms_fltr: LinearLayout
    private lateinit var ll_ldr_lms_head: LinearLayout
    private lateinit var civ_ldr_lms_sec_pos_cir_img_photo: CircleImageView
    private lateinit var tv_ldr_lms_scnd_pos_name: TextView
    private lateinit var tv_ldr_lms_scnd_pos_pnts: TextView
    private lateinit var iv_ldr_lms_frst_pos_outside: ImageView
    private lateinit var iv_ldr_lms_frst_to_thrd_pos_badge: ImageView
    private lateinit var tv_ldr_lms_own_pos_rank: TextView
    private lateinit var tv_ldr_lms_frst_to_thrd_pos_name: TextView
    private lateinit var tv_ldr_lms_frst_to_thrd_pos_pnts: TextView
    private lateinit var iv_ldr_lms_thrd_pos_cir_img_photo: CircleImageView
    private lateinit var tv_ldr_lms_thrd_pos_name: TextView
    private lateinit var tv_ldr_lms_thrd_pos_pnts: TextView
    private lateinit var ll_ldr_lms_rnk_all_emplye_pnts_list_hdr: LinearLayout
    private lateinit var rv_ldr_lms_list: RecyclerView
    private lateinit var ll_ldr_lms_own_pnts: LinearLayout
    private lateinit var ll_ldr_lms_own_no_data: LinearLayout
    private lateinit var tv_ldr_lms_own_no_data: TextView
    private lateinit var ll_ldr_lms_ovr_empty_page: LinearLayout
    private lateinit var progress_wheel_frag_ldr_lms: ProgressWheel
    private lateinit var ll_ldr_lms_scnd: LinearLayout
    private lateinit var ll_ldr_lms_thrd: LinearLayout
    private var ownclick:Boolean = false
    lateinit var mLeaderboardLMSAdapter: LeaderboardLMSAdapter
    lateinit var mLeaderboardLmsItemsViewModel: ArrayList<LeaderboardLmsItemsViewModel>
    private var filterDialog: Dialog? = null
    private lateinit var tv_header: TextView
    private lateinit var ll_subBranch: LinearLayout
    private lateinit var ll_durationFilter: LinearLayout
    private lateinit var ll_durationFilter2: LinearLayout
    private lateinit var ll_branchFilter: LinearLayout
    private lateinit var tv_subBranch: TextView
    private lateinit var tv_headbranch: TextView
    private lateinit var tv_durationown: TextView
    private lateinit var tv_filterDoneOwn: TextView
    private lateinit var iv_close: ImageView
    private var respBranchData: LeaderboardBranchData = LeaderboardBranchData()
    var idMultiSubBranchL:String =""
    private var subBranch_list: ArrayList<SubBranchData> = ArrayList()
    private var subBranch_listTemp: ArrayList<SubBranchData> = ArrayList()
    private var mFilterbranchData: ArrayList<BranchData> =ArrayList()
    private var subBranchList: ArrayList<BranchData> = ArrayList()
    private var str_filterBranchID:String = ""
    private var str_filterSubBranchID:String = ""
    private var str_filterBranchIDTemp:String = ""
    private var str_filterSubBranchIDTemp:String = ""
    private var str_filterDurationID:String = ""
    private var flag:String = "M"
    private lateinit var adapterSubBranchName:AdapterSubBranchName
    private lateinit var iv_ldr_lms_fsrt_pos_cir_img_photo_own:CircleImageView
    private lateinit var iv_ldr_lms_frst_to_thrd_pos_badge_own:ImageView
    private lateinit var iv_ldr_lms_fsrt_pos_cir_img_photo:CircleImageView
    private lateinit var tv_nointernet:TextView
    private lateinit var ll_online_view_data:LinearLayout

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

    private var sharedPreferences: SharedPreferences? = null
    private var startTime: Long = 0
    private var totalTime: Long = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }


    override fun onResume() {
        super.onResume()
        startTime = SystemClock.elapsedRealtime()
    }



    override fun onStop() {
        super.onStop()
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_leaderboard_lms, container, false)

        initView(view)

        //--------date+time store------//
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val formattedDate = dateFormat.format(currentDate)
        val formattedTime = timeFormat.format(currentDate)

        var sharedPreferences = requireContext().getSharedPreferences("ModulePrefs", 0)
        val editor = sharedPreferences!!.edit()
        editor.putString("last_opening_date_leaderboard_lms", formattedDate)
        editor.putString("last_opening_time_leaderboard_lms", formattedTime)
        editor.apply()

        val lastOpeningDate = sharedPreferences.getString("last_opening_date_leaderboard_lms", null)
        val lastOpeningTime = sharedPreferences.getString("last_opening_time_leaderboard_lms", null)
        Log.d("LeaderboardLMS", "Date+time: $lastOpeningDate $lastOpeningTime")

        //-----------Time spend-------//
        sharedPreferences = mContext.getSharedPreferences("ModulePrefs", Context.MODE_PRIVATE);
        //totalTime = sharedPreferences!!.get("total_time_leaderboard_lms", 0)



        //-----------Entry cunt-------//
        // Increment the entry count
        incrementDailyEntryCount()
        // Get the entry count and display
        val entryCount = getDailyEntryCount()
        Log.d("LeaderboardLMS", "Today's entry count:onCreateview: $entryCount")

        return view
    }

    override fun onPause() {
        super.onPause()
        val endTime = SystemClock.elapsedRealtime()
        val timeSpent = endTime - startTime
        totalTime += timeSpent
        val sharedPreferences = requireActivity()!!.getSharedPreferences("ModulePrefs", Context.MODE_PRIVATE)

        val hours = totalTime / 3600000
        val minutes = (totalTime % 3600000) / 60000
        val seconds = (totalTime % 60000) / 1000

        val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        Log.d("LeaderboardLMS", "Total time spent: $timeString")
        sharedPreferences!!.edit().putString("total_time_leaderboard_lms", timeString).apply()

    }
    private fun incrementDailyEntryCount() {
        val sharedPreferences = requireContext().getSharedPreferences("ModulePrefs", 0)
        val editor = sharedPreferences!!.edit()

        val today = getCurrentDate()
        val lastDate = sharedPreferences!!.getString("LastEntryDate", null)
        val previousCount = sharedPreferences!!.getInt("DailyEntryCountOfLeaderboardLms", 0)

        Log.d("LeaderboardLMS", "Previous entry count: $previousCount")

        val newCount = if (lastDate == today) {
            previousCount + 1
        } else {
            1 // Reset count for the new day
        }
        editor.putString("LastEntryDate", today)
        editor.putInt("DailyEntryCountOfLeaderboardLms", newCount)
        editor.apply()
    }

    private fun getDailyEntryCount(): Int {
        val sharedPreferences = requireContext().getSharedPreferences("ModulePrefs", 0)
        val today = getCurrentDate()
        val lastDate = sharedPreferences!!.getString("LastEntryDate", null)

        return if (lastDate == today) {
            sharedPreferences!!.getInt("DailyEntryCountOfLeaderboardLms", 0)
        } else {
            0 // New day, reset count
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun initView(view: View) {
        ll_ldr_lms_top_stick_bar=view.findViewById(R.id.ll_ldr_lms_top_stick_bar)
        ll_ldr_lms_ovrown=view.findViewById(R.id.ll_ldr_lms_ovrown)
        tv_ldr_lms_ovr=view.findViewById(R.id.tv_ldr_lms_ovr)
        tv_ldr_lms_own=view.findViewById(R.id.tv_ldr_lms_own)
        iv_ldr_lms_hand_anim=view.findViewById(R.id.iv_ldr_lms_hand_anim)
        ll_ldr_lms_fltr=view.findViewById(R.id.ll_ldr_lms_fltr)
        ll_ldr_lms_head=view.findViewById(R.id.ll_ldr_lms_head)
        civ_ldr_lms_sec_pos_cir_img_photo=view.findViewById(R.id.civ_ldr_lms_sec_pos_cir_img_photo)
        tv_ldr_lms_scnd_pos_name=view.findViewById(R.id.tv_ldr_lms_scnd_pos_name)
        tv_ldr_lms_scnd_pos_pnts=view.findViewById(R.id.tv_ldr_lms_scnd_pos_pnts)
        iv_ldr_lms_frst_pos_outside=view.findViewById(R.id.iv_ldr_lms_frst_pos_outside)
        iv_ldr_lms_frst_to_thrd_pos_badge=view.findViewById(R.id.iv_ldr_lms_frst_to_thrd_pos_badge)
        tv_ldr_lms_own_pos_rank=view.findViewById(R.id.tv_ldr_lms_own_pos_rank)
        tv_ldr_lms_frst_to_thrd_pos_name=view.findViewById(R.id.tv_ldr_lms_frst_to_thrd_pos_name)
        tv_ldr_lms_frst_to_thrd_pos_pnts=view.findViewById(R.id.tv_ldr_lms_frst_to_thrd_pos_pnts)
        iv_ldr_lms_thrd_pos_cir_img_photo=view.findViewById(R.id.iv_ldr_lms_thrd_pos_cir_img_photo)
        tv_ldr_lms_thrd_pos_name=view.findViewById(R.id.tv_ldr_lms_thrd_pos_name)
        tv_ldr_lms_thrd_pos_pnts=view.findViewById(R.id.tv_ldr_lms_thrd_pos_pnts)
        ll_ldr_lms_rnk_all_emplye_pnts_list_hdr=view.findViewById(R.id.ll_ldr_lms_rnk_all_emplye_pnts_list_hdr)
        rv_ldr_lms_list=view.findViewById(R.id.rv_ldr_lms_list)
        ll_ldr_lms_own_pnts=view.findViewById(R.id.ll_ldr_lms_own_pnts)
        ll_ldr_lms_own_no_data=view.findViewById(R.id.ll_ldr_lms_own_no_data)
        tv_ldr_lms_own_no_data=view.findViewById(R.id.tv_ldr_lms_own_no_data)
        ll_ldr_lms_ovr_empty_page=view.findViewById(R.id.ll_ldr_lms_ovr_empty_page)
        progress_wheel_frag_ldr_lms=view.findViewById(R.id.progress_wheel_frag_ldr_lms)
        ll_ldr_lms_scnd=view.findViewById(R.id.ll_ldr_lms_scnd)
        ll_ldr_lms_thrd=view.findViewById(R.id.ll_ldr_lms_thrd)
        iv_ldr_lms_fsrt_pos_cir_img_photo_own=view.findViewById(R.id.iv_ldr_lms_fsrt_pos_cir_img_photo_own)
        iv_ldr_lms_frst_to_thrd_pos_badge_own=view.findViewById(R.id.iv_ldr_lms_frst_to_thrd_pos_badge_own)
        iv_ldr_lms_fsrt_pos_cir_img_photo=view.findViewById(R.id.iv_ldr_lms_fsrt_pos_cir_img_photo)
        tv_nointernet=view.findViewById(R.id.tv_nointernet)
        ll_online_view_data=view.findViewById(R.id.ll_online_view_data)

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

        ll_lms_performance.setOnClickListener(this)
        ll_lms_mylearning.setOnClickListener(this)
        ll_lms_leaderboard.setOnClickListener(this)
        ll_lms_knowledgehub.setOnClickListener(this)

        iv_lms_leaderboard.setImageResource(R.drawable.leaderboard_new_filled_clr)


        if (AppUtils.isOnline(mContext)) {
            tv_ldr_lms_ovr.setOnClickListener(this)
            tv_ldr_lms_own.setOnClickListener(this)
            ll_ldr_lms_fltr.setOnClickListener(this)

            tv_ldr_lms_ovr.setBackgroundResource(R.drawable.attached_image_rounded_bg)
            ll_ldr_lms_top_stick_bar.setBackgroundColor(Color.parseColor("#3d30d7"));
            context?.getColor(R.color.white)?.let { tv_ldr_lms_ovr.setTextColor(it) }
            context?.getColor(R.color.black)?.let { tv_ldr_lms_own.setTextColor(it) }

            rv_ldr_lms_list.layoutManager = LinearLayoutManager(mContext)

            mLeaderboardLmsItemsViewModel = ArrayList()
            mLeaderboardLMSAdapter = LeaderboardLMSAdapter(mLeaderboardLmsItemsViewModel)

            rv_ldr_lms_list.adapter = mLeaderboardLMSAdapter

            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#4",R.drawable.userleaderimg,"Sayantan De","5487142156","385"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#5",R.drawable.userleaderimg,"Sayan Roy","5487647156","375"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#6",R.drawable.userleaderimg,"Ritesh Kr", "548764256","353"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#7",R.drawable.userleaderimg,"Pritam Kr", "5487942156","334"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#8",R.drawable.userleaderimg,"Jayant Sharma", "5487242156","325"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#9",R.drawable.userleaderimg,"Mrinal Ghosh", "5487242136","284"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#10",R.drawable.userleaderimg,"Priyam Saha", "5484242156","268"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#11",R.drawable.userleaderimg,"Tanmoy Roy", "5487042156","212"))
            mLeaderboardLmsItemsViewModel.add(LeaderboardLmsItemsViewModel("#12",R.drawable.userleaderimg,"Rahul Kr", "5487241156","205"))

            mLeaderboardLMSAdapter.notifyDataSetChanged()

        }else{

        }

    }

    companion object {
            private const val KEY_START_TIME = "startTime"
            private const val KEY_ACCUMULATED_TIME = "accumulatedTime"
            private const val KEY_LAST_DATE = "lastDate"

        fun getInstance(objects: Any): LeaderboardLmsFrag {
            val fragment = LeaderboardLmsFrag()
            return fragment
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_ldr_lms_ovr ->{
                ownclick=false
                setToolbar()
                tv_ldr_lms_ovr.setBackgroundResource(R.drawable.attached_image_rounded_bg)
                tv_ldr_lms_own.setBackgroundResource(0)
                ll_ldr_lms_top_stick_bar.setBackgroundColor(Color.parseColor("#3d30d7"));
                context?.getColor(R.color.white)?.let { tv_ldr_lms_ovr.setTextColor(it) }
                context?.getColor(R.color.black)?.let { tv_ldr_lms_own.setTextColor(it) }
                (mContext as DashboardActivity).setTopBarTitle("Learners Leaderboard")
                iv_ldr_lms_hand_anim.visibility = View.GONE
                ll_ldr_lms_own_pnts.visibility = View.GONE
                ll_ldr_lms_own_no_data.visibility = View.GONE
                tv_ldr_lms_own_pos_rank.visibility = View.GONE
                ll_ldr_lms_rnk_all_emplye_pnts_list_hdr.visibility =View.VISIBLE
                rv_ldr_lms_list.visibility =View.VISIBLE
                ll_ldr_lms_thrd.visibility=View.VISIBLE
                ll_ldr_lms_scnd.visibility=View.VISIBLE
                iv_ldr_lms_frst_pos_outside.visibility=View.VISIBLE
                iv_ldr_lms_frst_to_thrd_pos_badge.visibility = View.VISIBLE
                tv_ldr_lms_frst_to_thrd_pos_name.text ="Satyam Roy"
                tv_ldr_lms_frst_to_thrd_pos_pnts.text ="555"
                iv_ldr_lms_fsrt_pos_cir_img_photo_own.visibility =View.GONE
                iv_ldr_lms_frst_to_thrd_pos_badge_own.visibility =View.GONE
                iv_ldr_lms_fsrt_pos_cir_img_photo.visibility =View.VISIBLE

            }
            R.id.tv_ldr_lms_own ->{
                ownclick=true
                setToolbar()
                tv_ldr_lms_own.setBackgroundResource(R.drawable.attached_image_rounded_bg)
                ll_ldr_lms_top_stick_bar.setBackgroundColor(Color.parseColor("#3d30d7"));
                tv_ldr_lms_ovr.setBackgroundResource(0)
                context?.getColor(R.color.white)?.let { tv_ldr_lms_own.setTextColor(it) }
                context?.getColor(R.color.black)?.let { tv_ldr_lms_ovr.setTextColor(it) }
                rv_ldr_lms_list.visibility=View.GONE
                ll_ldr_lms_rnk_all_emplye_pnts_list_hdr.visibility=View.GONE
                ll_ldr_lms_thrd.visibility=View.GONE
                ll_ldr_lms_scnd.visibility=View.GONE
                iv_ldr_lms_frst_pos_outside.visibility=View.GONE
                ll_ldr_lms_own_pnts.visibility = View.VISIBLE
                tv_ldr_lms_frst_to_thrd_pos_name.text = Pref.user_name
                tv_ldr_lms_frst_to_thrd_pos_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17F)
                iv_ldr_lms_hand_anim.visibility = View.VISIBLE
                tv_ldr_lms_own_pos_rank.visibility = View.VISIBLE
                tv_ldr_lms_frst_to_thrd_pos_pnts.setText("190")
                iv_ldr_lms_frst_to_thrd_pos_badge.visibility = View.GONE
                iv_ldr_lms_fsrt_pos_cir_img_photo_own.visibility =View.VISIBLE
                iv_ldr_lms_frst_to_thrd_pos_badge_own.visibility =View.GONE
                iv_ldr_lms_fsrt_pos_cir_img_photo.visibility =View.GONE

            }
            R.id.ll_ldr_lms_fltr ->{

                filterDialog = Dialog(mContext)
                filterDialog!!.setCancelable(true)
                filterDialog!!.setCanceledOnTouchOutside(false)
                filterDialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                filterDialog!!.setContentView(R.layout.filter_of_leaderboard)
                tv_header = filterDialog!!.findViewById(R.id.tv_header) as TextView
                ll_branchFilter = filterDialog!!.findViewById(R.id.ll_branchFilter) as LinearLayout
                ll_subBranch = filterDialog!!.findViewById(R.id.ll_subBranch) as LinearLayout
                tv_subBranch = filterDialog!!.findViewById(R.id.tv_subBranch) as TextView
                ll_durationFilter = filterDialog!!.findViewById(R.id.ll_durationFilter) as LinearLayout
                ll_durationFilter2 = filterDialog!!.findViewById(R.id.ll_durationFilter2) as LinearLayout
                tv_headbranch = filterDialog!!.findViewById(R.id.tv_headbranch) as TextView
                tv_durationown = filterDialog!!.findViewById(R.id.tv_durationown) as TextView
                iv_close = filterDialog!!.findViewById(R.id.iv_close) as ImageView
                tv_filterDoneOwn = filterDialog!!.findViewById(R.id.tv_filterDoneOwn) as TextView

                tv_filterDoneOwn.setOnClickListener(this)

                if (AppUtils.isOnline(mContext)) {
                    getHeadBranchList(tv_headbranch, tv_subBranch)
                    ll_online_view_data.visibility =View.VISIBLE
                    tv_nointernet.visibility=View.GONE
                    ll_ldr_lms_top_stick_bar.visibility=View.VISIBLE
                }else{
                     tv_nointernet.visibility=View.VISIBLE
                     ll_online_view_data.visibility =View.INVISIBLE
                     ll_ldr_lms_top_stick_bar.visibility =View.INVISIBLE
                }

                tv_filterDoneOwn.setOnClickListener {
                    str_filterBranchIDTemp = str_filterBranchID
                    str_filterSubBranchIDTemp = str_filterSubBranchID
                    println("subBranch_list--"+subBranch_list.size)
                    subBranch_listTemp = subBranch_list
                    println("subBranch_listTemp--"+subBranch_listTemp.size)
                    if (ownclick) {
                        if (!str_filterSubBranchID.equals("")) {
                            filterDialog!!.dismiss()
                            // ownAPI()
                        }
                        else{
                            showNoDataDialog()
                        }
                    }else{
                        if (!str_filterSubBranchID.equals("")) {
                            filterDialog!!.dismiss()
                            //  overAllAPI()
                        }
                        else{
                            showNoDataDialog()
                        }
                    }

                }

                tv_header.text = "Filter"
                val params: WindowManager.LayoutParams = filterDialog!!.getWindow()!!.getAttributes() // change this to your dialog.
                params.y = -440 // Here is the param to set your dialog position. Same with params.x
                filterDialog!!.getWindow()?.setAttributes(params)
                filterDialog!!.show()

                iv_close.setOnClickListener {
                    str_filterBranchID = str_filterBranchIDTemp
                    str_filterSubBranchID = str_filterSubBranchIDTemp
                    println("subBranch_listTemp--"+subBranch_listTemp.size)
                    subBranch_list = subBranch_listTemp
                    println("subBranch_list--"+subBranch_list.size)
                    filterDialog!!.dismiss()
                }

                Handler().postDelayed(Runnable {
                    if(respBranchData.status == NetworkConstant.SUCCESS){
                        mFilterbranchData.clear()
                        mFilterbranchData = respBranchData.branch_list.clone() as ArrayList<BranchData>
                        if(mFilterbranchData.size>0){
                            var headBranchAll = respBranchData.branch_list.filter { it.branch_head.equals("All",ignoreCase = true) }.first()
                            subBranchList =(mFilterbranchData.filter { it.branch_head_id == headBranchAll.branch_head_id }).first().sub_branch as ArrayList<BranchData>
                            subBranch_list =headBranchAll.sub_branch

                            var genericL : ArrayList<CustomData> = ArrayList()
                            for(i in 0..mFilterbranchData.size-1){
                                genericL.add(CustomData(mFilterbranchData.get(i).branch_head_id.toString(),mFilterbranchData.get(i).branch_head))
                            }
                            GenericDialog.newInstance("Head Branch",genericL as ArrayList<CustomData>){
                                str_filterBranchID = it.id
                                tv_headbranch.setText(it.name)
                                subBranchList =mFilterbranchData.filter { it.branch_head_id == str_filterBranchID.toInt() } as ArrayList<BranchData>
                                for(i in 0..subBranchList.size-1){
                                    subBranch_list = subBranchList.get(i).sub_branch
                                    if (subBranch_list.size > 0) {
                                        tv_subBranch.text = subBranch_list.get(0).value
                                    }else{
                                        tv_subBranch.text = ""
                                        str_filterSubBranchID=""
                                    }
                                }

                            }
                        }else{
                            Toaster.msgShort(mContext, "No Branch Found")
                        }
                    }
                    else{
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    }

                }, 1500)



                //branch filter
                ll_branchFilter.setOnClickListener {
                    ll_branchFilter.isEnabled = false
                    if(respBranchData.status == NetworkConstant.SUCCESS){

                        //mFilterbranchData.clear()
                        mFilterbranchData = respBranchData.branch_list.clone() as ArrayList<BranchData>
                        if(mFilterbranchData.size>0){
                            var genericL : ArrayList<CustomData> = ArrayList()
                            for(i in 0..mFilterbranchData.size-1){
                                genericL.add(CustomData(mFilterbranchData.get(i).branch_head_id.toString(),mFilterbranchData.get(i).branch_head))
                            }
                            GenericDialog.newInstance("Head Branch",genericL as ArrayList<CustomData>){
                                str_filterBranchID = it.id
                                tv_headbranch.setText(it.name)
                                tv_subBranch.text = ""
                                str_filterSubBranchID=""
                                subBranchList =mFilterbranchData.filter { it.branch_head_id == str_filterBranchID.toInt() } as ArrayList<BranchData>
                                for(i in 0..subBranchList.size-1){
                                    subBranch_list = subBranchList.get(i).sub_branch
                                    if (subBranch_list.size > 0) {
                                        // tv_subBranch.text = subBranch_list.get(0).value
                                        // str_filterSubBranchID = subBranch_list.get(0).id.toString()
                                    }else{
                                        tv_subBranch.text = ""
                                        str_filterSubBranchID=""
                                    }
                                }

                            }.show((mContext as DashboardActivity).supportFragmentManager, "")
                        }else{
                            Toaster.msgShort(mContext, "No Branch Found")
                        }
                    }
                    else{
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    }

                    Handler().postDelayed(Runnable {
                        ll_branchFilter.isEnabled = true
                    }, 1000)
                }
                //Sub branch filter
                ll_subBranch.setOnClickListener {
                    println("subBranch_list>>>"+subBranch_list.size)
                    ll_subBranch.isEnabled = false
                    Handler().postDelayed(Runnable {
                        if (subBranch_list.size > 0) {
                            getSubBranchList(tv_subBranch, subBranch_list)
                        } else {
                            showNoDataDialog()
                        }
                    },500)
                    Handler().postDelayed(Runnable {
                        ll_subBranch.isEnabled = true
                    },1000)
                }
                //duration filter
                ll_durationFilter2.setOnClickListener {
                    ll_durationFilter2.isEnabled = false
                    var mFilterbranchData = ArrayList<LeaderBoardFilterOnDurationData>()
                    mFilterbranchData.add(LeaderBoardFilterOnDurationData(5,"MTD"))
                    mFilterbranchData.add(LeaderBoardFilterOnDurationData(5,"Overall"))
                    if(mFilterbranchData.size>0){
                        var genericL : ArrayList<CustomData> = ArrayList()
                        for(i in 0..mFilterbranchData.size-1){
                            genericL.add(CustomData(mFilterbranchData.get(i).id.toString(),mFilterbranchData.get(i).name.toString()))
                        }
                        GenericDialogWithOutSearch.newInstance("Duration",genericL as ArrayList<CustomData>){
                            str_filterDurationID = it.id
                            tv_durationown.setText(it.name)

                            if (it.name.equals("MTD")){
                                flag = "M"
                            }
                            if (it.name.equals("Overall")){
                                flag = "O"
                            }
                        }.show((mContext as DashboardActivity).supportFragmentManager, "")
                    }else{
                        Toaster.msgShort(mContext, "No Duration Found")
                    }
                    Handler().postDelayed(Runnable {
                        ll_durationFilter2.isEnabled = true
                    },1000)
                }
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
        }
    }

    private fun getHeadBranchList(
        tv_headbranch: TextView,
        tv_subBranch: TextView
    ) {
        progress_wheel_frag_ldr_lms.spin()
        val repository = LeaderboardRepoProvider.provideLeaderboardbranchRepository()
        BaseActivity.compositeDisposable.add(
            repository.branchlist(Pref.session_token.toString())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    respBranchData = result as LeaderboardBranchData
                    progress_wheel_frag_ldr_lms.stopSpinning()
                    if (respBranchData.branch_list.size > 0) {
                        tv_headbranch.text = respBranchData.branch_list.get(0).branch_head
                        str_filterBranchID = respBranchData.branch_list.get(0).branch_head_id.toString()
                        respBranchData.branch_list.get(0).sub_branch.get(0).id.toString()

                    }else{
                        tv_headbranch.text=""
                        str_filterBranchID=""
                        tv_subBranch.text = ""
                        str_filterSubBranchID=""

                    }
                }, { error ->
                    error.printStackTrace()
                    progress_wheel_frag_ldr_lms.stopSpinning()
                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                })
        )
    }

    private fun getSubBranchList(tv_subBranch: TextView, subBranch_list: ArrayList<SubBranchData>) {
        val subBranchDialog = Dialog(mContext)
        subBranchDialog.setCanceledOnTouchOutside(false)
        subBranchDialog.setCancelable(false)
        subBranchDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        subBranchDialog.setContentView(R.layout.dialog_leaderboard_subbranch_select)
        val rvSubBranchL = subBranchDialog.findViewById(R.id.rv_dialog_subBranch_list) as RecyclerView
        val tvHeader = subBranchDialog.findViewById(R.id.tv_dialog_subBranch_sel_header) as TextView
        val submit = subBranchDialog.findViewById(R.id.tv_dialog_subBranch_submit) as TextView
        val et_subBranchNameSearch =
            subBranchDialog.findViewById(R.id.et_dialog_subBranch_search) as AppCustomEditText
        val subBranch_selectAll =
            subBranchDialog.findViewById(R.id.cb_dialog_subBranch_select_all) as CheckBox
        val iv_close_sub =
            subBranchDialog.findViewById(R.id.iv_dialog_generic_list_close_icon) as ImageView

        var totalLSize = subBranch_list.size
        var totalLTickSize = subBranch_list.filter { it.isTick }.size
        if (totalLSize == totalLTickSize){
            subBranch_selectAll.isChecked = true
            subBranch_selectAll.setText("Deselect All")
        }else{
            subBranch_selectAll.isChecked = false
            subBranch_selectAll.setText("Select All")
        }
        var subBranch_listTemp : ArrayList<SubBranchData> = ArrayList()
        adapterSubBranchName = AdapterSubBranchName(mContext,subBranch_list,object :
            AdapterSubBranchName.OnSubBrCLick{
            override fun onTick(obj: SubBranchData, isTick: Boolean) {
                if (isTick) {
                    subBranch_listTemp.add(obj)
                } else {
                    subBranch_listTemp.remove(obj)
                }
            }
        }, {
            it
        })

        submit.setOnClickListener {
            var tickSize = subBranch_list.filter { it.isTick }.size
            if (tickSize==0){
                Toast.makeText(mContext, "Please select atleast one contact", Toast.LENGTH_SHORT).show()
            }
            else{
                var finalSUbBrL = subBranch_list.filter { it.isTick }
                try {
                    var subBrNameL = finalSUbBrL.map { it.value }
                    var nameL = ""
                    for(i in 0..subBrNameL.size-1){
                        nameL = nameL + subBrNameL.get(i)+","
                    }
                    if (nameL.endsWith(",")) {
                        nameL = nameL.substring(0, nameL.length - 1);
                    }
                    tv_subBranch.text = nameL
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    var subBrIdL = subBranch_list.filter { it.isTick }.map { it.id.toString() }
                    idMultiSubBranchL = ""
                    for (i in 0.. subBrIdL.size-1){
                        idMultiSubBranchL = idMultiSubBranchL+subBrIdL.get(i)+","
                    }
                    if (idMultiSubBranchL.endsWith(",")) {
                        idMultiSubBranchL = idMultiSubBranchL.substring(0, idMultiSubBranchL.length - 1);
                    }
                    str_filterSubBranchID = idMultiSubBranchL
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                subBranchDialog.cancel()
            }
            var finalSUbBrL = subBranch_list.filter { it.isTick }
            try {
                var subBrNameL = finalSUbBrL.map { it.value }
                var nameL = ""
                for(i in 0..subBrNameL.size-1){
                    nameL = nameL + subBrNameL.get(i)+","
                }
                if (nameL.endsWith(",")) {
                    nameL = nameL.substring(0, nameL.length - 1);
                }
                tv_subBranch.text = nameL
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                var subBrIdL = subBranch_list.filter { it.isTick }.map { it.id.toString() }
                idMultiSubBranchL = ""
                for (i in 0.. subBrIdL.size-1){
                    idMultiSubBranchL = idMultiSubBranchL+subBrIdL.get(i)+","
                }
                if (idMultiSubBranchL.endsWith(",")) {
                    idMultiSubBranchL = idMultiSubBranchL.substring(0, idMultiSubBranchL.length - 1);
                }
                str_filterSubBranchID = idMultiSubBranchL
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        subBranch_selectAll.setOnCheckedChangeListener { compoundButton, b ->
            println("tag_selectAllCheck")
            if (compoundButton.isChecked) {
                adapterSubBranchName.selectAll()
                subBranch_selectAll.setText("Deselect All")
            } else {
                adapterSubBranchName.deselectAll()
                subBranch_selectAll.setText("Select All")
            }
        }
        et_subBranchNameSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapterSubBranchName!!.getFilter().filter(et_subBranchNameSearch.text.toString().trim())
            }
        })

        iv_close_sub.setOnClickListener {
            for (i in 0..subBranch_listTemp.size-1){
                subBranch_list.filter { it.id == subBranch_listTemp.get(i).id }.first().isTick=false
            }
            subBranchDialog.dismiss()
        }

        rvSubBranchL.adapter = adapterSubBranchName
        subBranchDialog.show()
    }

    private fun showNoDataDialog() {
        val simpleDialog = Dialog(mContext)
        simpleDialog.setCancelable(true)
        simpleDialog.setCanceledOnTouchOutside(false)
        simpleDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        simpleDialog.setContentView(R.layout.dialog_yes_no_3)
        val dialogHeader = simpleDialog.findViewById(R.id.dialog_cancel_order_header_TV) as AppCustomTextView
        dialogHeader.text = mContext.applicationContext.getString(R.string.branch_select)
        val dialog_yes_no_headerTV = simpleDialog.findViewById(R.id.dialog_yes_no_headerTV) as AppCustomTextView
        dialog_yes_no_headerTV.text = "Hi "+Pref.user_name!!+"!"
        val dialogYes = simpleDialog.findViewById(R.id.tv_dialog_yes_no_yes) as AppCustomTextView
        dialogYes.setOnClickListener({ view ->
            simpleDialog.cancel()
        })
        simpleDialog.show()
    }

    private fun setToolbar() {
        if (!ownclick) {
            (mContext as DashboardActivity).setTopBarTitle("Learners Leaderboard")
            (mContext as DashboardActivity).setTopBarVisibility(TopBarConfig.LEADERBOARD)
        }else{
            (mContext as DashboardActivity).setTopBarTitle("Learners Leaderboard (Me)")
            (mContext as DashboardActivity).setTopBarVisibility(TopBarConfig.LEADERBOARD_OWN)

        }
    }
}