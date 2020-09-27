package com.test.batman

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.batman.databinding.VideoItemBinding
import com.test.batman.model.SearchItemModel
import kotlinx.android.synthetic.main.video_item.view.*

class VideoListAdapter(var videoList: List<SearchItemModel>): RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>(), VideoClickListener {
    private var context: Context? = null

    class VideoViewHolder(var view: VideoItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<VideoItemBinding>(inflater, R.layout.video_item, parent, false)
        context = parent.context
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.view.searchItemModel = videoList[position]
        holder.view.listener = this
        Log.d("bindview", videoList.size.toString())
    }

    override fun onVideoClicked(v: View) {
        val id = v.videoId.text
        val intent = Intent(context, DetailActivity:: class.java)
        intent.putExtra("VIDEO_ID", id)
        Log.d("vid", id.toString())
        context?.startActivity(intent)
    }

    fun setVideos(videos: List<SearchItemModel>) {
        videoList = videos
        notifyDataSetChanged()
    }

}