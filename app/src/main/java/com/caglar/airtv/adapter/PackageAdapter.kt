package com.caglar.airtv.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.R
import com.caglar.airtv.models.PackageData


class PackageAdapter : RecyclerView .Adapter<PackageAdapter.PackageViewHolder>() {
    private var packageList: List<PackageData> = emptyList()
    var addClick = MutableLiveData<PackageData>()
    var detailClick = MutableLiveData<PackageData>()

    inner class PackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val packageNameTextView: TextView = itemView.findViewById(R.id.textView1)
        private val imageButton: ImageButton = itemView.findViewById(R.id.imageButton)
        private val myImageButton: ImageButton = itemView.findViewById(R.id.myImageButton)
        private val myImageButton2: ImageButton = itemView.findViewById(R.id.myImageButton2)
        fun bind(packageData: PackageData) {
            myImageButton.setOnClickListener {
                addClick.postValue(packageData)
                if (packageData.isPaidContent==true){
                    AlertDialog()

                }
            }
            imageButton.setOnClickListener {
                if (packageData.isPaired == true){
                    detailClick.postValue(packageData)

                }
            }
            packageNameTextView.text = packageData.packageName
            if (packageData.isPaired==true){
                println("a")
                myImageButton.visibility = View.GONE
                myImageButton2.visibility = View.VISIBLE
            }else{
                println("b")
                myImageButton.visibility = View.VISIBLE
                myImageButton2.visibility= View.GONE
            }
            // Diğer öğeleri burada güncelleyeceğim
        }
        private fun AlertDialog() {
            val context = itemView.context
            val alertDialogBuilder = AlertDialog.Builder(context)
            val AlerDialogView = LayoutInflater.from(context).inflate(R.layout.popup, null)
            val popupbutton:Button = AlerDialogView.findViewById(R.id.popupbutton)
            val popupText:TextView = AlerDialogView.findViewById(R.id.popupText)
            alertDialogBuilder.setView(AlerDialogView)
            val alertDialog = alertDialogBuilder.create()
            popupbutton.setOnClickListener {
                val textFromPopup = popupText.text.toString()
                alertDialog.dismiss()
                println(textFromPopup)
            }

            alertDialog.show()
        }

    }
    fun setPackages(packages: List<PackageData>) {
        packageList = packages

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


}
