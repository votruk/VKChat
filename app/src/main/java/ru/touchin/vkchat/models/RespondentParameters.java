package ru.touchin.vkchat.models;

import com.google.api.client.util.Key;

import org.zuzuk.utils.serialization.json.ObjectFromJson;

public class RespondentParameters extends ObjectFromJson {

    @Key("log_batch_size")
    private Integer logBatchSize;

    @Key("activity_recognition_frequency")
    private Integer activityRecognitionFrequency;

    @Key("gps_sampling_frequency")
    private Integer gpsSamplingFrequency;

    @Key("motion_index_sampling_frequency")
    private Integer motionIndexSamplingFrequency;

    @Key("log_server_sync_frequency")
    private Integer logServerSyncFrequency;

    @Key("significant_change")
    private Integer significantChange;

    public Integer getLogBatchSize() {
        return logBatchSize;
    }

    public Integer getGpsSamplingFrequency() {
        return gpsSamplingFrequency;
    }

    public Integer getMotionIndexSamplingFrequency() {
        return motionIndexSamplingFrequency;
    }

    public Integer getLogServerSyncFrequency() {
        return logServerSyncFrequency;
    }

    public Integer getSignificantChange() {
        return significantChange;
    }

    public Integer getActivityRecognitionFrequency() {
        return activityRecognitionFrequency;
    }
}
