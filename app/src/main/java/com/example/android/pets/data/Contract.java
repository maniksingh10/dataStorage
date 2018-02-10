package com.example.android.pets.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by manik on 08-02-2018.
 */

public final class Contract {
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";


    public static class Entry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        public static final String TABLE_NAME = "pets";
        public static final String _ID = BaseColumns._ID;
        public static final String CL_NAME = "name";
        public static final String CL_BREED = "breed";
        public static final String CL_GENDER = "gender";
        public static final String CL_WEIGHT = "weight";

        public static final int GEN_UNKNOWN = 0;
        public static final int GEN_MALE = 1;
        public static final int GEN_FEMALE = 2;
    }
}
