package com.example.momoclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.momoclone.databinding.AudiobookCardItemBinding
import com.example.momoclone.viewmodel.AudioBookViewModel

// on below line we are creating an
// adapter class for our grid view.
internal class GridRVAdapter (
    // on below line we are creating two
    // variables for course list and context
    private var courseList: List<Language>,
    private val context: Context,
    var    audioBookViewModel: AudioBookViewModel
) :
    BaseAdapter() {
    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null
    private lateinit var courseTV: TextView
    private lateinit var courseIV: ImageView

    // below method is use to return the count of course list
    override fun getCount(): Int {
        return courseList.size
    }

    // below function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.

        if (position > courseList.size) {
            // Regular item view
            // Inflate and set data for regular item layout
            val inflater = LayoutInflater.from(context)
            val loadingView = inflater.inflate(R.layout.cirulcar_progress_indicator, parent, false)

            // Customize the loading state view as needed

            return loadingView
        } else
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater



//            holder = ViewHolder(itemBinding)
//            holder.view = itemBinding.getRoot()
//            holder.view.setTag(holder)
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.audiobook_card_item, null)

//            val itemBinding: AudiobookCardItemBinding =
//                AudiobookCardItemBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)

        }
        courseTV=convertView!!.findViewById(R.id.generename)
        courseTV.text=courseList.get(position).name + "$position ${audioBookViewModel.selectedItem.value}".toString()
        // on below line we are initializing our course image view
        // and course text view with their ids.

//        courseTV = convertView!!.findViewById(R.id.idTVCourse)
//        // on below line we are setting image for our course image view.
//        courseIV.setImageResource(courseList.get(position).courseImg)
//        // on below line we are setting text in our course text view.
//        courseTV.setText(courseList.get(position).courseName)
        // at last we are returning our convert view.
        return convertView
    }

    fun addAll(languageList: List<Language>) {
        courseList =courseList+languageList
        notifyDataSetChanged()
    }
    fun clearData(){
        courseList= listOf()
        notifyDataSetChanged()
    }

    private class ViewHolder internal constructor(binding: AudiobookCardItemBinding) {
        private val view: View
        private val binding: AudiobookCardItemBinding

        init {
            view = binding.getRoot()
            this.binding = binding
        }
    }
}