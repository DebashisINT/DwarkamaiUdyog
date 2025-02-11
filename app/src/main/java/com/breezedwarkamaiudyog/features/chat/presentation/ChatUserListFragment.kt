package com.breezedwarkamaiudyog.features.chat.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.breezedwarkamaiudyog.R
import com.breezedwarkamaiudyog.app.MaterialSearchView
import com.breezedwarkamaiudyog.app.NetworkConstant
import com.breezedwarkamaiudyog.app.Pref
import com.breezedwarkamaiudyog.app.SearchListener
import com.breezedwarkamaiudyog.app.types.FragType
import com.breezedwarkamaiudyog.app.utils.AppUtils
import com.breezedwarkamaiudyog.base.presentation.BaseActivity
import com.breezedwarkamaiudyog.base.presentation.BaseFragment
import com.breezedwarkamaiudyog.features.chat.api.ChatRepoProvider
import com.breezedwarkamaiudyog.features.chat.model.ChatUserDataModel
import com.breezedwarkamaiudyog.features.chat.model.ChatUserResponseModel
import com.breezedwarkamaiudyog.features.dashboard.presentation.DashboardActivity
import com.breezedwarkamaiudyog.widgets.AppCustomTextView

import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

// Revision History
// 1.0 ChatUserListFragment saheli 24-02-2032 AppV 4.0.7 mantis 0025683
class ChatUserListFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mContext: Context

    private lateinit var rv_user_list: RecyclerView
    private lateinit var progress_wheel: ProgressWheel
    private lateinit var tv_no_data: AppCustomTextView
    private lateinit var fab: FloatingActionMenu
    private lateinit var programFab1: FloatingActionButton
    private lateinit var programFab2: FloatingActionButton

    private lateinit var getFloatingVal: java.util.ArrayList<String>

    private var chatUserList: ArrayList<ChatUserDataModel>?= null
    private var adapter: ChatUserAdapter?= null
    private val preid: Int = 100

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_chat_user_list, container, false)

        initView(view)
        initSearchListener()
        getChatUserListApi()


        return view
    }

    private fun initView(view: View) {
        view.apply {
            rv_user_list = findViewById(R.id.rv_user_list)
            progress_wheel = findViewById(R.id.progress_wheel)
            tv_no_data = findViewById(R.id.tv_no_data)
            fab = findViewById(R.id.fab)
        }
        progress_wheel.stopSpinning()
        rv_user_list.layoutManager = LinearLayoutManager(mContext)

        getFloatingVal = java.util.ArrayList<String>()
        getFloatingVal.add("Add Group")
        getFloatingVal.add("Add Conversation")

        fab.apply {
            menuIconView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_people_white))
            menuButtonColorNormal = mContext.resources.getColor(R.color.colorAccent)
            menuButtonColorPressed = mContext.resources.getColor(R.color.colorPrimaryDark)
            menuButtonColorRipple = mContext.resources.getColor(R.color.colorPrimary)
            isIconAnimated = false
            setClosedOnTouchOutside(true)
        }

        getFloatingVal.forEachIndexed { i, value ->
            if (i == 0) {
                programFab1 = FloatingActionButton(mContext)
                programFab1.let {
                    it.buttonSize = FloatingActionButton.SIZE_MINI
                    it.id = preid + i
                    it.colorNormal = mContext.resources.getColor(R.color.colorPrimaryDark)
                    it.colorPressed = mContext.resources.getColor(R.color.delivery_status_green)
                    it.colorRipple = mContext.resources.getColor(R.color.delivery_status_green)
                    it.labelText = value
                    fab.addMenuButton(it)
                    it.setOnClickListener(this)
                }
            }

            if (i == 1) {
                programFab2 = FloatingActionButton(mContext)
                programFab2.let {
                    it.buttonSize = FloatingActionButton.SIZE_MINI
                    it.id = preid + i
                    it.colorNormal = mContext.resources.getColor(R.color.colorAccent)
                    it.colorPressed = mContext.resources.getColor(R.color.delivery_status_green)
                    it.colorRipple = mContext.resources.getColor(R.color.delivery_status_green)
                    it.labelText = value
                    fab.addMenuButton(it)
                    it.setOnClickListener(this)
                }
            }


            if (i == 0) {
                programFab1.setImageResource(R.drawable.ic_tick_float_icon)
                programFab1.colorNormal = mContext.resources.getColor(R.color.delivery_status_green)
            } else if (i == 1) {
                programFab2.setImageResource(R.drawable.ic_tick_float_icon_gray)
            }
        }
    }

    private fun initSearchListener() {
        (mContext as DashboardActivity).setSearchListener(object : SearchListener {
            override fun onSearchQueryListener(query: String) {
                if (query.isBlank()) {
                    adapter?.refreshList(chatUserList!!)
                } else {
                    adapter?.filter?.filter(query)
                }
            }
        })

        // 1.0 MicroLearningListFragment AppV 4.0.7 mantis 0025683 start
        (mContext as DashboardActivity).searchView.setVoiceIcon(R.drawable.ic_mic)
        (mContext as DashboardActivity).searchView.setOnVoiceClickedListener({ startVoiceInput() })
        // 1.0 MicroLearningListFragment AppV 4.0.7 mantis 0025683 end
    }

    // 1.0 MicroLearningListFragment AppV 4.0.7 mantis 0025683 start
    private fun startVoiceInput() {
        try {
            val intent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"hi")
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?")
            try {
                startActivityForResult(intent, MaterialSearchView.REQUEST_VOICE)
            } catch (a: ActivityNotFoundException) {
                a.printStackTrace()
            }
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == MaterialSearchView.REQUEST_VOICE){
            try {
                val result = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                var t= result!![0]
                (mContext as DashboardActivity).searchView.setQuery(t,false)
            }
            catch (ex: Exception) {
                ex.printStackTrace()
            }

//            tv_search_frag_order_type_list.setText(t)
//            tv_search_frag_order_type_list.setSelection(t.length);
        }
    }
    // 1.0 MicroLearningListFragment AppV 4.0.7 mantis 0025683 end

    private fun getChatUserListApi() {
        if (!AppUtils.isOnline(mContext)) {
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))
            return
        }

        progress_wheel.spin()
        val repository = ChatRepoProvider.provideChatRepository()
        BaseActivity.compositeDisposable.add(
                repository.getChatUserList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            val response = result as ChatUserResponseModel
                            Timber.d("Get Chat User List STATUS: " + response.status)
                            if (response.status == NetworkConstant.SUCCESS) {
                                progress_wheel.stopSpinning()
                                tv_no_data.visibility = View.GONE
                                chatUserList = response.chat_user_list
                                setAdapter()
                            }
                            else {
                                progress_wheel.stopSpinning()
                                (mContext as DashboardActivity).showSnackMessage(response.message!!)
                            }

                        }, { error ->
                            error.printStackTrace()
                            progress_wheel.stopSpinning()
                            if (error != null)
                                Timber.d("Get Chat User List ERROR: " + error.localizedMessage)
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                        })
        )
    }

    private fun setAdapter() {
        adapter = ChatUserAdapter(mContext, chatUserList) {
            if (it.name.contains("(")) {
                val name = it.name.substring(0, it.name.indexOf("("))
                (mContext as DashboardActivity).userName = name
            }
            else {
                (mContext as DashboardActivity).userName = it.name
            }
            (mContext as DashboardActivity).isGrp = it.isGroup
            (mContext as DashboardActivity).grpId = it.id
            (mContext as DashboardActivity).loadFragment(FragType.ChatListFragment, true, it)
        }
        rv_user_list.adapter = adapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            100 -> {
                fab.close(true)
                programFab1.colorNormal = mContext.resources.getColor(R.color.delivery_status_green)
                programFab2.colorNormal = mContext.resources.getColor(R.color.colorAccent)
                programFab1.setImageResource(R.drawable.ic_tick_float_icon)
                programFab2.setImageResource(R.drawable.ic_tick_float_icon_gray)

                (mContext as DashboardActivity).loadFragment(FragType.AddGroupFragment, true, "")
            }

            101 -> {
                fab.close(true)
                programFab1.colorNormal = mContext.resources.getColor(R.color.colorAccent)
                programFab2.colorNormal = mContext.resources.getColor(R.color.delivery_status_green)
                programFab1.setImageResource(R.drawable.ic_tick_float_icon_gray)
                programFab2.setImageResource(R.drawable.ic_tick_float_icon)

                (mContext as DashboardActivity).loadFragment(FragType.AddNewMsgFragment, true, "")
            }
        }
    }

    fun updateList() {
        initSearchListener()

        if ((mContext as DashboardActivity).isRefreshChatUserList) {
            (mContext as DashboardActivity).isRefreshChatUserList = false
            getChatUserListApi()
        }
    }
}