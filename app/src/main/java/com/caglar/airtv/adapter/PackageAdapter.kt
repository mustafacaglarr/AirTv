package com.caglar.airtv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.R
import com.caglar.airtv.data.PackageData

class PackageAdapter : RecyclerView.Adapter<PackageAdapter.PackageViewHolder>() {
    private var packageList: List<PackageData> = emptyList()

    inner class PackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val packageNameTextView: TextView = itemView.findViewById(R.id.textView1)

        fun bind(packageData: PackageData) {
            packageNameTextView.text = packageData.packageName

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
        notifyDataSetChanged()
    }
}