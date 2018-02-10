/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.pets.data.Contract;
import com.example.android.pets.data.Contract.Entry;
import com.example.android.pets.data.DbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new DbHelper(this);
        displayDatabaseInfo();
    }

    private void insert() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contract.Entry.CL_NAME, "Toto");
        cv.put(Contract.Entry.CL_BREED, "Lab");
        cv.put(Contract.Entry.CL_GENDER, Contract.Entry.GEN_MALE);
        cv.put(Contract.Entry.CL_WEIGHT, 7);

        db.insert(Contract.Entry.TABLE_NAME, null, cv);
    }

    private void displayDatabaseInfo() {

        String[] projection = {Entry._ID, Entry.CL_NAME, Entry.CL_BREED, Entry.CL_GENDER, Entry.CL_WEIGHT};

        Cursor curzor = getContentResolver().query(Entry.CONTENT_URI, projection, null, null, null);

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + curzor.getCount() + " pets.\n\n");
            displayView.append(Entry._ID + " - " +
                    Entry.CL_NAME + " - " + Entry.CL_BREED + " - " + Entry.CL_GENDER + " - " + Entry.CL_WEIGHT + "\n");

            // Figure out the index of each column
            int idCLIndex = curzor.getColumnIndex(Entry._ID);
            int nameCLIndex = curzor.getColumnIndex(Entry.CL_NAME);
            int breedCLIndex = curzor.getColumnIndex(Entry.CL_BREED);
            int genderCLIndx = curzor.getColumnIndex(Entry.CL_GENDER);
            int weightCLIndex = curzor.getColumnIndex(Entry.CL_WEIGHT);
            // Iterate through all the returned rows in the cursor
            while (curzor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = curzor.getInt(idCLIndex);
                String currentName = curzor.getString(nameCLIndex);
                String currentBreed = curzor.getString(breedCLIndex);
                int currentWeight = curzor.getInt(weightCLIndex);
                int currentGen = curzor.getInt(genderCLIndx);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " + currentBreed + " - " + currentGen + " - " + currentWeight + "kgs"));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            curzor.close();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insert();
                displayDatabaseInfo();
                // Do nothing for now
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

}
