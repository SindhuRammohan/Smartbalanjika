package com.template.SmartBalanjika.ui.photos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.template.SmartBalanjika.data.model.Photos
import com.template.SmartBalanjika.databinding.ItemRowPhotosBinding
import kotlinx.android.synthetic.main.item_row_photos.view.*
import kotlin.collections.ArrayList


open class PhotosAdapter :
    RecyclerView.Adapter<PhotosAdapter.DataViewHolder>(), Filterable {


    //Image world pixels

    var photosList: ArrayList<Photos> = ArrayList()
    var photosListFiltered: ArrayList<Photos> = ArrayList()


    var onItemClick: ((Photos) -> Unit)? = null

    inner class DataViewHolder(val binding: ItemRowPhotosBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(photosListFiltered[adapterPosition])
            }
        }

        fun bind(result: Photos) {
            itemView.name.text = result.author +" "+ result.location
            itemView.phno.text = result.phonenumber
            var imgurl = "https://intern.docker-dev.d-tt.nl" + result.downloadUrl
            Glide.with(itemView.imageView.context).load(imgurl).into(itemView.imageView)


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapter.DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowPhotosBinding.inflate(inflater)
        return DataViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(photosListFiltered[position])
    }

    override fun getItemCount(): Int = photosListFiltered.size


    fun addData(list: List<Photos>) {
        photosList = list as ArrayList<Photos>
        photosListFiltered = photosList
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) photosListFiltered = photosList else {
                    val filteredList = ArrayList<Photos>()
                    photosList
                        .filter {
                            (it.author.contains(constraint!!, ignoreCase = true)) or
                                    (it.author.contains(constraint, ignoreCase = true))

                        }
                        .forEach { filteredList.add(it) }
                    photosListFiltered = filteredList

                    Log.e("performFiltering: t1: ", filteredList.size.toString())

                }
                return FilterResults().apply { values = photosListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                photosListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Photos>
                notifyDataSetChanged()

                Log.e("performFiltering: t2 ", "called" + photosListFiltered.size)

            }
        }
    }


}