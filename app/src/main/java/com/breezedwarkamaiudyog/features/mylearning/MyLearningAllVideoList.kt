package com.breezedwarkamaiudyog.features.mylearning

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.breezedwarkamaiudyog.R
import com.breezedwarkamaiudyog.app.types.FragType
import com.breezedwarkamaiudyog.base.presentation.BaseFragment
import com.breezedwarkamaiudyog.features.dashboard.presentation.DashboardActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MyLearningAllVideoList : BaseFragment(), View.OnClickListener {
    private lateinit var mContext: Context

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
    private lateinit var gv_vdo: GridView
    lateinit var videoList: List<GridViewAllVideoModal>


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_my_learning_all_video_list, container, false)
        initView(view)

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

        gv_vdo=view.findViewById(R.id.idGRV)

        iv_lms_mylearning.setImageResource(R.drawable.my_learning_filled_clr)

        videoList = ArrayList<GridViewAllVideoModal>()

        videoList = videoList + GridViewAllVideoModal("Top Free Courses | ProductManagement | Product Manager", "http://3.7.30.86:8073/Commonfolder/LMS/ContentUpload/Sell Me This Pen.mp4")
        videoList = videoList + GridViewAllVideoModal("Top 3 Degrees to become a Product Manager", "http://3.7.30.86:8073/Commonfolder/LMS/ContentUpload/Sell Me This Pen.mp4")
        videoList = videoList + GridViewAllVideoModal("Salary of Product Manager", "http://3.7.30.86:8073/Commonfolder/LMS/ContentUpload/Sell Me This Pen.mp4")
        videoList = videoList + GridViewAllVideoModal("How to Transition to ProductManagement?!", "http://3.7.30.86:8073/Commonfolder/LMS/ContentUpload/Sell Me This Pen.mp4")

            //val courseAdapter = GridRVAllVideoAdapter(courseList = videoList, mContext)
        val courseAdapter = VideoGridAdapter(mContext, videoList)


        gv_vdo.adapter = courseAdapter

        gv_vdo.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
           /* Toast.makeText(
                mContext, videoList[position].videoName + " selected",
                Toast.LENGTH_SHORT
            ).show()*/

            (mContext as DashboardActivity).loadFragment(FragType.VideoPlayLMS, true, "")
        }

        ll_lms_performance.setOnClickListener(this)
        ll_lms_mylearning.setOnClickListener(this)
        ll_lms_leaderboard.setOnClickListener(this)
        ll_lms_knowledgehub.setOnClickListener(this)

    }

    companion object {
        fun getInstance(objects: Any): MyLearningAllVideoList {
            val fragment = MyLearningAllVideoList()
            return fragment
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            ll_lms_mylearning.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsLearningFrag, true, "")
            }

            ll_lms_leaderboard.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.LeaderboardLmsFrag, true, "")
            }

            ll_lms_knowledgehub.id -> {
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsKnowledgeFrag, true, "")
            }
        }
    }


/*    internal class GridRVAllVideoAdapter(
        private val courseList: List<GridViewAllVideoModal>,
        private val context: Context
    ) :
        BaseAdapter() {
        private var layoutInflater: LayoutInflater? = null
        private lateinit var iv_thumnail: ImageView
        private lateinit var tv_videoname: TextView

        override fun getCount(): Int {
            return courseList.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var convertView = convertView

            if (layoutInflater == null) {
                layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }

            if (convertView == null) {
                convertView = layoutInflater!!.inflate(R.layout.gridview_item_all_vdo, null)
            }
            iv_thumnail = convertView!!.findViewById(R.id.iv_thumnail)
            tv_videoname = convertView!!.findViewById(R.id.tv_videoname)

            CoroutineScope(Dispatchers.Main).launch {
                val bitmap = retrieveVideoFrameFromVideo("http://3.7.30.86:8073/Commonfolder/LMS/ContentUpload/Sell Me This Pen.mp4")
                bitmap?.let {
                    iv_thumnail.setImageBitmap(it)
                }
            }

            *//*try {
                var bitmap = retriveVideoFrameFromVideo("http://3.7.30.86:8073/Commonfolder/LMS/ContentUpload/Sell Me This Pen.mp4")
                if (bitmap != null) {
                    iv_thumnail.setImageBitmap(bitmap)
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }*//*

            tv_videoname.setText(courseList.get(position).videoName)
            return convertView
        }



        *//*fun retriveVideoFrameFromVideo(videoPath:String): Bitmap{
            var executorService : ExecutorService = Executors.newSingleThreadExecutor()
            var bitmap: Bitmap? = null
            Handler(Looper.getMainLooper()).postDelayed({
                executorService.execute{
                    var mediaMetadataRetriever: MediaMetadataRetriever? = null
                    try {
                        mediaMetadataRetriever = MediaMetadataRetriever()
                        mediaMetadataRetriever.setDataSource(videoPath, HashMap())
                        bitmap = mediaMetadataRetriever.frameAtTime
                    } catch (e: Exception) {
                        e.printStackTrace()
                        throw Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.message)
                    } finally {
                        mediaMetadataRetriever?.release()
                    }
                }
            }, 3000)
            return bitmap!!
        }*//*

        suspend fun retrieveVideoFrameFromVideo(videoPath: String?): Bitmap? {
            return withContext(Dispatchers.IO) {
                var bitmap: Bitmap? = null
                var mediaMetadataRetriever: MediaMetadataRetriever? = null
                try {
                    mediaMetadataRetriever = MediaMetadataRetriever()
                    mediaMetadataRetriever.setDataSource(videoPath)
                    val timeUs: Long = 1000000 // 1 second in microseconds, adjust as needed
                    bitmap = mediaMetadataRetriever.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Exception in retrieveVideoFrameFromVideo(String videoPath): ${e.message}")
                } finally {
                    mediaMetadataRetriever?.release()
                }
                bitmap
            }
        }

 *//*       fun retriveVideoFrameFromVideo(videoPath: String?): Bitmap? {
            var executorService : ExecutorService = Executors.newSingleThreadExecutor()
            var bitmap: Bitmap? = null
            var mediaMetadataRetriever: MediaMetadataRetriever? = null
            try {
                mediaMetadataRetriever = MediaMetadataRetriever()
                mediaMetadataRetriever.setDataSource(videoPath, HashMap())
                bitmap = mediaMetadataRetriever.frameAtTime
            } catch (e: Exception) {
                e.printStackTrace()
                throw Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.message)
            } finally {
                mediaMetadataRetriever?.release()
            }
            return bitmap
        }*//*

    }*/

}