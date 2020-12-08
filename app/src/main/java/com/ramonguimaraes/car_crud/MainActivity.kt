package com.ramonguimaraes.car_crud

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var database: CarDatabase? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = CarDatabase.getInstance(this)
        textView = findViewById(R.id.text_view)
        UpdateViewTask(database, textView).execute()

    }


    private inner class InsertCarTask(val database: CarDatabase?) : AsyncTask<Void, Void, Void?>() {

        override fun doInBackground(vararg params: Void?): Void? {

            var lastId = database?.carDao()?.findLastCarId()


            if (lastId == null) {
                lastId = 1
            } else {
                lastId++
            }

            val car = Car(null, "Car$lastId")

            database?.carDao()?.insert(car)

            return null

        }
    }

    private inner class DeleteAllCarTask(val database: CarDatabase?) :
        AsyncTask<Void, Void, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {

            database?.carDao()?.deleteAll()

            return null
        }

    }

    private inner class UpdateViewTask(val database: CarDatabase?, val textView: TextView?) :
        AsyncTask<Void, Void, List<Car>?>() {

        override fun doInBackground(vararg params: Void?): List<Car>? {
            return database?.carDao()?.getAllCar()
        }

        override fun onPostExecute(result: List<Car>?) {

            if (result!!.isNotEmpty()) {

                var text = ""

                for (car in result) {
                    text += "Id: ${car.id}, Name: ${car.name}" + "\n"
                }

                textView?.text = text


            } else {
                textView?.text = "Empty"
            }

        }

    }

    fun insertCar(view: View) {
        InsertCarTask(database).execute()
        UpdateViewTask(database, textView).execute()
    }

    fun deleteAll(view: View) {
        DeleteAllCarTask(database).execute()
        UpdateViewTask(database, textView).execute()
    }


}