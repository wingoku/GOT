package com.wingoku.gameofthrones.utils;

import java.util.HashMap;

public class Constants {
    public static final int DATA_REFRESH_THRESHOLD = 60 * 60 * 24 * 30; //30 days in seconds
    public static final int REQUEST_RETRIES = 5;
    public static final int CONNECTION_TIME_OUT = 25;
    public static HashMap<String, String> regionImageUrlMap = new HashMap<>();

    static {
        regionImageUrlMap.put("The North", "https://bit.ly/2Gcq0r4");
        regionImageUrlMap.put("The Vale", "https://bit.ly/34FAvws");
        regionImageUrlMap.put("The Riverlands OR Iron Islands", "https://bit.ly/3kJrIiP");
        regionImageUrlMap.put("The Westerlands", "https://bit.ly/2TAzjnO");
        regionImageUrlMap.put("The Reach", "https://bit.ly/2HSCW5N");
        regionImageUrlMap.put("Dorne", "https://bit.ly/2HOcavT");
        regionImageUrlMap.put("The Stormlands", "https://bit.ly/34F2sEC");
    }
}
