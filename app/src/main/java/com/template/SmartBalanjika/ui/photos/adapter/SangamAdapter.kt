package com.template.SmartBalanjika.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.SmartBalanjika.data.model.Sangam
import com.template.SmartBalanjika.databinding.AboutUsDetailsBinding
import kotlinx.android.synthetic.main.about_us_details.view.*
import kotlinx.android.synthetic.main.item_row_pdf.view.*
import javax.inject.Inject

class SangamAdapter @Inject constructor():
    RecyclerView.Adapter<SangamAdapter.DataViewHolder>() {


    //Image world pixels
    var onItemClick: ((Sangam) -> Unit)? = null


    var photosList: ArrayList<Sangam> = ArrayList()
    var photosListFiltered: ArrayList<Sangam> = ArrayList()



    inner class DataViewHolder(val binding: AboutUsDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(photosListFiltered[adapterPosition])
            }
        }

        fun bind(result: Sangam) {
            itemView.sangamdetails.text = result.author
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AboutUsDetailsBinding.inflate(inflater)

        return DataViewHolder(binding)
    }



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(photosListFiltered[position])
    }

    override fun getItemCount(): Int = photosListFiltered.size


    fun addData(list: List<Sangam>) {
        photosList = list as ArrayList<Sangam>
        photosListFiltered = photosList
        notifyDataSetChanged()
    }





}