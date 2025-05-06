package com.example.recyclerviewkotlinexample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviewkotlinexample.databinding.ActivityThirdBinding
import java.util.HashMap
import java.util.Objects

class ThirdActivity : AppCompatActivity(), CustomSpinnerAdapter.SendDataCallback {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var view: View
    private val userHashMapData = LinkedHashMap<String, UserHashMapData>()
    lateinit var adapter: CustomSpinnerAdapter
    lateinit var keyForUpdate: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        allListener()
        userHashMapData.put("Select Name", UserHashMapData("Select Name", 0, ""))
        userHashMapData.put("Kiran", UserHashMapData("Kiran", 9870539300, "Mumbai"))
        userHashMapData.put("Salman Sir", UserHashMapData("Salman Sir", 123456789, "Thane"))
        userHashMapData.put("Siera", UserHashMapData("Siera", 98782386862, "Navi Mumbai"))
        userHashMapData.put("Json Stathum", UserHashMapData("Json Stathum", 4444444444, "Goa"))
        userHashMapData.put("Amilie", UserHashMapData("Amilie", 777777777777, "Chiplun"))

        val userKeys = userHashMapData.keys.toList()
        val users = userHashMapData.values.toList()

        val users2 = userKeys.toMutableList()

//        val adapter = ArrayAdapter(
//            this,
//            R.layout.simple_spinner_update_delete,
//            userKeys
//        )
        adapter = CustomSpinnerAdapter(this@ThirdActivity, userHashMapData,
//            {
////                clickedItem ->
////                Log.d("Click", "Clicked item: $clickedItem")
////                Toast.makeText(this, "Clicked: $clickedItem", Toast.LENGTH_SHORT).show()
//            },
            this,
            {
                    deletedItem ->
                Log.d("Click", "Clicked item: $deletedItem")
                userHashMapData.remove(deletedItem)
                adapter.updateData(userHashMapData)
            }
        )
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        /*
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                //val selectedCity = userNames[position].
                //val selectedCode = userHashMapData.filterValues { it == selectedCity }.keys.first()
                binding.tvName.text = users[position].name
                binding.tvMobNo.text = users[position].mobNo.toString()
                binding.tvCity.text = users[position].city
                //Toast.makeText(this@ThirdActivity, "Code: $selectedCode | City: $selectedCity", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

         */
    }

    private fun allListener() {
        binding.btnAdd.setOnClickListener(){
            //validationCheck()
            if(binding.btnAdd.text == "Add DATA"){
                Log.d("Testing", "Add DATA")
                userHashMapData.put(binding.tvName.text.toString(), UserHashMapData(binding.tvName.text.toString(), binding.tvMobNo.text.toString().toLong(),
                    binding.tvCity.text.toString()))
                Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
            }else {
                Log.d("Testing", "Update DATA")
                userHashMapData.replace(keyForUpdate, UserHashMapData(binding.tvName.text.toString(), binding.tvMobNo.text.toString().toLong(),
                    binding.tvCity.text.toString()))
                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()
            }

            binding.tvName.setText("")
            binding.tvMobNo.setText("")
            binding.tvCity.setText("")

            binding.btnAdd.visibility = View.INVISIBLE
            binding.btnAdd.text = "Add DATA"

        }
        binding.tvName.addTextChangedListener(object : TextWatcher{
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * are about to be replaced by new text with length `after`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationCheck()
            }

            /**
             * This method is called to notify you that, somewhere within
             * `s`, the text has been changed.
             * It is legitimate to make further changes to `s` from
             * this callback, but be careful not to get yourself into an infinite
             * loop, because any changes you make will cause this method to be
             * called again recursively.
             * (You are not told where the change took place because other
             * afterTextChanged() methods may already have made other changes
             * and invalidated the offsets.  But if you need to know here,
             * you can use [Spannable.setSpan] in [.onTextChanged]
             * to mark your place and then look up from here where the span
             * ended up.
             */
            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.tvMobNo.addTextChangedListener(object : TextWatcher{
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * are about to be replaced by new text with length `after`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationCheck()
            }

            /**
             * This method is called to notify you that, somewhere within
             * `s`, the text has been changed.
             * It is legitimate to make further changes to `s` from
             * this callback, but be careful not to get yourself into an infinite
             * loop, because any changes you make will cause this method to be
             * called again recursively.
             * (You are not told where the change took place because other
             * afterTextChanged() methods may already have made other changes
             * and invalidated the offsets.  But if you need to know here,
             * you can use [Spannable.setSpan] in [.onTextChanged]
             * to mark your place and then look up from here where the span
             * ended up.
             */
            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.tvCity.addTextChangedListener(object : TextWatcher{
            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * are about to be replaced by new text with length `after`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            /**
             * This method is called to notify you that, within `s`,
             * the `count` characters beginning at `start`
             * have just replaced old text that had length `before`.
             * It is an error to attempt to make changes to `s` from
             * this callback.
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationCheck()
            }

            /**
             * This method is called to notify you that, somewhere within
             * `s`, the text has been changed.
             * It is legitimate to make further changes to `s` from
             * this callback, but be careful not to get yourself into an infinite
             * loop, because any changes you make will cause this method to be
             * called again recursively.
             * (You are not told where the change took place because other
             * afterTextChanged() methods may already have made other changes
             * and invalidated the offsets.  But if you need to know here,
             * you can use [Spannable.setSpan] in [.onTextChanged]
             * to mark your place and then look up from here where the span
             * ended up.
             */
            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun validationCheck() {
        if (!binding.tvName.text.toString().trim().isEmpty() && !binding.tvMobNo.text.toString().trim().isEmpty() && binding.tvMobNo.text.toString().trim().length == 10 && !binding.tvCity.text.toString().trim().isEmpty()){
            binding.btnAdd.visibility = View.VISIBLE
        }else{
            binding.btnAdd.visibility = View.INVISIBLE
        }
    }

    override fun onSendData(key: String, model: UserHashMapData) {
        Log.d("MainActivity", "Sending data from adapter. Key: $key, Model: $model")
        var model = model
        keyForUpdate = key
        binding.tvName.setText(model.name)
        binding.tvMobNo.setText(model.mobNo.toString())
        binding.tvCity.setText(model.city)

        binding.btnAdd.setText("UPDATE")
        binding.btnAdd.visibility = View.VISIBLE
        // Now you have the key and model, and you can send it as needed.
        // sendDataToServer(key, model)

    }
}