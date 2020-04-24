/*
 * Created by Hariom.Gupta.Gurugram on 30/01/20.
 * hk.mca08@gmail.com
 * 8510887828
 *
 * Copyright 2014 KC Ochibili
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hari.canvasfingerpainting.activity;

import com.hari.canvasfingerpainting.R;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public interface MyConstants {
    //All Constants
//    int Action_Camera = 200;
//    int Request_Camera = 15;
    int LOCATION_SETTING_REQUEST = 999;
    int REQUEST_FINE_LOCATION = 1234;

    //ALL KEYS
    boolean D = false;

    //All database tables here
//    String AppVersionName = BuildConfig.VERSION_NAME;
    //    int AppVersionCode = BuildConfig.VERSION_CODE;

    //ALL PREF KEYS HERE

    //all varialbles added here
    int REQUEST_TIMEOUT_CONNECT = 30, REQUEST_TIMEOUT_READ = 35, REQUEST_TIMEOUT_WRITE = 30;
    long CACHE_SIZE = 10 * 1024 * 1024;
    int MAX_AGE = 60;
    Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    SimpleDateFormat serverFormat = new SimpleDateFormat(DATE_PATTERN);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
    SimpleDateFormat returnDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    //all services

    //ALL VARIABLES
    String PLUS_NINE_ONE = "+91";
    String TEL_PLUS_NINE_ONE = "tel:" + PLUS_NINE_ONE;
    int OPEN_DOCUMENT_CODE = 807;
    int FRAME_CONTAINER_ID = 808;
    int statusColor = R.color.colorPrimaryDark;
    int themeColor = R.color.colorPrimary;
    String LAT_LNG_ZERO = "0.0";
    String LAT_FAKE = "28.0123456789";
    String LNG_FAKE = "77.0123456789";
    String MESSAGE_OK = "OK";
    String MESSAGE_ERROR = "ERROR";
    //    String MESSAGE_SUCCESS = "SUCCESS";
    String MESSAGE_ALERT = "Alert";

    String validLoginDate1 = "2020-04-21";
    String validLoginDate2 = "2020-04-20";
    String validLoginDate3 = "2020-04-23";
    String validLoginDate4 = "2020-04-24";
    String validLoginDate5 = "2020-04-25";
    String validLoginDate6 = "2020-04-26";
    String validLoginDate7 = "2020-04-27";
    String validLoginDate8 = "2020-04-28";
    String validLoginDate9 = "2020-04-29";
    String validLoginDate10 = "2020-04-22";

    //All Screens added here

    //ALL ARGUMENTS ADDED HERE

    CharSequence[] items = {"Take Photo", "Choose from Gallery"};


}
