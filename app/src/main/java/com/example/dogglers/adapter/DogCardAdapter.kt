/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Initialize the data using the List found in data/DataSource
    private val dataset = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // Declare and initialize all of the list item UI components
        val dogImage: ImageView = view!!.findViewById(R.id.dog_image)
        val dogName: TextView = view!!.findViewById(R.id.dog_name)
        val dogAge: TextView = view!!.findViewById(R.id.dog_age)
        val dogHobbies: TextView = view!!.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // Get the dog list layout corresponding to the passed in layout value
        val dogListLayout = when (layout) {
            Layout.GRID -> R.layout.grid_list_item
            Layout.VERTICAL, Layout.HORIZONTAL-> R.layout.vertical_horizontal_list_item
            else -> R.layout.vertical_horizontal_list_item
        }

        // Inflate the layout
        val dogAdapterLayout = LayoutInflater.from(parent.context).inflate(dogListLayout, parent, false)

        return DogCardViewHolder(dogAdapterLayout)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // Get the data at the current position
        val dogItem = dataset[position]
        // Set the image resource for the current dog
        holder.dogImage.setImageResource(dogItem.imageResourceId)
        // Set the text for the current dog's name
        holder.dogName.text = dogItem.name

        val resources = context?.resources
        // Set the text for the current dog's age
        holder.dogAge.text = resources?.getString(R.string.dog_age, dogItem.age)
        // Set the text for the current dog's hobbies
        holder.dogHobbies.text = resources?.getString(R.string.dog_hobbies, dogItem.hobbies)
    }
}
