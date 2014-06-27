package com.techiedb.app.clockie.providers;

import android.content.Context;

import com.techiedb.app.clockie.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashSet;

/**
 * Created by Larry on 6/27/2014.
 */
public class DaysOfWeek {
    public static final int DAYS_IN_A_WEEK= 7;
    public static final int ALL_DAYS_SET= 0x7f;
    public static final int NO_DAYS_SET= 0;

    /**
     * Need to have money start at index 0 to be the backward compatible. This converts Calendar.DAY_OF_WEEKS
     * constants to our internal bit structure.
     */
    private static int convertDayToBitIndex(int day){
        return (day + 5) % DAYS_IN_A_WEEK;
    }

    /**
     * Need to have monday start at index 0 to be backwards compatible. This converts our bit structure
     * to Calendar.DAY_OFF_WEEK constant value.
     */
    private static int convertBitIndexToDay(int bitIndex){
        return (bitIndex + 1) % DAYS_IN_A_WEEK + 1;
    }
    // Bitmask of all repeating days
    private int mBitSet;
    public DaysOfWeek(int bitSet){
        mBitSet = bitSet;
    }

    public String toString(Context context, boolean showNever){
        return toString(context, showNever,false);
    }
    public String toAccessibilityString(Context context){
        return toString(context, false, true);
    }

    private String toString(Context context, boolean showNever, boolean forAccessibility){
        StringBuilder ret = new StringBuilder();
        if (mBitSet == NO_DAYS_SET){
            return showNever ? context.getText(R.string.never).toString() : "";
        }

        if (mBitSet == ALL_DAYS_SET){
            return context.getText(R.string.every_day).toString();
        }
        int dayCount = 0;
        int bitSet = mBitSet;
        while (bitSet > 0){
            if ((bitSet & 1) == 1) dayCount++;
            bitSet >>= 1;
        }

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] dayList = (forAccessibility || dayCount <= 1) ? dfs.getWeekdays() : dfs.getShortWeekdays();
        for (int bitIndex = 0; bitIndex < DAYS_IN_A_WEEK; bitIndex++){
            if ((mBitSet & (1 << bitIndex)) != 0){
                ret.append(dayList[convertBitIndexToDay(bitIndex)]);
                dayCount -= 1;
                if (dayCount > 0) ret.append(context.getText(R.string.day_concat));
            }
        }
        return ret.toString();
    }

    public boolean isRepeating(){
        return mBitSet != NO_DAYS_SET;
    }
    public int calculateDaysToNextAlarm(Calendar current){
        if (!isRepeating()){
            return -1;
        }
        int dayCount = 0;
        int currentDayBit = convertBitIndexToDay(current.get(Calendar.DAY_OF_WEEK));
        for (;dayCount <DAYS_IN_A_WEEK; dayCount++){
            int nextAlarmBit =(currentDayBit + dayCount) % DAYS_IN_A_WEEK;
            if (isBitEnabled(nextAlarmBit)){
                break;
            }
        }
        return dayCount;
    }
    private boolean isBitEnabled(int bitIndex){
        return ((mBitSet & (1 << bitIndex)) > 0);
    }

    private void setBit(int bitIndex, boolean set){
        if (set){
            mBitSet |= (1 << bitIndex);
        }else{
            mBitSet &= ~(1 << bitIndex);
        }
    }

    public void setBitSet(int bitSet){
        mBitSet = bitSet;
    }

    public int getBitSet(){
        return mBitSet;
    }

    public void setDaysOfWeek(boolean value, int ... daysOfWeek){
        for (int day : daysOfWeek){
            setBit(convertDayToBitIndex(day), value);
        }
    }
    public HashSet<Integer> getSetDays(){
        final HashSet<Integer> result = new HashSet<Integer>();
        for (int bitIndex = 0; bitIndex < DAYS_IN_A_WEEK; bitIndex ++){
            if (isBitEnabled(bitIndex)){
                result.add(convertBitIndexToDay(bitIndex));
            }
        }
        return  result;
    }
    public void clearAllDays(){
        mBitSet = NO_DAYS_SET;
    }
    @Override
    public String toString() {
        return "DaysOfWeek{" + "mBitSet=" + mBitSet + '}';
    }
}
