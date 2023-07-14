package com.caglar.airtv.adapter

import android.app.AlertDialog
import android.media.MediaPlayer
import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.navigation.NavDeepLinkRequest.Builder.Companion.fromUri
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.R
import com.caglar.airtv.data.PackageData
import com.google.android.exoplayer2.MediaItem.fromUri
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PackageAdapter : RecyclerView.Adapter<PackageAdapter.PackageViewHolder>() {
    private var packageList: List<PackageData> = emptyList()

    inner class PackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val packageNameTextView: TextView = itemView.findViewById(R.id.textView1)
        private val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)
        fun bind(packageData: PackageData) {
            packageNameTextView.text = packageData.packageName
            imageButton.setOnClickListener {
                println("pencere açılacak")
                val videoUrl = packageData.preview

                // MediaPlayer ile videoyu oynatma
                val mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(videoUrl)
                mediaPlayer.prepare()
                mediaPlayer.start()



            }


            // Diğer öğeleri burada güncelleyeceğim
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PackageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        holder.bind(packageList[position])
    }

    override fun getItemCount(): Int {
        return packageList.size
    }

    fun setPackages(packages: List<PackageData>) {
        packageList = packages
    }
}
