package com.YinnnH.utils;

import com.YinnnH.pojo.ResultMessage;
import com.alibaba.fastjson.JSON;

public class MessageUtils {
        public static String getMessage(boolean isSystemMessage, String fromName, Object message) {
            ResultMessage result = new ResultMessage();
            result.setSystem(isSystemMessage);
            result.setMessage(message);
            if (fromName != null) {
                result.setFromName(fromName);
            }
            return JSON.toJSONString(result);
        }
}
