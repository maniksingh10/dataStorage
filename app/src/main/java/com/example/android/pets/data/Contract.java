package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Created by manik on 08-02-2018.
 */

public final class Contract {

    public class Entry implements BaseColumns {

        public static final String TABLE_NAME = "pets";

        public static final String _ID = BaseColumns._ID;
        public static final String CL_NAME = "name";
        public static final String Cl_BREED = "breed";
        public static final String CL_GENDER = "gender";
        public static final String CL_WEIGHT = "weight";

        public static final int GEN_UNKNOWN = 0;
        public static final int GEN_MALE = 1;
        public static final int GEN_FEMALE = 2;
    }
}
