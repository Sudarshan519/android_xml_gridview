package com.example.momoclone

import RvAdapter
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore.Audio
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.AbsListView
import android.widget.GridView
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.view.marginBottom
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.momoclone.databinding.HomeBinding
import com.example.momoclone.viewmodel.AudioBookViewModel
import kotlin.math.absoluteValue


class MainActivity : ComponentActivity(), AbsListView.OnScrollListener  {
    // view binding for the activity
    private var _binding : HomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var audioBookViewModel:AudioBookViewModel
    var languagelist= mutableStateListOf<Language>()
    var selectedItem= mutableStateOf<Int>(0)

    private  var isLoading=false

    private lateinit var rvAdapter: RvAdapter
    private lateinit var languageList : List<Language>
    private var scrollingDown=false
    private  lateinit var gridRVAdapter: GridRVAdapter
    lateinit var audiobookList: List<Language>
    var previousSrollIndex:Int=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = HomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        audioBookViewModel = ViewModelProvider(this).get(AudioBookViewModel::class.java)

        // load data to language list
        loadLanguage()

        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        // pass it to rvLists layoutManager
        binding.rec1.setLayoutManager(layoutManager)

        // initialize the adapter,
        // and pass the required argument
        rvAdapter = RvAdapter(languageList,audioBookViewModel)

        // attach adapter to the recycler view
        binding.rec1.adapter = rvAdapter
        gridRVAdapter = GridRVAdapter(courseList = languageList, this@MainActivity,audioBookViewModel)

        // on below line we are setting adapter to our grid view.
        binding.rec2.adapter = gridRVAdapter

        binding.rec2.setOnScrollListener(this)


//        binding.progressbar.visibility = View.INVISIBLE

        binding.rec2.viewTreeObserver.addOnScrollChangedListener(scrollChangedListener)

audioBookViewModel.selectedItem.observe(this, Observer { newWord ->
    Log.d("TAG", "onCreate: $newWord")
    if(newWord==0){
        gridRVAdapter.addAll(languageList )
    }else
    gridRVAdapter.clearData()
})
    }
    private val scrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
        val scrollY = binding.rec2.scrollY

        if (scrollY > previousSrollIndex) {
            // Scrolling down
            // Perform actions for scrolling down
            println("Scrolling Down $scrollY $previousSrollIndex")
            scrollingDown=true
        } else if (scrollY < previousSrollIndex) {
            // Scrolling up
            // Perform actions for scrolling up
            println("Scrolling Up  $scrollY $previousSrollIndex")
            if(scrollY.absoluteValue<=1) {
                binding.progressbar.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({


                    gridRVAdapter.addAll(languageList)
                    binding.progressbar.visibility = View.INVISIBLE
                }, 4000)
            }

//            binding.progressbar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressbar.visibility = View.INVISIBLE

            }, 3000)
            scrollingDown=false
        }

        previousSrollIndex = scrollY
    }

    // add items to the list manually in our case
    private fun loadLanguage() {
        languageList = listOf(
            Language("Java" , "Exp : 3 years"),
            Language("Kotlin" , "Exp : 2 years"),
            Language("Python" , "Exp : 4 years"),
            Language("JavaScript" , "Exp : 6 years"),
            Language("PHP" , "Exp : 1 years"),
            Language("CPP" , "Exp : 8 years"),
            Language("CPP" , "Exp : 8 years"),  Language("CPP" , "Exp : 8 years"),
            Language("CPP" , "Exp : 8 years"),
            Language("CPP" , "Exp : 8 years"),  Language("CPP" , "Exp : 8 years"),
        )
        binding.progressbar.visibility = View.VISIBLE

       audioBookViewModel. loading.value=true
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressbar.visibility = View.INVISIBLE
            languagelist.addAll(languageList)
            audioBookViewModel. loading.value=false
            binding.progressbar.visibility = View.INVISIBLE
        }, 3000)

    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

        Log.d("TAG", "onScrollStateChanged: $scrollState  ")

    }

    override fun onScroll(
        view: AbsListView?,
        firstVisibleItem: Int,
        visibleItemCount: Int,
        totalItemCount: Int
    ) {
        val lastVisibleItem = firstVisibleItem + visibleItemCount
//        Log.d("TAG", "onScroll: $firstVisibleItem $visibleItemCount")
//         if (firstVisibleItem + visibleItemCount == totalItemCount) {
//        // Load more data
//    }
//
//        if (firstVisibleItem + visibleItemCount == totalItemCount && !isLoading && scrollingDown ) {
//            // Reached the end of the GridView and not already loading data
//            isLoading = true
//            binding.rec2.setPadding(0,0,0,60)
//            binding.progressbar.visibility = View.VISIBLE
////            binding.progressbar.visibility = View.VISIBLE
//
//            gridRVAdapter.addAll(languageList)
//            Handler(Looper.getMainLooper()).postDelayed({
//                binding.progressbar.visibility = View.INVISIBLE
//
//
//            }, 1000)
//
//previousSrollIndex=lastVisibleItem
//        }
//        else{
//            isLoading = false
//            binding.progressbar.visibility = View.INVISIBLE
//
//        }
//        if(isLoading){
//            binding.progressbar.visibility = View.VISIBLE
//        }else{
//            binding.progressbar.visibility = View.INVISIBLE
//        }
//        Log.d("TAG", "onScroll: $isLoading")

    }
}
