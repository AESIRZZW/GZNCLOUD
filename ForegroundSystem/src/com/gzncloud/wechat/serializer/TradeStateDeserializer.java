package com.gzncloud.wechat.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gzncloud.wechat.model.enums.TradeState;

import java.io.IOException;

/**
 * 交易状态反序列化器
 */
public class TradeStateDeserializer extends JsonDeserializer<TradeState> {

    @Override
    public TradeState deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return TradeState.from(jp.getValueAsString());
    }
}
