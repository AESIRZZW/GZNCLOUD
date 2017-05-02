package com.gzncloud.wechat.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gzncloud.wechat.model.enums.TradeType;

import java.io.IOException;

/**
 * 交易类型反序列化器
 */
public class TradeTypeDeserializer extends JsonDeserializer<TradeType> {

    @Override
    public TradeType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return TradeType.from(jp.getValueAsString());
    }
}
