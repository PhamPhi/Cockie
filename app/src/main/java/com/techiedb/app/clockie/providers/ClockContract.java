package com.techiedb.app.clockie.providers;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author Larry  Pham
 *
 */
public final class ClockContract {

    public static final String AUTHORITY = "com.techiedb.app.clockie";
    private ClockContract(){

    }

    private interface AlarmSettingsColumns extends BaseColumns{
        public static final Uri NO_RINGTONE_URI = Uri.EMPTY;
        public static final String NO_RINGTONE = NO_RINGTONE_URI.toString();
        public static final String VIBRATE = "vibrate";
        public static final String LABEL = "label";
        public static final String RINGTONE= "ringtone";
    }

    protected interface AlarmsColumns extends AlarmSettingsColumns, BaseColumns{
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/alarms");
        public static final String HOURS= "hours";
        public static final String MINUTES= "minutes";
        public static final String DAYS_OF_WEEK= "daysofweek";
        public static final String ENABLED= "enabled";
        public static final String DELETE_AFTER_USE= "delteafteruse";
    }

    protected interface InstanceColumns extends AlarmSettingsColumns, BaseColumns{
        public static final Uri CONTENT_URI= Uri.parse("content://" + AUTHORITY + "/instances");
        public static final int POWER_OFF_ALARM_STATE= -1;

        public static final int SILENT_STATE= 0;
        public static final int LOW_NOTIFICATION_STATE= 1;
        public static final int HIDE_NOTIFICATION_STATE= 2;
        public static final int HIGH_NOTIFICATION_STATE= 3;
        public static final int SNOOZE_STATE= 4;
        public static final int FIRED_STATE= 5;
        public static final int MISSED_STATE= 6;
        public static final int DISMISSED_STATE= 7;

        public static final String YEAR= "year";
        public static final String MONTH = "month";
        public static final String DAY= "day";
        public static final String HOUR= "hour";
        public static final String MINUTE="minute";

        public static final String ALARM_ID="alarm_id";
        public static final String ALARM_STATE="alarm_state";
    }

    protected interface CitiesColumns{
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/cities");
        public static final String CITY_ID="city_id";
        public static final String CITY_NAME="city_name";
        public static final String TIMEZONE_NAME="timezone_name";
        public static final String TIMEZONE_OFFSET="timezone_offset";
    }
}
