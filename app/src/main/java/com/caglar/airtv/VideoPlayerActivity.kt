package com.caglar.airtv


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.adapter.ChannelAdapter
import com.caglar.airtv.adapter.DetailAdapter
import com.caglar.airtv.models.ChannelData
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView


class VideoPlayerActivity : AppCompatActivity() {
    lateinit var playerView: PlayerView
    private var isFullScreen = false
    private lateinit var adapter: ChannelAdapter
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        val channelbutton:ImageButton = findViewById(R.id.exo_channel)

        channelbutton.setOnClickListener {
            println("dialog box oluşturuldu")
            showCustomDialogBox()

        }

        playerView = findViewById(R.id.player_view)
        val clickedChannelData = intent.getSerializableExtra("clickedChannelData") as? ChannelData
        enterFullScreen()
        if (clickedChannelData != null) {
            val channelLink = clickedChannelData.clink
            val player = ExoPlayer.Builder(this).build()
            playerView.player = player
            val newlink = channelLink.replace("\\/", "/")
            val uri     = newlink
            if (uri.contains("m3u8")){

                player.setMediaItem(MediaItem.fromUri(uri))
                player.prepare()
                player.play()
                playerView.findViewById<View>(R.id.fullscreen_button)?.setOnClickListener {
                    if (isFullScreen) {
                        exitFullScreen()
                    } else {
                        enterFullScreen()
                    }
                }
            }
        }
    }


    private fun enterFullScreen() {
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN


                )
        requestedOrientation =   ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        isFullScreen = true
    }

    private fun exitFullScreen() {
        supportActionBar?.show()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        isFullScreen = false
    }

    private fun showCustomDialogBox(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dailog)
        dialog.window?.setLayout(playerView.width, playerView.height)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val recyclerView = dialog.findViewById<RecyclerView>(R.id.dialogRecyclerView)
        val closeButton = dialog.findViewById<ImageButton>(R.id.closeButton)

        val gridLayoutManager = GridLayoutManager(this, 4)

        recyclerView.layoutManager = gridLayoutManager

        adapter = ChannelAdapter()
        adapter.setChannel(Constants.alldetailchannel)
        recyclerView.adapter = adapter

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()


    }
    override fun onDestroy() {
        super.onDestroy()
        playerView.player?.playWhenReady = false
        playerView.player?.release()
    }

}