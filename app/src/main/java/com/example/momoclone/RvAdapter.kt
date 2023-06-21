import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.recyclerview.widget.RecyclerView
import com.example.momoclone.Language
import com.example.momoclone.R
import com.example.momoclone.databinding.CirlceGenereItemBinding
import com.example.momoclone.viewmodel.AudioBookViewModel

class RvAdapter(
    var languageList: List<Language>,var audioBookViewModel: AudioBookViewModel
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
val index=0;
    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: CirlceGenereItemBinding) : RecyclerView.ViewHolder(binding.root)
    fun onItemChanged(position:Int){

    }
    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CirlceGenereItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){


            with(languageList[position]){
                binding.label.text = this.name+ "".toString()
                if(audioBookViewModel.selectedItem.value==position)
binding.ivLanguage.alpha= 1.0F
                else{
                    binding.ivLanguage.alpha= 0.5F
                }
            }
            binding.ivLanguage.setOnClickListener {
                audioBookViewModel.selectedItem.value =position

                Log.d("TAG", "onBindViewHolder: $position")
                notifyDataSetChanged();
            }

        }

    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return languageList.size
    }
}