package com.techiedb.app.clockie.providers;

import android.content.ContentValues;

/**
 * Created by Larry on 6/27/2014.
 */
public class City implements ClockContract.CitiesColumns {
    private static final String[] QUERY_COLUMNS = {
            CITY_ID, CITY_NAME, TIMEZONE_NAME, TIMEZONE_OFFSET
    };
    private static final int CITY_ID_INDEX= 0;
    private static final int CITY_NAME_INDEX= 1;
    private static final int TIMEZONE_NAME_INDEX= 2;
    private static final int TIMEZONE_OFFSET_INDEX= 3;
    private static final int COLUMN_COUNT = TIMEZONE_OFFSET_INDEX + 1;
}
