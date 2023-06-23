package com.template.SmartBalanjika.ui.photos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.template.SmartBalanjika.data.model.pdf
import com.template.SmartBalanjika.databinding.ItemRowPdfBinding
import kotlinx.android.synthetic.main.item_row_pdf.view.*
import javax.inject.Inject

class pdfAdapter @Inject constructor():
    RecyclerView.Adapter<pdfAdapter.DataViewHolder>() , Filterable {


    //Image world pixels
    var onItemClick: ((pdf) -> Unit)? = null


    var photosList: ArrayList<pdf> = ArrayList()
    var photosListFiltered: ArrayList<pdf> = ArrayList()



    inner class DataViewHolder(val binding: ItemRowPdfBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(photosListFiltered[adapterPosition])
            }
        }

        fun bind(result: pdf) {
            itemView.namepdf.text = result.date
            Log.e("date test Sindhu","string"+ result.link)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowPdfBinding.inflate(inflater)
        return DataViewHolder(binding)
    }



    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(photosListFiltered[position])
    }

    override fun getItemCount(): Int = photosListFiltered.size


    fun addData(list: List<pdf>) {
        photosList = list as ArrayList<pdf>
        photosListFiltered = photosList
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                Log.e("performFiltering: 1", "called")
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) photosListFiltered = photosList else {
                    val filteredList = ArrayList<pdf>()
                    photosList
                        .filter {
                            (it.date.contains(constraint!!, ignoreCase = true)) or
                                    (it.date.contains(constraint, ignoreCase = true))

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
                    results.values as ArrayList<pdf>
                notifyDataSetChanged()

                Log.e("performFiltering: t2 ", "called" + photosListFiltered.size)

            }
        }
    }


}