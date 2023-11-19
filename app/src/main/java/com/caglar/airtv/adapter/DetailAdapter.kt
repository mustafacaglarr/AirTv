package com.caglar.airtv.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.R
import com.caglar.airtv.VideoPlayerActivity
import com.caglar.airtv.models.ChannelData
import com.squareup.picasso.Picasso


class DetailAdapter: RecyclerView.Adapter<DetailAdapter.DetailHolder>(){
    private var detailList: List<ChannelData> = emptyList()
    var channelClick = MutableLiveData<ChannelData>()


    inner class DetailHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private var channelTextView:TextView = itemView.findViewById(R.id.textView1)
        private var channelLogo:ImageView = itemView.findViewById(R.id.detail_image)
        private val playButton: ImageButton = itemView.findViewById(R.id.playButton)


        fun bind(channelData: ChannelData){
            channelTextView.text =channelData.cname
            val imageurl = channelData.logoUrl
            val newurl = imageurl.replace("\\", "/").replace(" ", "%20")
            val secureUrl = newurl.replace("http://", "https://")
            Picasso.get().load(secureUrl)
                .resize(150, 100)
                .centerCrop()
                .into(channelLogo)


            playButton.setOnClickListener {
               channelClick.postValue(channelData)
                println("play button")
                println(channelData.clink)
                val intent = Intent(itemView.context, VideoPlayerActivity::class.java)
                intent.putExtra("clickedChannelData", channelData)
                itemView.context.startActivity(intent)

            }

        }

    }
    fun setChannel(packages: List<ChannelData>) {
        detailList = packages

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        return DetailHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.bind(detailList[position])

    }

    override fun getItemCount(): Int {
        return detailList.size
    }



}