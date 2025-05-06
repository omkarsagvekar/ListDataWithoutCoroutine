package com.example.recyclerviewkotlinexample

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast

class CustomSpinnerAdapter(
    private val context: Context,
    private var items:  LinkedHashMap<String, UserHashMapData>,
    private val callback: SendDataCallback, // Callback instance
    private val onDeleteClick: (String) -> Unit // Add this lambda parameter
) : BaseAdapter() {
    private var keys = items.keys.toList() // Store keys for indexed access
    override fun getCount() = items.size
    override fun getItem(position: Int) : UserHashMapData{
        keys = items.keys.toList()   //for the new updated list keys
        // Corrected: Access the value by key
        val key = keys[position]
        return items[key]!! //Handle null
    }
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // For selected item view
        return createCustomView(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createCustomView(position, parent)
    }

    private fun createCustomView(position: Int, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.simple_spinner_update_delete, parent, false)
        val textView = view.findViewById<TextView>(R.id.tvName)
        val deleteBtn = view.findViewById<ImageButton>(R.id.ivDelete)
        val llParent = view.findViewById<LinearLayout>(R.id.llParent)

        deleteBtn.visibility = if (position == 0) View.GONE else View.VISIBLE


        val item = getItem(position)
        textView.text = item.name
        Log.d("Testing", "item.name: "+item.name)

        if (position != 0){
            textView.setOnLongClickListener(){
                val popup = PopupMenu(context, llParent)
                popup.menu.add("Update") // Add "Update" option
                popup.setOnMenuItemClickListener { menuItem ->
                    if (menuItem.title == "Update") {
                        // Handle update action here

                        //Toast.makeText(context, "Update ${item.name}", Toast.LENGTH_SHORT).show()
                        // You can show an edit dialog or navigate to an update activity
                        items.let {
                            items.get(keys[position]) ?.let { it1 -> callback.onSendData(keys[position], it1) } // Notify the Activity/Fragment
                        }
                    }
                    true
                }
                popup.show()
                true
            }
        }

        deleteBtn.setOnClickListener{
            onDeleteClick(keys[position])
        }

        return view
    }

    fun updateData(newItems: LinkedHashMap<String, UserHashMapData>) {
        //items.clear()
        items = newItems
        notifyDataSetChanged()
    }

    interface SendDataCallback {
        fun onSendData(key: String, userData: UserHashMapData)
    }
}
