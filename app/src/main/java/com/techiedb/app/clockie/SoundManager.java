package com.techiedb.app.clockie;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.rtp.AudioStream;

import java.util.HashMap;

/**
 * @author Larry Pham
 * @since 27.06.2014
 */
public class SoundManager {
    private Context mContext;
    private static SoundManager mSoundManagerInstance;
    private static final int SOUND_SIDE_MENU= 1;
    private static final int SOUND_LAPTIME= 2;
    private static final int SOUND_RESERT= 3;
    private static final int SOUND_START= 4;
    private static final int SOUND_STOP = 5;
    private static final int SOUND_TICK= 6;
    private static final int SOUND_COUNT_DOWN_ALARM= 7;
    private static boolean mAudioOn = true;
    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;

    public SoundManager(Context context){
        this.mContext = context;
        mSoundPool =  new SoundPool(3, AudioManager.STREAM_MUSIC, 100);
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mSoundPoolMap.put(SOUND_SIDE_MENU, mSoundPool.load(mContext, R.raw.side_menu, 1));
        mSoundPoolMap.put(SOUND_LAPTIME, mSoundPool.load(mContext, R.raw.lap_time, 1));
        mSoundPoolMap.put(SOUND_RESERT, mSoundPool.load(mContext, R.raw.reset_watch, 1));
        mSoundPoolMap.put(SOUND_STOP, mSoundPool.load(mContext,R.raw.stop, 1));
        mSoundPoolMap.put(SOUND_START, mSoundPool.load(mContext, R.raw.start, 1));
        mSoundPoolMap.put(SOUND_TICK, mSoundPool.load(mContext, R.raw.tok_repeatit, 1));
        mSoundPoolMap.put(SOUND_COUNT_DOWN_ALARM, mSoundPool.load(mContext, R.raw.countdown_alarm, 1));
    }

    public static SoundManager getInstance(Context context){
        if (mSoundManagerInstance == null) mSoundManagerInstance = new SoundManager(context);
        return mSoundManagerInstance;
    }
    int mLoopingSoundId = -1;
    public void playSound(int soundId, boolean endlessLoop){
        if (mAudioOn){
            if (endlessLoop) stopEndlessAlarm();
            AudioManager manager =  (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            float streamVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
            int playingSoundId = mSoundPool.play(mSoundPoolMap.get(soundId), streamVolume,
                    streamVolume, 1, endlessLoop ? 35: 0, 1f);
            if (endlessLoop) mLoopingSoundId = playingSoundId;
        }
    }

    public void stopEndlessAlarm(){
        try{
            if (mLoopingSoundId != -1) mSoundPool.stop(mLoopingSoundId);
            mLoopingSoundId = -1;
        }catch (Exception ignored){}
    }

    public void doTick(){
    }
}
